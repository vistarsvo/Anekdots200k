package pro.vistar.anekdots200k.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pro.vistar.anekdots200k.Config;
import pro.vistar.anekdots200k.utils.StorageUtils;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static SQLiteHelper sInstance;

    private SQLiteHelper(Context context, String path, int version) {
        super(context, path, null, version);
    }

    public static synchronized SQLiteHelper getInstance(Context context, String path, int version) {
        if (sInstance == null) {
            sInstance = new SQLiteHelper(context, path, version);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}