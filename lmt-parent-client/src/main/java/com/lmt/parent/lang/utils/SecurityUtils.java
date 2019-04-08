package com.lmt.parent.lang.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.lmt.parent.client.enums.BasicExceptionEnum;
import com.lmt.parent.client.exception.BasicException;

import java.io.File;
import java.io.IOException;

/**
 * @description 加密工具类
 * @author bazhandao
 * @date 2018/11/8 17:51
 * @since JDK1.8
 */
public class SecurityUtils {

    /**
     * 获取字符串的md5值 32位小写
     * @author bazhandao
     * @date 2018-11-10
     * @param str
     * @return
     */
    public static String md5(String str) {
        @SuppressWarnings("deprecation")
        String md5 = Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
        return md5;
    }

    /**
     * 获取字符串的md5值 16位小写
     * @author bazhandao
     * @date 2018-11-10
     * @param str
     * @return
     */
    public static String shortMd5(String str) {
        return md5(str).substring(8, 24);
    }

    /**
     * 获取文件md5值
     * @author bazhandao
     * @date 2018-11-10
     * @param filePath
     * @return
     */
    public static String getFileMd5(String filePath) {
        return getFileMd5(new File(filePath));
    }

    /**
     * 获取文件md5值
     * @author bazhandao
     * @date 2018-11-10
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        try {
            @SuppressWarnings("deprecation")
            String md5 = Files.hash(file, Hashing.md5()).toString();
            return md5;
        } catch (IOException e) {
            throw BasicException.wrap(BasicExceptionEnum.GEN_FILE_MD5_ERR.getCode(), BasicExceptionEnum.GEN_FILE_MD5_ERR.getMsg(), e)
                .set("file", file);
        }
    }

}
