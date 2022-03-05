package com.lmt.zeus.parent.utils;

import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @description http请求工具类
 * @author bazhandao
 * @date 2018/11/10 15:58
 * @since JDK1.8
 */
public class HttpUtils {

    private static HttpClient client = HttpClients.createDefault();

    /**
     * 发送post请求，form表单格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String post(String url, Map<String, Object> param) {
        return post(client,url,param);
    }

    /**
     * 发送post请求，form表单格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String post(HttpClient client, String url, Map<String, Object> param) {
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(buildProxy(url)).setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        post.setConfig(requestConfig);
        //设置参数
        List<NameValuePair> list = new ArrayList<>();
        for(Map.Entry<String, Object> e : param.entrySet()){
            list.add(new BasicNameValuePair(e.getKey(),String.valueOf(e.getValue())));
        }
        try {
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
                post.setEntity(entity);
            }
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .setAll(param);
        }
        return null;
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @return
     */
    public static String get(String url) {
        HttpClient client = HttpClients.createDefault();
        return get(client,url,null);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String get(String url, Map<String, Object> param) {
        HttpClient client = HttpClients.createDefault();
        return get(client,url,param);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @return
     */
    public static String get(HttpClient client, String url) {
        return get(client,url,null);
    }

    /**
     * 发送get请求
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param param
     * @return
     */
    public static String get(HttpClient client, String url, Map<String, Object> param) {
        StringBuilder sb = new StringBuilder();
        if(param != null){
            for(Map.Entry<String, Object> e : param.entrySet()){
                String k = e.getKey();
                String v = String.valueOf(e.getValue());
                sb.append("&").append(k).append("=").append(v);
            }
        }
        String query = sb.toString();
        if(query.length() >= 1){
            if(url.indexOf("?") == -1){
                query = query.replaceFirst("&", "");
                url = url + "?" + query;
            }else{
                url = url + query;
            }
        }
        HttpGet get = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(buildProxy(url)).setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        get.setConfig(requestConfig);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .setAll(param);
        }
        return null;
    }

    /**
     * 发送post请求,raw body格式
     * @author bazhandao
     * @date 2018-11-12
     * @param url
     * @param body
     * @return
     */
    public static String postBody(String url, String body) {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(buildProxy(url)).setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        post.setConfig(requestConfig);
        post.setEntity(new StringEntity(body, "UTF-8"));
        try {
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .set("body", body);
        }
        return null;
    }

    public static String postBody(String url, Map<String, String> headers, String body) {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(buildProxy(url)).setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        post.setConfig(requestConfig);
        post.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        for (Map.Entry<String, String> header : headers.entrySet()) {
            post.addHeader(header.getKey(), header.getValue());
        }
        try {
            HttpResponse response = client.execute(post);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .set("body", body);
        }
        return null;
    }

    public static String getWithHeaders(String url, Map<String, String> headers) {
        HttpGet get = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(buildProxy(url)).setSocketTimeout(60*1000).setConnectTimeout(60*1000).setConnectionRequestTimeout(60*1000).build();
        get.setConfig(requestConfig);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            get.addHeader(header.getKey(), header.getValue());
        }
        try {
            HttpResponse response = client.execute(get);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                return result;
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url);
        }
        return null;
    }

    /**
     * 从启动参数里获取代理服务地址、端口,如果存在则返回代理服务器,否则返回null
     * @author bazhandao
     * @date 2022-03-04
     * @param url
     * @return
     */
    private static HttpHost buildProxy(String url) {
//        -Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=1087 -Dhttps.proxyHost=127.0.0.1 -Dhttps.proxyPort=1087
        String host = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");
        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(port)) {
            return new HttpHost(host, Integer.parseInt(port), "http");
        }
        host = System.getProperty("https.proxyHost");
        port = System.getProperty("https.proxyPort");
        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(port)) {
            return new HttpHost(host, Integer.parseInt(port), "https");
        }
        return null;
    }

}
