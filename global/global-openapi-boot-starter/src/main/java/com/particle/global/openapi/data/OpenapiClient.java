package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.util.Set;

/**
 * <p>
 * 开放接口客户端
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 17:13
 */
@Data
public class OpenapiClient extends DTO {


	/**
	 * 客户端id
	 */
	private String clientId;

	/**
	 * 密码，这里只是 clientId 对应的密钥，相当于密码
	 */
	private String secret;

	/**
	 * 是否检查摘要，如果为 false，则签名也不会激活
	 */
	private Boolean isCheckSignature = true;

	/**
	 * 请求算法
	 */
	private OpenapiAlgorithmSecret requestAlgorithmSecret;

	/**
	 * 响应算法
	 */
	private OpenapiAlgorithmSecret responseAlgorithmSecret;

	/**
	 * 权限信息
	 */
	private Set<String> authorities;

	/**
	 * 是否禁用
	 */
	private Boolean isDisabled;


	public static OpenapiClient create(String clientId,
									   String secret,
									   Boolean isCheckSignature,
									   OpenapiAlgorithmSecret requestAlgorithmSecret,
									   OpenapiAlgorithmSecret responseAlgorithmSecret,
									   Set<String> authorities,
									   Boolean isDisabled) {
		OpenapiClient openapiClient = new OpenapiClient();
		openapiClient.clientId = clientId;
		openapiClient.secret = secret;
		openapiClient.isCheckSignature = isCheckSignature;
		openapiClient.requestAlgorithmSecret = requestAlgorithmSecret;
		openapiClient.responseAlgorithmSecret = responseAlgorithmSecret;
		openapiClient.authorities = authorities;
		openapiClient.isDisabled = isDisabled;
		return openapiClient;
	}
}
