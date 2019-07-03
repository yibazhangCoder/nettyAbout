package com.cafebaby.cafewechat.common;

import java.io.Serializable;

public class ResultDTO implements Serializable{
    Integer code;

    String msg;

    Object data;

    public static ResultDTO success() {
        return ResultDTO.success(null);
    }

    public static ResultDTO success( Object data) {
        ResultDTO vo = new ResultDTO();
        vo.setMsg("操作成功");
        vo.setCode(200);
        vo.setData(data);
        return vo;
    }



    public static ResultDTO error() {
        ResultDTO vo = new ResultDTO();
        vo.setMsg("系统错误");
        vo.setCode(500);
        return vo;
    }

    public static ResultDTO error(String message) {
        ResultDTO vo = new ResultDTO();
        vo.setMsg(message);
        vo.setCode(400);
        return vo;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
