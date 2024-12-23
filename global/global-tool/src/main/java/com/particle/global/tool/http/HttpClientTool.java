package com.particle.global.tool.http;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.io.IoStreamTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http Client工具类
 * @author yangwei
 * @since 2018/4/28 10:19
 */
@Slf4j
public class HttpClientTool{
    /**
     * 建立链接超时时间,毫秒
     * 连接建立时间，三次握手完成时间
     */
    public static final int CONN_TIMEOUT = 5000;
    public static final Timeout CONN_OF_TIMEOUT = Timeout.ofMilliseconds(CONN_TIMEOUT);
    /**
     * 从池中获取链接超时时间,毫秒
     * 和网络无关，这是httpClient池化技术的超时时间
     */
    public static final int CONN_REQUEST_TIMEOUT = 5000;
    public static final Timeout CONN_OF_REQUEST_TIMEOUT = Timeout.ofMilliseconds(CONN_REQUEST_TIMEOUT);
    /**
     * 响应超时时间，毫秒,httpclient5已不支持单个请求设置socketTimeout
     * 响应超时，即服务器处理请求的时间，超过此时间会抛出异常
     */
    public static final int REPSONSE_TIMEOUT = 5000;
    public static final Timeout REPSONSE_OF_TIMEOUT = Timeout.ofMilliseconds(REPSONSE_TIMEOUT);

    /**
     * 读取超时时间，毫秒，httpclient5 在设置连接池管理器中设置 socketTimout
     * 数据传输过程中数据包之间间隔的最大时间也可以理解为READ_TIMEOUT
     */
    public static final int SOCKET_TIMEOUT = 5000;
    public static final Timeout SOCKET_OF_TIMEOUT = Timeout.ofMilliseconds(SOCKET_TIMEOUT);

    public static final Charset CHARSET = StandardCharsets.UTF_8;
    private static volatile HttpClient CLIENT = null;
    // cookie默认存储
    private static final BasicCookieStore COOKIE_STORE = new BasicCookieStore();


    public static HttpClient getCLIENT() {
        if (CLIENT == null) {
            synchronized(HttpClientTool.class) {
                if (CLIENT == null) {
                    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                    cm.setMaxTotal(128);
                    cm.setDefaultMaxPerRoute(128);
                    ConnectionConfig connectionConfig = ConnectionConfig.custom()
                            .setConnectTimeout(CONN_OF_TIMEOUT)
                            .setSocketTimeout(SOCKET_OF_TIMEOUT)
                            .build();
                    SocketConfig socketConfig = SocketConfig.custom()
                            .setSoTimeout(SOCKET_OF_TIMEOUT) // 设置Socket超时时间
                            .build();
                    cm.setDefaultSocketConfig(socketConfig);
                    cm.setDefaultConnectionConfig(connectionConfig);

                    RequestConfig requestConfig = RequestConfig.custom()
                            .setConnectionRequestTimeout(CONN_OF_REQUEST_TIMEOUT)
                            .setResponseTimeout(REPSONSE_OF_TIMEOUT)
                            .build();
                    CLIENT = HttpClients.custom()
                            .setConnectionManager(cm)
                            .setDefaultRequestConfig(requestConfig)
                            .setDefaultCookieStore(COOKIE_STORE)
                            .build();
                }
            }
        }
        return CLIENT;
    }
    public static CookieStore getCookieStore() {
        return COOKIE_STORE;
    }

    /**
     * get请求
     * @param url 请求地址
     * @param extConfig 扩展配置
     * @return 请求结果
     * @throws IOException io异常，主要是访问异常
     * @throws ParseException 解析异常
     * @throws URISyntaxException 请求地址语法错误异常
     */
    public static String get(String url,ExtConfig extConfig) throws IOException, ParseException, URISyntaxException {
        log.debug("start get.url={},extConfig={}",url,extConfig == null? null : extConfig.toJsonString());
        HttpGet get = new HttpGet(url);
        String result = executeRequestAsString(get, getCLIENT(), extConfig);
        log.debug("end get.url={},result={}",url,result);
        return result;
    }


    /**
     * 以普通表单形式请求
     * @param url 请求地址
     * @param params 请求参数
     * @param extConfig 扩展配置
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String,String> params, ExtConfig extConfig) throws IOException, ParseException, URISyntaxException {
        log.debug("start postForm.url={},params={},extConfig={}",url,params,extConfig == null? null : extConfig.toJsonString());
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
        String result = executeRequestAsString(post, getCLIENT(), extConfig);
        log.debug("end postForm.url={},result={}",url,result);
        return result;

    }

    /**
     * 以 json 形式请求
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String body,ExtConfig extConfig) throws IOException, ParseException, URISyntaxException {
        log.debug("start postJson.url={},body={},extConfig={}",url,body,extConfig == null? null : extConfig.toJsonString());
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");

        // 创建参数列表
        if(StrUtil.isNotEmpty(body)){
            post.setEntity(new StringEntity(body,CHARSET));
        }

        String result = executeRequestAsString(post,getCLIENT(),extConfig);
        log.debug("end postJson.url={},result={}",url,result);
        return result;
    }
    /**
     * 以 json 形式请求
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String putJson(String url, String body,ExtConfig extConfig) throws IOException, ParseException, URISyntaxException {
        log.debug("start putJson.url={},body={},extConfig={}",url,body,extConfig == null? null : extConfig.toJsonString());
        HttpPut put = new HttpPut(url);
        put.addHeader("Content-Type", "application/json");

        // 创建参数列表
        if(StrUtil.isNotEmpty(body)){
            put.setEntity(new StringEntity(body,CHARSET));
        }

        String result = executeRequestAsString(put,getCLIENT(),extConfig);
        log.debug("end putJson.url={},result={}",url,result);
        return result;
    }
    /**
     * 获取状态码
     * @param res
     * @return
     */
    public static int HttpResponseStatus(CloseableHttpResponse res){
        return res.getCode();
    }

    /**
     * 响应内容转为字符串
     * @param res
     * @return
     * @throws IOException
     */
    public static String httpResponseContentToString(CloseableHttpResponse res) throws IOException, ParseException {
        HttpEntity entity = res.getEntity();
        return httpResponseEntityContentToString(entity);
    }
    /**
     * 响应内容转为字符串
     * @param entity
     * @return
     * @throws IOException
     */
    public static String httpResponseEntityContentToString(HttpEntity entity) throws IOException, ParseException {
        return EntityUtils.toString(entity, CHARSET);
    }
    /**
     * 下载并返回二进制数据
     * @param urlPath
     * @return
     * @throws IOException
     */
    public static byte[] download(String urlPath,ExtConfig extConfig) throws IOException, URISyntaxException {
        HttpClient client = HttpClientTool.getCLIENT();
        HttpGet get = new HttpGet(urlPath);
        CloseableHttpResponse closeableHttpResponse = executeRequest(get, client, extConfig);
        InputStream inputStream = closeableHttpResponse.getEntity().getContent();
        byte[] bytes = IoStreamTool.inputStreamToByteArray(inputStream);
        IoUtil.close(inputStream);
        IoUtil.close(closeableHttpResponse);
        return bytes;
    }

    /**
     * 发起请求
     * @param request
     * @param client
     * @param extConfig 扩展配置 可以为 null
     * @return
     * @throws IOException
     */
    public static CloseableHttpResponse executeRequest(HttpUriRequestBase request, HttpClient client,ExtConfig extConfig) throws IOException, URISyntaxException {

        if (extConfig != null) {
            RequestConfig requestConfig = request.getConfig();
            // 代理处理
            if (requestConfig == null || requestConfig.getProxy() == null) {
                // 添加 proxy
                HttpHost proxy = extConfig.getProxy();
                String proxyUser = extConfig.getProxyUser();
                String proxyPassword = extConfig.getProxyPassword();

                ProxyConfig proxyConfig = ProxyConfig.finalProxyConfig(extConfig.getProxyConfig());

                if (proxyConfig != null) {
                    boolean useProxy = proxyConfig.getUseProxy()!= null && proxyConfig.getUseProxy();
                    if (proxy == null && useProxy ) {
                        if (Strings.isNotEmpty(proxyConfig.getProxyType()) && !ProxyConfig.ProxyType.http.name().equals(proxyConfig.getProxyType())) {
                            log.warn("executeRequest config proxyType={} is not http,can not support likely! ",proxyConfig.getProxyType());
                        }

                        proxy = HttpHost.create(proxyConfig.getProxyAddress() + ":" + proxyConfig.getProxyPort());
                    }
                    if (StrUtil.isEmpty(proxyUser)) {
                        proxyUser = proxyConfig.getProxyUsername();
                    }
                    if (StrUtil.isEmpty(proxyPassword)) {
                        proxyPassword = proxyConfig.getProxyPassword();
                    }
                }


                if (proxy != null) {

                    if (StrUtil.isNotEmpty(proxyUser) && StrUtil.isNotEmpty(proxyPassword)) {
                        String auth = proxyUser + ":" + proxyPassword;
                        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(CHARSET));
                        String authHeader = "Basic " + new String(encodedAuth);
                        request.addHeader("Proxy-Authorization", authHeader);
                    }
                    RequestConfig.Builder custom = null;
                    if (requestConfig == null) {
                        custom = RequestConfig.custom();
                    }else {
                        custom = RequestConfig.copy(requestConfig);
                    }
                    custom.setProxy(proxy);
                    requestConfig = custom.build();
                    request.setConfig(requestConfig);
                }

            }
            // 超时时间处理

            if (extConfig.getConnTimeout() != null || extConfig.getConnRequestTimeout() != null || extConfig.getSocketTimeout() != null) {
                RequestConfig.Builder custom = null;
                if (requestConfig == null) {
                    custom = RequestConfig.custom();
                }else {
                    custom = RequestConfig.copy(requestConfig);
                }
                if (extConfig.getConnTimeout() != null) {
                    custom.setConnectTimeout(Timeout.ofMilliseconds(extConfig.getConnTimeout()));
                }
                if (extConfig.getConnRequestTimeout() != null) {
                    custom.setConnectionRequestTimeout(Timeout.ofMilliseconds(extConfig.getConnRequestTimeout()));
                }
                if (extConfig.getSocketTimeout() != null) {
                    custom.setResponseTimeout(Timeout.ofMilliseconds(extConfig.getResponseTimeout()));
                }
                requestConfig = custom.build();
                request.setConfig(requestConfig);
            }


            // cookie 处理
            if (extConfig.getCookie() != null) {
                request.addHeader("Cookie", extConfig.getCookie());
            }
            // 请求头处理
            List<Header> headers = extConfig.getHeaders();
            if (headers != null && !headers.isEmpty()) {
                headers.stream().forEach(header -> request.addHeader(header));
            }
        }

        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(request);
        return response;
    }

    public static String executeRequestAsString(HttpUriRequestBase request, HttpClient client, ExtConfig extConfig) throws IOException, ParseException, URISyntaxException {
        CloseableHttpResponse res = null;
        String r = null;
        try{
            res = executeRequest(request,client,extConfig);
            r = httpResponseContentToString(res);
        }finally {
            if (res != null) {
                res.close();
            }
        }
        return r;
    }

    @Data
    @Builder
    public static class ExtConfig{

        /**
         * 自定义代理
         */
        private ProxyConfig proxyConfig;

        // 代理地址
        private HttpHost proxy;
        // 请求cookie ,即：cookie值
        private String cookie;
        /**
         * 代理用户
         */
        private String proxyUser;
        /**
         * 代理密码
         */
        private String proxyPassword;

        /**
         * 请求头
         */
        private List<Header> headers;

        /**
         * 连接超时时间，ms
         */
        private Integer connTimeout;
        /**
         * 读取超时时间,从连接池中获取连接的超时时间，ms
         */
        private Integer connRequestTimeout;
        /**
         * 响应超时时间，ms
         */
        private Integer responseTimeout;
        /**
         * 读取超时时间，ms，注意httpclient5已不支持单独请求设置socketTimout，这里保留是只有在设置httpclient时可以设置，所以想在单次请求中支持，必须先建议一个亲的httpclient，但这样是耗资源的
         */
        private Integer socketTimeout;


        public ExtConfig withProxy(String proxyId, Integer proxyPort) throws URISyntaxException {
            this.proxy = HttpHost.create(proxyId + ":" + proxyPort);
            return this;
        }

        public ExtConfig withProxy(ProxyConfig proxyConfig) {
            this.proxyConfig = proxyConfig;
            return this;
        }

        public ExtConfig addHeader(String name, String value) {
            if (headers == null) {
                headers = new ArrayList<>();
            }
            headers.add(new BasicHeader(name,value));
            return this;
        }

        public String toJsonString() {
            return JsonTool.toJsonStr(this);
        }
        public static ExtConfig create() {
            return ExtConfig.builder().build();
        }
    }
}
