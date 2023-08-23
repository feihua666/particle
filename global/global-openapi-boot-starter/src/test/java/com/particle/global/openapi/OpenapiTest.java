package com.particle.global.openapi;

import cn.hutool.core.codec.Base64;
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
import com.particle.global.openapi.tool.OpenapiTool;
import com.particle.global.tool.http.HttpClientTool;
import lombok.Data;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.security.KeyPair;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	private static String clientId = "11";
	/**
	 * 客户端密码全局配置
	 */
	private static String clientSecret = "22";

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
	 * 摘要算法映射
	 */
	private static Map<DigestAlgorithm, Function<String, String>> digestFunctionMap = new HashMap<>();
	private static Map<SignAlgorithm, BiFunction<String, String, String>> signFunctionMap = new HashMap<>();
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
	 * 测试入口方法
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
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
	private static void testGet() throws IOException {
		// 构造请求参数，这里以 OpenapiTestCommand 举例
		OpenapiTestCommand openapiTestCommand = testCommand();
		// 将对象参数转为请求字符串
		String queryString = toQueryString(openapiTestCommand);

		// 摘要签名相关
		HeaderData headerData = HeaderData.create();
		ParameterData parameterData = ParameterData.create(null, queryString, null, null);
		ClientConfig clientConfig = ClientConfig.create();

		// 摘要处理
		String digest = digestData(headerData, parameterData, clientConfig);

		String signature = null;
		if (clientConfig.isSign()) {
		//	签名逻辑
			// 签名使用私钥签名
			signature = signFunctionMap.get(clientConfig.getSignAlgorithm()).apply(digest, clientConfig.getSignPrivateKey());
		}else {
			signature = digest;
		}
		headerData.setSignature(signature);


		HttpClientTool.ExtConfig extConfig = extConfig(headerData);

		String testGetResponse = HttpClientTool.get(apiPrefix + "/openapi/testGet?" + queryString, extConfig);
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

	/**
	 * 构建配置，主要是添加请求头
	 * @param headerData
	 * @return
	 */
	private static HttpClientTool.ExtConfig extConfig(HeaderData headerData) {
		return HttpClientTool.ExtConfig.create()
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

		public static HeaderData create() {
			HeaderData headerData = new HeaderData();
			headerData.clientId = OpenapiTest.clientId;
			headerData.timestamp = System.currentTimeMillis();
			headerData.nonce = UUID.fastUUID().toString(true);

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

				return OpenapiTool.buildMapToStringForSignature(treeMap);
			}
			// 按照key字典序排序，将所有key=value进行拼接
			if (CollectionUtil.isNotEmpty(formParameters)) {
				TreeMap<String, String> treeMap = MapUtil.sort(formParameters);
				return OpenapiTool.buildMapToStringForSignature(treeMap);
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
	private static class ClientConfig{
		private String clientId = OpenapiTest.clientId;
		private String clientSecret = OpenapiTest.clientSecret;

		private DigestAlgorithm digestAlgorithm = OpenapiTest.digestAlgorithm;

		/**
		 * 配置是否签名
		 */
		private boolean sign;

		private SignAlgorithm signAlgorithm = OpenapiTest.signAlgorithm;

		private String signPrivateKey = OpenapiTest.signPrivateKey;

		public static ClientConfig create() {
			return new ClientConfig();
		}
	}
}
