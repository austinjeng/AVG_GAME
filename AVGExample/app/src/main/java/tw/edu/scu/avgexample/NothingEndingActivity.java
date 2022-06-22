package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class NothingEndingActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nothing_ending);
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

        ConanConstants.ending_finished[4] = true;

        //事務所
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        KWFullScreenEventModel event1 = new KWFullScreenEventModel("這一起荒謬的案件就這樣落幕,\n\n毛利偵探事務所一如往常的歡樂,\n\n新一也依舊活躍在各個案件,\n\n" +
                "主角則一直在事務所和老闆毛利小五郎一起努力,\n\n往成為名偵探的路上前進著。")
                .setBackgroundDrawable(firm_background);

        eventManager.addEvent(event1);

        eventManager.play("都不是結局");
    }

}