package com.particle.global.oss;

import com.particle.global.oss.client.GlobalOssClient;
import com.particle.global.oss.client.sftp.GlobalSftpOssClient;
import com.particle.global.oss.client.sftp.GlobalSftpOssProperties;

import static com.particle.global.oss.endpoint.OssController.API_DOWNLOAD_PREFIX;

/**
 * <p>
 * sftp 测试
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 10:02
 */
public class GlobalSftpOssTest extends GlobalOssTestBase {



	static GlobalOssClient globalSftpOssClient;

	static {
		GlobalSftpOssProperties globalSftpOssProperties = new GlobalSftpOssProperties();
		globalSftpOssProperties.setBasePath("/home/open/yw");
		globalSftpOssProperties.setHost("192.168.50.11");
		globalSftpOssProperties.setPort(22);
		globalSftpOssProperties.setUser("open");
		globalSftpOssProperties.setPassword("open123");
		globalSftpOssProperties.setEndpoint("http://localhost:8080" + API_DOWNLOAD_PREFIX);

		globalSftpOssClient = GlobalSftpOssClient.create(globalSftpOssProperties);

	}

	@Override
	public void test() {
		ossClient = globalSftpOssClient;
		testBucket = "sftptestbucket";
		super.test();
	}

	/**
	 * 使用minio进行测试
	 * 本测试使用
	 * @param args
	 */
	public static void main(String[] args) {
		new GlobalSftpOssTest().test();
	}

}
