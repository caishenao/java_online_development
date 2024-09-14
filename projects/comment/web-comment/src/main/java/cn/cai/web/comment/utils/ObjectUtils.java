package cn.cai.web.comment.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectUtils {

    /**
     * 将传入的对象转为map
     * @param obj 要转换为对象
     * @return map
     */
    public static Map<String, Object> conversionMap(Object obj) throws IllegalAccessException {
        // 1. 获取对象的字段
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            // 设置字段可以被访问
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * 将传入的对象转为map
     * @param obj 要转换为对象
     * @return map
     */
    public static Map<String, String> conversionStringMap(Object obj) throws IllegalAccessException {
        // 1. 获取对象的字段
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, String> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            // 设置字段可以被访问
            field.setAccessible(true);
            map.put(field.getName(), Objects.toString(field.get(obj), ""));
        }
        return map;
    }
}
