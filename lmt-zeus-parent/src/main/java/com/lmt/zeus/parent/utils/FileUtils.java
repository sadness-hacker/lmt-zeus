package com.lmt.zeus.parent.utils;

import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

    /**
     * 读取文件中所有内容为字符串
     * @author bazhandao
     * @date 2019-11-17
     * @param path
     * @return
     */
    public static String readAll(String path) {
        try {
            List<String> lineList = Files.readAllLines(Paths.get(path));
            StringBuilder sb = new StringBuilder();
            lineList.forEach(s -> sb.append(s));
            return sb.toString();
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.FILE_READ_ERROR.getCode(), ZeusExceptionEnum.FILE_READ_ERROR.getMsg(), e);
        }
    }

    /**
     * 写入内容到指定文件
     * @author bazhandao
     * @date 2019-11-17
     * @param path
     * @param content
     */
    public static void write(String path, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(content);
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.FILE_WRITE_ERROR.getCode(), ZeusExceptionEnum.FILE_WRITE_ERROR.getMsg(), e);
        }
    }
}
