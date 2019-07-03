package com.cafebaby.cafewechat.service;

import com.cafebaby.cafewechat.pojo.FriendsRequest;
import com.cafebaby.cafewechat.pojo.MyFriends;

import java.util.List;
import java.util.Map;

/**
 * @Author 一巴掌
 * @Date 2019/6/13 14:10
 * @Description TODO
 * @Version 1.0
 **/
public interface FriendsService {

    /**
     * 发送添加好友请求
     * @param friendsRequest
     * @return
     */
    boolean sendRequestOfAddUser(FriendsRequest friendsRequest);


    /**
     * 添加好友
     * @return
     */
    boolean addFriends(String sendUserId, String acceptUserId);

    /**
     * 删除好友请求
     * @param friendsRequest
     * @return
     */
    boolean removeRequestFriends(FriendsRequest friendsRequest);

    /**
     * 判断是否可以将用户添加为朋友
     * @return
     */
    Integer couldAddUser(FriendsRequest friendsRequest);


    /**
     * 查询我的添加请求
     * @param friendsRequest
     * @return
     */
    List<Map<String,Object>> findMyFriendsRequest(FriendsRequest friendsRequest);


    /**
     * 查询我的所有好友
     * @param myFriends
     * @return
     */
    List<Map<String,Object>> getMyAllFriends(MyFriends myFriends);

}
