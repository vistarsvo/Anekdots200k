package pro.vistar.anekdots200k.models;

import android.database.Cursor;
import java.util.LinkedList;
import pro.vistar.anekdots200k.data.sqlite.entity.CensorEntity;

public class CensorModel extends AbstractModel {
    // Get all censor entities
    public LinkedList<CensorEntity> getAll() {
        String query = "SELECT id, is_system, message FROM censor";
        Cursor cursor = db.rawQuery(query, null);
        LinkedList<CensorEntity> censorItemCollection = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                CensorEntity censorEntity = new CensorEntity();
                censorEntity.setId(cursor.getInt(0));
                censorEntity.setIs_system(cursor.getInt(1) == 1);
                censorEntity.setWord(cursor.getString(2));
                censorItemCollection.add(censorEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return censorItemCollection;
    }
}
