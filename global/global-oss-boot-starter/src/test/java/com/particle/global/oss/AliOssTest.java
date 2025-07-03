package com.particle.global.oss;

import com.particle.global.oss.client.aws.GlobalAwsOssClient;
import com.particle.global.oss.client.aws.GlobalAwsOssProperties;

/**
 * <p>
 * 阿里oss使用aws sdk测试
 * </p>
 *
 * @author yangwei
 * @since 2025/7/3 14:03
 */
public class AliOssTest extends GlobalOssTestBase {



    static com.particle.global.oss.client.GlobalOssClient globalOssClient;

    static {
        GlobalAwsOssProperties globalAwsOssProperties = new GlobalAwsOssProperties();
        globalAwsOssProperties.setAccessKey("LTAI5tDuoiRKnw2yTJ9i9WXL");
        globalAwsOssProperties.setSecretKey("x0wPsMchlTUEzY3Whg8QPlW2K0oAoQ");
        // globalAwsOssProperties.setEndpoint("https://oss-cn-beijing.aliyuncs.com");
        // globalAwsOssProperties.setPathStyleAccessEnabled(false);
        globalAwsOssProperties.setEndpoint("https://pargj.oss-cn-beijing.aliyuncs.com");
        globalAwsOssProperties.setRegion("cn-beijing");


        globalOssClient = GlobalAwsOssClient.create(globalAwsOssProperties);

    }

    @Override
    public void test() {
        ossClient = globalOssClient;
        testBucket = "pargj";
        // super.test();
        putObjectTest();
    }

    /**
     * 使用minio进行测试
     * 本测试使用
     * @param args
     */
    public static void main(String[] args) {
        AliOssTest aliOssTest = new AliOssTest();
        aliOssTest.test();
    }

}
