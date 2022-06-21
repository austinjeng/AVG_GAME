package tw.edu.scu.avgexample.sample;

import android.os.Bundle;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class SampleScene_7_Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定好對應的Layout檔案
        setContentView(R.layout.activity_sample_scene_7);
    }

    //覆寫上層的事件初始化Method
    //建立這個場景預設的事件合集吧！
    @Override
    protected void initializeEvent () {
        super.initializeEvent();
        SampleScene_7_Utils.scene_7_1(this, this.eventManager);
    }

    //當有任意事件合集完成之後，根據完成的事件合集識別碼來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void didFinishAllEvent (String eventIdentifier){
        super.didFinishAllEvent(eventIdentifier);

        if ( "場景7-1".equals(eventIdentifier)) {
            SampleScene_7_Utils.scene_7_2(this, this.eventManager);
        }
    }

    //當有任意事件中的選項被觸發時，根據選項事件的識別碼，以及選擇到的索引，來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void onOptionSelected (String identifier, int index){
        super.onOptionSelected(identifier, index);

        if ("場景7-2選項".equals(identifier)) {

            switch (index) {
                case 0:
                    switchSceneActivity(SampleEndScene_3_Activity.class);
                    break;
                case 1:
                    switchSceneActivity(SampleEndScene_2_Activity.class);
                    break;
            }

        } else {
            KWLog.d("無法識別的選項代號");
        }
    }
}
