package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleScene_6_Utils {

    public static void scene_6_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_024);

        eventManager.addEvent(
                new KWFirstPersonEventModel( "（ 於是我看了看我手上的手機 ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_club_02)
                        .setSouncEffectResouceId(R.raw.kw_sound_pick));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 看樣子只要輸入完「身高」和「體重」之後，按下「計算」按鈕就可以了 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 試試看吧～ ）"));

        eventManager.play("場景6-1");

    }

    public static void scene_6_1_0(Context context, KWEventManager eventManager, int type) {

        eventManager.stop();

        String message = "...你是認真的嗎？";

        //這邊是取決於人設才設定的防呆對話，絕對不是在罵各位
        switch (type) {
            case 0:
                message = "認真點填身高！怎麼看你都不像是這麼矮的傢伙。";
                break;
            case 1:
                message = "你的身高比目前金氏世界記錄還要高？這不可能，重填！";
                break;
            case 2:
                message = "你這體重比紙片人還要輕了，拜託老實點填寫啊！";
                break;
            case 3:
                message = "這體重你應該躺在棺材裡了，不應該出現在我們這，死胖子。";
                break;
            case 4:
                message = "你的身高沒有填啊，該幫你配付老花眼鏡嗎？";
                break;
            case 5:
                message = "你的體重忘了填了，該說你是白痴還是笨蛋呢...";
                break;
        }

        //這煙我偷懶直接使用 KWFirstPersonEventModel，來代替應該要使用的 KWThirdPersonEventModel
        //因為這樣程式碼比較簡短，又可以達到相同的效果（因為在這個模式下，不用顯示角色圖片）
        eventManager.addEvent(
                new KWFirstPersonEventModel( SampleGlobalData.boy001FullName, message)
                        .setSouncEffectResouceId(R.raw.kw_sound_male_05));

        eventManager.play("場景6-1-0");

    }

    public static void scene_6_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 好了！看起來BMI算好了！ ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 給他們看看吧～ ）"));

        eventManager.play("場景6-2");

    }
}
