package tw.edu.scu.avgexample.framework.utility;


import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Size;
import android.util.TypedValue;
import android.view.WindowManager;

import tw.edu.scu.avgexample.framework.KWAppConstants;

public class KWScreenUtils {

    // Custom method to get screen width in dp/dip using Context object
    public static int getScreenWidthInDPs(Context context){

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);// .getMetrics(dm);

        int widthInDP = Math.round(dm.widthPixels / dm.density);
        return widthInDP;
    }

    // Custom method to get screen height in dp/dip using Context object
    public static int getScreenHeightInDPs(Context context) {

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);// .getMetrics(dm);

        int heightInDP = Math.round(dm.heightPixels / dm.density);
        return heightInDP;
    }

    public static int dpToPx(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float getFullScreenScaleFactor(Context context) {
        Size deivceSize = getDeviceSize(context);
        float widthFactor = (float) deivceSize.getWidth() / KWAppConstants.DEFAULT_GAME_CONTAINER_WIDTH_DP;
        float heightFactor = (float) deivceSize.getHeight() / KWAppConstants.DEFAULT_GAME_CONTAINER_HEIGHT_DP;

        return Math.max(widthFactor, heightFactor);
    }

    public static Size getDeviceSize(Context context) {
        int width = KWScreenUtils.getScreenWidthInDPs(context);
        int height = KWScreenUtils.getScreenHeightInDPs(context);

        return new Size(width, height);
    }
}
