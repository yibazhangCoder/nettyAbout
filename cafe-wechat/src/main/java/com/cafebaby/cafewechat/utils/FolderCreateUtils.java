package com.cafebaby.cafewechat.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 一巴掌
 * @Date 2019/3/10 9:24
 * @Description TODO
 * @Version 1.0
 **/
public class FolderCreateUtils {

    public static String getRootPath(){
        File path1 = null;
        String uploadDir="";
        try {
            path1 = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path1.exists()) path1 = new File("");
            File upload = new File(path1.getAbsolutePath(),"static/qrcode/");
            if(!upload.exists()) upload.mkdirs();
            uploadDir= upload.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return uploadDir;
    }

    public static String createFolder(String des,int roleId){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String first = des+"/"+ format.format(date);
        String second = "";
        if(roleId==2){
            second = first + "/male/"+format1.format(date);
        }else if(roleId==1){
            second = first + "/female/"+format1.format(date);
        }else{
            second = first+"/temp";
        }
        File firstFile = new File(first);
        File secondFile = new File(second);
        while(!secondFile.exists()){
            if(!firstFile.exists())firstFile.mkdirs();
            else secondFile.mkdirs();
        }

        return second+"/";
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\一巴掌\\Desktop\\毕业设计\\project\\GraduationProject\\worksystem\\static\\upload/2019/teacher/2019-03/";
        String fileName = "85d9176b-baa6-4ed0-ae44-cd48a198f51e.docx";
        File file = new File(path+fileName);
        System.out.println(file.getName().substring(0,file.getName().lastIndexOf(".")));
    }
}
