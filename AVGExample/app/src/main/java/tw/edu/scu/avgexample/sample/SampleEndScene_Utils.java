package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWFullScreenEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleEndScene_Utils {

    public static void end_1(Context context, KWEventManager eventManager) {
        eventManager.stop();

        Drawable background;
        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_002);

        eventManager.addEvent(
                new KWFullScreenEventModel("就這樣，我離開了六大樓，沒找到任何有興趣的社團。後來因為忙碌於課業也就一直跟社團活動無緣了。\n\n雖然一直維持很好的成績，也不知道該如何表達，但總覺得大學生活缺少了點什麼就是了。")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_bad_end));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_004);
        eventManager.addEvent(new KWFullScreenEventModel("畢業後，到一家科技公司任職，過著傳說中工程師口耳相傳的上班打卡制，下班責任制的生活。雖然上班有錢可以領很好，但每次一回想起學生時代，總覺得當學生比起上班族而言，真的是幸福很多。\n\n以前當學生只想趕快畢業，趕快找到工作賺大錢，但如果可以的話，真希望能夠重來一次，讓我好好地享受校園生活啊......" ).setBackgroundDrawable(background));

        eventManager.play("結局場景1");
    }

    public static void end_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;
        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_009);

        eventManager.addEvent(new KWFullScreenEventModel("就這樣，我回絕了" + SampleGlobalData.girl004Name + "和" + SampleGlobalData.boy001Name + "的邀請。後來因為忙碌於課業也就一直跟社團活動無緣了。\n\n雖然一直維持很好的成績，也不知道該如何表達，但總覺得大學生活缺少了點什麼就是了。")
                .setBackgroundDrawable(background)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_bad_end));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_004);
        eventManager.addEvent(new KWFullScreenEventModel("畢業後，到一家科技公司任職，過著傳說中工程師口耳相傳的上班打卡制，下班責任制的生活。雖然上班有錢可以領很好，但每次一回想起學生時代，總覺得當學生比起上班族而言，真的是幸福很多。\n\n以前當學生只想趕快畢業，趕快找到工作賺大錢，但如果可以的話，我會希望那時候我是選擇加入 Android 研究社的。" ).setBackgroundDrawable(background));

        eventManager.play("結局場景2");
    }

    public static void end_3(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;
        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_025);

        eventManager.addEvent(
                new KWFullScreenEventModel("就這樣，我加入了Android研究社團，每天的社團活動在" + SampleGlobalData.girl004Name + "和" + SampleGlobalData.boy001Name + "的指導下，我的 Android App 開發能力持續地在進步。\n\n大三的專題，靠著社團學來的Android功力，寫了一款跟智能生活相關的App，獲得大家一致的好評！")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_good_end));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_038);
        eventManager.addEvent(new KWFullScreenEventModel("畢業後，在" + SampleGlobalData.boy001Name + "的邀請下，跟" + SampleGlobalData.girl004Name + "一起三人成立了App工作室。雖然接案的日子是真的很忙碌，但每天和同伴們一起努力打拼、一起賺錢、一起朝同個目標前進的感覺真的很好。\n\n現在仔細想想，幸好當初沒有回絕掉他們的邀請，App開發真的很有趣呢！").setBackgroundDrawable(background));

        eventManager.play("結局場景3");
    }

}
