package com.lmt.parent.lang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @description Property文件工具类
 * @author bazhandao
 * @date 2018/11/8 17:53
 * @since JDK1.8
 */
public class PropertiesUtil {

    protected static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 根据文件路径读取配置文件到map
     * @author bazhandao
     * @date 2018-11-10
     * @param propertiesFile
     * @return
     */
    public static Map read(String propertiesFile) {
        ResourceBundle rb = ResourceBundle.getBundle(propertiesFile);
        Map<String, String> map = new HashMap();
        Enumeration enu = rb.getKeys();
        while (enu.hasMoreElements()) {
            String key = String.valueOf(enu.nextElement());
            String value = String.valueOf(rb.getObject(key));
            if(logger.isDebugEnabled()) {
                logger.debug("property ["+ key +"]:"+ value);
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * 读取String类型值
     * @param propertiesFile
     * @param key
     * @return
     */
    public static String getPropertyValue(String propertiesFile,String key){
        return (String)read(propertiesFile).get(key);
    }

    /**
     * 读取int类型值
     * @param propertiesFile
     * @param key
     * @return
     */
    public static int getIntValue(String propertiesFile,String key){
        return Integer.valueOf((String)read(propertiesFile).get(key));
    }

    /**
     * 读取long类型值
     * @param propertiesFile
     * @param key
     * @return
     */
    public static long getLongValue(String propertiesFile,String key){
        return Long.valueOf((String)read(propertiesFile).get(key));
    }

    /**
     * 读取double类型值
     * @param propertiesFile
     * @param key
     * @return
     */
    public static double getDoubleValue(String propertiesFile,String key){
        return Double.valueOf((String)read(propertiesFile).get(key));
    }

    /**
     * 读取boolean类型值
     * @param propertiesFile
     * @param key
     * @return
     */
    public static boolean getBooleanValue(String propertiesFile,String key){
        return Boolean.valueOf((String)read(propertiesFile).get(key));
    }

}
