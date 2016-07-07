package com.meizhu.a3dgame.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Kun Yu on 2016/6/22.
 */
public class FileUtils {
    public static boolean saveFile(byte[] data , String fileName){
        boolean flag = false;
        String s = Environment.getExternalStorageState();
        if (s.equals(Environment.MEDIA_MOUNTED)) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(root,fileName);
//            Log.i("aaa","file="+file.getAbsolutePath());
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data,0,data.length);
                flag = true;
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static Bitmap readAndwriteFile(){
        return null;
    }
}
