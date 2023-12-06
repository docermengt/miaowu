package com.maoyan.util;

import javax.servlet.http.HttpServletRequest;

public class GetPathUrl {
    public  static String getUrl(String path){

        //获取最后一个斜杠下标
        int index = path.lastIndexOf("/");
        //获取请求名称
        String realPath = path.substring(index+1);

        return  realPath;
    }
}
