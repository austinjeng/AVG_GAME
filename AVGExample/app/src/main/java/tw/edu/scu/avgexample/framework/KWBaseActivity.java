package tw.edu.scu.avgexample.framework;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import tw.edu.scu.avgexample.framework.utility.KWLog;

public class KWBaseActivity extends AppCompatActivity {

    public interface KWOnExitListener {
        void onExit();
    }

    private boolean isConfirmExit = false;
    protected String confirmString = "再按一次返回鍵，將回到主選單畫面";

    private KWOnExitListener onExitListener;


    @Override
    public void onBackPressed() {
        exitWithConfirmOnBackPressed();
    }

    private void exitWithConfirmOnBackPressed() {

        if (this.isConfirmExit)

            if (this.onExitListener != null) {
                this.onExitListener.onExit();
            }
            else {
                super.onBackPressed();
            }

        else {
            this.isConfirmExit = true;
            Toast.makeText(this, confirmString, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    KWLog.d("[資訊] 確認返回逾時，將取消此次的返回事件");
                    isConfirmExit = false;
                }
            }, 2000);
        }
    }

    protected void setOnExitListener(KWOnExitListener onExitListener) {
        this.onExitListener = onExitListener;
    }


}
