package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class ThiefEndingActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thief_ending);
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

        //月光
        Drawable moon_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.moon);

        KWFullScreenEventModel event1 = new KWFullScreenEventModel("之後就沒有見到主角出現在毛利偵探事務所,\n\n 似乎是跑去當怪盜基德的徒弟了。")
                .setBackgroundDrawable(moon_background);

        eventManager.addEvent(event1);
        eventManager.play("怪盜基德結局");
    }
}