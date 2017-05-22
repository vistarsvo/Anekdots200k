package pro.vistar.anekdots200k;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Application sApplication;
    private Activity mCurrentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }
}