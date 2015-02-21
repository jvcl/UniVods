package au.com.innovus.univods;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import au.com.innovus.univods.helper.DatabaseHandler;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final String TAG  = "MAIN ACTIVITY";
    ArrayList<Topic> selectedTopics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set onclick listener to add button
        findViewById(R.id.add_topic_button).setOnClickListener(this);

        ListView listView = (ListView) findViewById(R.id.listViewCurrentTopics);
        SimpleCursorAdapter mAdapter;

        DatabaseHandler db = new DatabaseHandler(this);
        selectedTopics = (ArrayList<Topic>) db.getAllSelectedTopics();
        db.closeDB();

        Log.d(TAG, "size" + selectedTopics.size());

        String[] selected = new String[selectedTopics.size()];

        for (int i = 0; i < selected.length; i++){
            Topic t = selectedTopics.get(i);
            Log.d(TAG, t.toString());
            selected[i] = new String(t.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                selected);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "ITEM CLICKED" + position);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = selectedTopics.get(position).getURL();
        Log.d(TAG, "URL" + url);

// Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {

                        String r = (String) response;
                        Log.d(TAG, "ITEM CLICKED" + r);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"No Videos found", Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
