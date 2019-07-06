package com.cafebaby.cafewechat.pojo.ext;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class FriendsRequestExt implements Serializable {
    private Long id;

    private Long sendUserId;

    private Long acceptUserId;

    private Date requestDateTime;


}