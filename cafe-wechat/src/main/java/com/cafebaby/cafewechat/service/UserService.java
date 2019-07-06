package com.cafebaby.cafewechat.service;


import com.cafebaby.cafewechat.pojo.ChatUsers;
import com.cafebaby.cafewechat.pojo.ext.UsersExt;

import java.io.IOException;

/**
 * @Author 一巴掌
 * @Date 2019/6/4 23:48
 * @Description TODO
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 查询用户是否存在
     * @param username
     * @return
     */
     UsersExt queryUserIsExistByUsername(String username);

    /**
     * 查询用户名或密码是否正确
     * @param username
     * @param password
     * @return
     */
     UsersExt queryUserForLogin(String username, String password);

    /**
     * 用户注册
     * @param users
     * @return
     */
     UsersExt registerUser(UsersExt users) throws IOException;


    /**
     * 根据主键更新
     * @param users
     * @return
     */
    ChatUsers updateUserByPrimaryKey(ChatUsers users);
}
