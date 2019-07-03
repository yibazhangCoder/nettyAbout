package com.cafebaby.cafewechat.mapper.ext;

import com.cafebaby.cafewechat.pojo.FriendsRequest;
import com.cafebaby.cafewechat.pojo.MyFriends;

import java.util.List;
import java.util.Map;

public interface FriendsRequestMapperExt {

    List<Map<String, Object>> selectFriendsRequestsByDirection(FriendsRequest friendsRequest);

    List<Map<String,Object>> selectMyFriends(MyFriends myFriends);

    int deleteFriendRequest(FriendsRequest friendsRequest);

}