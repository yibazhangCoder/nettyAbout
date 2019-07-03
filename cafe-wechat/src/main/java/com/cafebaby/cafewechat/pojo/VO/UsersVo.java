package com.cafebaby.cafewechat.pojo.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author 一巴掌
 * @Date 2019/6/5 19:28
 * @Description TODO
 * @Version 1.0
 **/
@Setter
@Getter
public class UsersVo implements Serializable {
    private String id;

    private String username;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;
}
