package com.cafebaby.cafewechat.controller;

import com.cafebaby.cafewechat.common.ResultDTO;
import com.cafebaby.cafewechat.pojo.ChatUsers;
import com.cafebaby.cafewechat.pojo.VO.UsersVo;
import com.cafebaby.cafewechat.pojo.ext.UsersExt;
import com.cafebaby.cafewechat.service.impl.UserServiceImpl;
import com.cafebaby.cafewechat.utils.FolderCreateUtils;
import com.cafebaby.cafewechat.utils.MD5Utils;
import com.cafebaby.cafewechat.utils.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 一巴掌
 * @Date 2019/6/4 23:46
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/registorOrLogin")
    public ResultDTO registorOrLogin(@RequestBody ChatUsers users, HttpServletRequest request){
        if(Strings.isBlank(users.getUsername())||Strings.isBlank(users.getPassword())){
            return ResultDTO.error("用户名和密码不能为空！");
        }

        UsersExt userIsExist = userService.queryUserIsExistByUsername(users.getUsername());

        UsersExt usersExt = null;
        if(userIsExist!=null){
            usersExt = userService.queryUserForLogin(users.getUsername(), MD5Utils.encrypt(users.getPassword()));
            if(usersExt==null){
                return ResultDTO.error("用户名或密码不正确！");
            }
        }else {
            UsersExt ext = new UsersExt();
            ext.setUsername(users.getUsername());
            ext.setNickname("我是"+users.getUsername());
            ext.setFaceImage(users.getFaceImage());
            String path  = request.getServletContext().getContextPath()+"/static";
            System.out.println(path);
            String qrcode =  FolderCreateUtils.createFolder(path,1)+"qrcode_"+ext.getUsername()+".jpg";
            ext.setQrcode(qrcode);
            ext.setFaceImageBig("");
            ext.setPassword(MD5Utils.encrypt(users.getPassword()));
            ext.setCid(users.getCid());
            usersExt = userService.registerUser(ext);
        }
        UsersVo usersVo =  new UsersVo();
        BeanUtils.copyProperties(usersExt,usersVo);
        return ResultDTO.success(usersVo);
    }


    @PostMapping("/updateUser")
    public ResultDTO updateUsers(@RequestBody ChatUsers users){
        if(users.getId()==null)return ResultDTO.error("参数错误！");
        ChatUsers  res =  userService.updateUserByPrimaryKey(users);
        UsersVo usersVo =  new UsersVo();
        BeanUtils.copyProperties(res,usersVo);
        return res==null?ResultDTO.error("更新失败！") :ResultDTO.success(usersVo);
    }


    @PostMapping("/searchUser")
    public ResultDTO queryUsersByUsername(String username){
        if(Strings.isBlank(username))return ResultDTO.error("用户名不能为空！");
        UsersExt usersExt = userService.queryUserIsExistByUsername(username);
        return usersExt==null?ResultDTO.error("用户不存在"):ResultDTO.success(usersExt);
    }
}
