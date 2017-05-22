package pro.vistar.anekdots200k.utils;

import android.os.Build;
import android.os.StatFs;

import java.io.File;
import java.io.IOException;

import pro.vistar.anekdots200k.App;

/**
 * Storage helper.
 * - check/delete file
 * - check space on storage
 */
public class StorageUtils {
    /**
     * App data directory
     */
    public static String APP_DATA_DIR = App.getContext().getApplicationInfo().dataDir + "/";


    /**
     * Check is file exists
     *
     * @param fileName
     * @return boolean
     */
    public static boolean checkFileExists(String fileName) {
        File curFile = new File(APP_DATA_DIR + fileName);
        return curFile.exists();
    }

    /**
     * Try to delete file if file exists
     * If file does not exists function return false
     *
     * @param fileName
     * @return boolean
     * @throws IOException ioException
     */
    public static boolean deleteFile(String fileName) throws IOException {
        File curFile = new File(APP_DATA_DIR + fileName);
        if (curFile.exists()) {
            return curFile.delete();
        } else {
            return false;
        }
    }

    /**
     * Check free space on storage by path param
     *
     * @param path
     * @return available free space in bytes
     */
    public static long getAvailableSpaceForData(String path) {
        long availableSpace = -1L;
        try {
            StatFs stat = new StatFs(path);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                availableSpace = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
            } else {
                availableSpace = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableSpace;
    }

    /**
     * Get free space on storage by path param and convert to MB
     *
     * @param path
     * @return available free space in megabytes
     */
    public static long getAvailableSpaceForDataInMB(String path) {
        long availableSpace = StorageUtils.getAvailableSpaceForData(path);
        return Math.round(availableSpace / 1024 / 1024);
    }
}
