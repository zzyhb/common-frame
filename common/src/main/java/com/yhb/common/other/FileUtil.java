package com.yhb.common.other;

import java.io.File;
import java.io.InputStream;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }
    /**
     * 解密
     * @param base64Str
     * @return
     */
    public static byte[] base64Decoder(String base64Str) {
        if (NullUtil.isNullTrim(base64Str)) {
            return null;
        }
        // 解密
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] b = decoder.decode(base64Str);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
