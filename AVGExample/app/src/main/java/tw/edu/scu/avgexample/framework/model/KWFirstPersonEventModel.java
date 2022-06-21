package tw.edu.scu.avgexample.framework.model;

import android.graphics.drawable.Drawable;

public class KWFirstPersonEventModel extends KWEventModel {

    public KWFirstPersonEventModel(String message) {
        this("", message);
    }

    public KWFirstPersonEventModel(String characterName, String message) {
        super.characterName = characterName;
        super.message = message;
    }

    public KWFirstPersonEventModel setCharacterNameVisibility(boolean visibility) {
        super.characterNameVisibility = visibility;
        return this;
    }

    public KWFirstPersonEventModel setName(String name) {
        super.characterName = name;
        return this;
    }

    public KWFirstPersonEventModel setMessage(String message) {
        super.message = message;
        return this;
    }

    public KWFirstPersonEventModel setBackgroundDrawable(Drawable drawable) {
        super.backgroundDrawable = drawable;
        return this;
    }

    public KWFirstPersonEventModel setBackgroundMusicResouceId(int resouceId) {
        super.backgroundMusicResourceId = resouceId;
        return this;
    }

    public KWFirstPersonEventModel setSouncEffectResouceId(int resouceId) {
        super.soundEffectResourceId = resouceId;
        return this;
    }

    public KWFirstPersonEventModel setMessageColorString(String colorString) {
        super.messageColorString = colorString;
        return this;
    }
}
