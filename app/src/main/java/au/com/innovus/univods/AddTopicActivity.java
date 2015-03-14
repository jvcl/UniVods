package au.com.innovus.univods;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

import au.com.innovus.univods.helper.DatabaseHandler;


public class AddTopicActivity extends Activity implements View.OnClickListener {

    private String TAG = "UniVods-AddTopicActivity";
    private Topic topic;
    private LinearLayout layout;
    private HashMap<CheckBox, Topic> mapSelected = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);


        findViewById(R.id.search_by_code).setOnClickListener(this);
        findViewById(R.id.add_by_code).setOnClickListener(this);
        findViewById(R.id.button_remove).setOnClickListener(this);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Topic> selected = (ArrayList<Topic>) db.getAllSelectedTopics();
        layout = (LinearLayout) findViewById(R.id.linearLayoutAddTopic);

        for (Topic topic : selected){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(topic.getCode() + " " +topic.getName());
            layout.addView(checkBox);
            mapSelected.put(checkBox, topic);
        }
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_topic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.search_by_code){

            String text = ((EditText) findViewById(R.id.editTextAddCode)).getText().toString().toUpperCase();

            if (!isTopicCodeValid(text)){
                Toast.makeText(this, "Invalid Topic, Try Again", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseHandler db = new DatabaseHandler(this);
            topic = db.getTopic(text);
            db.closeDB();

            if (topic != null){
                ((TextView) findViewById(R.id.textViewTopicName)).setText(topic.getName());
                findViewById(R.id.add_by_code).setEnabled(true);
                Log.d(TAG, "search by code "+ text);
            }
        }
        if (v.getId() == R.id.add_by_code){
            DatabaseHandler db = new DatabaseHandler(this);
            if (topic != null){
                db.setSelected(topic, 1);
            }

            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(topic.getCode() + " " +topic.getName());
            layout.addView(checkBox);

            db.closeDB();

            Log.d(TAG, "add By code");
        }
        if (v.getId() == R.id.button_remove) {

            DatabaseHandler db = new DatabaseHandler(this);
            for (CheckBox checkBox : mapSelected.keySet()){
                if (checkBox.isChecked()){
                    Topic topic1 = mapSelected.get(checkBox);
                    db.setSelected(topic1, 0);
                    layout.removeView(checkBox);
                }
            }
            db.closeDB();

            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isTopicCodeValid(String code){

        if (code.isEmpty() || code.length() > 9){
            return false;
        }
        return true;
    }
}