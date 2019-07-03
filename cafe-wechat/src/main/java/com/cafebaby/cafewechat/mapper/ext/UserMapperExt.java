package com.cafebaby.cafewechat.mapper.ext;

import com.cafebaby.cafewechat.pojo.ext.UsersExt;

/**
 * @Author 一巴掌
 * @Date 2019/6/5 10:43
 * @Description TODO
 * @Version 1.0
 **/
public interface UserMapperExt {

    /**
     * 根据指定条件查询用户信息
     * @param usersExt
     * @return
     */
    UsersExt selectUsersByDesignCondition(UsersExt usersExt);
}
