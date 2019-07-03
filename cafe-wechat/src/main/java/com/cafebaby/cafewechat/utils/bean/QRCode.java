package com.cafebaby.cafewechat.utils.bean;

import java.io.Serializable;

/**
 * Created by feizi on 2017/11/24.
 */
public class QRCode implements Serializable {
    private static final long serialVersionUID = -8247240134411644267L;

    //宽度
    private int width;
    //高度
    private int height;
    //边距
    private int margin;
    //等级
    private String level;
    //格式
    private String format;
    //内容
    private String content;

    public QRCode(int width, int height, int margin, String level, String format, String content) {
        this.width = width;
        this.height = height;
        this.margin = margin;
        this.level = level;
        this.format = format;
        this.content = content;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "QRCode{" +
                "width=" + width +
                ", height=" + height +
                ", margin=" + margin +
                ", level='" + level + '\'' +
                ", format='" + format + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
