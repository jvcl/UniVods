package au.com.innovus.univods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoDetailActivity extends Activity {

    VideoView videoView;
    int position;
    String url;
    private final String TAG = "VideoDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle != null){
            url = bundle.getString("url");
        }

        setContentView(R.layout.activity_video_detail);
        MediaController mediaController = new MediaController(this);
        videoView = (VideoView) findViewById(R.id.videoView);

        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


        if (savedInstanceState != null){
            position = savedInstanceState.getInt("pos", 0);
            url = savedInstanceState.getString("url");
            Log.d(TAG, position+" restored");
            videoView.setVideoPath(url);
            videoView.seekTo(position);
        }
        videoView.setVideoPath(url);
        videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView != null){
            videoView.pause();
            position = videoView.getCurrentPosition();
            Log.d(TAG, position+"");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (videoView != null){
            videoView.seekTo(position);
            videoView.start();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("pos", position);
        outState.putString("url", url);
        Log.d(TAG, position+" saved");
        super.onSaveInstanceState(outState);
    }
}
