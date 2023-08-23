package com.particle.global.openapi.data;

import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.enums.OpenapiDigestAlgorithm;
import com.particle.global.openapi.enums.OpenapiSignatureAlgorithm;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;

/**
 * <p>
 * 算法密钥相关存储
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 09:50:26
 */
@Data
public class OpenapiAlgorithmSecret extends DTO {


	/**
	 * 摘要算法，必须配置
	 */
	private OpenapiDigestAlgorithm digestAlgorithm;
	/**
	 * 是否签名，否则只做摘要
	 * 签名是将摘要字符串作为原始数据加密，本项目中并没有使用原始数据直接签名
	 */
	private Boolean isSign;

	/**
	 * 签名算法
	 */
	private OpenapiSignatureAlgorithm signatureAlgorithm;

	/**
	 * 签名密钥，针对响应场景这里存储的是私钥，原则就是私钥签名，公钥验签
	 * 响应场景：一般三方请求我们，我们响应数据，我们需要对数据签名，我们需要使用私钥加签，三方需要使用公钥验签，如果使用 hmac算法做数字签名，则不存在公私钥对，即一样的密码
	 * 如果接口不签名可不使用
	 */
	private String privateSignSecret;
	/**
	 * 签名密钥，针对请求场景这里存储的是公钥，原则就是私钥签名，公钥验签
	 * 请求场景：一般三方请求我们，我们需要验证签名，三方需要使用私钥加签，我们需要使用公钥验签，如果使用 hmac算法做数字签名，则不存在公私钥对，即一样的密码
	 * 如果接口不签名可不使用
	 */
	private String publicSignSecret;


	/**
	 * 创建
	 * @param isSign
	 * @param privateSignSecret
	 * @param publicSignSecret
	 * @return
	 */
	public static OpenapiAlgorithmSecret create(
											  OpenapiDigestAlgorithm digestAlgorithm,
											  Boolean isSign,
											  OpenapiSignatureAlgorithm signatureAlgorithm,
											  String privateSignSecret,
											  String publicSignSecret) {
		OpenapiAlgorithmSecret result = new OpenapiAlgorithmSecret();
		result.digestAlgorithm = digestAlgorithm;
		result.isSign = isSign == null ? false : isSign;
		result.signatureAlgorithm = signatureAlgorithm;
		result.privateSignSecret = privateSignSecret;
		result.publicSignSecret = publicSignSecret;

		return result;

	}

	/**
	 * 创建不使用签名的密钥
	 * @param digestAlgorithm
	 * @return
	 */
	public static OpenapiAlgorithmSecret create(
											  OpenapiDigestAlgorithm digestAlgorithm) {
		return create(digestAlgorithm, false, null, null, null);
	}

	public static OpenapiAlgorithmSecret createFromJsonStr(String jsonStr) {
		if (JsonTool.isJsonStrEmpty(jsonStr)) {
			return null;
		}
		return JSONUtil.toBean(jsonStr, OpenapiAlgorithmSecret.class);
	}
}
