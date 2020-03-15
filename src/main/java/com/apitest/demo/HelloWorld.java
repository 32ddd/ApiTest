package com.apitest.demo;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloWorld {


    @ResponseBody
    @RequestMapping
    public String hello(){
        return "123";
    }

    @Test
    public void getInfo() {
        String host = "http://character.market.alicloudapi.com";
        String path = "/ln/tag/personal/character_keyword/1.0";
        String method = "POST";
        String appcode = "5af85ac5be714d9b924e73d20c622faa";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("birthday", "19861006");
        bodys.put("keywordType", "2");
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            HttpEntity entity = response.getEntity();
            System.out.println("==================" + EntityUtils.toString(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sout(){
        System.out.println("11111111111111111111111111111111111111");
    }
}
