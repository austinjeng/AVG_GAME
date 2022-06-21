package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.utility.KWScreenUtils;

public class SampleScene_6_Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定好對應的Layout檔案
        setContentView(R.layout.activity_sample_scene_6);

        initializeBmiFeature();
    }

    //覆寫上層的事件初始化Method
    //建立這個場景預設的事件合集吧！
    @Override
    protected void initializeEvent () {
        super.initializeEvent();
        SampleScene_6_Utils.scene_6_1(this, this.eventManager);
    }

    //當有任意事件合集完成之後，根據完成的事件合集識別碼來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void didFinishAllEvent (String eventIdentifier){
        super.didFinishAllEvent(eventIdentifier);

        if ( "場景6-2".equals(eventIdentifier)) {
            switchSceneActivity(SampleScene_7_Activity.class, R.anim.kw_scene_animation_scale_in_up, R.anim.kw_scene_animation_scale_out_down);
        }
    }


    private void initializeBmiFeature() {
        final EditText heightEditText = findViewById(R.id.heightEditText);
        final EditText weightEditText = findViewById(R.id.weightEditText);

        final Button bmiButton = findViewById(R.id.bmiButton);

        ///將按鈕繪製成圓角樣式
        bmiButton.post(new Runnable() {
            @Override
            public void run() {
                int buttonHeight = bmiButton.getHeight();
                GradientDrawable gradientDrawable =  new GradientDrawable();
                gradientDrawable.setCornerRadius(KWScreenUtils.dpToPx(SampleScene_6_Activity.this, buttonHeight/ 2));
                gradientDrawable.setColor(Color.parseColor("#ff5100"));
                bmiButton.setBackground(gradientDrawable);
            }
        });

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (valueIsEmpty(heightEditText.getText(), weightEditText.getText())) {
                    return;
                }

                String heightString = heightEditText.getText().toString();
                String weightString = weightEditText.getText().toString();

                double height = Double.parseDouble(heightString);
                double weight = Double.parseDouble(weightString);


                if (!valueIsValid(height, weight)) {
                    return;
                }
                double bmi = calculateBmiValue(height, weight);

                updateBmiResult(bmi);
                hideKeyboard();

                completeBmi(bmi);
            }
        });
    }

    private double calculateBmiValue(double height, double weight) {
        double heightMeter = height / 100;
        double bmi = weight / (heightMeter * heightMeter);

        bmi = bmi * 100;
        bmi = Math.round(bmi);
        bmi = bmi / 100;

        return bmi;
    }

    private void updateBmiResult(double bmi) {

        TextView resultTextView = findViewById(R.id.resultTextView);
        ImageView bmiImageView = findViewById(R.id.bmiImageView);

        if (bmi < 18.5) {
            resultTextView.setText("BMI值為：" + bmi + "(過輕)");
            bmiImageView.setImageDrawable(getResources().getDrawable(R.drawable.kw_bmi_level_01));
        }
        else if (18.5 <= bmi && bmi < 25) {
            resultTextView.setText("BMI值為：" + bmi + "（正常）");
            bmiImageView.setImageDrawable(getResources().getDrawable(R.drawable.kw_bmi_level_02));
        }
        else if (25 <= bmi && bmi < 30) {
            resultTextView.setText("BMI值為：" + bmi + "（有點胖）");
            bmiImageView.setImageDrawable(getResources().getDrawable(R.drawable.kw_bmi_level_03));
        }
        else if (30 <= bmi && bmi < 34) {
            resultTextView.setText("BMI值為：" + bmi + "（肥胖）");
            bmiImageView.setImageDrawable(getResources().getDrawable(R.drawable.kw_bmi_level_04));
        }
        else {
            resultTextView.setText("BMI值為：" + bmi + "（太胖了啦）");
            bmiImageView.setImageDrawable(getResources().getDrawable(R.drawable.kw_bmi_level_05));
        }

        resultTextView.setVisibility(View.VISIBLE);
        bmiImageView.setVisibility(View.VISIBLE);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private boolean valueIsEmpty(Editable heightEditable, Editable weightEditable) {

        //這邊的type建議另外取變數命名各自的類別，比較好區別各自的編號對應的type的意義
        //但現在就先以比較簡單的方式來進行

        int type = -1;

        if (heightEditable == null || heightEditable.toString().length() == 0) {
            type = 4;
        }
        else if (weightEditable == null || weightEditable.toString().length() == 0) {
            type = 5;
        }

        if (type == -1) {
            return false;
        }
        else {
            SampleScene_6_Utils.scene_6_1_0(this, this.eventManager, type);
            return true;
        }

    }

    private boolean valueIsValid(double height, double weight) {

        //這邊的type建議另外取變數命名各自的類別，比較好區別各自的編號對應的type的意義
        //但現在就先以比較簡單的方式來進行

        int type = -1;

        if (height < 54.6) {
            type = 0;
        }
        else if (height > 247) {
            type = 1;
        }
        else if (weight <= 10) {
            type = 2;
        }
        else if (weight > 635) {
            type = 3;
        }

        if (type == -1) {
            return true;
        }
        else {
            SampleScene_6_Utils.scene_6_1_0(this, this.eventManager, type);
            return false;
        }

    }

    private void completeBmi(double bmi) {
        SampleGlobalData.bmiValue = bmi;
        SampleScene_6_Utils.scene_6_2(this, this.eventManager);
    }
}
