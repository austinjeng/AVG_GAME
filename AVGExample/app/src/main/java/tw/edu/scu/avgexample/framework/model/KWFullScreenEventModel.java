package tw.edu.scu.avgexample.framework.model;

import android.graphics.drawable.Drawable;

public class KWFullScreenEventModel extends KWEventModel {

    public KWFullScreenEventModel(String message) {
        super.characterName = "";
        super.characterNameVisibility = false;
        super.messageType = KW_EVENT_MESSAGE_TYPE_FULLSCREEN;
        super.message = message;
        super.messageColorString = "#E0E0E0";
    }

    public KWFullScreenEventModel setBackgroundDrawable(Drawable drawable) {
        super.backgroundDrawable = drawable;
        return this;
    }

    public KWFullScreenEventModel setBackgroundMusicResouceId(int resouceId) {
        super.backgroundMusicResourceId = resouceId;
        return this;
    }

    public KWFullScreenEventModel setSouncEffectResouceId(int resouceId) {
        super.soundEffectResourceId = resouceId;
        return this;
    }

    public KWFullScreenEventModel setMessageColorString(String colorString) {
        super.messageColorString = colorString;
        return this;
    }
}
