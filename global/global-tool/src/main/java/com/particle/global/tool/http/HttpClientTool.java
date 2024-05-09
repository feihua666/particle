package com.particle.global.tool.http;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.io.IoStreamTool;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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
    // 响应超时时间,毫秒
    /**
     * 从池中获取链接超时时间,毫秒
     * 和网络无关，这是httpClient池化技术的超时时间
     */
    public static final int CONN_REQUEST_TIMEOUT = 5000;
    /**
     * 读取超时时间，毫秒
     * 数据传输过程中数据包之间间隔的最大时间也可以理解为READ_TIMEOUT
     */
    public static final int SOCKET_TIMEOUT = 5000;

    public static final String CHARSET = "UTF-8";
    private static volatile HttpClient CLIENT = null;
    // cookie默认存储
    private static BasicCookieStore COOKIE_STORE = new BasicCookieStore();


    public static HttpClient getCLIENT() {
        if (CLIENT == null) {
            synchronized(HttpClientTool.class) {
                if (CLIENT == null) {
                    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                    cm.setMaxTotal(128);
                    cm.setDefaultMaxPerRoute(128);
                    RequestConfig requestConfig = RequestConfig.custom()
                            .setConnectTimeout(CONN_TIMEOUT)
                            .setConnectionRequestTimeout(CONN_REQUEST_TIMEOUT)
                            .setSocketTimeout(SOCKET_TIMEOUT).build();
                    CLIENT = HttpClients.custom()
                            .setConnectionManager(cm)
                            .setDefaultRequestConfig(requestConfig)
                            .setDefaultCookieStore(COOKIE_STORE)
                            // todo 添加全局代理认证机制，以下代码先保留，待以后参考
                            //.setProxyAuthenticationStrategy()
                            /**
                             *
                             CredentialsProvider credsProvider = new BasicCredentialsProvider();
                             credsProvider.setCredentials(
                             new AuthScope(proxy),
                             new UsernamePasswordCredentials(proxyUser, proxyPassword));

                             DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
                             */
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
     * @param url
     * @param extConfig
     * @return
     * @throws IOException
     */
    public static String get(String url,ExtConfig extConfig) throws IOException {
        HttpGet get = new HttpGet(url);
        return executeRequestAsString(get,getCLIENT(),extConfig);
    }


    /**
     * 以普通表单形式请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String,String> params, ExtConfig extConfig) throws IOException {
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
        return executeRequestAsString(post,getCLIENT(),extConfig);

    }

    /**
     * 以 json 形式请求
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String postJson(String url, String body,ExtConfig extConfig) throws IOException {
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");

        // 创建参数列表
        if(StrUtil.isNotEmpty(body)){
            post.setEntity(new StringEntity(body,CHARSET));
        }

        return executeRequestAsString(post,getCLIENT(),extConfig);
    }
    /**
     * 以 json 形式请求
     * @param url
     * @param body
     * @return
     * @throws IOException
     */
    public static String putJson(String url, String body,ExtConfig extConfig) throws IOException {
        HttpPut put = new HttpPut(url);
        put.addHeader("Content-Type", "application/json");

        // 创建参数列表
        if(StrUtil.isNotEmpty(body)){
            put.setEntity(new StringEntity(body,CHARSET));
        }

        return executeRequestAsString(put,getCLIENT(),extConfig);
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
        return IoUtil.read(res.getEntity().getContent(), Charset.forName(CHARSET));
    }
    /**
     * 响应内容转为字符串
     * @param entity
     * @return
     * @throws IOException
     */
    public static String httpResponseEntityContentToString(HttpEntity entity) throws IOException {
        return IoUtil.read(entity.getContent(), Charset.forName(CHARSET));
    }
    /**
     * 下载并返回二进制数据
     * @param urlPath
     * @return
     * @throws IOException
     */
    public static byte[] download(String urlPath,ExtConfig extConfig) throws IOException {
        HttpClient client = HttpClientTool.getCLIENT();
        HttpGet get = new HttpGet(urlPath);
        CloseableHttpResponse closeableHttpResponse = executeRequest(get, client, null);
        InputStream inputStream = closeableHttpResponse.getEntity().getContent();
        byte[] bytes = IoStreamTool.inputStreamToByteArray(inputStream);
        inputStream.close();
        closeableHttpResponse.close();
        get.releaseConnection();
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
    public static CloseableHttpResponse executeRequest(HttpRequestBase request, HttpClient client,ExtConfig extConfig) throws IOException {

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
                        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName(CHARSET)));
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
                    custom.setConnectTimeout(extConfig.getConnTimeout());
                }
                if (extConfig.getConnRequestTimeout() != null) {
                    custom.setConnectionRequestTimeout(extConfig.getConnRequestTimeout());
                }
                if (extConfig.getSocketTimeout() != null) {
                    custom.setSocketTimeout(extConfig.getSocketTimeout());
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

    public static String executeRequestAsString(HttpRequestBase request, HttpClient client,ExtConfig extConfig) throws IOException {
        CloseableHttpResponse res = null;
        String r = null;
        try{
            res = executeRequest(request,client,extConfig);
            r = httpResponseContentToString(res);
        }finally {
            if (res != null) {
                res.close();
            }
            request.releaseConnection();
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
         * 连接超时时间
         */
        private Integer connTimeout;
        /**
         * 读取超时时间
         */
        private Integer connRequestTimeout;
        /**
         * 读取超时时间
         */
        private Integer socketTimeout;


        public ExtConfig withProxy(String proxyId, Integer proxyPort) {
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

        public static ExtConfig create() {
            return ExtConfig.builder().build();
        }
    }
}
