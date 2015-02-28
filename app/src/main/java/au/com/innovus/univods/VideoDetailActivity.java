package au.com.innovus.univods;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoDetailActivity extends Activity {

    VideoView videoView;
    int position;
    private final String TAG = "VideoDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        MediaController mediaController = new MediaController(this);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("http://video.flinders.edu.au/videodl2013/120244_adsl.mp4");
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        if (savedInstanceState != null){
            position = savedInstanceState.getInt("pos", 0);
            Log.d(TAG, position+" restored");
            videoView.seekTo(position);
        }
        videoView.start()
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
        Log.d(TAG, position+" saved");
        super.onSaveInstanceState(outState);
    }
}
