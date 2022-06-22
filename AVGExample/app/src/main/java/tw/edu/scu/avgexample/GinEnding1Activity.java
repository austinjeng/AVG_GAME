package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class GinEnding1Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gin_ending1);
    }

    @Override
    protected void initializeEvent () {
        super.initializeEvent();

        Button replayButton = findViewById(R.id.buttonReplay);
        TextView endingFinishedTextView = findViewById(R.id.textViewEndingFinished);

        //計數器
        ConanConstants.ending_finished[2] = true;

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchSceneActivity(MyScene2Activity.class, 200);
            }
        });

        endingFinishedTextView.setText("已完成結局: " +ConanConstants.showFinishedEndings()  + "/5");

        eventManager.stop();

        ConanConstants.ending_finished[2] = true;

        //月光
        Drawable smaller_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.smaller);

        KWFullScreenEventModel event1 = new KWFullScreenEventModel("因偷看黑暗組織的秘密交易,\n\n兩人遭打暈後, \n\n被琴酒餵了組織研發的藥物\"APTX4896\"而變成小孩。\n\n但新一也因此化名為" +
                "\"江戶川柯南\", \n\n繼續在暗中調查著黑暗組織。")
                .setBackgroundDrawable(smaller_background);

        eventManager.addEvent(event1);

        eventManager.play("琴酒結局1");
    }
}