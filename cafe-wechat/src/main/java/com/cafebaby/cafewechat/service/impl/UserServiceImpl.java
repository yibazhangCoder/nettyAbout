package com.cafebaby.cafewechat.service.impl;

import com.cafebaby.cafewechat.mapper.ChatUsersMapper;
import com.cafebaby.cafewechat.mapper.ext.UserMapperExt;
import com.cafebaby.cafewechat.pojo.ChatUsers;
import com.cafebaby.cafewechat.pojo.ext.UsersExt;
import com.cafebaby.cafewechat.service.UserService;
import com.cafebaby.cafewechat.utils.IdUtils;
import com.cafebaby.cafewechat.utils.QRCodeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Author 一巴掌
 * @Date 2019/6/4 23:49
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapperExt userMapperExt;

    @Autowired
    private ChatUsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UsersExt queryUserIsExistByUsername(String userName) {
        UsersExt usersExt = new UsersExt();
        usersExt.setUsername(userName);
        UsersExt res = userMapperExt.selectUsersByDesignCondition(usersExt);
        return res;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UsersExt queryUserForLogin(String userName, String password) {
            UsersExt usersExt = new UsersExt();
            usersExt.setUsername(userName);
            usersExt.setPassword(password);
            UsersExt res = userMapperExt.selectUsersByDesignCondition(usersExt);

        return res;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UsersExt registerUser(UsersExt usersExt)  {
        usersExt.setId(IdUtils.make());
        //String path = FolderCreateUtils.createFolder(FolderCreateUtils.getRootPath(),2)+"qrCode_"+usersExt.getUsername()+".jpg";
        try {
            QRCodeUtil.createQRCode(usersExt.getQrcode(),QRCodeUtil.qrCodeInstance("weiliao:"+usersExt.getUsername()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        usersExt.setQrcode(usersExt.getQrcode());
        ChatUsers users = new ChatUsers();
        BeanUtils.copyProperties(usersExt,users);
        int i =usersMapper.insertSelective(users);
        return i>0?usersExt:null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ChatUsers updateUserByPrimaryKey(ChatUsers usersExt) {
        if(usersExt.getId()==null)return null;
        int i  = usersMapper.updateByPrimaryKeySelective(usersExt);
        return i>0?usersMapper.selectByPrimaryKey(usersExt.getId()):null;
    }
}
