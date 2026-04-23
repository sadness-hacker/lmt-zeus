package com.lmt.zeus.parent.utils;

import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description http请求工具类
 * @author bazhandao
 * @date 2018/11/10 15:58
 * @since JDK1.8
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) ZeusHttpClient/4.0.5";

    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(30))
            .executor(java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor())
            .build();

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
        //设置参数
        String formData = param.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(String.valueOf(e.getValue()), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("User-Agent", USER_AGENT)
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                return response.body();
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
        return get(client, url,null);
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
        return get(client, url, param);
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
        String fullUrl = url;
        if (param != null && !param.isEmpty()) {
            String query = param.entrySet().stream()
                    .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" +
                            URLEncoder.encode(String.valueOf(e.getValue()), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            fullUrl = url.contains("?") ? url + "&" + query : url + "?" + query;
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .timeout(Duration.ofSeconds(20))
                .header("User-Agent", USER_AGENT)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                return response.body();
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
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", USER_AGENT)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200){
                return response.body();
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .set("body", body);
        }
        return null;
    }

    public static String postBody(String url, Map<String, String> headers, String body) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", USER_AGENT)
                .POST(HttpRequest.BodyPublishers.ofString(body));
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder = builder.header(header.getKey(), header.getValue());
        }
        try {
            HttpRequest request = builder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                return response.body();
            } else {
                log.error("post请求失败,url={}, {}", url, response.statusCode());
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url)
                    .set("body", body);
        }
        return null;
    }

    public static String getWithHeaders(String url, Map<String, String> headers) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(20))
                .header("User-Agent", USER_AGENT)
                .GET();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder = builder.header(header.getKey(), header.getValue());
        }
        try {
            HttpResponse<String> response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                return response.body();
            } else {
                log.error("get请求失败,url={}, {}", url, response.statusCode());
            }
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.HTTP_REQUEST_ERROR.getCode(), ZeusExceptionEnum.HTTP_REQUEST_ERROR.getMsg(), e)
                    .set("url", url);
        }
        return null;
    }

}
