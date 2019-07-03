package com.cafebaby.cafewechat.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author jared
 * 
 * @Description: String工具类
 * 
 * @date Nov 5, 2014 3:36:54 PM
 * 
 */
public class StringUtils {

	private static Random RANDOM = new Random(System.currentTimeMillis());

	/**
	 * 字符串截取
	 * 
	 * @param source
	 *            待截取字符串
	 * @param size
	 *            保留截取长度
	 * @param suffix
	 *            多余部门字符替换
	 * @return
	 */
	public static String truncate(String source, Integer size, String suffix) {
		if (source != null) {
			String s = substring(source, size == null ? source.length() : size);
			if (s.length() < source.length()) {
				return s + suffix;
			}
			return s;
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 字符串截取
	 * 
	 * @param source
	 *            源字符串
	 * @param size
	 *            截取长度
	 * @return
	 */
	public static String substring(String source, int size) {
		return substring(source, 0, size);
	}

	/**
	 * 字符串截取
	 * 
	 * @param source
	 *            源字符串
	 * @param offset
	 *            字符截取位子
	 * @param size
	 *            截取长度
	 * @return
	 */
	public static String substring(String source, int offset, int size) {
		if (source == null || size < 1 || offset < 0) {
			return "";
		}

		if (source.length() <= (offset + size)) {
			return source;
		}

		return source.substring(offset, size);
	}

	/**
	 * 字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串为null时赋""值，不为null则返回本身
	 * 
	 * @param value
	 *            字符串
	 * @return
	 */
	public static String nullToEmpty(String value) {
		return value == null ? ConstStrings.EMPTY : value;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param values
	 *            多字符串
	 * @return
	 */
	public static boolean isNotEmpty(String... values) {
		if (values != null && values.length > 0) {
			for (String value : values) {
				if (value == null || value.length() == 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 字符串为空时赋默认值，不为空则返回本身
	 * 
	 * @param value
	 *            字符串
	 * @param def
	 *            默认值
	 * @return
	 */
	public static String emptyToDefault(String value, String def) {
		return (value == null || value.length() == 0) ? def : value;
	}

	/**
	 * 过滤表情等特殊符号
	 * 
	 * @param source
	 * @return
	 */
	public static String escapeEmoji(String source) {
		if (StringUtils.isNotEmpty(source)) {
			StringBuilder buff = new StringBuilder(source.length());
			for (char codePoint : source.toCharArray()) {
				if (((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
						|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
						|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)))) {
					buff.append(codePoint);
				}
			}
			return buff.toString();
		}
		return source;
	}

	/**
	 * 字符串转义
	 * 
	 * @param s
	 *            源字符串
	 * @param c
	 *            转义字符
	 * @return
	 */
	public static String decode(String s, char c) {
		if (s != null && s.length() > 0) {
			char[] cs = s.toCharArray();
			StringBuilder buff = new StringBuilder(cs.length);
			for (int i = 0, n = cs.length; i < n; i++) {
				if (!(cs[i] == ConstCharacters.BACKSLASH && i < n - 1 && cs[i + 1] == c)) {
					buff.append(cs[i]);
				}
			}
			return buff.toString();
		}
		return s;
	}

	public static String decode(String s, char... chars) {
		if (s != null && s.length() > 0 && chars != null) {
			Arrays.sort(chars);
			char[] cs = s.toCharArray();
			StringBuilder buff = new StringBuilder(cs.length);
			for (int i = 0, n = cs.length; i < n; i++) {
				if (!(cs[i] == ConstCharacters.BACKSLASH && i < n - 1 && Arrays.binarySearch(chars, cs[i + 1]) >= 0)) {
					buff.append(cs[i]);
				}
			}
			return buff.toString();
		}
		return s;
	}

	/**
	 * 字符串加密
	 * 
	 * @param s
	 * @param chars
	 * @return
	 */
	public static String encode(String s, char... chars) {
		if (s != null && s.length() > 0 && chars != null) {
			Arrays.sort(chars);
			char[] cs = s.toCharArray();
			StringBuilder sb = new StringBuilder(cs.length + 10);
			for (int i = 0; i < cs.length; i++) {
				if (Arrays.binarySearch(chars, cs[i]) >= 0) {
					sb.append(ConstCharacters.BACKSLASH);
				}
				sb.append(cs[i]);
			}
			return sb.toString();
		}
		return s;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String random(int length) {
		return random(length, ConstArrays.RANDOM_SEEDS);
	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @param options
	 *            随机字符范围数组
	 * @return
	 */
	public static String random(final int length, char[] options) {
		if (length > 1) {
			int maxLength = 0;
			if (ArrayUtils.isEmpty(options)) {
				options = ConstArrays.BASE64;
				maxLength = 62;
			} else {
				maxLength = options.length;
			}
			char[] buff = new char[length];
			for (int i = 0; i < length; i++) {
				buff[i] = options[RANDOM.nextInt(maxLength)];
			}
			return new String(buff);
		}

		return ConstStrings.EMPTY;
	}

	/**
	 * 字符串累加
	 * 
	 * @param values
	 * @return
	 */
	public static String join(String... values) {
		return concate(values, ConstStrings.EMPTY);
	}

	/**
	 * 字符串数组间添加固定字符串进行累加
	 * 
	 * @param values
	 *            字符串数组
	 * @param delimiter
	 *            字符串间隔间添加的字符
	 * @return
	 */
	public static String concate(String[] values, String delimiter) {
		if (values != null && values.length > 0) {
			if (delimiter == null) {
				delimiter = ConstStrings.EMPTY;
			}
			int length = 0;
			for (String value : values) {
				if (value != null) {
					length += (value.length() + delimiter.length());
				}
			}

			StringBuilder buff = new StringBuilder(length);
			for (String value : values) {
				if (isNotEmpty(value)) {
					if (buff.length() > 0) {
						buff.append(delimiter);
					}
					buff.append(value);
				}
			}

			return buff.toString();
		}

		return ConstStrings.EMPTY;
	}

	/**
	 * 判断字符串中第几位开始包含指定字符串
	 * 
	 * @param source
	 * @param sub
	 * @param offset
	 *            第几位开始
	 * @return
	 */
	public static boolean contains(String source, String sub, int offset) {
		return isNotEmpty(source) && source.indexOf(sub, offset) >= offset;
	}

	/**
	 * 字符串标准化处理
	 * 
	 * @param value
	 * @return
	 */
	public static String normalize(String value) {
		if (isNotEmpty(value)) {
			return value.toLowerCase().trim();
		}

		return ConstStrings.EMPTY;
	}

	public static String sqlLike(String value) {
		if (StringUtils.isNotEmpty(value)) {
			StringBuilder builder = new StringBuilder();
			builder.append("%");
			builder.append(value);
			builder.append("%");
			return builder.toString();
		}
		return ConstStrings.EMPTY;
	}
	
	public static String formatString(String text){
		return text==null ? "" : text.trim();
	}
	
	/**
	 * 隐藏字符串
	 * @param preNum 显示前几位
	 * @param lastNum 显示后几位
	 * @param hideNum 显示的*数
	 * @param val 要隐藏的字符串值
	 * @return
	 */
	public static String hideString(int preNum,int lastNum,int hideNum,String val){
		if(isNotEmpty(val)){
			StringBuilder sb = new StringBuilder();
			sb.append(val.substring(0, preNum));
			sb.append(getHideNum(hideNum));
			sb.append(val.substring(val.length()-lastNum));
			return sb.toString();
		}
		return val;		
	}
	
	public static String hideName(String val){
		if(StringUtils.isEmpty(val)){
			return val;
		}
		int len = val.length();
		StringBuilder sb= new StringBuilder(val.substring(0, 1));
		if(len == 2){
			sb.append("*");
			
		}else if(len >2){
			sb.append("*").append(val.substring(len-1));
		}
		return sb.toString();		
	}
	
	private static String getHideNum(int hideNum){
		StringBuilder sb = new StringBuilder();
		while(hideNum > 0){
			sb.append("*");
			hideNum --;
		}
		return sb.toString();	
	}
}
