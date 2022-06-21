package tw.edu.scu.avgexample.sample;

import android.os.Handler;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseApplication;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;

import static tw.edu.scu.avgexample.framework.library.KWSimpleGestureListener.KW_GESTURE_SINGLE_TAP_UP;

public class SampleCopyrightActivity extends KWBaseSceneActivity {

    private Handler handler;
    private Runnable runnable;

    private boolean lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_copyright);

        this.runnable = new Runnable() {
            @Override
            public void run() {
                switchSceneActivity(SampleMenuMainActivity.class);
            }
        };

        this.handler = new Handler();
        this.handler.postDelayed(this.runnable,6000);

        LinearLayout copyrightLinearLayout = findViewById(R.id.copyrightLinearLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        copyrightLinearLayout.addView(((KWBaseApplication)getApplication()).adView, params);

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
            switchSceneActivity(SampleMenuMainActivity.class);
        }
    }
}
