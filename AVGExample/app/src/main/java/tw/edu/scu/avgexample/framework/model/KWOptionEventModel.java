package tw.edu.scu.avgexample.framework.model;

import java.util.ArrayList;

public class KWOptionEventModel extends KWEventModel {
    //TODO: 觸發事件時，彈出可選擇的Dialog（開發者自行客製化的視窗），並根據對應的選項，跳轉至對應的頁面

    private String identifier;
    private String hint;
    private ArrayList<String> optionTitleArrayList;

    public KWOptionEventModel(String identifier, String hint, ArrayList<String> optionTitleArrayList) {
        this.identifier = identifier;
        this.hint = hint;
        this.optionTitleArrayList = optionTitleArrayList;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getHint() {
        return this.hint;
    }

    public ArrayList<String> getOptionTitleArrayList() {
        return this.optionTitleArrayList;
    }
}
