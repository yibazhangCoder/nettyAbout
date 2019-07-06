package com.cafebaby.cafewechat.controller;

import com.cafebaby.cafewechat.pojo.VO.FriendRequestVo;
import com.cafebaby.cafewechat.common.ResultDTO;
import com.cafebaby.cafewechat.pojo.FriendsRequest;
import com.cafebaby.cafewechat.pojo.MyFriends;
import com.cafebaby.cafewechat.pojo.VO.UsersVo;
import com.cafebaby.cafewechat.pojo.ext.UsersExt;
import com.cafebaby.cafewechat.service.FriendsService;
import com.cafebaby.cafewechat.service.UserService;
import com.cafebaby.cafewechat.utils.StringUtils;
import com.cafebaby.cafewechat.utils.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 一巴掌
 * @Date 2019/6/14 12:46
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("/friend")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserService userService;


    @PostMapping("/searchUser/{username}")
    public ResultDTO searchFriend(@PathVariable("username") String username){
        if(Strings.isBlank(username))return ResultDTO.error("用户名不能为空");
        UsersExt usersExt = userService.queryUserIsExistByUsername(username);
        if(usersExt==null)return ResultDTO.error("用户不存在");
        UsersVo vo = new UsersVo();
        BeanUtils.copyProperties(usersExt,vo);
        return ResultDTO.success(vo);
    }

    /**
     * @param friendsRequest
     * @return
     */
    @PostMapping("/sendFriendRequest")
    public ResultDTO sendFriendRequest(@RequestBody FriendsRequest friendsRequest){
        if(friendsRequest.getSendUserId()==null||friendsRequest.getAcceptUserId()==null)return ResultDTO.error("参数错误！");
        FriendsRequest request = new FriendsRequest();
        request.setSendUserId(friendsRequest.getSendUserId());
        request.setAcceptUserId(friendsRequest.getAcceptUserId());
        Integer i = friendsService.couldAddUser(request);
        if (i == 1) return ResultDTO.error("对方已经是您的好友！");
        if (i == 2) return ResultDTO.error("不可添加自己为好友！");
        if (i == 3) return ResultDTO.error("您已发送过好友请求！");
        boolean flag=false;
        if (i == 0) flag= friendsService.sendRequestOfAddUser(request);
        return flag?ResultDTO.success("请求发送成功"):ResultDTO.error("请求发送失败！");
    }

    @PostMapping("/getMyFriendRequests")
    public ResultDTO getAllFriendRequest(@RequestBody FriendsRequest friendsRequest){
        if(friendsRequest.getAcceptUserId()==null)return ResultDTO.error("参数错误");
        List<Map<String,Object>> res = friendsService.findMyFriendsRequest(friendsRequest);
        return ResultDTO.success(res);
    }


    @PostMapping("/passOrIgnore")
    public ResultDTO passOrIgnoreRequest(@RequestBody FriendRequestVo vo){
        FriendsRequest friendsRequest =null;
        if(vo!=null){
            friendsRequest = new FriendsRequest();
            BeanUtils.copyProperties(vo,friendsRequest);
        }
        if(friendsRequest.getSendUserId()==null||friendsRequest.getAcceptUserId()==null||vo.getType()==null)return ResultDTO.error("参数错误");
        if(vo.getType()==1){
            boolean i = friendsService.addFriends(friendsRequest.getAcceptUserId(), friendsRequest.getSendUserId());
            boolean j = friendsService.addFriends(friendsRequest.getSendUserId(), friendsRequest.getAcceptUserId());
            boolean k = friendsService.removeRequestFriends(friendsRequest);
            if(i&&j&&k)return ResultDTO.success("添加成功");
        }else if(vo.getType()==0){
            friendsService.removeRequestFriends(friendsRequest);
        }
        return ResultDTO.error();
    }

    @PostMapping("/getAllMyFriends/{myUserId}")
    public ResultDTO getAllMyFriends(@PathVariable("myUserId") Long myUserId){
        if(myUserId==null)return ResultDTO.error("参数错误");
        MyFriends myFriends = new MyFriends();
        myFriends.setMyUserId(myUserId);
        List<Map<String,Object>> res = friendsService.getMyAllFriends(myFriends);
        myFriends=null;
        return ResultDTO.success(res);
    }
}
