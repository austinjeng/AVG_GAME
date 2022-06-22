package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;

public class LeaveGameActivity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_leave_game);

        Button leaveGameButton = findViewById(R.id.buttonLeaveGame);

        leaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConanConstants.clearEndingFinished();
                leaveGame();
            }
        });

    }

    protected void leaveGame()
    {
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        KWSoundManager.sharedInstance(this).stopBgm();
        switchSceneActivity(MainMenuActivity.class, 100);
    }
}