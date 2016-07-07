package com.meizhu.a3dgame.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kun Yu on 2016/6/22.
 */
public class HttpUtils {
    public static byte[] request(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式
            conn.setRequestMethod("GET");
            //设置输入流
            conn.setDoInput(true);
            //连接
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = is.read(data))!=-1)
                {
                    baos.write(data,0,len);
                }
                is.close();
                return baos.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
