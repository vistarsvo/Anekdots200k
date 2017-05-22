package pro.vistar.anekdots200k.data.sqlite.cache;

import java.util.LinkedList;

import pro.vistar.anekdots200k.data.sqlite.entity.CensorEntity;

public class CensorSetCache {
    private static CensorSetCache sInstance;
    public static LinkedList<CensorEntity> censorEntitiesSet;

    CensorSetCache(LinkedList<CensorEntity> censorEntities) {
        censorEntitiesSet = censorEntities;
    }

    public static synchronized CensorSetCache getInstance(LinkedList<CensorEntity> censorEntities) {
        if (sInstance == null) {
            sInstance = new CensorSetCache(censorEntities);
        }
        return sInstance;
    }
}
