package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWOptionEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleScene_7_Utils {

    public static void scene_7_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_024);

        KWCharacterModel boy001CharacterModel = new KWCharacterModel(context, "kw_male_001", SampleGlobalData.boy001FullName);
        KWCharacterModel girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel)
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT)
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_general_02)
                        .setSouncEffectResouceId(R.raw.kw_sound_pick));

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel, "輸入完了嗎？讓我看看你的BMI值。")
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT)
                        .setSouncEffectResouceId(R.raw.kw_sound_male_06));

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel, "BMI為" + SampleGlobalData.bmiValue + "？")
                        .setSouncEffectResouceId(R.raw.kw_sound_male_03));

        double bmi = SampleGlobalData.bmiValue;
        if (bmi < 18.5) {
            eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY), "你太瘦了啦！要多吃一點才可以長得跟城堡一樣壯哦！"));
            eventManager.addEvent(new KWFirstPersonEventModel(" ( 呃，實在想像不到跟城堡一樣壯是一個怎樣的概念... ) "));
        }
        else if (18.5 <= bmi && bmi < 25) {
            eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY), "嘖，沒想到BMI值竟然落在正常範圍，沒意沒思。"));
            eventManager.addEvent(new KWFirstPersonEventModel(" ( 唔... 正常不好嗎？ ) "));
        }
        else if (25 <= bmi && bmi < 30) {
            eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EMBARRASS), "不要太在意！有點肉肉的抱起來會像卡比獸的抱枕一樣很舒服的！"));
            eventManager.addEvent(new KWFirstPersonEventModel(" ( 卡比獸的抱枕？卡比獸已經不是只有肉肉層級的了吧 ) "));
        }
        else if (30 <= bmi && bmi < 34) {
            eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC), "哈哈哈！你這個死胖子！"));
            eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EMBARRASS), SampleGlobalData.boy001Name + "這樣太失禮了啦... 人家是像卡比獸一樣，胖胖的很可愛的！"));
            eventManager.addEvent(new KWFirstPersonEventModel(" ( 謝謝妳，但我覺得你的說法並沒有比較好... ) "));
        }
        else {
            eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_GENERAL), "......."));
            eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_GENERAL), "......."));
            eventManager.addEvent(new KWFirstPersonEventModel(" ( 看起來他們已經無力吐槽我的BMI了 ) "));
        }

        eventManager.play("場景7-1");

    }


    public static void scene_7_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        KWCharacterModel boy001CharacterModel = new KWCharacterModel(context, "kw_male_001", SampleGlobalData.boy001FullName);
        KWCharacterModel girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName);

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY), "看來你合格了呢！")
                        .setSouncEffectResouceId(R.raw.kw_sound_male_01));

        eventManager.addEvent(new KWFirstPersonEventModel(SampleGlobalData.playerName,"咦？合格？誰？是在說我嗎？"));
        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC), "這邊除了你之外沒有別人了哦！"));
        eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC), "只要你的BMI可以被計算的出來，就是我們Android研究社團的合格社員了！"));
        eventManager.addEvent(new KWFirstPersonEventModel(SampleGlobalData.playerName,".......我可以吐槽一下嗎？"));

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY), "不行。")
                        .setSouncEffectResouceId(R.raw.kw_sound_male_05));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY), "來來來，這邊這張入社申請單幫我簽個名哦！")
                .setSouncEffectResouceId(R.raw.kw_sound_female_03));

        ArrayList<String> optionArrayList = new ArrayList<>();
        optionArrayList.add("雖然總覺得哪邊怪怪的，但還是簽吧");
        optionArrayList.add("不不不，這種社團實在是太詭異了...");

        eventManager.addEvent(new KWOptionEventModel("場景7-2選項", "要加入Android研究社團？", optionArrayList));

        eventManager.play("場景7-2");

    }


}
