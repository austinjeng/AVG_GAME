package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class BlackmanEndingActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackman_ending);


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

        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");

        ConanConstants.ending_finished[0] = true;

        Drawable rumbling_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.rumbling);

        KWCharacterModel eren_character = new KWCharacterModel(this,"eren", "黑衣人(艾連葉卡)");

        KWFullScreenEventModel event1 = new KWFullScreenEventModel("地鳴已經發動\n\n沒人阻止的了艾連.....")
                .setBackgroundDrawable(rumbling_background);
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(maori_character)
                .setCharacterImageVisibility(false);

        eventManager.addEvent(event1);
        eventManager.addEvent(event2);

        eventManager.play("黑衣人結局");

    }
}