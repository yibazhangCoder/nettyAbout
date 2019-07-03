package com.cafebaby.cafewechat.pojo.ext;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class FriendsRequestExt implements Serializable {
    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Date requestDateTime;


}