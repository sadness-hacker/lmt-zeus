package com.lmt.zeus.parent.lang.utils;

import com.lmt.zeus.parent.client.enums.BasicExceptionEnum;
import com.lmt.zeus.parent.client.exception.BasicException;

import java.io.File;
import java.io.IOException;

/**
 * @description 文件工具类
 * @author bazhandao
 * @date 2018/11/8 17:53
 * @since JDK1.8
 */
public class FileUtils {

    /**
     * 删除目录
     * @author bazhandao
     * @date 2018-11-02
     * @param path
     */
    public static void deleteDir(String path) {
        File file = new File(path);
        if(file.exists()) {
            try {
                org.apache.commons.io.FileUtils.deleteDirectory(file);
            } catch (IOException e) {
                throw BasicException.wrap(BasicExceptionEnum.FILE_DEL_ERROR.getCode(), BasicExceptionEnum.FILE_DEL_ERROR.getMsg(), e);
            }
        }
    }

    /**
     * 创建目录
     * @author bazhandao
     * @date 2018-11-02
     * @param path
     */
    public static void mkdirs(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

}
