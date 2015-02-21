package au.com.innovus.univods.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.innovus.univods.Major;
import au.com.innovus.univods.R;
import au.com.innovus.univods.Topic;

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

    private static final String SQL_CREATE_ENTRIES_TOPICS =
            "CREATE TABLE " + UniContract.TopicEntry.TABLE_NAME + " (" +
                    UniContract.TopicEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UniContract.TopicEntry.COLUMN_NAME_MAJOR + TEXT_TYPE + COMMA_SEP +
                    UniContract.TopicEntry.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                    UniContract.TopicEntry.COLUMN_NAME_NAME + TEXT_TYPE  + COMMA_SEP +
                    UniContract.TopicEntry.COLUMN_NAME_CHOSEN + " INTEGER NOT NULL" +
                    " )";

    private static final String SQL_DELETE_ENTRIES_MAJOR =
            "DROP TABLE IF EXISTS " + UniContract.MajorEntry.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_TOPIC =
            "DROP TABLE IF EXISTS " + UniContract.TopicEntry.TABLE_NAME;

    private Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES_MAJORS);
        db.execSQL(SQL_CREATE_ENTRIES_TOPICS);
        addMajorstoDB(db);
        addTopicstoDB(db);

    }

    private void addMajorstoDB(SQLiteDatabase db){
        InputStreamReader reader = new InputStreamReader(context.getResources().openRawResource(R.raw.codes));
        BufferedReader br = new BufferedReader(reader);

        Gson gson = new Gson();
        Major[] topics = gson.fromJson(br, Major[].class);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Major topic : topics){
            addMajor(topic, db);
        }
    }

    private void addTopicstoDB(SQLiteDatabase db){
        InputStreamReader reader = new InputStreamReader(context.getResources().openRawResource(R.raw.topics));
        BufferedReader br = new BufferedReader(reader);

        Gson gson = new Gson();
        Topic[] topics = gson.fromJson(br, Topic[].class);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Topic topic : topics){
            addTopic(topic, db);
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

    public long addTopic(Topic topic, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_MAJOR, topic.getMajor());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_CODE, topic.getCode());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_NAME, topic.getName());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_CHOSEN, 0);

        long newRowId;
        newRowId = db.insert(
                UniContract.TopicEntry.TABLE_NAME,
                null,
                contentValues);

        return newRowId;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_MAJOR);
        db.execSQL(SQL_DELETE_ENTRIES_TOPIC);

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
    // Getting All Majors
    public List<Topic> getAllTopics() {
        List<Topic> contactList = new ArrayList<Topic>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + UniContract.TopicEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(Integer.parseInt(cursor.getString(0)));
                topic.setMajor(cursor.getString(1));
                topic.setCode(cursor.getString(2));
                topic.setName(cursor.getString(3));
                topic.setSelected(Integer.parseInt(cursor.getString(4)) == 0);
                // Adding contact to list
                contactList.add(topic);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    public List<Topic> getAllSelectedTopics() {
        List<Topic> contactList = new ArrayList<Topic>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + UniContract.TopicEntry.TABLE_NAME + " WHERE "+ UniContract.TopicEntry.COLUMN_NAME_CHOSEN + " == 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(Integer.parseInt(cursor.getString(0)));
                topic.setMajor(cursor.getString(1));
                topic.setCode(cursor.getString(2));
                topic.setName(cursor.getString(3));
                topic.setSelected(Integer.parseInt(cursor.getString(4)) == 1);
                // Adding contact to list
                contactList.add(topic);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    public Topic getTopic(String code){

        Topic topic = null;

        String selectQuery = "SELECT  * FROM " + UniContract.TopicEntry.TABLE_NAME +
                " WHERE " + UniContract.TopicEntry.COLUMN_NAME_CODE + " = \""+ code+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.d(TAG, ""+ cursor.getCount());
        Log.d(TAG, selectQuery);


        if (cursor == null || cursor.getCount() <=0) {
            return topic;
        }
        else{
            cursor.moveToFirst();
            topic = new Topic();
            topic.setId(Integer.parseInt(cursor.getString(0)));
            topic.setMajor(cursor.getString(1));
            topic.setCode(cursor.getString(2));
            topic.setName(cursor.getString(3));
            topic.setSelected(Integer.parseInt(cursor.getString(4)) == 1);
        }
        return topic;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    public void setSelected(Topic topic, int selected){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_MAJOR, topic.getMajor());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_CODE, topic.getCode());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_NAME, topic.getName());
        contentValues.put(UniContract.TopicEntry.COLUMN_NAME_CHOSEN, selected);

        db.update(UniContract.TopicEntry.TABLE_NAME,
                contentValues,
                UniContract.TopicEntry.COLUMN_NAME_CODE + " = ?",
                new String[] {topic.getCode()});
    }
}
