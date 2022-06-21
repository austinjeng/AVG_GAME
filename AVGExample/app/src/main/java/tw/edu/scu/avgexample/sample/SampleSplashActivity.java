package tw.edu.scu.avgexample.sample;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;

import static tw.edu.scu.avgexample.framework.library.KWSimpleGestureListener.KW_GESTURE_SINGLE_TAP_UP;

public class SampleSplashActivity extends KWBaseSceneActivity {

    private Handler handler;
    private Runnable runnable;

    private boolean lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_splash);

        this.runnable = new Runnable() {
            @Override
            public void run() {
                switchSceneActivity(SampleCopyrightActivity.class);
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable,3000);
    }

    @Override
    protected void onDetectedGesture(int type) {
        super.onDetectedGesture(type);

        if (type == KW_GESTURE_SINGLE_TAP_UP) {
            if (this.lock) {
                return;
            }

            this.lock = true;
            this.handler.removeCallbacks(runnable);
            switchSceneActivity(SampleCopyrightActivity.class);
        }
    }
}
