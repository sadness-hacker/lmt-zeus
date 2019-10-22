package com.lmt.zeus.parent.utils;

import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;

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
                throw ZeusException.wrap(ZeusExceptionEnum.FILE_DEL_ERROR.getCode(), ZeusExceptionEnum.FILE_DEL_ERROR.getMsg(), e);
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
