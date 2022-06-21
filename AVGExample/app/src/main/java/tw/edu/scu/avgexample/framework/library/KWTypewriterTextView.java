//[Reference] https://stackoverflow.com/questions/6700374/android-character-by-character-display-text-animation

package tw.edu.scu.avgexample.framework.library;

import android.content.Context;
import android.os.Handler;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;


public class KWTypewriterTextView extends AppCompatTextView {

    public interface KWTypewriterFinishListener {
        public void animationDidFinish();
    }

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500;

    private KWTypewriterFinishListener listener;

    public KWTypewriterTextView(Context context) {
        super(context);
    }

    public KWTypewriterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
            else {
                KWTypewriterTextView.this.listener.animationDidFinish();
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }

    public boolean isFinishAnimation() {
        return mIndex > mText.length();
    }

    public void animateTextImmediately(CharSequence text) {
        mText = text;
        mIndex = text.length() + 1;
        setText(text);
        mHandler.removeCallbacks(characterAdder);
        KWTypewriterTextView.this.listener.animationDidFinish();

    }

    public void setTypewriterFinishListener(KWTypewriterFinishListener listener) {
        this.listener = listener;
    }
}