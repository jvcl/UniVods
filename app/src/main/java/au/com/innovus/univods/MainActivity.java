package au.com.innovus.univods;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import au.com.innovus.univods.helper.DatabaseHandler;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set onclick listener to add button
        findViewById(R.id.add_topic_button).setOnClickListener(this);

        DatabaseHandler db = new DatabaseHandler(this);

        ArrayList<Topic> topics = (ArrayList) db.getAllTopics();

        Topic topic = db.getTopic("SPAN340244");

        if (topic == null){
            Log.d("Main", "topic is null");
        }else{
            Log.d("Main", "topic is not null "+ topic.toString());
        }

        db.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (v.getId() == R.id.add_topic_button){

            startActivity(new Intent(this, AddTopicActivity.class));

        }
    }
}
