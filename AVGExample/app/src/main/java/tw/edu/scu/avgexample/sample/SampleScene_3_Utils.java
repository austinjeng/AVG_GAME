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

import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_NO_BACKGROUND_MUSIC_ID;

public class SampleScene_3_Utils {
    public static void scene_3_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_024);

        KWCharacterModel girl004CharacterModel;

        girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", "？？？").setAction(KWCharacterModel.KW_CHARACTER_ACTION_SURPRISE);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "那我先ㄌ一ˊ......\n哇哇啊啊！痛痛痛痛痛!")
                        .setCharacterPositionWithDefaultFacing(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER)
                        .setBackgroundDrawable(background)
                        .setBackgroundMusicResouceId(KW_EVENT_NO_BACKGROUND_MUSIC_ID)
                        .setSouncEffectResouceId(R.raw.kw_sound_collision));

        eventManager.addEvent(new KWFirstPersonEventModel( "我","咦咦咦咦咦！？"));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 開門的瞬間，似乎社辦裡的人也正好要走出來 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 結果就這樣撞個正著 ）"));

        eventManager.addEvent(
                new KWFirstPersonEventModel( "我", "抱歉抱歉，你沒受傷吧？")
                        .setBackgroundMusicResouceId(R.raw.kw_bgm_general_02));

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "嚇了我一大跳！怎麼突然冒出一個人......")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_04));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_HAPPY);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "啊！不過你來的剛好！我叫做 " + SampleGlobalData.girl004FullName + " ～")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_03));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setName(SampleGlobalData.girl004FullName),"你可以直接叫我 " + SampleGlobalData.girl004Name + " 就可以囉！"));
        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.girl004FullName + "... 感覺好像外國人的名字啊 "));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setName(SampleGlobalData.girl004FullName),"你呢？你呢？ 你叫做什麼名字？"));
        eventManager.addEvent(new KWFirstPersonEventModel( "我", "啊... 我的名字啊... "));

        ArrayList<String> optionArrayList = new ArrayList<>();
        optionArrayList.add("沒必要告訴陌生人我的名字");
        optionArrayList.add("老實地告訴她吧");

        eventManager.addEvent(new KWOptionEventModel("場景3-1選項", "該告訴" + SampleGlobalData.girl004Name + "我的名字嗎？", optionArrayList));


        eventManager.play("場景3-1");
    }

    public static void scene_3_1_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        KWCharacterModel girl004CharacterModel;

        girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName).setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "不想透漏名字啊！看起來是個口風緊的傢伙呢。")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_08));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EMBARRASS);

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "那....\n這位不知道名字的路人同學，從今天起就稱呼你為..."));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC);

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "..." + SampleGlobalData.playerName + "！"));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "如何？" + SampleGlobalData.playerName + "，這真是一個好名字對吧？"));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 看著" + SampleGlobalData.girl004Name + "同學自顧自的說著，似乎很開心的樣子，就先不吐槽了 ）"));

        eventManager.play("場景3-1-1");
    }


    public static void scene_3_1_2(Context context, KWEventManager eventManager) {

        eventManager.stop();

        KWCharacterModel girl004CharacterModel;

        girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName).setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "原來你叫做" + SampleGlobalData.playerName + "啊，這真是一個好名字呢！")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_03));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_MOODY);

        String tempString = "";

        for (int i = 1; i < 6; i++) {
            tempString += SampleGlobalData.playerName;
        }

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel,  tempString + "..."));

        for (int i = 1; i < 6; i++) {
            tempString += SampleGlobalData.playerName;
        }

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel,  tempString + "..."));

        for (int i = 1; i < 6; i++) {
            tempString += SampleGlobalData.playerName;
        }

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel,  tempString + "..."));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 不知為何，" + SampleGlobalData.girl004Name + "就像念符咒般的，一直重複我的名字 ）"));
        eventManager.addEvent(new KWFirstPersonEventModel( "（ 怪不好意思的，得趕快打斷她才行 ）"));

        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.playerName, "呃... 不好意思... \n請問我的名字怎麼了嗎？"));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_SURPRISE);

        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "啊！不好意思！")
                        .setSouncEffectResouceId(R.raw.kw_sound_female_07));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_SHY);
        eventManager.addEvent(
                new KWThirdPersonEventModel(girl004CharacterModel, "因為我很不會記名字... 怕忘記所以才一直重複複習的... "));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 喂喂，一般人大多是默唸在心中，不會發出聲音來吧... ）"));

        eventManager.play("場景3-1-2");
    }



}
