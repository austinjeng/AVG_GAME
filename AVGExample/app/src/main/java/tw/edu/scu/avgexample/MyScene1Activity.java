package tw.edu.scu.avgexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;
import tw.edu.scu.avgexample.sample.SampleSplashActivity;

public class MyScene1Activity extends KWBaseSceneActivity {

    private boolean[] isOptionSelected = new boolean[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scene1);
    }

    @Override
    protected void initializeEvent () {
        super.initializeEvent();

        //場景
        Drawable mihua_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.mihua);
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        //角色
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");
        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");

        KWThirdPersonEventModel eventBGM1 = new KWThirdPersonEventModel(conan_character)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_general_01)
                .setCharacterImageVisibility(false);

        //米花町獨白
        KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(這就是米花町嗎?)");
        KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("(跟電視上看到的一模一樣呢!)");

        //剛到事務所獨白
        KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("(終於到了...)")
                .setSouncEffectResouceId(R.raw.kw_sound_door_open);
        KWFirstPersonEventModel event4 = new KWFirstPersonEventModel("(沒錯! 搬來這裡就是為了能夠到大名鼎鼎的毛利事務所工作!)");

        //新一登場跟主角談話
        KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(conan_character, "哈囉~ 我是高中生偵探工藤新一!");
        KWFirstPersonEventModel event6 = new KWFirstPersonEventModel("(哇! 本人比電視上還帥呢...)");
        KWFirstPersonEventModel event7 = new KWFirstPersonEventModel("我","你好, 我是第一天來的新人, 請多多指教!");
        KWThirdPersonEventModel event8 = new KWThirdPersonEventModel(conan_character, "呃... 我不是這裡的員工拉! 話說...")
                .setSouncEffectResouceId(R.raw.kw_sound_male_03);
        KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(conan_character, "你也想當偵探嗎?");
        KWThirdPersonEventModel event10 = new KWThirdPersonEventModel(conan_character, "hmm... 當偵探可不是那麼容易呢!");
        KWThirdPersonEventModel event11 = new KWThirdPersonEventModel(conan_character, "首先要和我一樣又聰明又帥...");

        //小五郎登場
        KWFirstPersonEventModel event12 = new KWFirstPersonEventModel("???", "什麼!!!(打斷新一)");
        KWThirdPersonEventModel event13 = new KWThirdPersonEventModel(conan_character, "叔叔又怎麼了...");
        event13.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT);
            //新一的FACING LEFT是向右看
        event13.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event14 = new KWThirdPersonEventModel(maori_character, "我放在冰箱裡的披薩不見啦!");
        event14.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT);
        KWThirdPersonEventModel event15 = new KWThirdPersonEventModel(maori_character, "欸? 你是...今天要來的新人嗎?");
        KWFirstPersonEventModel event16 = new KWFirstPersonEventModel("我","沒錯!")
                .setSouncEffectResouceId(R.raw.kw_sound_male_01);
        KWThirdPersonEventModel event17 = new KWThirdPersonEventModel(maori_character, "既然這樣, 你的第一個任務, 去幫我找出誰偷了我的披薩吧!");
        KWThirdPersonEventModel event18 = new KWThirdPersonEventModel(maori_character, "新一, 你也去幫他吧!");
        KWThirdPersonEventModel event19 = new KWThirdPersonEventModel(conan_character, "這種程度是低估我了吧!");
        event19.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event20 = new KWThirdPersonEventModel(conan_character, "其實我已經鎖定3個嫌疑人, 你去調查看看。");
        event20.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWFirstPersonEventModel event21 = new KWFirstPersonEventModel("我","沒問題!")
                .setSouncEffectResouceId(R.raw.kw_sound_male_02);

        //隱藏角色圖片
        KWThirdPersonEventModel event22 = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false);
        KWThirdPersonEventModel event23 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false);


        //背景轉換區
        event1.setBackgroundDrawable(mihua_background);
        event3.setBackgroundDrawable(firm_background);

        eventManager.addEvent(eventBGM1);
        eventManager.addEvent(event1);
        eventManager.addEvent(event2);
        eventManager.addEvent(event3);
        eventManager.addEvent(event4);
        eventManager.addEvent(event5);
        eventManager.addEvent(event6);
        eventManager.addEvent(event7);
        eventManager.addEvent(event8);
        eventManager.addEvent(event9);
        eventManager.addEvent(event10);
        eventManager.addEvent(event11);
        eventManager.addEvent(event12);
        eventManager.addEvent(event13);
        eventManager.addEvent(event14);
        eventManager.addEvent(event15);
        eventManager.addEvent(event16);
        eventManager.addEvent(event17);
        eventManager.addEvent(event18);
        eventManager.addEvent(event19);
        eventManager.addEvent(event20);
        eventManager.addEvent(event21);
        eventManager.addEvent(event22);
        eventManager.addEvent(event23);
//        eventManager.addEvent(event24);

        eventManager.play("我的第一個事件");
        investigateSuspect();
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("場景1-2選項".equals(identifier)) {
            if (index == 0) {
                Log.d("SCU", "黑衣人事件觸發!");
                blackman_event();
            }
            else if (index == 1) {
                magician_event();
            }
            else if (index == 2) {
                gin_event();
            }

        }
    }

    void printIsOptionSelected()
    {
        for(int i = 0; i < isOptionSelected.length; i++)
        {
            Log.d("SCU", i + String.valueOf(isOptionSelected[i]));
        }
    }

    //選擇嫌疑人 index為完成的事件
//    protected void investigateSuspect(int index)
//    {
//        //eventManager.stop();
//
//        isOptionSelected[index] = true;
//
//        ArrayList<String> optionArrayList = new ArrayList<>();
//
//        optionArrayList.add("來借廁所的黑衣人");
//        optionArrayList.add("找新一單挑的魔術師");
//        optionArrayList.add("總在附近徘迴的長髮男");
//
//        KWOptionEventModel event1 = new KWOptionEventModel("場景1-2選項", "想要調查誰呢?", optionArrayList);
//
//        eventManager.addEvent(event1);
//        eventManager.play("選擇嫌疑人");
//
//
//    }

    //選擇初始嫌疑人
    protected void investigateSuspect()
    {
        //eventManager.stop();
        printIsOptionSelected();

        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");

        KWThirdPersonEventModel eventBGM = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_pink_soldier);

        if (isOptionSelectedFinished())
            return;
        ArrayList<String> optionArrayList = new ArrayList<>();

        optionArrayList.add("來借廁所的黑衣人");
        optionArrayList.add("找新一單挑的魔術師");
        optionArrayList.add("總在附近徘迴的長髮男");


        KWOptionEventModel event1 = new KWOptionEventModel("場景1-2選項", "想要調查誰呢?", optionArrayList);

        eventManager.addEvent(eventBGM);
        eventManager.addEvent(event1);
        eventManager.play("選擇嫌疑人");
    }

    //黑衣人事件
    protected void blackman_event()
    {
        eventManager.stop();

        //偵探事務所
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        //黑衣人
        KWCharacterModel blackman_character = new KWCharacterModel(this,"blackman", "黑衣人");

        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");
        KWThirdPersonEventModel eventBGM = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_bad_end);

        if (isOptionSelected[0])
        {
            KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(blackman_character);
            event5.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT);
            event5.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(這...一看就是犯人了吧...)");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我","你...");
            KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(blackman_character, "...又來!!");
            event3.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT);
            event3.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(blackman_character).setCharacterImageVisibility(false);

            //背景切換
            event5.setBackgroundDrawable(firm_background);

            eventManager.addEvent(eventBGM);
            eventManager.addEvent(event5);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("黑衣人已經訪問過");

            investigateSuspect();
        }
        else
        {

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(blackman_character);
            event1.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT);
            //黑衣人每次說話都得調整位置
            event1.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("(這...一看就是犯人了吧...)");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我","請問你知道事務所裡的冰箱有披薩消失這件事嗎?");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(blackman_character, "不知道呢, 我只是尿急去借了廁所而已, 馬上就離開了。");
            event4.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWFirstPersonEventModel event5 = new KWFirstPersonEventModel("我","要怎麼證明你沒有偷吃呢?");
            KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(blackman_character, "我上完廁所就離開了, 根本沒時間吃阿!");
            event6.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(blackman_character, "而且你看我這樣, 哪有地方讓我藏著帶出去阿!");
            event7.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWFirstPersonEventModel event8 = new KWFirstPersonEventModel("我","有道理, 但是你長這樣很難讓人相信呢!");
            KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(blackman_character, "我出生就長這樣啊!! 我其實很善良的。");
            event9.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWThirdPersonEventModel event10 = new KWThirdPersonEventModel(blackman_character, "這種事我也遇多了, 但是真的不是我!!");
            event10.setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
            KWFirstPersonEventModel event11 = new KWFirstPersonEventModel("(好吧!那接著調查其他人。)");
            KWThirdPersonEventModel event12 = new KWThirdPersonEventModel(blackman_character).setCharacterImageVisibility(false);

            //背景切換
            event1.setBackgroundDrawable(firm_background);

            eventManager.addEvent(eventBGM);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.addEvent(event6);
            eventManager.addEvent(event7);
            eventManager.addEvent(event8);
            eventManager.addEvent(event9);
            eventManager.addEvent(event10);
            eventManager.addEvent(event11);
            eventManager.addEvent(event12);

            eventManager.play("黑衣人事件");

            isOptionSelected[0] = true;

            investigateSuspect();
        }
    }

    protected void magician_event()
    {
        eventManager.stop();

        //月光
        Drawable moon_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.moon);

        //怪盜基德
        KWCharacterModel thief_character = new KWCharacterModel(this,"thief", "怪盜基德");
        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");
        KWThirdPersonEventModel eventBGM = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_kid_theme);

        if (isOptionSelected[1])
        {
            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(。。。。。。)");
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("(啊... 又等了一個小時)");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("(怪盜先生肯定在忙吧, 還是別等了)");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(thief_character).setCharacterImageVisibility(false);

            //背景切換
            event1.setBackgroundDrawable(moon_background);

            eventManager.addEvent(eventBGM);
            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);

            eventManager.play("怪盜基德已經訪問過");

            investigateSuspect();
        }
        else
        {
            KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(馬上就要見到傳說中的\"世紀末的魔術師\"了, 我可是粉絲阿!)");
            KWThirdPersonEventModel event99 = new KWThirdPersonEventModel(thief_character);
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我","久仰大名了, 能見到怪盜先生真不容易。");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我","雖然知道不太可能, 但是還是要問一下, 那個關於冰箱的披薩...");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(thief_character, "哈哈哈哈! 開甚麼玩笑!");
            KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(thief_character, "我有可能偷那種東西?!");
            KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(thief_character, "簡直是浪費時間!");
            KWFirstPersonEventModel event8 = new KWFirstPersonEventModel("我","確實是這樣呢~");
            KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(thief_character, "如果沒有其他事, 我要走了~");
            KWThirdPersonEventModel event10 = new KWThirdPersonEventModel(thief_character, "今天可是要準備上演一場精采的竊盜大秀呢!");
            KWThirdPersonEventModel event11 = new KWThirdPersonEventModel(thief_character).setCharacterImageVisibility(false);
            KWFirstPersonEventModel event12 = new KWFirstPersonEventModel("我","那怪盜先生, 可以幫我簽...");
            KWFirstPersonEventModel event13 = new KWFirstPersonEventModel("(啊... 已經消失了啊!)")
                    .setSouncEffectResouceId(R.raw.kw_sound_male_03);

            event1.setBackgroundDrawable(moon_background);

            eventManager.addEvent(eventBGM);
            eventManager.addEvent(event1);
            eventManager.addEvent(event99);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            //eventManager.addEvent(event6);
            eventManager.addEvent(event7);
            eventManager.addEvent(event8);
            eventManager.addEvent(event9);
            eventManager.addEvent(event10);
            eventManager.addEvent(event11);
            eventManager.addEvent(event12);
            eventManager.addEvent(event13);

            eventManager.play("怪盜基德事件");

            isOptionSelected[1] = true;

            investigateSuspect();
        }
    }

    protected void gin_event()
    {
        eventManager.stop();

        //夜晚事務所背景
        Drawable firm_night_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm_night);

        //琴酒
        KWCharacterModel gin_character = new KWCharacterModel(this,"gin", "琴酒");

        if (isOptionSelected[2])
        {
            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(gin_character, "不是叫你閃邊去嗎!")
                    .setBackgroundMusicResouceId(R.raw.kw_bgm_weird);
            KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(gin_character, "找死嗎!");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我","對...對..對不起!!!!");
            event1.setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER);
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(gin_character).setCharacterImageVisibility(false);

            //背景切換
            event1.setBackgroundDrawable(firm_night_background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.play("琴酒已經訪問過");

            investigateSuspect();
        }
        else
        {
            isOptionSelected[2] = true;

            KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(gin_character)
                    .setBackgroundMusicResouceId(R.raw.kw_bgm_weird);
            KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("(呃... 這個人看起來不好惹啊...)");
            KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我", "那個... 請問你有吃掉冰箱裡的披薩嗎?");
            KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(gin_character, "我看起來很閒嗎");
            KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(gin_character, "我可忙得, 沒事就閃邊去!");
            KWFirstPersonEventModel event6 = new KWFirstPersonEventModel("(這個人太可怕了吧! 貌似還帶著槍...)");
            KWFirstPersonEventModel event7 = new KWFirstPersonEventModel("(為了一個披薩賭上小命, 不值得啊!)");
            KWThirdPersonEventModel event8 = new KWThirdPersonEventModel(gin_character).setCharacterImageVisibility(false);

            event1.setBackgroundDrawable(firm_night_background);

            eventManager.addEvent(event1);
            eventManager.addEvent(event2);
            eventManager.addEvent(event3);
            eventManager.addEvent(event4);
            eventManager.addEvent(event5);
            eventManager.addEvent(event6);
            eventManager.addEvent(event7);
            eventManager.addEvent(event8);

            eventManager.play("琴酒事件");

            investigateSuspect();
        }
    }


    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("琴酒事件".equals(eventIdentifier) || "怪盜基德事件".equals(eventIdentifier) || "黑衣人事件".equals(eventIdentifier))
        {
            if (isOptionSelectedFinished())
            {
                Log.d("SCU", "All done la");
                switchSceneActivity(MyScene2Activity.class, 350);
            }


        }
    }



    private boolean isOptionSelectedFinished() {

        for (int i = 0; i < isOptionSelected.length; i++) {
            if (isOptionSelected[i] == false) {
                return false;
            }
        }

        return true;
    }
}


