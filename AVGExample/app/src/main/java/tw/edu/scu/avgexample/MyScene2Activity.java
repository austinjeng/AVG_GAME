package tw.edu.scu.avgexample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;

import tw.edu.scu.avgexample.framework.KWBaseSceneActivity;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class MyScene2Activity extends KWBaseSceneActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scene2);
    }

    @Override
    protected void initializeEvent() {
        super.initializeEvent();

        eventManager.stop();

        //場景
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        //角色
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");
        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");

        KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(唉... 這次的調查真是亂七八糟, 完蛋了...)")
                .setBackgroundMusicResouceId(R.raw.kw_bgm_general_01);


        //新一出現
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(conan_character, "調查的如何~")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我","呃... 那個... 該怎麼說咧...");
        //小五郎出現
        KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(maori_character, "找到兇手沒! 我的披薩啊啊啊!")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT);
        KWFirstPersonEventModel event5 = new KWFirstPersonEventModel("我","仔細想想, 我覺得兇手呢...");
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false);
        KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false);

        //場景
        event1.setBackgroundDrawable(firm_background);

        eventManager.addEvent(event1);
        eventManager.addEvent(event2);
        eventManager.addEvent(event3);
        eventManager.addEvent(event4);
        eventManager.addEvent(event5);
        eventManager.addEvent(event6);
        eventManager.addEvent(event7);
        eventManager.play("第二場景");

        selectSuspect();
    }

    protected void selectSuspect()
    {
        ArrayList<String> optionArrayList = new ArrayList<>();

        optionArrayList.add("來借廁所的黑衣人");
        optionArrayList.add("找新一單挑的魔術師");
        optionArrayList.add("總在附近徘迴的長髮男");
        optionArrayList.add("好像都不是呢");

        KWOptionEventModel event1 = new KWOptionEventModel("選擇嫌犯", "覺得兇手是?", optionArrayList);

        eventManager.addEvent(event1);
        eventManager.play("兇手選擇");
    }

    protected void blackman_ending()
    {
        eventManager.stop();

        //事務所
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);
        Drawable black_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.black);
        Drawable rumbling_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.rumbling);
        Drawable lightning_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.lightning);

        //新一
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");
        KWCharacterModel blackman_character = new KWCharacterModel(this,"blackman", "黑衣人");
        KWCharacterModel eren_character = new KWCharacterModel(this,"eren", "黑衣人(艾連葉卡)");

        KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("怎麼想還是覺得那個黑衣人最可疑。");
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(conan_character, "是嗎? 那我們去把他抓出來!。")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false);
        KWThirdPersonEventModel event4= new KWThirdPersonEventModel(blackman_character, "哈... 結果你們還是來了。")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(blackman_character, "一直以來我都受著這種被當作犯人的眼光過活...")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(blackman_character, "沒人想理解我, 解釋了也沒用!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(blackman_character, "啊啊啊啊!!!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT)
                .setCharacterImageVisibility(false);
        KWFirstPersonEventModel event8 = new KWFirstPersonEventModel("-黑衣人全身發著火光-")
                .setBackgroundDrawable(black_background);
        KWFirstPersonEventModel event9 = new KWFirstPersonEventModel("-一會兒後-");
        KWFirstPersonEventModel event10 = new KWFirstPersonEventModel("-黑衣人變成了另一個樣貌-");
        KWThirdPersonEventModel event11 = new KWThirdPersonEventModel(eren_character, "是你們逼我的!");
        KWThirdPersonEventModel event12 = new KWThirdPersonEventModel(eren_character).setCharacterImageVisibility(false);
        KWFirstPersonEventModel event13 = new KWFirstPersonEventModel("-轟隆隆隆-")
                .setBackgroundDrawable(lightning_background);


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

        eventManager.play("黑衣人跳轉結局");
    }

    //怪盜基德結尾
    protected void magician_ending()
    {
        eventManager.stop();

        //月光
        Drawable moon_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.moon);

        //新一
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");

        KWFirstPersonEventModel event1 = new KWFirstPersonEventModel("(其實是因為想和怪盜先生要簽名呢! 披薩是誰吃得一點也不重要!)");
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(conan_character, "今天基德的作案現場好像是這裡, 有不少人圍觀。")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER);
        KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("大家都是基德的粉絲嗎?)");
        KWFirstPersonEventModel event4 = new KWFirstPersonEventModel("-忽然一陣歡呼-");
        KWFirstPersonEventModel event5 = new KWFirstPersonEventModel("我", "看到了! 白色的滑翔翼!");
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(conan_character, "哼! 今天又是一場精彩的演出呢!");
        KWFirstPersonEventModel event7 = new KWFirstPersonEventModel("(實在是太帥啦!)");
        KWFirstPersonEventModel event8 = new KWFirstPersonEventModel("當怪盜好像也是不錯的選擇, 嘿嘿!");
        KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false);

        //背景
        event1.setBackgroundDrawable(moon_background);

        eventManager.addEvent(event1);
        eventManager.addEvent(event2);
        eventManager.addEvent(event3);
        eventManager.addEvent(event4);
        eventManager.addEvent(event5);
        eventManager.addEvent(event6);
        eventManager.addEvent(event7);
        eventManager.addEvent(event8);
        eventManager.addEvent(event9);
        eventManager.play("怪盜基德跳轉結局");
    }

    //琴酒結局分岐事件
    protected void gin_ending_selection()
    {
        eventManager.stop();

        //背景
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);
        Drawable firm_night_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm_night);
        Drawable background_034 = KWResourceUtils.getDrawableByResourceId(this, R.drawable.kw_background_034);

        //角色
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");
        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");
        KWCharacterModel gin_character = new KWCharacterModel(this,"gin", "琴酒");
        KWCharacterModel vodka_character = new KWCharacterModel(this,"vodka", "琴酒");


        //新一和小五郎角色現身
        KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(conan_character)
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT)
                .setBackgroundMusicResouceId(R.raw.kw_bgm_weird);
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(maori_character)
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT);
        KWFirstPersonEventModel event3 = new KWFirstPersonEventModel("我", "我覺得兇手是琴酒!");
        KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(conan_character, "好! 我們這就去抓他")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);

        //切換夜晚事務所 先讓兩人淡出
        KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(maori_character).setCharacterImageVisibility(false)
                .setBackgroundDrawable(firm_night_background);
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false);
        KWFirstPersonEventModel event7 = new KWFirstPersonEventModel("我", "找到了, 他在那裡!");
        KWThirdPersonEventModel event8 = new KWThirdPersonEventModel(conan_character, "好像有甚麼不對勁...")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER);
        KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(conan_character, "我們先躲在旁邊看看。");
        KWThirdPersonEventModel event10 = new KWThirdPersonEventModel(conan_character).setCharacterImageVisibility(false)
                .setBackgroundDrawable(background_034);

        //切換交易地點
        KWFirstPersonEventModel event11 = new KWFirstPersonEventModel("???", "你要的東西我準備好了。");
        KWFirstPersonEventModel event12 = new KWFirstPersonEventModel("???", "費用的部分...");
        KWThirdPersonEventModel event13 = new KWThirdPersonEventModel(gin_character, "少廢話, 拿了就滾!");
        KWFirstPersonEventModel event14 = new KWFirstPersonEventModel("(有點危險的樣子)");

        ArrayList<String> optionArrayList = new ArrayList<>();

        optionArrayList.add("繼續偷看");
        optionArrayList.add("趕緊離開");

        KWThirdPersonEventModel event15 = new KWThirdPersonEventModel(gin_character).setCharacterImageVisibility(false);
        KWOptionEventModel event16 = new KWOptionEventModel("看見琴酒交易選項", "該怎麼辦?", optionArrayList);

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


        eventManager.play("琴酒結局分岐");
    }

    protected void gin_ending1()
    {
        eventManager.stop();

        //背景
        Drawable background_034 = KWResourceUtils.getDrawableByResourceId(this, R.drawable.kw_background_034);
        Drawable black_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.black);
        Drawable smaller_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.smaller);

        //角色
        KWCharacterModel gin_character = new KWCharacterModel(this,"gin", "琴酒");
        KWCharacterModel vodka_character = new KWCharacterModel(this,"vodka", "伏特加");

        KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(gin_character)
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(vodka_character)
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT);
        KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(vodka_character, "老大! 那裡有人在偷看!");
        KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(gin_character, "是剛才見過的傢伙啊!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(gin_character, "別怪我沒體醒過你!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(vodka_character, "就用組織新研發的毒藥把他們滅口吧!");
        KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(vodka_character).setCharacterImageVisibility(false);
        KWThirdPersonEventModel event8 = new KWThirdPersonEventModel(gin_character).setCharacterImageVisibility(false);
        KWFirstPersonEventModel event9 = new KWFirstPersonEventModel("(呃啊啊啊... 全身發燙...)")
                .setBackgroundDrawable(black_background);
        KWFirstPersonEventModel event10 = new KWFirstPersonEventModel("(。。。。。。)")
                .setBackgroundDrawable(smaller_background);
        KWFirstPersonEventModel event11 = new KWFirstPersonEventModel("(怎麼回事... 身體竟然變小了)");

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

        eventManager.play("琴酒跳轉結局1");
    }

    protected void gin_ending2()
    {
        eventManager.stop();

        //背景
        Drawable background_034 = KWResourceUtils.getDrawableByResourceId(this, R.drawable.kw_background_034);

        //角色
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");

        KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(conan_character, "我們先走吧, 繼續待著覺得不太妙。")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER)
                .setBackgroundDrawable(background_034);
        KWFirstPersonEventModel event2 = new KWFirstPersonEventModel("我", "了解!");

        eventManager.addEvent(event1);
        eventManager.addEvent(event2);

        eventManager.play("琴酒跳轉結局2");
    }

    protected void nothing_ending()
    {
        eventManager.stop();

        //場景
        Drawable firm_background = KWResourceUtils.getDrawableByResourceId(this, R.drawable.firm);

        //角色
        KWCharacterModel conan_character = new KWCharacterModel(this,"conan", "工藤新一");
        KWCharacterModel maori_character = new KWCharacterModel(this,"maori", "毛利小五郎");

        KWThirdPersonEventModel event1 = new KWThirdPersonEventModel(conan_character, "其實我也早就發現了呢!")
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event2 = new KWThirdPersonEventModel(maori_character)
                .setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT);
        KWThirdPersonEventModel event3 = new KWThirdPersonEventModel(conan_character, "真正的兇手就是~")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event4 = new KWThirdPersonEventModel(conan_character, "叔叔你自己啊!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event5 = new KWThirdPersonEventModel(maori_character, "什...什麼?!");
        KWThirdPersonEventModel event6 = new KWThirdPersonEventModel(conan_character, "肯定是叔叔昨天晚上喝得爛醉, 就拿出來配酒吃掉啦!")
                .setCharacterFacing(KWEventModel.KW_EVENT_CHARACTER_FACING_LEFT);
        KWThirdPersonEventModel event7 = new KWThirdPersonEventModel(maori_character, "啊...哈哈哈哈!");
        KWFirstPersonEventModel event8 = new KWFirstPersonEventModel("我", "沒錯耶! 仔細一看就發現桌子下還有空酒瓶和披薩的碎屑。");
        KWThirdPersonEventModel event9 = new KWThirdPersonEventModel(maori_character, "其...其實我只是要考驗你新人的能力啦! 哈哈哈哈!");
        KWFirstPersonEventModel event10 = new KWFirstPersonEventModel("(這就是大名鼎鼎的偵探毛利小五郎嗎...)");

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

        eventManager.play("都不是跳轉結局");
    }

    @Override
    protected void didFinishAllEvent(String eventIdentifier) {
        super.didFinishAllEvent(eventIdentifier);

        if ("怪盜基德跳轉結局".equals(eventIdentifier))
        {
            switchSceneActivity(ThiefEndingActivity.class, 250);
        }

        if ("琴酒跳轉結局1".equals(eventIdentifier))
        {
            switchSceneActivity(GinEnding1Activity.class, 250);
        }
        else if ("琴酒跳轉結局2".equals(eventIdentifier))
        {
            switchSceneActivity(GinEnding2Activity.class, 250);
        }

        if ("都不是跳轉結局".equals(eventIdentifier))
        {
            switchSceneActivity(NothingEndingActivity.class, 250);
        }

        if ("黑衣人跳轉結局".equals(eventIdentifier))
        {
            switchSceneActivity(BlackmanEndingActivity.class, 250);
        }
    }

    @Override
    protected void onOptionSelected(String identifier, int index) {
        super.onOptionSelected(identifier, index);

        if ("選擇嫌犯".equals(identifier)) {
            if (index == 0) {
                blackman_ending();
            }
            else if (index == 1) {
                magician_ending();
            }
            else if (index == 2) {
                gin_ending_selection();
            }
            else if (index == 3)
            {
                nothing_ending();
            }
        }

        if ("看見琴酒交易選項".equals(identifier))
        {
            if (index == 0)
            {
                gin_ending1();
            }
            else if (index == 1)
            {
                gin_ending2();
            }
        }
    }
}