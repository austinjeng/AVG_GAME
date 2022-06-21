package tw.edu.scu.avgexample.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;
import tw.edu.scu.avgexample.R;
import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.library.KWTypewriterTextView;

public class KWMessageDialog {

    private Context context;
    protected KWBaseDialog dialog;

    private DialogInterface.OnClickListener onClickListener;

    public String name = "";
    public String message = "";
    public String colorString = "#FFFFFF";

    private KWTypewriterTextView typewriterTextView;
    private GifImageView dialogHintIGifmageView;

    //TODO: 可以開放出來給開發者做文字速度的調整（通常會多一個設定頁面在裡面做處理）
    private int characterDelay = 50;

    public KWMessageDialog(Context context){
        this.context = context;
        initDialog();
    }

    protected void initDialog() {
        this.dialog = new KWBaseDialog(this.context, R.style.MessageDialog) {
            //[Reference] http://wangkuiwu.github.io/2015/01/03/TouchEvent-View/#anchor2_1
            public boolean dispatchTouchEvent(MotionEvent event)
            {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    KWSoundManager.sharedInstance(KWMessageDialog.this.context).playSound(R.raw.kw_sound_button_click);

                    if (KWMessageDialog.this.typewriterTextView.isFinishAnimation()) {
                        KWMessageDialog.this.onClickListener.onClick(this, 0);
                    }
                    else {
                        updateMessageImmediately();
                    }

                }
                return false;
            }
        };

        this.dialog.setContentView(R.layout.kw_message_dialog);
        updateDialogContentView();

        this.dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        this.dialog.setCancelable(false);
        this.dialog.setCanceledOnTouchOutside(false);

        this.typewriterTextView = (KWTypewriterTextView)this.dialog.findViewById(R.id.dialogMessageTextView);
        this.typewriterTextView.setCharacterDelay(this.characterDelay);

        this.typewriterTextView.setTypewriterFinishListener(new KWTypewriterTextView.KWTypewriterFinishListener() {
            @Override
            public void animationDidFinish() {
                KWMessageDialog.this.dialogHintIGifmageView.setVisibility(View.VISIBLE);
            }
        });

        this.dialogHintIGifmageView = this.dialog.findViewById(R.id.dialogHintIGifmageView);
    }

    protected void updateDialogContentView() {
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public KWMessageDialog show(String message){
        updateDialogNameAndMessage(null, message);
        return this;
    }

    public KWMessageDialog show(String name, String message){
        updateDialogNameAndMessage(name, message);
        return this;
    }

    public KWMessageDialog show(String name, String message, String colorString){
        updateDialogNameAndMessage(name, message, colorString);
        return this;
    }

    public KWMessageDialog dismiss() {
        this.dialog.dismiss();
        return this;
    }

    public void setOnClickListener(DialogInterface.OnClickListener listener) {
        this.onClickListener = listener;
    }

    private void updateDialogNameAndMessage(String name, String message) {

        updateDialogNameAndMessage(name, message, this.colorString);
    }

    private void updateDialogNameAndMessage(String name, String message, String colorString) {

        ((KWTypewriterTextView)this.dialog.findViewById(R.id.dialogMessageTextView)).setTextColor(Color.parseColor(colorString));

        KWMessageDialog.this.dialogHintIGifmageView.setVisibility(View.GONE);

        //如果傳入的名字是「null」表示沿用前一次的名字
        this.name = (name == null) ? this.name : name;
        this.message = (message == null) ? this.message : message;

        //如果傳入的名字是「空字串」表示不顯示名字顯示框
        this.dialog.findViewById(R.id.dialogNameFrameLayout).setVisibility((this.name == null || this.name.length() == 0) ? View.INVISIBLE : View.VISIBLE);
        ((TextView)this.dialog.findViewById(R.id.dialogNameTextView)).setText(this.name);
        //((TextView)this.dialog.findViewById(R.id.dialogMessageTextView)).setText(this.message);

        //原本是使用Android預設的TextView，現在改用用打字機效果的自製元件
        this.typewriterTextView.animateText(this.message);

        this.dialog.show();
    }

    private void updateMessageImmediately() {
        KWMessageDialog.this.typewriterTextView.animateTextImmediately(this.message);
        KWMessageDialog.this.dialogHintIGifmageView.setVisibility(View.VISIBLE);

    }

}
