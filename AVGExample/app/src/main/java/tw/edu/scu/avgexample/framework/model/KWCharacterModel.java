package tw.edu.scu.avgexample.framework.model;


import android.content.Context;
import android.graphics.drawable.Drawable;

import tw.edu.scu.avgexample.framework.utility.KWLog;
import tw.edu.scu.avgexample.framework.utility.KWResourceUtils;

public class KWCharacterModel implements Cloneable {

    //CHARACTER_ACTION
    //預設的action編號從11000~11010，主要是為了避免編號從零開始會跟其它開發者定義的編號重複
    final static public int KW_CHARACTER_ACTION_DEFAULT         =  11000;
    final static public int KW_CHARACTER_ACTION_ANGRY           =  11001;
    final static public int KW_CHARACTER_ACTION_CRY             =  11002;
    final static public int KW_CHARACTER_ACTION_EMBARRASS       =  11003;
    final static public int KW_CHARACTER_ACTION_EUPHORIC        =  11005;
    final static public int KW_CHARACTER_ACTION_GENERAL         =  11006;
    final static public int KW_CHARACTER_ACTION_HAPPY           =  11007;
    final static public int KW_CHARACTER_ACTION_MOODY           =  11008;
    final static public int KW_CHARACTER_ACTION_SHY             =  11009;
    final static public int KW_CHARACTER_ACTION_SURPRISE        =  11010;

    //CHARACTER_TYPE
    //預設的type編號從12000~12001，主要是為了避免編號從零開始會跟其它開發者定義的編號重複
    final static public int KW_CHARACTER_TYPE_CASUAL            =  12000;
    final static public int KW_CHARACTER_TYPE_UNIFORM           =  12001;

    private Context context;

    //用來識別每個角色的ID，同時也是該角色圖片檔名預設的「前綴」詞
    private String identifier = "DEFAULT_CHARACTER_IDENTIFIER";


    //Public Properties with Getter Setter
    //在尚未幫角色取名前，預設的角色顯示名稱為？？？
    private String name = "？？？";
    private int action = KW_CHARACTER_ACTION_DEFAULT;
    private int type = KW_CHARACTER_TYPE_CASUAL;

    //建構式
    public KWCharacterModel(Context context) {
        this.context = context;
    }

    //建構式
    public KWCharacterModel(Context context, String identifier) {
        this.context = context;
        this.identifier = identifier;
    }

    //建構式
    public KWCharacterModel(Context context, String identifier, String name) {
        this.context = context;
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() { return name; }

    public KWCharacterModel setName(String name) {
        if (name == null || name.trim().length() == 0) {
            KWLog.d("[錯誤] 角色（" + identifier +"）的名字不能設定為空");
            return this;
        }
        this.name = name.trim();
        return this;
    }

    public int getAction() {
        return action;
    }

    public KWCharacterModel setAction(int action) {
        this.action = action;
        return this;
    }

    public int getType() {
        return type;
    }

    public KWCharacterModel setType(int type) {
        this.type = type;
        return this;
    }

    protected String getResourceNameByActionAndType(int action, int type) {
        //先將前贅詞填入檔名
        String prefix = identifier;
        String actionName = getActionName(action);
        String typeName = getTypeName(type);

        String resourceName;

        if (prefix.length() == 0 || actionName.length() == 0 || typeName.length() == 0) {
            //因為無法正確識別 identifier 或是 action 或是 typeName，所以使用預設的圖片名稱
            KWLog.d("[錯誤] 無法正確識別 identifier 或是 action 或是 typeName");
            return getDefaultResourceName();
        }

        resourceName = prefix + "_" + actionName + "_" + typeName;
        KWLog.d("[資訊] 角色（" + identifier +"）的圖片名稱為" + resourceName);

        return resourceName;
    }

    protected String getActionName(int action) {

        String actionName;

        switch (action) {
            case KW_CHARACTER_ACTION_DEFAULT:
                actionName = "general";
                break;
            case KW_CHARACTER_ACTION_ANGRY:
                actionName = "angry";
                break;
            case KW_CHARACTER_ACTION_CRY:
                actionName = "cry";
                break;
            case KW_CHARACTER_ACTION_EMBARRASS:
                actionName = "embarrass";
                break;
            case KW_CHARACTER_ACTION_EUPHORIC:
                actionName = "euphoric";
                break;
            case KW_CHARACTER_ACTION_GENERAL:
                actionName = "general";
                break;
            case KW_CHARACTER_ACTION_HAPPY:
                actionName = "happy";
                break;
            case KW_CHARACTER_ACTION_MOODY:
                actionName = "moody";
                break;
            case KW_CHARACTER_ACTION_SHY:
                actionName = "shy";
                break;
            case KW_CHARACTER_ACTION_SURPRISE:
                actionName = "surprise";
                break;
            default:
                actionName = "";
                break;
        }
        return actionName;
    }

    protected String getTypeName(int type) {

        String typeName;

        switch (type) {
            case KW_CHARACTER_TYPE_CASUAL:
                typeName = "a";
                break;
            case KW_CHARACTER_TYPE_UNIFORM:
                typeName = "b";
                break;
            default:
                typeName = "";
                break;
        }
        return typeName;
    }

    protected String getDefaultResourceName() {
        String resourceName;

        //所以透過亂數隨機選擇預設的男性或是女性的圖片檔名（這邊事實上也可以不需要用亂數2選1，直接寫死就好了，但因為我兩張圖都做了，所以....）
        int r = (int)Math.round(Math.random() * 10);
        resourceName = (r % 2) == 0 ? "kw_male_default" : "kw_female_default";
        KWLog.d("[資訊] 使用" + resourceName + "作為角色（" + identifier +"）預設的圖片名稱");

        return resourceName;
    }

    protected Drawable getDrawable(){
        return getDrawableByActionAndType(this.action, this.type);
    }

    protected Drawable getDrawableByAction(int action){
        return getDrawableByActionAndType(action, this.type);
    }

    protected Drawable getDrawableByActionAndType(int action, int type){
        String resourceName = getResourceNameByActionAndType(action, type);
        Drawable drawable = null;

        try {
            drawable = KWResourceUtils.getDrawableByResourceName(this.context, resourceName);
        }
        catch (Exception ex) {
            KWLog.d("[錯誤] 無法例外錯誤：" +  ex.toString());
        }

        if (drawable == null) {
            KWLog.d("[錯誤] 無法找到名稱為" + resourceName + "的檔案，將使用預設的圖片名稱作為角色（" + identifier +"）預設的圖片");
            drawable = KWResourceUtils.getDrawableByResourceName(this.context, getDefaultResourceName());
        }

        return drawable;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
