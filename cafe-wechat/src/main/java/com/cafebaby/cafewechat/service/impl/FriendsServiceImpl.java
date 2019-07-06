package com.cafebaby.cafewechat.service.impl;

import com.cafebaby.cafewechat.mapper.FriendsRequestMapper;
import com.cafebaby.cafewechat.mapper.MyFriendsMapper;
import com.cafebaby.cafewechat.mapper.ext.FriendsRequestMapperExt;
import com.cafebaby.cafewechat.pojo.FriendsRequest;
import com.cafebaby.cafewechat.pojo.MyFriends;
import com.cafebaby.cafewechat.service.FriendsService;
import com.cafebaby.cafewechat.utils.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 一巴掌
 * @Date 2019/6/13 14:11
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    FriendsRequestMapper friendsRequestMapper;

    Logger logger = LoggerFactory.getLogger(FriendsServiceImpl.class);

    @Autowired
    MyFriendsMapper myFriendsMapper;

    @Autowired
    FriendsRequestMapperExt friendsRequestMapperExt;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean sendRequestOfAddUser(FriendsRequest friendsRequest) {
        if(friendsRequest==null)return false;
        friendsRequest.setId(IdUtils.make());
        friendsRequest.setRequestDateTime(new Date());
        int i  = friendsRequestMapper.insertSelective(friendsRequest);
        return i>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addFriends(Long sendUserId, Long acceptUserId) {
        MyFriends myFriends = new MyFriends();
        myFriends.setId(IdUtils.make());
        myFriends.setMyUserId(acceptUserId);
        myFriends.setMyFriendUserId(sendUserId);
        int i  =myFriendsMapper.insertSelective(myFriends);
        return i>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean removeRequestFriends(FriendsRequest request) {
        int i=friendsRequestMapperExt.deleteFriendRequest(request);
        return i>0;
    }


    /**
     * 是否可以添加好友
     *
     * 0.可以添加对方为好友
     * 1.对方已经是您好友
     * 2.不可添加自己为好友
     * 3.你已经发送过好友请求
     * @param request
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer couldAddUser(FriendsRequest request) {
        if(request.getSendUserId()==request.getAcceptUserId())return 2;
        List<Map<String,Object>> requestList = friendsRequestMapperExt.selectFriendsRequestsByDirection(request);
        if(!requestList.isEmpty())return 3;
        MyFriends myFriends = new MyFriends();
        myFriends.setMyUserId(request.getSendUserId());
        myFriends.setMyFriendUserId(request.getAcceptUserId());
        List<Map<String,Object>> friendList = friendsRequestMapperExt.selectMyFriends(myFriends);
        if(!friendList.isEmpty())return 1;
        return 0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Map<String, Object>> findMyFriendsRequest(FriendsRequest friendsRequest) {
        if (friendsRequest.getAcceptUserId()==null){
            logger.error("has error");
        }
        List<Map<String,Object>> list =  friendsRequestMapperExt.selectFriendsRequestsByDirection(friendsRequest);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Map<String, Object>> getMyAllFriends(MyFriends myFriends) {
        return friendsRequestMapperExt.selectMyFriends(myFriends);
    }


}
