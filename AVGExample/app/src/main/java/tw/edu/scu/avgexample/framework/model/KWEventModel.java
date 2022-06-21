package tw.edu.scu.avgexample.framework.model;

import android.graphics.drawable.Drawable;

public class KWEventModel implements Cloneable {

    //EVENT_CHARACTER_POSITION
    //預設的position編號從21000~21003，主要是為了避免編號從零開始會跟其它開發者定義的編號重複
    final static public int KW_EVENT_CHARACTER_POSITION_UNDEFINED   =  21000;
    final static public int KW_EVENT_CHARACTER_POSITION_LEFT        =  21001;
    final static public int KW_EVENT_CHARACTER_POSITION_CENTER      =  21002;
    final static public int KW_EVENT_CHARACTER_POSITION_RIGHT       =  21003;

    //EVENT_CHARACTER_FACING
    final static public int KW_EVENT_CHARACTER_FACING_UNDEFINED     =  0;
    final static public int KW_EVENT_CHARACTER_FACING_LEFT          =  1;
    final static public int KW_EVENT_CHARACTER_FACING_RIGHT         = -1;

    //預設的messageType編號從22000~22002，主要是為了避免編號從零開始會跟其它開發者定義的編號重複
    final static public int KW_EVENT_MESSAGE_TYPE_UNDEFINED         =  22000;
    final static public int KW_EVENT_MESSAGE_TYPE_DIALOG            =  22001;
    final static public int KW_EVENT_MESSAGE_TYPE_FULLSCREEN        =  22002;

    final static public int KW_EVENT_NO_BACKGROUND_MUSIC_ID         =  0;

    protected KWCharacterModel characterModel = null;

    protected int characterFacing = KW_EVENT_CHARACTER_FACING_UNDEFINED;
    protected int characterPosition = KW_EVENT_CHARACTER_POSITION_UNDEFINED;

    protected boolean characterImageVisibility = true;
    protected boolean characterNameVisibility = true;

    protected String characterName = null;

    protected String message = null;
    protected int messageType = KW_EVENT_MESSAGE_TYPE_UNDEFINED;

    protected Drawable backgroundDrawable = null;
    protected int backgroundMusicResourceId = -1;
    protected int soundEffectResourceId = -1;

    protected String messageColorString = "#2E2E2E";

    public String getCharacterIdentifier() {
        return this.characterModel.getIdentifier();
    }

    public Drawable getCharacterDrawable() {
        return (this.characterModel != null && this.characterImageVisibility) ?
                this.characterModel.getDrawable() : null;
    }

    public String getCharacterName() {
        if (this.characterName != null) {
            return this.characterName;
        }

        return (this.characterModel != null && this.characterNameVisibility) ?
                this.characterModel.getName() : null;
    }

    public int getCharacterFacing() {
        return this.characterFacing;
    }

    public int getCharacterPosition() {
        return this.characterPosition;
    }

    public boolean getCharacterImageVisibility() {
        return this.characterImageVisibility;
    }

    public boolean getCharacterNameVisibility() {
        return this.characterNameVisibility;
    }

    public String getMessage() {
        return this.message;
    }

    public int getMessageType() {
        return this.messageType;
    }

    public String getMessageColorString() {
        return messageColorString;
    }

    public Drawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    public int getBackgroundMusicResourceId() {
        return this.backgroundMusicResourceId;
    }

    public int getSoundEffectResourceId() {
        return this.soundEffectResourceId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        KWEventModel eventModel = (KWEventModel)super.clone();
        eventModel.characterModel = (this.characterModel == null) ? null : (KWCharacterModel)this.characterModel.clone();

        return eventModel;
    }

}
