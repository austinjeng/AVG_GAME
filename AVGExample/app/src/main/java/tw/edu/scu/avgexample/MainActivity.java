package tw.edu.scu.avgexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import tw.edu.scu.avgexample.sample.SampleSplashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();
    }

    private void initButton() {

        Button exampleButton = findViewById(R.id.exampleButton);
        Button myGameButton = findViewById(R.id.myGameButton);

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SampleSplashActivity.class);
                startActivity(intent);
            }
        });

        myGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyScene2Activity.class);
                startActivity(intent);
            }
        });

    }

}
