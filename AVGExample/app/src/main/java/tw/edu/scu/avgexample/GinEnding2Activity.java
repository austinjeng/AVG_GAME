package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class GinEnding2Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gin_ending2);
    }

    @Override
    protected void initializeEvent () {
        super.initializeEvent();

        Button replayButton = findViewById(R.id.buttonReplay);

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSceneActivity(MyScene2Activity.class, 200);
            }
        });

        eventManager.stop();

        ConanConstants.ending_finished[3] = true;

        //事務所
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        KWFullScreenEventModel event1 = new KWFullScreenEventModel("兩人順利離開了黑暗組織的秘密交易現場,\n\n新一也開始著手調查黑暗組織的行為與目的,\n\n同時也繼續以高中生偵探工藤新一的身分活躍" +
                "於各大案發現場。\n\n而主角也持續往成為偵探的路上前進!")
                .setBackgroundDrawable(firm_background);

        eventManager.addEvent(event1);

        eventManager.play("琴酒結局2");
    }
}