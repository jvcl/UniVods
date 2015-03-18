package au.com.innovus.univods;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import au.com.innovus.univods.helper.DatabaseHandler;


public class RemoveTopicActivity extends Activity implements View.OnClickListener {

    private LinearLayout layout;
    private HashMap<CheckBox, Topic> mapSelected = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_topic);

        findViewById(R.id.button_remove).setOnClickListener(this);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Topic> selected = (ArrayList<Topic>) db.getAllSelectedTopics();
        layout = (LinearLayout) findViewById(R.id.linearLayoutRemoveTopic);

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
        getMenuInflater().inflate(R.menu.menu_remove_topic, menu);
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
}
