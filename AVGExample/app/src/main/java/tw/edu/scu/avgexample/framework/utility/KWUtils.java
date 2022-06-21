package tw.edu.scu.avgexample.framework.utility;

import android.app.Activity;
import android.content.Intent;
import android.view.ViewGroup;

public class KWUtils {

    public static ViewGroup getRootViewFromActivity(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        return viewGroup;
    }

    public static void switchActivity(Activity currentActivity, Class targetActivityClass) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        intent.setClass(currentActivity.getApplicationContext(), targetActivityClass);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

}
