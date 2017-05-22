package pro.vistar.anekdots200k.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import pro.vistar.anekdots200k.App;

/**
 * This class is display params helper
 */
final public class DisplayUtils {

    /**
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDefaultDisplayMetrics() {
        WindowManager windowManager = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /**
     * @return Orientation name in String
     */
    public static String getOrientationName() {
        App sApp = (App) App.getApplication();
        Activity currentActivity = sApp.getCurrentActivity();

        String orientation = "unknown";
        if (currentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientation = "PORTRAIT";
        }
        if (currentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientation = "LANDSCAPE";
        }
        if (currentActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_UNDEFINED) {
            orientation = "UNDEFINED";
        }
        return orientation;
    }

    /**
     * Convert dp to px
     *
     * @param dp
     * @return px
     */
    public static int dpToPx(int dp) {
        return (int) (dp * getDefaultDisplayMetrics().density);
    }

    /**
     * Convert px to dp
     *
     * @param px
     * @return dp
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * This method calculate the best menu columns count
     *
     * @return menu column count
     */
    public static int getDefaultMenuColumns() {
        return (int) Math.floor(DisplayUtils.getDefaultDisplayMetrics().widthPixels / DisplayUtils.getDefaultDisplayMetrics().densityDpi);
    }
}
