package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_NO_BACKGROUND_MUSIC_ID;

public class SampleScene_5_Utils {
            /*
        KWCharacterModel boy001CharacterModel;
        boy001CharacterModel = new KWCharacterModel(context, "kw_male_001", "？？？");
        boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY);

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel, "")
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER)
                        .setBackgroundDrawable(background));
        */

    public static void scene_5_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_024);

        KWCharacterModel boy001CharacterModel = new KWCharacterModel(context, "kw_male_001", "？？？");
        KWCharacterModel girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName);

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_SURPRISE);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel).setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER)
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(KW_EVENT_NO_BACKGROUND_MUSIC_ID));

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY), "在門口吵吵鬧鬧的在幹什麼？都不能專心寫程式了！")
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT)
                        .setCharacterImageVisibility(false).setSouncEffectResouceId(R.raw.kw_sound_male_04));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 從社辦深處傳來一個男生的聲音，似乎對於我們在門口的談話很不悅啊！ ）"));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EMBARRASS)).setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT));

        eventManager.addEvent(
                new KWThirdPersonEventModel(boy001CharacterModel, SampleGlobalData.girl004Name +  "怎麼回事？這個看起來鬼鬼祟祟的傢伙是誰啊？要來加入社團的同學嗎？")
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT)
                        .setCharacterImageVisibility(true)
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_general_03)
                        .setSouncEffectResouceId(R.raw.kw_sound_male_05));

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "啊啊！我來介紹一下！")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_05));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, SampleGlobalData.playerName + "，在我左手邊這位是我們的 Android 研究社的社長～"));
        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "不過大家都叫他前兩個字， " + SampleGlobalData.boy001Name + " ，跟Android的發音是不是很像！"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ ...我倒是還蠻想吐槽妳的名字也跟蘋果手機撞音了啊... ）"));
        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, SampleGlobalData.boy001Name + "，在我前面這位自稱是不小心路過社辦，手上還偷拿著我們手機且鬼鬼祟祟的傢伙，叫做" + SampleGlobalData.playerName + "。"));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 喂喂喂，我說妳啊！我也被被妳形容的太可疑了吧！ ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 而且手機明明就是妳自己遞給我的。 ）"));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY), "不過"  + SampleGlobalData.playerName + "絕．對．不．是．什麼可疑的人哦！而且他還說他要加入我們的社團呢！"));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 謝謝啊，雖然澄清的有點晚了........ ）"));
        eventManager.addEvent(
                new KWFirstPersonEventModel( "（ ...等等？加入社團？ ）")
                        .setBackgroundMusicResouceId(KW_EVENT_NO_BACKGROUND_MUSIC_ID));

        eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setName(SampleGlobalData.boy001FullName).setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY),  "哦？這傢伙想加入我們社團啊？"));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 不不不，我可沒這樣說啊！ ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 正當我想要反駁時，這位社長緊接著打斷我 ）"));

        eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY),  "你覺得我們的計算BMI的App寫的如何？是不是很厲害？")
                .setSouncEffectResouceId(R.raw.kw_sound_male_06));

        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.playerName, "呃，不，我連App都還沒操作過..."));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY)));
        eventManager.addEvent(new KWThirdPersonEventModel(boy001CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY)));

        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.girl004FullName + " & " + SampleGlobalData.boy001FullName, "（ 盯───────────── ）")
                .setSouncEffectResouceId(R.raw.kw_sound_female_08));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 我感受到來自兩人滿滿怨念的視線 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 看來如果我不使用一下App是不能平安的離開這裡了 ）"));
        eventManager.play("場景5-1");

    }

}
