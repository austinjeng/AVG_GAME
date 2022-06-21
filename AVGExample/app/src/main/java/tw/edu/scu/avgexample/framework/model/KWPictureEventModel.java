package tw.edu.scu.avgexample.framework.model;

import android.graphics.drawable.Drawable;

public class KWPictureEventModel  extends KWEventModel {

    protected Drawable pictureDrawable = null;

    public KWPictureEventModel(String message) {
        this(null, null, message);
    }

    public KWPictureEventModel(Drawable pictureDrawable, String message) {
        this(pictureDrawable, null, message);
    }

    public KWPictureEventModel(Drawable pictureDrawable, String characterName, String message) {
        this.pictureDrawable = pictureDrawable;
        super.characterName = characterName;
        super.message = message;
    }

    public Drawable getPictureDrawable() {
        return this.pictureDrawable;
    }

    public KWPictureEventModel setName(String name) {
        super.characterName = name;
        return this;
    }

    public KWPictureEventModel setMessage(String message) {
        super.message = message;
        return this;
    }

    public KWPictureEventModel setCharacterNameVisibility(boolean visibility) {
        super.characterNameVisibility = visibility;
        return this;
    }

    public KWPictureEventModel setBackgroundDrawable(Drawable drawable) {
        super.backgroundDrawable = drawable;
        return this;
    }

    public KWPictureEventModel setBackgroundMusicResouceId(int resouceId) {
        super.backgroundMusicResourceId = resouceId;
        return this;
    }

    public KWPictureEventModel setSouncEffectResouceId(int resouceId) {
        super.soundEffectResourceId = resouceId;
        return this;
    }

    public KWPictureEventModel setMessageColorString(String colorString) {
        super.messageColorString = colorString;
        return this;
    }
}
