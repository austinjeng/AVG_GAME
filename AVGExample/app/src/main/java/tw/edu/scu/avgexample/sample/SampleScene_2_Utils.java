package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleScene_2_Utils {

    public static void scene_2_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_033);
        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 不知不覺竟然逛到傍晚了 ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(KWEventModel.KW_EVENT_NO_BACKGROUND_MUSIC_ID));

        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 但是大家都跑去那兒了呢？\n應該要很熱鬧的社辦卻沒半個人影 ）")
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_weird));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_025);
        eventManager.addEvent(new KWFirstPersonEventModel("（ 此時，我注意到一間社辦，傳來了喧嘩的聲音。而且門口窗戶還綻放著異常耀眼的光芒 ）").setBackgroundDrawable(background));

        ArrayList<String> optionArrayList = new ArrayList<>();
        optionArrayList.add("人都來了，就進去吧");
        optionArrayList.add("還是算了，總覺得哪裡怪怪的");

        eventManager.addEvent(new KWOptionEventModel("場景2-1選項", "該進去這間社辦看看嗎？", optionArrayList));

        eventManager.play("場景2-1");
    }

    public static void scene_2_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_025);
        eventManager.addEvent(new KWFirstPersonEventModel("（ 我怎麼能在這時候退縮呢！ ）").setBackgroundDrawable(background));
        eventManager.addEvent(new KWFirstPersonEventModel("（ 這可是我夢寐以求的大學社團生活呢！ ）"));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_009);
        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 於是我打開了門 ）")
                        .setBackgroundDrawable(background)
                        .setSouncEffectResouceId(R.raw.kw_sound_door_open));


        eventManager.play("場景2-2");
    }

}
