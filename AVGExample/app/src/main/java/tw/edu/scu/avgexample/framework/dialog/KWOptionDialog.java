package tw.edu.scu.avgexample.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class KWOptionDialog {

    public interface KWOptionDialogOptionListener {
        public void onOptionSelected(String identifier, int index);
    }

    final private static int MIN_OPTION_COUNT = 2;
    final private static int MAX_OPTION_COUNT = 4;

    private Context context;
    private KWBaseDialog dialog;

    private ArrayList<Button> optonButtonArrayList = new ArrayList<>();

    private String identifier;
    private String hint;
    private ArrayList<String> optionTitleArrayList = new ArrayList<>();

    private KWOptionDialogOptionListener listener;

    public KWOptionDialog(Context context, String identifier){
        this.context = context;
        this.identifier = identifier;
        initDialog();
    }

    protected void initDialog() {
        this.dialog = new KWBaseDialog(this.context, R.style.OptionDialog);

        this.dialog.setContentView(R.layout.kw_option_dialog);
        this.dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        this.dialog.setCancelable(false);
        this.dialog.setCanceledOnTouchOutside(false);

        this.optonButtonArrayList.add((Button)this.dialog.findViewById(R.id.option1Button));
        this.optonButtonArrayList.add((Button)this.dialog.findViewById(R.id.option2Button));
        this.optonButtonArrayList.add((Button)this.dialog.findViewById(R.id.option3Button));
        this.optonButtonArrayList.add((Button)this.dialog.findViewById(R.id.option4Button));

        for (int i = 0; i < this.optonButtonArrayList.size(); i++) {

            final int index = i;

            this.optonButtonArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (KWOptionDialog.this.listener != null) {

                        KWSoundManager.sharedInstance(KWOptionDialog.this.context).playSound(R.raw.kw_sound_button_click);

                        KWOptionDialog.this.listener.onOptionSelected(KWOptionDialog.this.identifier, index);
                        KWOptionDialog.this.dialog.dismiss();
                    }
                }
            });
        }
    }

    protected void updateDialogContentView() {
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public KWOptionDialog show(String hint, ArrayList<String> optionArrayList){
        updateHintAndOptions(hint, optionArrayList);
        return this;
    }


    public KWOptionDialog dismiss() {
        this.dialog.dismiss();
        return this;
    }

    private void updateHintAndOptions(String hint, ArrayList<String> optionTitleArrayList) {

        this.hint = (hint == null) ? this.hint : hint;
        this.optionTitleArrayList = (optionTitleArrayList == null) ? this.optionTitleArrayList : optionTitleArrayList;

        ((TextView)this.dialog.findViewById(R.id.optionHintTextView)).setText(this.hint);

        for (int i = 0; i < this.optonButtonArrayList.size(); i++) {
            this.optonButtonArrayList.get(i).setVisibility(View.GONE);
        }

        for (int i = 0; i < this.optionTitleArrayList.size(); i++) {
            String optionTitle =this.optionTitleArrayList.get(i);

            this.optonButtonArrayList.get(i).setVisibility(View.VISIBLE);
            this.optonButtonArrayList.get(i).setText(optionTitle);
        }

        this.dialog.show();
    }

    public void setOnOptionSelectedListener(KWOptionDialogOptionListener listener) {
        this.listener = listener;
    }


}
