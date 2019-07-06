package com.cafebaby.cafewechat.pojo.ext;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class UsersExt implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String faceImage;

    private String faceImageBig;

    private String nickname;

    private String qrcode;

    private String cid;


}