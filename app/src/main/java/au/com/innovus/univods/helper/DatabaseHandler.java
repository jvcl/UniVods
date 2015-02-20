package au.com.innovus.univods.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.innovus.univods.Major;
import au.com.innovus.univods.R;

/**
 * Created by jorge on 20/02/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHandler";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "uni.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES_MAJORS =
            "CREATE TABLE " + UniContract.MajorEntry.TABLE_NAME + " (" +
                    UniContract.MajorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UniContract.MajorEntry.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                    UniContract.MajorEntry.COLUMN_NAME_NAME + TEXT_TYPE  +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UniContract.MajorEntry.TABLE_NAME;

    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES_MAJORS);

        InputStreamReader reader = new InputStreamReader(context.getResources().openRawResource(R.raw.codes));
        BufferedReader br = new BufferedReader(reader);

        Gson gson = new Gson();
        Major[] topics = gson.fromJson(br, Major[].class);

        for (Major topic : topics){
            addMajor(topic, db);

        }
    }

    public long addMajor(Major major, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UniContract.MajorEntry.COLUMN_NAME_CODE, major.getCode());
        contentValues.put(UniContract.MajorEntry.COLUMN_NAME_NAME, major.getName());

        long newRowId;
        newRowId = db.insert(
                UniContract.MajorEntry.TABLE_NAME,
                null,
                contentValues);

        return newRowId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    // Getting All Majors
    public List<Major> getAllMajors() {
        List<Major> contactList = new ArrayList<Major>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + UniContract.MajorEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Major major = new Major();
                major.setId(Integer.parseInt(cursor.getString(0)));
                major.setCode(cursor.getString(1));
                major.setName(cursor.getString(2));
                // Adding contact to list
                contactList.add(major);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }
}
