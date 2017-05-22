package pro.vistar.anekdots200k.models;

import android.database.Cursor;
import java.util.LinkedList;
import pro.vistar.anekdots200k.data.sqlite.entity.ThemeEntity;

public class ThemeModel extends AbstractModel {

    // Get all censor entities
    public LinkedList<ThemeEntity> getAllActive(boolean needSortBySRT) {
        String query = "SELECT id, active, cnt, fullname, shortname, srt, theme_id FROM themes WHERE active = 1 ";
        if (needSortBySRT) query += " ORDER by srt ";
        Cursor cursor = db.rawQuery(query, null);
        LinkedList<ThemeEntity> themeItemCollection = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                ThemeEntity themeEntity = new ThemeEntity();
                themeEntity.setId(cursor.getInt(0));
                themeEntity.setActive(cursor.getInt(1) == 1);
                themeEntity.setCnt(cursor.getInt(2));
                themeEntity.setFullname(cursor.getString(3));
                themeEntity.setShortname(cursor.getString(4));
                themeEntity.setTheme_id(cursor.getInt(6));
                themeItemCollection.add(themeEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return themeItemCollection;
    }
}

