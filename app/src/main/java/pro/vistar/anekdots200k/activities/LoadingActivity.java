package pro.vistar.anekdots200k.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import pro.vistar.anekdots200k.App;
import pro.vistar.anekdots200k.Config;
import pro.vistar.anekdots200k.R;
import pro.vistar.anekdots200k.data.loaders.BaseAsyncInit;
import pro.vistar.anekdots200k.data.preferences.AppSharedPreferences;
import pro.vistar.anekdots200k.utils.DisplayUtils;
import pro.vistar.anekdots200k.utils.StorageUtils;

public class LoadingActivity extends AppCompatActivity {
    protected App sApp;
    public AppSharedPreferences appSharedPreferences;
    public TextView progressText;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        appSharedPreferences = new AppSharedPreferences(this);

        progressText = (TextView) findViewById(R.id.progressText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Remember current Activity
        sApp = (App) this.getApplicationContext();
        sApp.setCurrentActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            //TODO remove always clean DB
            StorageUtils.deleteFile(Config.MAIN_DB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Check DB exists. If not - then copy
        if (!StorageUtils.checkFileExists(Config.MAIN_DB)) {
            if (StorageUtils.getAvailableSpaceForDataInMB(StorageUtils.APP_DATA_DIR) < 100L) {
                showErrorAlert(getResources().getString(R.string.errorFreeSpace));
            } else {
                new BaseAsyncInit(true).execute();
            }
        } else {
            new BaseAsyncInit(false).execute();
        }
    }

    /**
     * Show an error Alert Dialog
     *
     * @param error String with a message
     */
    public void showErrorAlert(String error) {
        new AlertDialog.Builder(LoadingActivity.this)
                .setTitle(getResources().getString(R.string.errorTitle))
                .setMessage(error)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    /**
     * This method calls by .data.loaders.BaseAsyncInit when main DB copy to storage
     * and load data for App
     * Then go to Main Menu or Intro screen
     */
    public void goNextScreen() {
        Intent intent;
        if (!appSharedPreferences.getBooleanSetting(AppSharedPreferences.IS_NOT_FIRST_RUN)) {
            // is this a First Run APP
            //TODO Set to IntroScreen && Make Intro Screen
            intent = new Intent(this, MenuActivity.class);
            // save preference that is not first run for skip intro screen
            appSharedPreferences.setBooleanSetting(AppSharedPreferences.IS_NOT_FIRST_RUN, true);
        } else {
            // is this Not a First Run
            intent = new Intent(this, MenuActivity.class);
        }
        this.startActivity(intent);
        this.finish();
    }
}
