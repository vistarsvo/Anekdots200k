package pro.vistar.anekdots200k.data.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import pro.vistar.anekdots200k.Config;

/**
 * This is wrapper for manage app preferences
 */
public class AppSharedPreferences {
    /**
     * Key name in preferences
     * Is this a not first run an App? False if first, true if not...
     */
    public static final String IS_NOT_FIRST_RUN = "isNotFirstRun";
    /**
     * Key name in preferences
     * Is swipe gestures need to go prev-next joke
     */
    public static final String NEED_SWIPE_GESTURES = "needSwipeGestures";
    /**
     * Key name in preferences
     * Is need PinchZoom gestures for text content while reading the joke
     */
    public static final String NEED_ZOOM_GESTURES = "needZoomGestures";
    /**
     * Key name in preferences
     * Check the joke by Censor
     */
    public static final String NEED_CENSOR = "needCensor";
    /**
     * Key name in preferences
     * Skin name for app
     */
    public static final String APP_SKIN = "appSkin";
    /**
     * Key name in preferences
     * Save menu count columns by user in app options
     */
    public static final String PORTRAIT_MENU_COLUMNS = "portraitMenuColumns";
    /**
     * Key name in preferences
     * Save menu chapter font size by user in app options
     */
    public static final String PORTRAIT_MENU_FONT_SIZE = "portraitMenuFontSize";
    /**
     * Key name in preferences
     * Save menu count columns by user in app options
     */
    public static final String ALBUM_MENU_COLUMNS = "albumMenuColumns";
    /**
     * Key name in preferences
     * Save menu chapter font size by user in app options
     */
    public static final String ALBUM_MENU_FONT_SIZE = "albumMenuFontSize";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;

    public AppSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Config.APP_PACKAGE_NAME, Activity.MODE_PRIVATE);
        this.preferencesEditor = sharedPreferences.edit();
    }

    /**
     * Get string value by settings key name
     *
     * @param settingName
     * @return value or null
     */
    public String getStringSetting(String settingName) {
        return sharedPreferences.getString(settingName, null);
    }

    /**
     * Save string value in preferences storage
     *
     * @param settingName key name
     * @param settingValue value
     * @return boolean
     */
    public boolean setStringSetting(String settingName, String settingValue) {
        preferencesEditor.putString(settingName, settingValue);
        return  preferencesEditor.commit();
    }

    /**
     * Get integer value by settings key name
     *
     * @param settingName
     * @return integer value or 0 as default
     */
    public int getIntSetting(String settingName) {
        return sharedPreferences.getInt(settingName, 0);
    }

    /**
     * Save integer value in preferences storage
     *
     * @param settingName key name
     * @param settingValue value
     * @return boolean
     */
    public boolean setIntSetting(String settingName, int settingValue) {
        preferencesEditor.putInt(settingName, settingValue);
        return preferencesEditor.commit();
    }

    /**
     * Get bool value by settings key name
     *
     * @param settingName
     * @return bool value or false as default
     */
    public boolean getBooleanSetting(String settingName) {
        return sharedPreferences.getBoolean(settingName, false);
    }

    /**
     * Save boolean value in preferences storage
     *
     * @param settingName key name
     * @param settingValue value
     * @return boolean
     */
    public boolean setBooleanSetting(String settingName, boolean settingValue) {
        preferencesEditor.putBoolean(settingName, settingValue);
        return preferencesEditor.commit();
    }
}
