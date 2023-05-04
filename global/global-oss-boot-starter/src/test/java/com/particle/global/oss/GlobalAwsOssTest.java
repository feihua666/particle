package com.particle.global.oss;

import com.particle.global.oss.client.aws.GlobalAwsOssClient;
import com.particle.global.oss.client.aws.GlobalAwsOssProperties;

/**
 * <p>
 * aws 测试
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 10:02
 */
public class GlobalAwsOssTest extends GlobalOssTestBase {



	static com.particle.global.oss.client.GlobalOssClient globalOssClient;

	static {
		GlobalAwsOssProperties globalAwsOssProperties = new GlobalAwsOssProperties();
		globalAwsOssProperties.setAccessKey("2CZ2ONDVR7TFhx1T");
		globalAwsOssProperties.setSecretKey("CEXO4mWwZVfbCdpxqjdEI5YfCZy09Ccw");
		globalAwsOssProperties.setEndpoint("http://localhost:9000");
		globalAwsOssProperties.setRegion("noneForMinio");

		globalOssClient = GlobalAwsOssClient.create(globalAwsOssProperties);

	}

	@Override
	public void test() {
		ossClient = globalOssClient;
		testBucket = "awstestbucket";
		super.test();
	}

	/**
	 * 使用minio进行测试
	 * 本测试使用
	 * @param args
	 */
	public static void main(String[] args) {
		GlobalAwsOssTest globalAwsOssTest = new GlobalAwsOssTest();
		globalAwsOssTest.test();
	}

}
