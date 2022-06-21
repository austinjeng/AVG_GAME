package tw.edu.scu.avgexample.framework.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class KWResourceUtils {

    public static  Drawable getEmptyDrawable() {
        return new ColorDrawable(Color.TRANSPARENT);
    }

    public static Drawable getDrawableByResourceId(Context context, int resourceId) {
        return  context.getResources().getDrawable(resourceId);
    }

    public static Drawable getDrawableByResourceName(Context context, String resourceName) {
        return  context.getResources().getDrawable(KWResourceUtils.getDrawableResourceId(context, resourceName));
    }

    public static int getDrawableResourceId(Context context, String variableName) {
        try {
            return KWResourceUtils.getResourceId(context, variableName, "drawable", context.getPackageName());
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getResourceId(Context context, String variableName, String resourceName, String packageName) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(variableName, resourceName, packageName);
        } catch (Exception e) {
            throw new RuntimeException("Error getting Resource ID.", e);
        }
    }

}
