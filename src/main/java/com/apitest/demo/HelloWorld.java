package com.apitest.demo;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
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
        String appcode = "a7f9da62685341b08fa9d31644ff8365";
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
            Locale locale = response.getLocale();
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            Header header = response.getFirstHeader("X-Ca-Error-Message");
            System.out.println("====================X-Ca-Error-Message: " + JSON.toJSON(response.getHeaders("X-Ca-Error-Message")));
            System.out.println("====================local: " + JSON.toJSONString(locale));
            System.out.println("====================statusLine: " + JSON.toJSONString(statusLine));
            System.out.println("====================entity:" + JSON.toJSONString(entity));
            if(statusLine.getStatusCode() != 200 && header != null) {
                System.out.println("错误信息：" + header.getValue());
            } else {
                System.out.println("返回结果：" + EntityUtils.toString(entity,"utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sout(){
        System.out.println("11111111111111111111111111111111111111");
    }
}
