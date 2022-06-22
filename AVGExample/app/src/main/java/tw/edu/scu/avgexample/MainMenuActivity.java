package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import tw.edu.scu.avgexample.framework.library.KWSoundManager;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_main_menu);

        KWSoundManager.sharedInstance(this).playBgm(R.raw.kw_bgm_conan_theme);

        Button startGameButton = findViewById(R.id.buttonStartGame);
        Button quitGameButton = findViewById(R.id.buttonQuit);



        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        quitGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }

    protected void exitGame()
    {
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        KWSoundManager.sharedInstance(this).stopBgm();
        finishAndRemoveTask();
    }

    protected void startGame()
    {
        KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);
        Intent intent = new Intent();
        intent.setClass(MainMenuActivity.this, MyScene1Activity.class);
        startActivity(intent);
    }
}