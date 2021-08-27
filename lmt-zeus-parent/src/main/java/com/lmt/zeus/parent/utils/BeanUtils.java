package com.lmt.zeus.parent.utils;

import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @description Bean拷备、map转换工具类
 * @author bazhandao
 * @date 2018/11/8 17:55
 * @since JDK1.8
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * bean转为HashMap
     * @author bazhandao
     * @date 2018-11-10
     * @param obj
     * @return
     */
    public static Map<String,Object> toMap(Object obj) {
        if(obj == null) return null;
        if(obj instanceof Map) {
            Map<?,?> objMap = (Map<?, ?>) obj;
            Map<String,Object> map = new HashMap<>(objMap.size());
            for(Map.Entry<?, ?> e : objMap.entrySet()) {
                String key = String.valueOf(e.getKey());
                map.put(key, e.getValue());
            }
            return map;
        }
        PropertyDescriptor[] pds = getPropertyDescriptors(obj.getClass());
        Map<String,Object> map = new HashMap<>(pds.length);
        putToMap(obj, pds, map);
        return map;
    }

    /**
     * bean转为TreeMap
     * @author bazhandao
     * @date 2018-11-10
     * @param obj
     * @return
     */
    public static TreeMap<String, Object> toTreeMap(Object obj) {
        if(obj == null) return null;
        if(obj instanceof Map) {
            TreeMap<String,Object> map = new TreeMap<>();
            Map<?,?> objMap = (Map<?, ?>) obj;
            for(Map.Entry<?, ?> e : objMap.entrySet()) {
                String key = String.valueOf(e.getKey());
                map.put(key, e.getValue());
            }
            return map;
        }
        PropertyDescriptor[] pds = getPropertyDescriptors(obj.getClass());
        TreeMap<String,Object> map = new TreeMap<>();
        putToMap(obj, pds, map);
        return map;
    }

    /**
     * 对象中的字段放入map中
     * @param obj
     * @param pds
     * @param map
     */
    private static void putToMap(Object obj, PropertyDescriptor[] pds, Map<String, Object> map) {
        for (PropertyDescriptor pd : pds) {
            if(pd.getReadMethod() == null) {
                continue;
            }
            Method rm = pd.getReadMethod();
            if (!Modifier.isPublic(rm.getDeclaringClass().getModifiers())) {
                rm.setAccessible(true);
            }
            try {
                Object value = rm.invoke(obj);
                //排除getClass方法
                if(obj.getClass().equals(value)) {
                    continue;
                }
                String key = pd.getName();
                map.put(key, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not get properties from source", ex);
            }
        }
    }

    /**
     * 从map中复制属性对对象
     * @author bazhandao
     * @date 2018-11-10
     * @param map
     * @param target
     */
    public static void copyProperties(Map<String,Object> map, Object target) {
        if(map == null || target == null || map.isEmpty()){
            return;
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if(targetPd.getWriteMethod() == null) {
                continue;
            }
            try {
                String key = targetPd.getName();
                Object value = map.get(key);
                // 这里判断以下value是否为空
                setValue(target, targetPd, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not copy properties from source to target", ex);
            }
        }
    }

    /**
     * bean copy不复制null值
     * @author bazhandao
     * @date 2018-11-10
     * @param source
     * @param target
     */
    public static void copyPropertiesWithoutNull(Object source, Object target) {
        if(source == null || target == null){
            return;
        }
        Class<?> actualEditable = target.getClass();
        Class<?> sourceClass = source.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if(targetPd.getWriteMethod() == null) {
                continue;
            }
            PropertyDescriptor sourcePd = getPropertyDescriptor(sourceClass, targetPd.getName());
            if(sourcePd == null || sourcePd.getReadMethod() == null) {
                continue;
            }
            try {
                Method readMethod = sourcePd.getReadMethod();
                if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                    readMethod.setAccessible(true);
                }
                Object value = readMethod.invoke(source);
                setValue(target, targetPd, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not copy properties from source to target", ex);
            }
        }
    }

    /**
     * 根据class创建对象,并拷备source到新建的对像
     * @author bazhandao
     * @date 2020-11-29
     * @param source
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (source == null || clazz == null) {
            return null;
        }
        T t;
        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            throw new FatalBeanException("Could not copy properties from source to target, class " + clazz.getName() + " can't create new instance", e);
        }
        if (source instanceof Map) {
            copyProperties((Map<String, Object>) source, t);
        } else {
            copyProperties(source, t);
        }
        return t;
    }

    /**
     * 拷备obj到指定类型的对象,并返回该对象
     * @author bazhandao
     * @date 2020-11-29
     * @param obj
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T copy(Object obj, T t) {
        if (obj == null || t == null) {
            return null;
        }
        if (obj instanceof Map) {
            copyProperties((Map<String, Object>) obj, t);
        } else {
            copyProperties(obj, t);
        }
        return t;
    }

    /**
     * 将list内容转换为对应类型的list
     * @author bazhandao
     * @dat 2020-09-15
     * @param sourceList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> clazz) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            List<T> list = new ArrayList<>(sourceList.size());
            for (Object obj : sourceList) {
                T t = clazz.newInstance();
                if (obj instanceof Map) {
                    copyProperties((Map<String, Object>) obj, t);
                } else {
                    copyProperties(obj, t);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new FatalBeanException("Could not copy list properties from source to target", e);
        }
    }

    /**
     * 设置值到目标bean
     * @param target
     * @param targetPd
     * @param value
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static void setValue(Object target, PropertyDescriptor targetPd, Object value) throws IllegalAccessException, InvocationTargetException {
        // 这里判断以下value是否为空
        if (value != null) {
            Method writeMethod = targetPd.getWriteMethod();
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
            }
            writeMethod.invoke(target, value);
        }
    }

}
