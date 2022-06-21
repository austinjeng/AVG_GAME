package tw.edu.scu.avgexample.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.widget.EditText;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class SampleScene_3_Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定好對應的Layout檔案
        setContentView(R.layout.activity_sample_scene_3);
    }

    //覆寫上層的事件初始化Method
    //建立這個場景預設的事件合集吧！
    @Override
    protected void initializeEvent () {
        super.initializeEvent();
        SampleScene_3_Utils.scene_3_1(this, this.eventManager);
    }

    //當有任意事件合集完成之後，根據完成的事件合集識別碼來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void didFinishAllEvent (String eventIdentifier){
        super.didFinishAllEvent(eventIdentifier);

        if ("場景3-1-1".equals(eventIdentifier) ||
            "場景3-1-2".equals(eventIdentifier)) {

            switchSceneActivity(SampleScene_4_Activity.class, false);
        }
    }

    //當有任意事件中的選項被觸發時，根據選項事件的識別碼，以及選擇到的索引，來決定下一個事件合集要播放的是哪一個（或是其它要做的事情）
    @Override
    protected void onOptionSelected (String identifier, int index){
        super.onOptionSelected(identifier, index);

        if ("場景3-1選項".equals(identifier)) {

            switch (index) {
                case 0:
                    SampleGlobalData.playerName = "路人";
                    SampleScene_3_Utils.scene_3_1_1(this, this.eventManager);
                    break;
                case 1:
                    showNameInputDialog();
                    break;
            }

        } else {
            KWLog.d("無法識別的選項代號");
        }
    }

    private void showNameInputDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        alert.setTitle("");
        alert.setMessage("我叫做什麼名字呢？");
        alert.setCancelable(false);

        alert.setView(edittext);

        alert.setPositiveButton("告訴她吧", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SampleGlobalData.playerName = edittext.getText().toString();
                KWLog.d("於互動模式下的文字輸入對話框中輸入了：" + SampleGlobalData.playerName);

                SampleScene_3_Utils.scene_3_1_2(SampleScene_3_Activity.this, SampleScene_3_Activity.this.eventManager);
            }
        });

        alert.setNegativeButton("想想還是算了", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                SampleGlobalData.playerName = "小氣路人";
                SampleScene_3_Utils.scene_3_1_1(SampleScene_3_Activity.this, SampleScene_3_Activity.this.eventManager);
            }
        });


        alert.show();
    }

    //
}
