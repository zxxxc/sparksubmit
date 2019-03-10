package com.zxc.sparksubmit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RestUtil {

    /**
     * 实现对REST服务的请求
     * @param urlStr
     * @param urlParam
     * @return
     * @throws IOException
     */
    public static String httpGet(String urlStr, List<String> urlParam) throws IOException, InterruptedException {
//        if (!urlParam.isEmpty()) {
//            urlStr += "?";
//            // 定义一个迭代器，并将MAP值的集合赋值
//            for (String string : urlParam) {
//                urlStr += string + "&";
//            }
//            urlStr = urlStr.substring(0, urlStr.length() - 1);
//        }
        // 实例一个URL资源
        URL url = new URL(urlStr);
        //实例一个HTTP CONNECT
       /* HttpURLConnection connet = (HttpURLConnection) url.openConnection();
        connet.setRequestMethod("GET");
        connet.setRequestProperty("Charset", "UTF-8");
        connet.setRequestProperty("Content-Type", "application/json");
        connet.setConnectTimeout(15000);// 连接超时 单位毫秒
        connet.setReadTimeout(15000);// 读取超时 单位毫秒*/
//        if(connet.getResponseCode() != 200){
//            System.out.println(connet.getResponseCode()+"请求异常:" + urlStr);
//            return "{}";
//        }

        HttpURLConnection connet = null;
        int i = 0;
        while(connet==null || connet.getResponseCode() != 200 ){
            connet = (HttpURLConnection) url.openConnection();
            connet.setRequestMethod("GET");
            connet.setRequestProperty("Charset", "UTF-8");
            connet.setRequestProperty("Content-Type", "application/json");
            connet.setConnectTimeout(15000);// 连接超时 单位毫秒
            connet.setReadTimeout(15000);// 读取超时 单位毫秒
            i++;
            if (i==50)break;
            Thread.sleep(500);
        }

        //将返回的值存入到String中
        BufferedReader brd = new BufferedReader(new InputStreamReader(connet.getInputStream(),"UTF-8"));
        StringBuilder  sb  = new StringBuilder();
        String line;

        while((line = brd.readLine()) != null){
            sb.append(line);
        }
        brd.close();
        connet.disconnect();
        return sb.toString();
    }
}
