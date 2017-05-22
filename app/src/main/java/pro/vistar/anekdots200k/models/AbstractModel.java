package pro.vistar.anekdots200k.models;

import android.database.sqlite.SQLiteDatabase;

import pro.vistar.anekdots200k.App;
import pro.vistar.anekdots200k.Config;
import pro.vistar.anekdots200k.data.sqlite.SQLiteHelper;
import pro.vistar.anekdots200k.utils.StorageUtils;

class AbstractModel {
    private SQLiteHelper sqLiteHelper;
    SQLiteDatabase db;

    AbstractModel() {
        String path = StorageUtils.APP_DATA_DIR + '/' + Config.MAIN_DB;
        sqLiteHelper = SQLiteHelper.getInstance(App.getContext(), path,  Config.DB_VERSION);
        db = sqLiteHelper.getWritableDatabase();
    }
}
