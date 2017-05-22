package pro.vistar.anekdots200k.data.sqlite.cache;

import java.util.LinkedList;

import pro.vistar.anekdots200k.data.sqlite.entity.ThemeEntity;

public class ThemeSetCache {
    private static ThemeSetCache sInstance;
    public static LinkedList<ThemeEntity> themeEntitiesSet;

    ThemeSetCache(LinkedList<ThemeEntity> themeEntities) {
        themeEntitiesSet = themeEntities;
    }

    public static synchronized ThemeSetCache getInstance(LinkedList<ThemeEntity> themeEntities) {
        if (sInstance == null) {
            sInstance = new ThemeSetCache(themeEntities);
        }
        return sInstance;
    }
}
