package tw.edu.scu.avgexample.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseApplication;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;

public class SampleMenuSwitchSceneActivity extends KWBaseSceneActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_menu_switch_scene);

        initLayout();
    }

    private void initLayout() {

        Button switchAdButton = findViewById(R.id.switchAdButton);
        switchAdButton.setOnClickListener(this);

        Button switchScene1Button = findViewById(R.id.switchScene1Button);
        switchScene1Button.setOnClickListener(this);

        Button switchScene2Button = findViewById(R.id.switchScene2Button);
        switchScene2Button.setOnClickListener(this);

        Button switchScene3Button = findViewById(R.id.switchScene3Button);
        switchScene3Button.setOnClickListener(this);

        Button switchScene4Button = findViewById(R.id.switchScene4Button);
        switchScene4Button.setOnClickListener(this);

        Button switchScene5Button = findViewById(R.id.switchScene5Button);
        switchScene5Button.setOnClickListener(this);

        Button switchScene6Button = findViewById(R.id.switchScene6Button);
        switchScene6Button.setOnClickListener(this);

        Button switchScene7Button = findViewById(R.id.switchScene7Button);
        switchScene7Button.setOnClickListener(this);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Class targetClass = null;

        switch (v.getId()) {
            case R.id.switchAdButton:

                if (((KWBaseApplication)getApplication()).rewardedVideoAd.isLoaded()) {
                    ((KWBaseApplication)getApplication()).rewardedVideoAd.show();
                } else {
                    ((KWBaseApplication)getApplication()).loadRewardedVideoAd();
                }

                break;
            case R.id.switchScene1Button:
                targetClass = SampleScene_1_Activity.class;
                break;
            case R.id.switchScene2Button:
                targetClass = SampleScene_2_Activity.class;
                break;
            case R.id.switchScene3Button:
                targetClass = SampleScene_3_Activity.class;
                break;
            case R.id.switchScene4Button:
                targetClass = SampleScene_4_Activity.class;
                break;
            case R.id.switchScene5Button:
                targetClass = SampleScene_5_Activity.class;
                break;
            case R.id.switchScene6Button:
                targetClass = SampleScene_6_Activity.class;
                break;
            case R.id.switchScene7Button:
                if (SampleGlobalData.bmiValue == 0) {
                    SampleGlobalData.bmiValue = 24;
                }
                targetClass = SampleScene_7_Activity.class;
                break;
            case R.id.backButton:
                switchSceneActivity(SampleMenuMainActivity.class, R.anim.kw_scene_animation_slide_in_left, R.anim.kw_scene_animation_slide_out_right, 0);
                break;
        }

        if ( targetClass != null ) {
            switchSceneActivity(targetClass, R.anim.kw_scene_animation_fade_in, R.anim.kw_scene_animation_zoom_out, 0);
        }
    }


}
