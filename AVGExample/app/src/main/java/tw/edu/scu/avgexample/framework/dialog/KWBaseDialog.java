package tw.edu.scu.avgexample.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

public class KWBaseDialog extends Dialog {

    public KWBaseDialog(Context context) {
        super(context);
    }

    public KWBaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected KWBaseDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void show() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        super.show();
    }
}
