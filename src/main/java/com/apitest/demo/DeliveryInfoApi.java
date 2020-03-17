package com.apitest.demo;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class DeliveryInfoApi {
    @Test
    public static void getDeliveryInfo() {
        String host = "https://ali-deliver.showapi.com";
        String path = "/showapi_expInfo";
        String method = "GET";
        String appcode = "5af85ac5be714d9b924e73d20c622faa";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("com", "zhongtong");
        querys.put("nu", "535962308717");
        querys.put("receiverPhone", "15624979700");
        querys.put("senderPhone", "15624979700");


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
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
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
}
