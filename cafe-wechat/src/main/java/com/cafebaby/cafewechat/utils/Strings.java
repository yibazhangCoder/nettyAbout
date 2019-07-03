package com.cafebaby.cafewechat.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串格式化处理类
 *
 * @author zhifeng
 */
public final class Strings extends StringUtils {

    private Strings() {
    }

    /**
     * 指定分割符分割字符串，并返回分割后的最后一个字符串
     *
     * @param source
     * @param delim
     * @return
     */
    public static String lastSplitString(String source, String delim) {
        return substringAfterLast(source, delim);
    }

    /**
     * 如果字符串数组中任意一个字符串为blank，则返回true
     *
     * @param source
     * @return
     */
    public static boolean isBlankAny(String... source) {
        for (String str : source) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果字符串数组中所有字符串为blank，则返回true
     *
     * @param source
     * @return
     */
    public static boolean isBlankAll(String... source) {
        for (String str : source) {
            if (isNotBlank(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据模板格式化，template的格式请参见：@see java.text.MessageFormat
     *
     * @param template
     * @param param
     * @return
     */
    public static String format(String template, Object... param) {
        return (isNotBlank(template) ? MessageFormat.format(template, param) : template);
    }

    /**
     * 如果字符串数组当中有一个和要比较的值相等 则返回true
     *
     * @param compareString 要比较的字符串
     * @param source 字符串数组
     * @return
     */
    public static boolean isEqualsAny(String compareString, String... source) {
        for (String str : source) {
            if (StringUtils.equals(compareString, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果字符串数组当中所有字符串都和要比较的字符串相等 则返回true
     *
     * @param compareString 要比较的字符串
     * @param source 字符串数组
     * @return
     */
    public static boolean isEqualsAll(String compareString, String... source) {
        for (String str : source) {
            if (!StringUtils.equals(compareString, str)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 转换已逗号或者空格分隔的字符串为list
     * @param str
     * @param splitChar
     * @return
     */
    public static List<String> parseStrsToList(String str,String splitChar){
        if(!Arrays.asList(",","").contains(splitChar)){
            return null;
        }
        if(isBlank(str)){
            return null;
        }
        if(splitChar == null){
            return Arrays.asList(str);
        }
        return Arrays.asList( RegexUtil.formatKeyWord(str,splitChar).split(splitChar));
        
    }
}
