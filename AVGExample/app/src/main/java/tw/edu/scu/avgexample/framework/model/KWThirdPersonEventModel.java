package tw.edu.scu.avgexample.framework.model;

import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class KWThirdPersonEventModel extends KWEventModel {

    public KWThirdPersonEventModel(KWCharacterModel characterModel) {
        this(characterModel, null);
    }

    public KWThirdPersonEventModel(KWCharacterModel characterModel, String message) {
        super.characterModel = characterModel;
        super.characterName = characterModel.getName();
        super.message = message;
    }

    @Override
    public int getCharacterFacing() {

        if (super.characterFacing == KW_EVENT_CHARACTER_FACING_UNDEFINED) {
            setCharacterPositionWithDefaultFacing(super.getCharacterPosition());
        }

        return super.getCharacterFacing();
    }

    public KWThirdPersonEventModel setCharacterFacing(int facing) {
        if (facing != KW_EVENT_CHARACTER_FACING_LEFT &&
            facing != KW_EVENT_CHARACTER_FACING_RIGHT) {
            KWLog.d("[錯誤] Facing（面相）只會有「向左」（KW_EVENT_CHARACTER_FACING_LEFT）或「向右」（KW_EVENT_CHARACTER_FACING_RIGHT）兩種類型。" +
                    "如果有自己新增的面相，例如「正面」，請覆寫這個 method 來解除只有兩種面相的限制" +
                    "（但現階段的模組尚未提供的如此彈性，或需要修改到framework中的程式碼，所以不建議追加類型）。");
            return this;
        }
        super.characterFacing = facing;
        return this;
    }

    public KWThirdPersonEventModel setCharacterNameVisibility(boolean visibility) {
        super.characterNameVisibility = visibility;
        return this;
    }

    public KWThirdPersonEventModel setCharacterImageVisibility(boolean visibility) {
        super.characterImageVisibility = visibility;
        return this;
    }

    public KWThirdPersonEventModel setCharacterPosition(int position) {
        if (position != KW_EVENT_CHARACTER_POSITION_LEFT &&
            position != KW_EVENT_CHARACTER_POSITION_CENTER &&
            position != KW_EVENT_CHARACTER_POSITION_RIGHT) {
            KWLog.d("[錯誤] Position（位置）只會有「左」（KW_EVENT_CHARACTER_POSITION_LEFT）或「中」（KW_EVENT_CHARACTER_POSITION_CENTER）或「右」（KW_EVENT_CHARACTER_POSITION_RIGHT）三種類型。" +
                    "如果有自己新增的面相，例如「中間偏上」，請覆寫這個 method 來解除只有三種位置的限制" +
                    "（但現階段的模組尚未提供的如此彈性，或需要修改到framework中的程式碼，所以不建議追加類型）。");
            return this;
        }

        super.characterPosition = position;
        return this;
    }

    public KWThirdPersonEventModel setCharacterPositionWithDefaultFacing(int position) {

        switch (position) {
            case KW_EVENT_CHARACTER_POSITION_LEFT:
                super.characterFacing = KW_EVENT_CHARACTER_FACING_RIGHT;
                break;
            case KW_EVENT_CHARACTER_POSITION_RIGHT:
                super.characterFacing = KW_EVENT_CHARACTER_FACING_LEFT;
                break;
            default:
                super.characterFacing = KW_EVENT_CHARACTER_FACING_RIGHT;
        }

        return setCharacterPosition(position);
    }

    public KWThirdPersonEventModel setBackgroundDrawable(Drawable drawable) {
        super.backgroundDrawable = drawable;
        return this;
    }

    public KWThirdPersonEventModel setBackgroundMusicResouceId(int resouceId) {
        super.backgroundMusicResourceId = resouceId;
        return this;
    }

    public KWThirdPersonEventModel setSouncEffectResouceId(int resouceId) {
        super.soundEffectResourceId = resouceId;
        return this;
    }

    public KWThirdPersonEventModel setMessageColorString(String colorString) {
        super.messageColorString = colorString;
        return this;
    }
}
