package com.particle.global.tool.http;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.io.IoStreamTool;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http Client工具类
 * Created by yangwei
 * Created at 2018/4/28 10:19
 */
public class HttpClientTool{
    // 建立链接超时时间,毫秒
    public static final int CONN_TIMEOUT = 5000;
    // 响应超时时间,毫秒
    public static final int READ_TIMEOUT = 5000;
    // 响应超时时间,毫秒
    public static final int SOCKET_TIMEOUT = 5000;
    public static final String CHARSET = "UTF-8";
    private static HttpClient CLIENT = null;
    private static BasicCookieStore COOKIE_STORE;
    static {
        COOKIE_STORE = new BasicCookieStore();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONN_TIMEOUT).setConnectionRequestTimeout(READ_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        CLIENT = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).setDefaultCookieStore(COOKIE_STORE).build();
    }

    public static String httpGet(String url) throws IOException {
        return httpGet(url,null);
    }
    public static String httpGet(String url, List<Header> headers) throws IOException {
        HttpGet get = new HttpGet(url);
        if (headers != null && !headers.isEmpty()) {
            headers.stream().forEach(header -> get.addHeader(header));
        }
        HttpResponse res = null;
        String r = null;
        try{
            res = CLIENT.execute(get);
            r = httpResponseContentToString(res);
        }finally {
            get.releaseConnection();
        }
        return r;
    }
    public static HttpResponse httpGetWithResponse(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        HttpResponse res = null;
        try{
            res = CLIENT.execute(get);
        }finally {
            get.releaseConnection();
        }
        return res;
    }
    public static HttpClient getCLIENT() {
        return CLIENT;
    }
    public static CookieStore getCookieStore() {
        return COOKIE_STORE;
    }

    /**
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String httpPost(String url,Map<String,String> params) throws IOException {
        HttpPost post = new HttpPost(url);
        // 创建参数列表
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (String key : params.keySet()) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
            // 模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, CHARSET);
            post.setEntity(entity);
        }
        HttpResponse res = null;
        String r = null;
        try{
            res = CLIENT.execute(post);
            r = httpResponseContentToString(res);
        }finally {
            post.releaseConnection();
        }
        return r;
    }

    /**
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static HttpResponse httpPostWithResponse(String url,Map<String,String> params) throws IOException {
        HttpPost post = new HttpPost(url);
        // 创建参数列表
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (String key : params.keySet()) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
            // 模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, CHARSET);
            post.setEntity(entity);
        }
        HttpResponse res = null;
        String r = null;
        try{
            res = CLIENT.execute(post);
        }finally {
            post.releaseConnection();
        }
        return res;
    }
    /**
     *
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String httpPostJson(String url,String body, List<Header> headers) throws IOException {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        if (headers != null && !headers.isEmpty()) {
            headers.stream().forEach(header -> post.addHeader(header));
        }
        // 创建参数列表
        if(StrUtil.isNotEmpty(body)){
            post.setEntity(new StringEntity(body,"utf-8"));
        }
        HttpResponse res = null;
        String r = null;
        try{
            res = CLIENT.execute(post);
            r = httpResponseContentToString(res);
        }finally {
            post.releaseConnection();
        }
        return r;
    }

    /**
     * 获取状态码
     * @param res
     * @return
     */
    public static int HttpResponseStatus(HttpResponse res){
        return res.getStatusLine().getStatusCode();
    }

    /**
     * 响应内容转为字符串
     * @param res
     * @return
     * @throws IOException
     */
    public static String httpResponseContentToString(HttpResponse res) throws IOException {
        return IoUtil.read(res.getEntity().getContent(), CHARSET);
    }

    /**
     * 下载
     * @param urlPath
     * @return
     * @throws IOException
     */
    public static byte[] download(String urlPath) throws IOException {
        HttpClient client = HttpClientTool.getCLIENT();
        HttpGet get = new HttpGet(urlPath);
        HttpResponse httpResponse =  client.execute(get);
        InputStream inputStream = httpResponse.getEntity().getContent();
        byte[] bytes = IoStreamTool.inputStreamToByteArray(inputStream);
        get.releaseConnection();
        return bytes;
    }
}
