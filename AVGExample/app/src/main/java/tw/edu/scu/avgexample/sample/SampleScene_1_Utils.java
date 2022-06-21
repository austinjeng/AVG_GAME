package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleScene_1_Utils {

    //Scene 1-1 寫法範例1
    public static void scene_1_1(Context context, KWEventManager eventManager) {

        //停止當前所有事件
        eventManager.stop();

        //建立背景的圖檔
        Drawable background1 = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_019);
        Drawable background2 = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_032);

        //建立第1個第一人稱事件
        //傳入必要參數：角色名稱、對話訊息
        //設定選用參數：背景圖片、背景音樂
        KWFirstPersonEventModel event1 =
                new KWFirstPersonEventModel("", "（ 好緊張啊！嶄新的大學生活開始了！ ）")
                .setBackgroundDrawable(background1)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_general_01);

        //建立第2個第一人稱事件
        //傳入必要參數：對話訊息
        //（因為沿用前一個事件的名字，所以這邊就不再傳入角色名稱了）
        //（KWFirstPersonEventModel建構式有提供多載的建構式）
        KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("（ 說到我嚮往的大學生活啊... 就應該要好好享受豐富多姿的社團活動啊！）");

        //建立第3個第一人稱事件
        //傳入必要參數：對話訊息
        //設定選用參數：背景圖片
        KWFirstPersonEventModel event3 =
                new KWFirstPersonEventModel("（ 沒記錯的話... 六大樓好像都是社團的辦公室的樣子 ）")
                .setBackgroundDrawable(background2);

        //建立第4個第一人稱事件
        //傳入必要參數：對話訊息
        KWFirstPersonEventModel event4 = new KWFirstPersonEventModel("（ 先來看看有哪些有趣的社團吧！ ）");

        //將上述4個事件，依序加入到eventManager中
        eventManager.addEvent(event1);
        eventManager.addEvent(event2);
        eventManager.addEvent(event3);
        eventManager.addEvent(event4);

        //接著，請eventManager幫忙「播放」，並且給這次播放的「事件們」取個名字
        eventManager.play("場景1-1");
    }

    //Scene 1-1寫法範例2
//    public static void scene_1_1(Context context, KWEventManager eventManager) {
//
//        eventManager.stop();
//
//        Drawable background;
//
//        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_019);
//
//        eventManager.addEvent(
//                new KWFirstPersonEventModel("", "（ 好緊張啊！嶄新的大學生活開始了！ ）")
//                        .setBackgroundDrawable(background)
//                        .setBackgroundMusicResouceId(R.raw.kw_bgm_general_01));
//
//        eventManager.addEvent(new KWFirstPersonEventModel("（ 說到我嚮往的大學生活啊... 就應該要好好享受豐富多姿的社團活動啊！）"));
//
//        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_032);
//        eventManager.addEvent(new KWFirstPersonEventModel("（ 沒記錯的話... 六大樓好像都是社團的辦公室的樣子 ）").setBackgroundDrawable(background));
//        eventManager.addEvent(new KWFirstPersonEventModel("（ 先來看看有哪些有趣的社團吧！ ）"));
//
//        eventManager.play("場景1-1");
//    }

    //選項分岐方法
    public static void scene_1_2(Context context, KWEventManager eventManager) {

        //停止當前所有事件
        eventManager.stop();

        //建立一個文字的陣列
        ArrayList<String> optionArrayList = new ArrayList<>();

        //依序加入選項的文字（最多四個）
        optionArrayList.add("游泳社");
        optionArrayList.add("科學研究社");
        optionArrayList.add("烹飪社");
        optionArrayList.add("福利社");

        //建立選項事件
        //傳入必要參數：識別碼、提示訊息文字、選項文字陣列
        KWOptionEventModel event1 = new KWOptionEventModel("場景1-2選項", "接下來要看看哪個社團呢？", optionArrayList);

        //將上述選項事件，加入到eventManager中
        eventManager.addEvent(event1);

        //接著，請eventManager幫忙「播放」，並且給這次播放的「事件們」取個名字
        eventManager.play("場景1-2");
    }

    //重複訪問對話
    public static void scene_1_2_0(Context context, KWEventManager eventManager, int index) {

        String message = "";
        eventManager.stop();

        switch (index) {
            case 0:
                message = "（ 游泳社已經去看過了，都沒有人的樣子 ）";
                break;

            case 1:
                message = "（ 人體模型感覺狠狠的盯著我，不想再回去那裏了 ）";
                break;

            case 2:
                message = "（ 我不想和小強待在同個地方... ）";
                break;

            case 3:
                message = "（ 如果可以加入福利社好像不錯，可惜不是社團啊 ）";
                break;

            default:
                message = "（ 該該腦袋恍了一下神，剛剛我是要做什麼事情來著？ ）";
        }

        eventManager.addEvent(new KWFirstPersonEventModel(message));

        eventManager.play("場景1-2-x");
    }

    public static void scene_1_2_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_027);

        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 來到了游泳社...  ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_club_03));

        eventManager.addEvent(new KWFirstPersonEventModel("（ 夏天到了就會有想一頭栽進游泳池的衝動 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel("（ 但今天社團似乎沒有活動的樣子）"));

        eventManager.play("場景1-2-1");
    }

    public static void scene_1_2_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_028);

        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 來到了科學研究社...  ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_club_02));

        eventManager.addEvent(new KWFirstPersonEventModel("（ 不知為何總會聯想到科學小飛俠  ）").setBackgroundDrawable(background));
        eventManager.addEvent(new KWFirstPersonEventModel("（ 不過... 人體模型看起來好可怕啊！還是趕快離開好了 ）"));

        eventManager.play("場景1-2-2");
    }

    public static void scene_1_2_3(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_026);

        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 來到了烹飪社...  ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_club_04));

        eventManager.addEvent(new KWFirstPersonEventModel("（ 無時無刻都洋溢著了美食香味，總會讓人口水直流  ）").setBackgroundDrawable(background));
        eventManager.addEvent(new KWFirstPersonEventModel("（ 咦... 剛剛角落那個兩根觸角的小黑影該不會是... ）"));

        eventManager.play("場景1-2-3");
    }

    public static void scene_1_2_4(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_017);

        eventManager.addEvent(
                new KWFirstPersonEventModel("（ 來到了福利社...  ）")
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_club_01));

        eventManager.addEvent(new KWFirstPersonEventModel("（ 每次下課不知道要去哪時，都會想來晃晃的地方 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel("（ 呃，等等，不對，這根本不是社團啊！ ）"));

        eventManager.play("場景1-2-4");
    }
}
