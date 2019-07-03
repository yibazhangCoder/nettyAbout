package com.cafebaby.cafewechat.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

/**
 * @author liuronghuan
 *
 *         2013年12月11日
 */
public class RegexUtil {
	
	public static final String TEL_SEG = "^(13|15|18)\\d{9}$|^(145|147)\\d{8}$|^(1700|1705|1709|1707|1708)\\d{7}$|^(176|177|178)\\d{8}$";
	
    private static final int FILE_NAME_INDEX = 1;

    private static final int OWNER_ID_INDEX = 3;

    private static final int CATEGORY_INDEX = 5;

    /**
     * 号码
     */
    public static final String PHONEREGEX = "^\\d{4}-\\d{7,8}$|^\\d{11}$|^\\+86\\d{11}$|^0\\d{10,12}$|^\\d{7,8}$";
    
    /**
     * 电话号码
     */
    public static final String TELPHONEREGEX = "^\\d{11}$";
    /**
     * 邮箱
     */
    public static final String EMAILREGEX = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$";

    public static final String TEXTAREAIMGREGEX = "name=[0-9]+.[a-zA-Z]+";

    public static final String IMGTAGREG = "<img[^>]+src=[\"'][^\"']+\"[^>]*>";

    public static final String HTMLTAGREG = "<[\\w]+?\\s*[^><]*\\s*>|<\\s*\\/[\\w]+\\s*>";
    
    /**
     * 由于文本图片的url变更，为了兼容之前的项目，用这个匹配进行替换
     */
    public static final String OLD_TEXT_IMAGE_URL = "src=\"\\$\\{imageServerUrl\\}\\?imageName=(\\d+.\\w+)(&amp;|&)ownerId=(\\d+)(&amp;|&)category=(\\w+)\"";
    
    
    public static final Pattern OLDIMAGEPattern = Pattern.compile(OLD_TEXT_IMAGE_URL); 
    
    //图片访问地址占位符 
    private static final String IMAGE_SERVER_URL = "\\$\\{imageServerUrl\\}";
    
    public static final Pattern ImageServerURLPattern = Pattern.compile(IMAGE_SERVER_URL); 
    /**
     * 邮编
     */
    public static final String POSTCODE = "^[1-9][0-9]{5}$";

    public static final String QOUTREGX="(\"|&quot;|&quot)";

    /**
     * 校验value是否匹配regex所指定的格式
     *
     * @param value
     * @param regex
     * @return
     */
    public static Boolean match(String value, String regex) {
        return Pattern.matches(regex, value);
    }

    /**
     * 格式化关键词模糊查询str，将空格、中英文逗号统一转换为value
     *
     * @param keyWord
     * @param value
     * @return
     */
    public static String formatKeyWord(String keyWord, String value) {
        if (null == keyWord) {
            return null;
        }
        return keyWord.replaceAll("，+", value).replaceAll("\\s+", value).replaceAll("\"+", "\"").replaceAll(",+", value);
    }

    /**
     * 解析文本中嵌入的图片链接，嵌入的超链接中文件的参数名为name， 例如{@code <img src="image/project/1.html?name=12323243.png"/>}
     * 解析后得到的结果为：12323243.png
     *
     * @param content
     * @return 默认返回值为空字符串
     */
    public static String findImagNameOfText(String content) {
        if (content == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Matcher ma = Pattern.compile(TEXTAREAIMGREGEX).matcher(content);
        while (ma.find()) {
            sb.append(ma.group().split("=")[1]).append(",");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return "";

    }

    public static List<String> getImagNameOfText(String content) {
        List<String> imagesList = new ArrayList<String>(0);

        if (Strings.isNotBlank(content)) {
            imagesList = asList(findImagNameOfText(content).split(","));
        }

        return imagesList;
    }

    /**
     * 删除文本中的html和图片标签，只显示标签中的文字
     *
     * @param content
     * @return
     */
    public static String removeImagAndHTMLTag(String content) {
        if (content == null) {
            return null;
        }
        return content.replaceAll(IMGTAGREG, "").replaceAll(HTMLTAGREG, "");

    }

    public static String repaceQout(String content){
        if(content == null){
            return null;
        }
        return content.replaceAll(QOUTREGX, "\\\"");

    }
    
    /**
     * 替换文本框里的imageUrl
     * @param content
     * @param replaceStr
     * @return
     */
    public static String repaceTextImageUrl(String content,String replaceStr){
        if (content == null || replaceStr == null) {
            return null;
        }
        Matcher m = OLDIMAGEPattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        StringBuilder append = new StringBuilder();
        append.append("src=\"").append(replaceStr);
        int len = append.length();
        while (m.find()) {
            append.append(m.group(CATEGORY_INDEX)).append("/");
            append.append(m.group(OWNER_ID_INDEX)).append(".html?");
            append.append("name=").append(m.group(FILE_NAME_INDEX)).append("\"");
            m.appendReplacement(sb, append.toString());
            append.delete(len, append.length());
        }
        m.appendTail(sb);
        m = ImageServerURLPattern.matcher(sb);

        StringBuffer newsb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(newsb, replaceStr);
        }
        m.appendTail(newsb);
        return newsb.length() == 0 ? null : newsb.toString();
    } 
}
