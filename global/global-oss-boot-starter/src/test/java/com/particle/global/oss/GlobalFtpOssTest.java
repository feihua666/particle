package com.particle.global.oss;

import com.particle.global.oss.client.GlobalOssClient;
import com.particle.global.oss.client.ftp.GlobalFtpOssClient;
import com.particle.global.oss.client.ftp.GlobalFtpOssProperties;

import static com.particle.global.oss.endpoint.OssController.API_DOWNLOAD_PREFIX;

/**
 * <p>
 * ftp 测试
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 10:02
 */
public class GlobalFtpOssTest extends GlobalOssTestBase {



	static GlobalOssClient globalFtpOssClient;

	static {
		GlobalFtpOssProperties globalFtpOssProperties = new GlobalFtpOssProperties();
		globalFtpOssProperties.setBasePath("/yw");
		globalFtpOssProperties.setHost("192.168.50.11");
		globalFtpOssProperties.setPort(21);
		globalFtpOssProperties.setUser("open");
		globalFtpOssProperties.setPassword("open123");
		globalFtpOssProperties.setEndpoint("http://localhost:8080" + API_DOWNLOAD_PREFIX);

		globalFtpOssClient = GlobalFtpOssClient.create(globalFtpOssProperties);

	}

	@Override
	public void test() {
		ossClient = globalFtpOssClient;
		testBucket = "ftptestbucket";
		super.test();
	}

	/**
	 * 使用minio进行测试
	 * 本测试使用
	 * @param args
	 */
	public static void main(String[] args) {
		new GlobalFtpOssTest().test();
	}

}
