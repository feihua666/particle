package com.particle.global.openapi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 简单测试开放接口示例代码
 * </p>
 *
 * @author yangwei
 * @since 2024/7/12 18:05
 */
public class OpenapiSimpleTest {

    /**
     * 客户端id全局配置
     */
    private static String clientId = "1812440829352009728";
    /**
     * 客户端密码全局配置
     */
    private static String clientSecret = "1b6324b0a0a942449785fe794f88be98";


    public static void main(String[] args) {
        String response = postTest();
        // 打印一下结果
        System.out.println(response);
    }

    /**
     * 测试一下post方式请求
     */
    private static String postTest() {
        // 请求参数 请求体
        String body = postBody();
        // 请求流水号
        String nonce = UUID.randomUUID().toString();
        // 请求时间戳
        long timestamp = System.currentTimeMillis();
        // sha256摘要
        String sha256Hash = digestPostBody(nonce,timestamp,body);
        // 请求头
        Map<String, String> headers = headers(nonce, timestamp, sha256Hash);
        // 接口地址，请根据实际情况填写，此处以工商简项为例
        String apiUrl = "http://101.42.8.146/openapi/dq/data_company_md5_ids/ex/warehouse";
        String response = doPost(apiUrl,body,headers);
        return response;
    }

    /**
     * 请求参数
     * @return
     */
    private static String postBody() {
        // 请求体
        String body = "{\n" +
                "\"name\": \"碧桂园生活服务集团股份有限公司石狮分公司\",\n" +
                "\"uscc\": \"91350581MA31N8EL14\",\n" +
                "\"parentIdUuid0\": null\n" +
                "}";
        return body;
    }

    /**
     * 计算摘要
     * @param nonce
     * @param timestamp
     * @param body
     * @return
     */
    private static String digestPostBody(String nonce,long timestamp,String body) {
        StringBuffer sb = new StringBuffer();
        sb.append("clientId").append("=").append(clientId);
        sb.append("timestamp").append("=").append(timestamp);
        sb.append("nonce").append("=").append(nonce);
        sb.append(body);
        sb.append(clientSecret);
        String sha256Hash = getSHA256Hash(sb.toString());
        return sha256Hash;
    }

    /**
     * 请求头
     * @param nonce
     * @param timestamp
     * @param sha256Hash
     * @return
     */
    private static Map<String,String> headers(String nonce,long timestamp,String sha256Hash) {
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("clientId",clientId);
        headers.put("timestamp",timestamp + "");
        headers.put("nonce",nonce);
        headers.put("signature",sha256Hash);
        return headers;
    }
    /**
     * 请求数据
     * @param httpUrl
     * @param body
     * @param headers
     * @return
     */
    private static String doPost(String httpUrl, String body, Map<String,String> headers) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            for(Map.Entry<String,String> entry : headers.entrySet()){
                connection.setRequestProperty(entry.getKey(),entry.getValue());
            }
            os = connection.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            try {
                is = connection.getInputStream();
            } catch (IOException e) {
                is = connection.getErrorStream();
            }
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return result;
    }

    /**
     * sha256摘要
     * @param input
     * @return
     */
    public static String getSHA256Hash(String input) {
        try {
            // 创建MessageDigest实例，初始化为SHA-256算法
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 计算input字符串的字节
            byte[] messageDigest = md.digest(input.getBytes());
            // 创建Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            // 返回32个字符的十六进制数作为SHA-256哈希
            return hexString.toString();
        }
        // 处理NoSuchAlgorithmException异常
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
