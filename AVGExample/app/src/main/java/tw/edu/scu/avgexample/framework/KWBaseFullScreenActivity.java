package tw.edu.scu.avgexample.framework;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import tw.edu.scu.avgexample.framework.library.KWSimpleGestureListener;

import static tw.edu.scu.avgexample.framework.library.KWSimpleGestureListener.KW_GESTURE_SWIPE_DOWN;

public class KWBaseFullScreenActivity extends KWBaseActivity {

    GestureDetector gestureDetector;
    KWSimpleGestureListener gestureListener;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //if (hasFocus) {
            hideSystemUI();
        //}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideSystemUI();

        gestureListener = new KWSimpleGestureListener();
        gestureListener.setOnGestureListener(new KWSimpleGestureListener.OnGestureListener() {
            @Override
            public void onDetectedGesture(int type) {
                KWBaseFullScreenActivity.this.onDetectedGesture(type);
            }
        });

        gestureDetector = new GestureDetector(this, gestureListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    protected void onDetectedGesture(int type) {
        if (type == KW_GESTURE_SWIPE_DOWN) {
            hideSystemUI();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

}
