package tw.edu.scu.avgexample.sample;

import android.os.Bundle;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.utility.KWLog;


public class SampleScene_1_Activity extends KWBaseSceneActivity {

    private boolean[] scene_1_2_optionStauts = new boolean[4];

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //設定好對應的Layout檔案
        setContentView(R.layout.activity_sample_scene_1);
    }

    //覆寫上層的事件初始化Method
    //建立這個場景預設的事件合集吧！
    @Override
    protected void initializeEvent () {
        super.initializeEvent();
        SampleScene_1_Utils.scene_1_1(this, this.eventManager);
    }

    //當有任意事件合集完成之後，根據完成的事件合集識別碼來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void didFinishAllEvent (String eventIdentifier){
        super.didFinishAllEvent(eventIdentifier);

        if ( "場景1-1".equals(eventIdentifier) ||
             "場景1-2-x".equals(eventIdentifier)) {
            SampleScene_1_Utils.scene_1_2(this, this.eventManager);
        }
        else if ( "場景1-2-1".equals(eventIdentifier) ||
                  "場景1-2-2".equals(eventIdentifier) ||
                  "場景1-2-3".equals(eventIdentifier) ||
                  "場景1-2-4".equals(eventIdentifier)) {
            if (isScene_1_2_optionStautsFinished() == true) {
                //全部都完成後 跳轉到下一個activity
                switchSceneActivity(SampleScene_2_Activity.class);
            }
            else {
                //如果還有沒訪問過的場景
                SampleScene_1_Utils.scene_1_2(this, this.eventManager);
            }
        }
    }

    //當有任意事件中的選項被觸發時，根據選項事件的識別碼，以及選擇到的索引，來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void onOptionSelected (String identifier, int index){
        super.onOptionSelected(identifier, index);

        if ("場景1-2選項".equals(identifier)) {

            KWSoundManager.sharedInstance(this).playSound(R.raw.kw_sound_button_click);

            //如果已經被訪問過 觸發已訪問過對話
            if (this.scene_1_2_optionStauts[index] == true) {

                SampleScene_1_Utils.scene_1_2_0(this, this.eventManager, index);

            }
            //如果沒有訪問過 將optionStatus設為真 並觸發劇情
            else {
                this.scene_1_2_optionStauts[index] = true;

                switch (index) {
                    case 0:
                        SampleScene_1_Utils.scene_1_2_1(this, this.eventManager);
                        break;
                    case 1:
                        SampleScene_1_Utils.scene_1_2_2(this, this.eventManager);
                        break;
                    case 2:
                        SampleScene_1_Utils.scene_1_2_3(this, this.eventManager);
                        break;
                    case 3:
                        SampleScene_1_Utils.scene_1_2_4(this, this.eventManager);
                        break;
                }
            }
        } else {
            KWLog.d("無法識別的選項代號");
        }
    }

    //回傳是否已經訪問過所有地方
    private boolean isScene_1_2_optionStautsFinished() {

        for (int i = 0; i < scene_1_2_optionStauts.length; i++) {
            if (scene_1_2_optionStauts[i] == false) {
                return false;
            }
        }

        return true;
    }

}
