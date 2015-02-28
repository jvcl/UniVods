package au.com.innovus.univods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.Xml;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import au.com.innovus.univods.helper.VideoItem;
import au.com.innovus.univods.helper.VideoXmlParser;


/**
 * An activity representing a list of Videos. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link VideoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link VideoListFragment} and the item details
 * (if present) is a {@link VideoDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link VideoListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class VideoListActivity extends FragmentActivity
        implements VideoListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private static final String TAG = "VideoListActivity";
    List<VideoItem> videoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video_list);

        Intent intent = getIntent();
        String xmlString =  intent.getExtras().getString("xml");
        Topic topic = intent.getExtras().getParcelable("topic");

        VideoXmlParser parser = new VideoXmlParser();
        try {
            videoItems = parser.parse(xmlString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        for (VideoItem v : videoItems){
            Log.d(TAG, v.toString());
        }

        Log.d(TAG, topic.toString());



        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link VideoListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int idd) {

        Intent intent = new Intent(this, VideoDetailActivity.class);
        startActivity(intent);

    }

    @Override
    public String[] getVideoItems() {

        String[] entries = new String[videoItems.size()];
        Log.d(TAG, "videoitems size "+ videoItems.size());

        for (int i = 0; i < entries.length; i++){
            entries[i] = videoItems.get(i).getTitle();
        }
        return entries;
    }
}
