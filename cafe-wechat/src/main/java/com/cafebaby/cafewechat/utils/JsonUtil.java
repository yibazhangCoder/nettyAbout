package com.cafebaby.cafewechat.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * JSON转换工具类
 * 
 * @author lrh
 */
public final class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Log LOG = LogFactory.getLog(JsonUtil.class);

    /**
     * 如果json的内容是简单的javabean对象，使用该方法把json转换为java对象
     * 
     * @param content
     * @param valueType
     * @return
     */
    public static Object jsonToJava(String content, Class<?> valueType) {
        if (Strings.isNotBlank(content)) {
            try {
                return objectMapper.readValue(content, valueType);
            } catch (IOException e) {
                LOG.error("error in jsonToJava", e);
            }
        }
        return null;
    }

    /**
     * 如果json的内容是包含自定义对象的列表，则使用该方法把json字符转换为集合对象
     * 比如json的内容要转换成List<MyBean>集合，那么T的值为List<MyBean>
     * 
     * @param content
     * @param type
     * @return
     */
    public static Object jsonToJava(String content, TypeReference<?> type) {
        if (Strings.isNotBlank(content)) {
            try {
                return objectMapper.readValue(content, type);
            } catch (IOException e) {
                LOG.error("error in method jsonToJava", e);
            }
        }
        return null;
    }

    /**
     * 把java对象转换为json字符串
     * 
     * @param value
     * @return
     */
    public static String toJsonString(Object value) {
        if (value != null) {
            try {
                return objectMapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                LOG.error("error in method toJsonString", e);
            }
        }
        return "";
    }

    /**
     * 把java对象转换为json字符串的byte数组
     * 
     * @param value
     * @return
     */
    public static byte[] toJsonBytes(Object value) {
        try {
            return toJsonString(value).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("error in toJsonBytes", e);
        }
        return new byte[0];
    }
}
