package tw.edu.scu.avgexample.sample;

import android.os.Bundle;
import android.view.View;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class SampleEndScene_2_Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_end_scene_2);
    }

    @Override
    protected void initializeEvent () {
        super.initializeEvent();
        initEvents();
    }

    private void initEvents() {
        SampleEndScene_Utils.end_2(this, this.eventManager);
    }

    @Override
    protected void didFinishAllEvent (String eventIdentifier){
        super.didFinishAllEvent(eventIdentifier);
        KWLog.d("[資訊] 事件合集: " + eventIdentifier + " 中的所有事件已全數播放完畢");

        if ( "結局場景2".equals(eventIdentifier)) {
            showEndHint();
        }
    }

    private void showEndHint() {
        findViewById(R.id.endHintTextView).setVisibility(View.VISIBLE);

        findViewById(R.id.fullScreenButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSceneActivity(SampleMenuMainActivity.class, R.anim.kw_scene_animation_zoom_in, R.anim.kw_scene_animation_fade_out);
            }
        });

    }

}
