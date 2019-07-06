package com.cafebaby.cafewechat.pojo.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FriendRequestVo implements Serializable {

    private Long id;

    private Long sendUserId;

    private Long acceptUserId;

    private Date requestDateTime;

    //操作类型   1：通过   0：忽略
    private Integer type;

}
