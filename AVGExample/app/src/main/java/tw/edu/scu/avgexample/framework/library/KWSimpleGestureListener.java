//[Reference] https://www.jianshu.com/p/54822d760ef7
package tw.edu.scu.avgexample.framework.library;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import tw.edu.scu.avgexample.framework.KWEventManager;
import tw.edu.scu.avgexample.framework.model.KWEventModel;

public class KWSimpleGestureListener implements GestureDetector.OnGestureListener {

    public interface OnGestureListener {
        void onDetectedGesture(int type);
    }

    protected static final float FLIP_DISTANCE = 50;

    public static final int KW_GESTURE_DOWN = 1;
    public static final int KW_GESTURE_SHOW_PRESS = 2;
    public static final int KW_GESTURE_SINGLE_TAP_UP = 3;
    public static final int KW_GESTURE_SCROLL = 4;
    public static final int KW_GESTURE_LONG_PRESS = 5;
    public static final int KW_GESTURE_SWIPE_LEFT = 6;
    public static final int KW_GESTURE_SWIPE_RIGHT = 7;
    public static final int KW_GESTURE_SWIPE_UP = 8;
    public static final int KW_GESTURE_SWIPE_DOWN = 9;

    private OnGestureListener gestureListener;

    @Override
    public boolean onDown(MotionEvent e) {
        triggerGestureListener(KW_GESTURE_DOWN);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        triggerGestureListener(KW_GESTURE_SHOW_PRESS);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        triggerGestureListener(KW_GESTURE_SINGLE_TAP_UP);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        triggerGestureListener(KW_GESTURE_SCROLL);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        triggerGestureListener(KW_GESTURE_LONG_PRESS);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
            Log.i("MYTAG", "向左滑...");
            triggerGestureListener(KW_GESTURE_SWIPE_LEFT);
            return true;
        }
        if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
            Log.i("MYTAG", "向右滑...");
            triggerGestureListener(KW_GESTURE_SWIPE_RIGHT);
            return true;
        }
        if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
            Log.i("MYTAG", "向上滑...");
            triggerGestureListener(KW_GESTURE_SWIPE_UP);
            return true;
        }
        if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
            Log.i("MYTAG", "向下滑...");
            triggerGestureListener(KW_GESTURE_SWIPE_DOWN);
            return true;
        }

        return false;
    }

    public void setOnGestureListener(OnGestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    private void triggerGestureListener(int type) {
        if (this.gestureListener != null) {
            this.gestureListener.onDetectedGesture(type);
        }
    }
}
