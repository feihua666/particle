package com.particle.global.openapi;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestAlgorithm;
import com.particle.global.tool.http.OpenApiClientTool;
import lombok.Data;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyPair;

/**
 * <p>
 * 开放接口测试
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 15:07
 */
public class OpenapiTest {

	/**
	 * 客户端id全局配置
	 */
	private static String clientId = "1688811472965656576";
	/**
	 * 客户端密码全局配置
	 */
	private static String clientSecret = "03b109817ddc4269a40d30ca12459511";

	private static String apiPrefix = "http://localhost:8080";

	/**
	 * 摘要算法配置，指使用的是哪一种摘要算法，这里以 sha256 举例
	 */
	private static DigestAlgorithm digestAlgorithm = DigestAlgorithm.SHA256;
	/**
	 * 签名算法配置，指使用的是哪一种签名算法，这里以 rsa 举例
	 */
	private static SignAlgorithm signAlgorithm = SignAlgorithm.NONEwithRSA;

	/**
	 * 签名私钥密码，针对不同的算法密钥不同
	 * 示例：
	 * rsa private key:MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1IOYV/g3GrwBs6YSn5WqHQKGSWD+zvBHGafHeB10QZoodE+ZQ0uVxO5OMT0qVV/MFmx7q/+bNrNVPsZw8jXvh4U9iaXu4gkMQJOtMSFhK4TTM3keoRh4lZ/5NVTBRuHw1eXy+MKysWnR2WqcafoOovXyiU02DCOt2N/2y5368dAgMBAAECgYBmfDl/E6fC2fdDxrapcyHrbofjgyS9bsGmhNO1y5VJeqq9GeTG/dQKoh3jGXeY5H7eRuq+dXlvv9vv44sEAjhPCRlekeXnL51RETMhyV68GB5UfVOMtx+6U6RERpCAPMUZgbIEvdritNRewt92asRoofkl0U/BAAaXM9sWJBJsAQJBANrxmNS2tXlbjJ/Gv2dhOn+9Ul3NP9Xa73PgGzA88S4G/iZbiSY89wNlMjytfe9W4gAXbsbmaNhZnhnDnE/q1tECQQC35vNzhrEvw+972BlxWHHWYJyuLJPqpX6jeD15oOOso5RnzqptagcXlpEloD6m66FTC9PUs8ngDQnGOmlPJP6NAkA0Mw+1/02q/mm7mj3+Hs88r12Y4ZuLmCQo41B4Ir95ss8bRA2h4T0fQRxGHTL+Qh+l1/BgWYS7uDoaeGmnIB1RAkApF54Gjm5BMhLtJVhqD01Ajc30zVkv8VLezBtQdjbpeJPMrk0+rZZwGuDL+4aEXdpqaBYpTRMq0T8B4ENvv+BxAkBAU+SWD8YLYbe4ZYIfiz0nQdOxOav7FhBBhr6IMdWc72vHn3w1vaGEhVft/wvcTDBbEqqz7qlCBPfa8VqRN2EF
	 * rsa public key:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdSDmFf4Nxq8AbOmEp+Vqh0Chklg/s7wRxmnx3gddEGaKHRPmUNLlcTuTjE9KlVfzBZse6v/mzazVT7GcPI174eFPYml7uIJDECTrTEhYSuE0zN5HqEYeJWf+TVUwUbh8NXl8vjCsrFp0dlqnGn6DqL18olNNgwjrdjf9sud+vHQIDAQAB
	 */
	private static String signPrivateKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdSDmFf4Nxq8AbOmEp+Vqh0Chklg/s7wRxmnx3gddEGaKHRPmUNLlcTuTjE9KlVfzBZse6v/mzazVT7GcPI174eFPYml7uIJDECTrTEhYSuE0zN5HqEYeJWf+TVUwUbh8NXl8vjCsrFp0dlqnGn6DqL18olNNgwjrdjf9sud+vHQIDAQAB";



	/**
	 * 测试入口方法
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, ParseException, URISyntaxException {
		KeyPair rsa = SecureUtil.generateKeyPair("RSA");
		System.out.println(StrUtil.str(rsa.getPrivate().getEncoded(),"utf-8"));
		System.out.println("rsa private key:" + Base64.encode(rsa.getPrivate().getEncoded()));
		System.out.println("rsa public key:" + Base64.encode(rsa.getPublic().getEncoded()));

		// 测试get请求
		testGet();
	}

	/**
	 * 测试 get 请求
	 */
	private static void testGet() throws IOException, ParseException, URISyntaxException {
		// 构造请求参数，这里以 OpenapiTestCommand 举例
		OpenapiTestCommand openapiTestCommand = testCommand();
		// 将对象参数转为请求字符串
		String queryString = toQueryString(openapiTestCommand);

		OpenApiClientTool.ClientConfig clientConfig = OpenApiClientTool.ClientConfig.createWithSHA256DigestAndNoSign(clientId,clientSecret);

		String testGetResponse = OpenApiClientTool.get(apiPrefix + "/openapi/testGet" , queryString, clientConfig,null);
		System.out.println("testGetResponse:" + testGetResponse);
	}


	private static OpenapiTestCommand testCommand() {

		OpenapiTestCommand openapiTestCommand = new OpenapiTestCommand();
		openapiTestCommand.setTestField1("testField1");
		openapiTestCommand.setTestField2("testField2");
		return openapiTestCommand;
	}

	private static String toQueryString(OpenapiTestCommand testCommand) {
		StringBuffer sb = new StringBuffer();
		sb.append("testField1").append("=").append(testCommand.getTestField1());
		sb.append("&");
		sb.append("testField2").append("=").append(testCommand.getTestField2());

		return sb.toString();
	}



	@Data
	private static class OpenapiTestCommand {

		/**
		 * 测试内容一个必填字段
		 */
		private String testField1;

		/**
		 * 测试内容一个选填字段
		 */
		private String testField2;
	}


}
