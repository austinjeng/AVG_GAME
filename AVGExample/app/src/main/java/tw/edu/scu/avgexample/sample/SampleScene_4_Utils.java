package tw.edu.scu.avgexample.sample;

import android.content.Context;
import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWCharacterModel;
import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWFirstPersonEventModel;
import tw.edu.scu.avgexample.framework.model.KWPictureEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class SampleScene_4_Utils {

    public static void scene_4_1(Context context, KWEventManager eventManager) {

        eventManager.stop();

        Drawable background;

        KWCharacterModel girl004CharacterModel;
        girl004CharacterModel = new KWCharacterModel(context, "kw_female_004", SampleGlobalData.girl004FullName).setAction(KWCharacterModel.KW_CHARACTER_ACTION_GENERAL);

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel).setCharacterPosition(KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER));

        background = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_background_024);

        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.playerName, "所以你剛剛說我來的剛好，是什麼意思？").setBackgroundDrawable(background));

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_SURPRISE), "哦！差點忘了！就是這個！")
                .setSouncEffectResouceId(R.raw.kw_sound_female_07));

        Drawable pictureDrawable;

        pictureDrawable = KWResourceUtils.getDrawableByResourceId(context, R.drawable.kw_picture_android_bmi_app);

        KWPictureEventModel pictureEventModel =
                new KWPictureEventModel(pictureDrawable, SampleGlobalData.girl004Name, "登登 ✧*｡٩(ˊωˋ*)و✧*｡")
                        .setSouncEffectResouceId(R.raw.kw_sound_pick);

        eventManager.addEvent(pictureEventModel);

        eventManager.addEvent(
                new KWPictureEventModel("這是我們自己寫的App哦！"));

        eventManager.addEvent(
                new KWPictureEventModel("只要輸入你的身高和體重，就可以顯示出對應的BMI值\n下方的圖片也會跟著改變！"));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_EUPHORIC);

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "你看這是不是超厲害的？很厲害對吧對吧對吧？"));

        eventManager.addEvent(new KWFirstPersonEventModel( SampleGlobalData.playerName, "呃，對，看起來是蠻厲害的... "));

        eventManager.addEvent(new KWFirstPersonEventModel( "（ 這麼開朗熱情的人我實在應付不來啊... 隨便敷衍一下就閃人吧 ）"));

        girl004CharacterModel.setAction(KWCharacterModel.KW_CHARACTER_ACTION_ANGRY);

        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "什麼嘛？我還以為反應會更大一點的...")
                .setSouncEffectResouceId(R.raw.kw_sound_female_07));
        eventManager.addEvent(new KWThirdPersonEventModel(girl004CharacterModel, "這可是我們社團千辛萬苦才寫出來的App呢！"));

        eventManager.play("場景4-1");

    }
}
