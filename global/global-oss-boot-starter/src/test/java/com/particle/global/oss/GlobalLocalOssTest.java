package com.particle.global.oss;

import com.particle.global.oss.client.GlobalOssClient;
import com.particle.global.oss.client.local.GlobalLocalOssClient;
import com.particle.global.oss.client.local.GlobalLocalOssProperties;

import static com.particle.global.oss.endpoint.OssController.API_DOWNLOAD_PREFIX;

/**
 * <p>
 * local 测试
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 10:02
 */
public class GlobalLocalOssTest extends GlobalOssTestBase{



	static GlobalOssClient globalLocalOssClient;

	static {
		GlobalLocalOssProperties globalLocalOssProperties = new GlobalLocalOssProperties();
		globalLocalOssProperties.setBasePath("/Users/yw/temp1");
		globalLocalOssProperties.setEndpoint("http://localhost:8080" + API_DOWNLOAD_PREFIX);

		globalLocalOssClient = GlobalLocalOssClient.create(globalLocalOssProperties);
	}

	@Override
	public void test() {
		ossClient = globalLocalOssClient;
		testBucket = "localtestbucket";
		super.test();
	}
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		new GlobalLocalOssTest().test();
	}

}
