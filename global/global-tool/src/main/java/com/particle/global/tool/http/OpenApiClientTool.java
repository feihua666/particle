package com.particle.global.tool.http;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.Data;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 一个能够方便访问particle开放接口的工具类
 * </p>
 *
 * @author yangwei
 * @since 2024/9/14 17:18
 */
public class OpenApiClientTool {


    /**
     * 摘要算法映射
     */
    public static Map<DigestAlgorithm, Function<String, String>> digestFunctionMap = new HashMap<>();
    public static Map<SignAlgorithm, BiFunction<String, String, String>> signFunctionMap = new HashMap<>();
    static {
        digestFunctionMap.put(DigestAlgorithm.SHA256, (str) -> {
            Digester digester = new Digester(DigestAlgorithm.SHA256);
            return digester.digestHex(str);
        });

        signFunctionMap.put(SignAlgorithm.NONEwithRSA, (data, secret) -> {
            // 签名使用私钥签名
            Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithRSA, secret, null);
            return sign.signHex(data);
        });
    }
    /**
     * 发起 postJson 请求
     * @param url
     * @param queryString
     * @param clientConfig
     * @param extConfig
     * @return
     */
    public static String postJson(String url,String bodyParameters, String queryString, ClientConfig clientConfig, HttpClientTool.ExtConfig extConfig) throws IOException {
        // 摘要签名相关
        HeaderData headerData = HeaderData.create(clientConfig.getClientId(),clientConfig.getNonce());
        ParameterData parameterData = ParameterData.create(null, queryString, null, bodyParameters);
        // 摘要处理
        String digest = digestData(headerData, parameterData, clientConfig);

        String signature = null;
        if (clientConfig.isSign()) {
            //	签名逻辑
            // 签名使用私钥签名
            signature = OpenApiClientTool.signFunctionMap.get(clientConfig.getSignAlgorithm()).apply(digest, clientConfig.getSignPrivateKey());
        }else {
            signature = digest;
        }
        headerData.setSignature(signature);

        extConfig = extConfig(headerData,extConfig);

        if (StrUtil.isNotEmpty(queryString)) {
            url = url + "?" + queryString;
        }
        return HttpClientTool.postJson(url,bodyParameters, extConfig);
    }
    /**
     * 发起 postForm 请求
     * @param url
     * @param queryString
     * @param clientConfig
     * @param extConfig
     * @return
     */
    public static String postForm(String url,Map<String, String> formParameters, String queryString, ClientConfig clientConfig, HttpClientTool.ExtConfig extConfig) throws IOException {
        // 摘要签名相关
        HeaderData headerData = HeaderData.create(clientConfig.getClientId(),clientConfig.getNonce());
        ParameterData parameterData = ParameterData.create(null, queryString, formParameters, null);
        // 摘要处理
        String digest = digestData(headerData, parameterData, clientConfig);

        String signature = null;
        if (clientConfig.isSign()) {
            //	签名逻辑
            // 签名使用私钥签名
            signature = OpenApiClientTool.signFunctionMap.get(clientConfig.getSignAlgorithm()).apply(digest, clientConfig.getSignPrivateKey());
        }else {
            signature = digest;
        }
        headerData.setSignature(signature);

        extConfig = extConfig(headerData,extConfig);

        if (StrUtil.isNotEmpty(queryString)) {
            url = url + "?" + queryString;
        }
        return HttpClientTool.postForm(url,formParameters, extConfig);
    }
    /**
     * 发起get请求
     * @param url
     * @param queryString
     * @param clientConfig
     * @param extConfig
     * @return
     */
    public static String get(String url, String queryString, ClientConfig clientConfig, HttpClientTool.ExtConfig extConfig) throws IOException {
        // 摘要签名相关
        HeaderData headerData = HeaderData.create(clientConfig.getClientId(),clientConfig.getNonce());
        ParameterData parameterData = ParameterData.create(null, queryString, null, null);
        // 摘要处理
        String digest = digestData(headerData, parameterData, clientConfig);

        String signature = null;
        if (clientConfig.isSign()) {
            //	签名逻辑
            // 签名使用私钥签名
            signature = OpenApiClientTool.signFunctionMap.get(clientConfig.getSignAlgorithm()).apply(digest, clientConfig.getSignPrivateKey());
        }else {
            signature = digest;
        }
        headerData.setSignature(signature);

        extConfig = extConfig(headerData,extConfig);

        if (StrUtil.isNotEmpty(queryString)) {
            url = url + "?" + queryString;
        }
       return HttpClientTool.get(url, extConfig);
    }


    /**
     * 构建配置，主要是添加请求头
     * @param headerData
     * @return
     */
    private static HttpClientTool.ExtConfig extConfig(HeaderData headerData,HttpClientTool.ExtConfig extConfig) {
        if (extConfig == null) {
            extConfig = HttpClientTool.ExtConfig.create();
        }
        return extConfig
                .addHeader("clientId", headerData.getClientId())
                .addHeader("timestamp", headerData.getTimestamp() + "")
                .addHeader("nonce", headerData.getNonce())
                .addHeader("signature", headerData.getSignature());

    }


    /**
     * 对请求数据做摘要处理
     * @param headerData
     * @param parameterData
     * @param clientConfig
     * @return
     */
    private static String digestData(HeaderData headerData,ParameterData parameterData,ClientConfig clientConfig) {
        StringBuffer sb = new StringBuffer();
        sb.append(headerData.buildStringForSignature());
        sb.append(parameterData.buildStringForSignature());
        sb.append(clientConfig.getClientSecret());

        String finalStringForSignature = sb.toString();

        DigestAlgorithm digestAlgorithm = clientConfig.getDigestAlgorithm();
        Function<String, String> stringStringFunction = digestFunctionMap.get(digestAlgorithm);
        String digestHex = stringStringFunction.apply(finalStringForSignature);

        return digestHex;
    }

    /**
     * 将map转为待签名（可拼接）的字符串
     * 该方法来自于 {@link com.particle.global.openapi.tool.OpenapiTool#buildMapToStringForSignature(Map)}
     * @param map
     * @return
     */
    public static String buildMapToStringForSignature(Map<String,String> map) {
        StringBuffer sb = new StringBuffer();

        for (Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * 请求头数据
     */
    @Data
    private static class HeaderData {
        /**
         * 请求客户端id
         */
        private String clientId;
        /**
         * 请求时间戳
         */
        private Long timestamp;
        /**
         * 请求随机字符器，建议uuid
         */
        private String nonce;

        /**
         * 数据签名
         */
        private String signature;

        /**
         * 生成待签名的字符串
         * 请求头按如下顺序以 key=value 拼接：clientId、timestamp、nonce 如： clientId=xxxtimestamp=xxxnonce=xxx
         * @return
         */
        public String buildStringForSignature() {
            StringBuffer sb = new StringBuffer();
            sb.append("clientId").append("=").append(clientId);
            sb.append("timestamp").append("=").append(timestamp);
            sb.append("nonce").append("=").append(nonce);
            return sb.toString();
        }

        public static HeaderData create(String clientId,String nonce) {
            HeaderData headerData = new HeaderData();
            headerData.clientId = clientId;
            headerData.timestamp = System.currentTimeMillis();
            headerData.nonce = nonce == null ? UUID.fastUUID().toString(true) : nonce;

            return headerData;
        }
    }

    /**
     * 参数数据
     */
    @Data
    private static class ParameterData{

        /**
         * path参数，没有key,目前预留，
         * 如：spring mvc 的 /xxxxx/{name}/{id}
         * 则：按顺序存储 ['name值','id值']
         */
        private List<String> pathParameters;

        /**
         * 请求参数，不带问题
         * 如：name=张三&id=1
         */
        private String queryParameters;

        /**
         * 请求参数，一般用于form参数，请求 content-type = {@link MediaType#APPLICATION_FORM_URLENCODED_VALUE}
         */
        private Map<String, String> formParameters;

        /**
         * 请求参数，一般用于除form的情况，请求 content-type = {@link MediaType#APPLICATION_JSON_VALUE} 或 其它 String 类型
         */
        private String bodyParameters;


        /**
         * 生成待签名的字符串
         * 请求参数按如下顺序拼接：{@link pathParameters}、{@link queryParameters}、{@link formParameters}、{@link bodyParameters}
         * {@link pathParameters} 直接按顺序拼接
         * {@link queryParameters} 以 key=value 按key字典顺序拼接
         * {@link formParameters} 以 key=value 按key字典顺序拼接
         * {@link bodyParameters} 直接拼接
         * 将以上每一部分拼接完成的，按顺序整体拼接
         * @return
         */
        public String buildStringForSignature() {

            StringBuffer sb = new StringBuffer();
            // 按照path中的顺序将所有value进行拼接
            if (CollectionUtil.isNotEmpty(pathParameters)) {
                sb.append(pathParameters.stream().collect(Collectors.joining()));
            }
            // 按照key字典序排序，将所有key=value进行拼接
            if (StrUtil.isNotEmpty(queryParameters)) {
                String[] split = queryParameters.split("&");
                TreeMap<String, String> treeMap = Arrays.stream(split).map(item -> {
                    String[] splitKeyValue = item.split("=");
                    return Pair.<String, String>of(splitKeyValue[0], splitKeyValue[1]);
                }).collect(Collectors.toMap(Pair::getKey, Pair::getValue, (o, n) -> n, TreeMap::new));

                return buildMapToStringForSignature(treeMap);
            }
            // 按照key字典序排序，将所有key=value进行拼接
            if (CollectionUtil.isNotEmpty(formParameters)) {
                TreeMap<String, String> treeMap = MapUtil.sort(formParameters);
                return buildMapToStringForSignature(treeMap);
            }
            // 不拼接，直接按原始的值，因为这肯定是不变的
            if (StrUtil.isNotEmpty(bodyParameters)) {
                sb.append(bodyParameters);
            }
            return sb.toString();
        }

        public static ParameterData create(List<String> pathParameters,
                                           String queryParameters,
                                           Map<String, String> formParameters,
                                           String bodyParameters) {
            ParameterData requestParameterDTO = new ParameterData();
            requestParameterDTO.pathParameters = pathParameters;
            requestParameterDTO.queryParameters = queryParameters;
            requestParameterDTO.formParameters = formParameters;
            requestParameterDTO.bodyParameters = bodyParameters;
            return requestParameterDTO;
        }
    }

    @Data
    public static class ClientConfig{
        private String clientId;
        private String clientSecret;

        private DigestAlgorithm digestAlgorithm;

        /**
         * 配置是否签名
         */
        private boolean sign;

        private SignAlgorithm signAlgorithm;

        private String signPrivateKey;

        /**
         * 支持自定义nonce
         */
        private String nonce;

        public static ClientConfig createWithSHA256DigestAndNoSign(String clientId,
                                          String clientSecret){
            return create(clientId, clientSecret, DigestAlgorithm.SHA256, false, null, null);
        }
        public static ClientConfig create(String clientId,
                                          String clientSecret,
                                          DigestAlgorithm digestAlgorithm,
                                          boolean sign,
                                          SignAlgorithm signAlgorithm,
                                          String signPrivateKey
        ) {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.clientId = clientId;
            clientConfig.clientSecret = clientSecret;
            clientConfig.digestAlgorithm = digestAlgorithm;
            clientConfig.sign = sign;
            clientConfig.signAlgorithm = signAlgorithm;
            clientConfig.signPrivateKey = signPrivateKey;
            return clientConfig;

        }
    }
}
