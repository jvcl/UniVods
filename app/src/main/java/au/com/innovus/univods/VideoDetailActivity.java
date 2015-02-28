package au.com.innovus.univods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.MenuItem;
import android.widget.VideoView;


public class VideoDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://video.flinders.edu.au/videodl2013/120244_adsl.mp4");
        videoView.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
