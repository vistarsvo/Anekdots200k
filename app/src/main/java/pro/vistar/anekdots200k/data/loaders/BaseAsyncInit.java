package pro.vistar.anekdots200k.data.loaders;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pro.vistar.anekdots200k.App;
import pro.vistar.anekdots200k.Config;
import pro.vistar.anekdots200k.R;
import pro.vistar.anekdots200k.activities.LoadingActivity;
import pro.vistar.anekdots200k.data.sqlite.cache.ThemeSetCache;
import pro.vistar.anekdots200k.models.CensorModel;
import pro.vistar.anekdots200k.data.sqlite.cache.CensorSetCache;
import pro.vistar.anekdots200k.models.ThemeModel;
import pro.vistar.anekdots200k.utils.StorageUtils;

/**
 * This class copy main database to app storage
 * After finish copy call showNextScreen() from caller Activity
 */
public class BaseAsyncInit extends AsyncTask<Object, Object, Void>
{
    private String hasError = "";
    private boolean needCopyDb;

    public BaseAsyncInit(boolean needCopyDb) {
        this.needCopyDb = needCopyDb;
    }

    @Override
    protected void onPreExecute()
    {
        App sApp = (App) App.getApplication();
        LoadingActivity loadingActivity = (LoadingActivity) sApp.getCurrentActivity();

        loadingActivity.progressBar.setMax(100);
        loadingActivity.progressBar.setVisibility(View.VISIBLE);
        loadingActivity.progressText.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Object[] objects) {
            synchronized (this)
            {
                App sApp = (App) App.getApplication();
                Activity loadingActivity = sApp.getCurrentActivity();

                // If need copy db file, then start copy
                if (this.needCopyDb) {
                    try {
                        InputStream myInput = loadingActivity.getAssets().open(Config.MAIN_DB);
                        String outFileName = StorageUtils.APP_DATA_DIR + Config.MAIN_DB;
                        OutputStream myOutput = new FileOutputStream(outFileName);
                        byte[] buffer = new byte[16 * 1024]; //16 kbyte
                        int length;
                        int counter = 0;
                        while ((length = myInput.read(buffer)) > 0) {
                            myOutput.write(buffer, 0, length);
                            publishProgress(counter / (5326 / 100), loadingActivity.getResources().getString(R.string.initDB));
                            counter++;
                            //System.out.println("COUNTER: " + Integer.toString(counter));
                        }
                        myOutput.flush();
                        myOutput.close();
                        myInput.close();
                    } catch (IOException e) {
                        hasError = e.getMessage();
                    }
                }
                // Censor Data
                publishProgress(0, loadingActivity.getResources().getString(R.string.readCensorDB));
                CensorModel censorModel = new CensorModel();
                CensorSetCache.getInstance(censorModel.getAll());
                // Categories Data
                publishProgress(30, loadingActivity.getResources().getString(R.string.readThemesDB));
                ThemeModel themeModel = new ThemeModel();
                ThemeSetCache.getInstance(themeModel.getAllActive(true));
                //publishProgress(60, this.loadingActivity.getResources().getString(R.string.updateThemesDB));

                //TODO reColorized drawables

            }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values)
    {
        if((Integer)values[0] <= 100)
        {
            App sApp = (App) App.getApplication();
            LoadingActivity loadingActivity = (LoadingActivity) sApp.getCurrentActivity();
            String progressTest = String.valueOf(values[1]) + ": " + Integer.toString((Integer)values[0]) + "%";

            loadingActivity.progressText.setText(progressTest);
            loadingActivity.progressBar.setProgress((Integer)values[0]);
        }
    }

    @Override
    protected void onPostExecute(Void o)
    {
        // Hide UI progress elements and goto next screen or who error
        App sApp = (App) App.getApplication();
        LoadingActivity loadingActivity = (LoadingActivity) sApp.getCurrentActivity();

        loadingActivity.progressBar.setVisibility(View.INVISIBLE);
        loadingActivity.progressText.setVisibility(View.INVISIBLE);
        if (!hasError.equals("")) {
            loadingActivity.showErrorAlert(hasError);
        } else {
            loadingActivity.goNextScreen();
        }
    }
}
