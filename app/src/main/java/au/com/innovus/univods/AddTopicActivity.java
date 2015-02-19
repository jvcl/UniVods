package au.com.innovus.univods;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AddTopicActivity extends ActionBarActivity implements View.OnClickListener {

    private String TAG = "UniVods-AddTopicActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);

        findViewById(R.id.search_by_code).setOnClickListener(this);
        findViewById(R.id.add_by_code).setOnClickListener(this);
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

            String text = ((EditText) findViewById(R.id.editTextAddCode)).getText().toString();

            if (!isTopicCodeValid(text)){
                Toast.makeText(this, "Invalid Topic, Try Again", Toast.LENGTH_SHORT).show();
                return;
            }
            ((TextView) findViewById(R.id.textViewTopicName)).setText(text);

            findViewById(R.id.add_by_code).setEnabled(true);
            Log.d(TAG, "search by code "+ text);

            Bundle bundle = new Bundle();

        }
        if (v.getId() == R.id.add_by_code){
            Log.d(TAG, "add By code");
        }
    }

    private boolean isTopicCodeValid(String code){

        if (code.isEmpty() || code.length() > 9){
            return false;
        }
        return true;
    }
}
