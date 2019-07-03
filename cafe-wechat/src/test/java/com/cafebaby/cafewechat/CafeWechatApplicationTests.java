package com.cafebaby.cafewechat;

import com.cafebaby.bootstrap.CafeWechatApplication;
import com.cafebaby.cafewechat.mapper.ext.FriendsRequestMapperExt;
import com.cafebaby.cafewechat.mapper.ext.UserMapperExt;
import com.cafebaby.cafewechat.pojo.FriendsRequest;
import com.cafebaby.cafewechat.pojo.ext.UsersExt;
import com.cafebaby.cafewechat.service.FriendsService;
import com.cafebaby.cafewechat.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CafeWechatApplication.class)
public class CafeWechatApplicationTests {

    @Autowired
    UserMapperExt userMapperExt;

    @Autowired
    UserService userService;

    @Autowired
    FriendsService friendsService;

    @Autowired
    FriendsRequestMapperExt friendsRequestMapperExt;

    @Test
    public void contextLoads() {
        UsersExt usersExt = new UsersExt();
        usersExt.setUsername("bghjk");
        UsersExt usersExt1 = userMapperExt.selectUsersByDesignCondition(usersExt);
        System.out.println(usersExt1);
    }

    @Test
    public void test1(){
        UsersExt u =userService.queryUserIsExistByUsername("yibazhang");
        System.out.println(u.getNickname());
        System.out.println(u.getFaceImage());
    }


    @Test
    public void test2(){
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.setAcceptUserId("1560338304000108363");
        List<Map<String,Object>> list = friendsService.findMyFriendsRequest(friendsRequest);
        System.out.println(list.get(0).get("sendNickname"));
    }
}
