package com.particle.global.openapi.api;

import com.particle.global.openapi.data.*;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 * openApi接口，该接口是openapi的入口定义
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 14:44
 */
public interface OpenApi {

	/**
	 * 是否支持该请求
	 * @param request
	 * @return
	 */
	boolean support(HttpServletRequest request);


	/**
	 * 获取请求头信息
	 * @param request
	 * @return
	 */
	BasicHeaderDTO obtainBasicHeaderDTO(HttpServletRequest request);

	/**
	 * 获取请求业务数据参数
	 * @param request
	 * @return
	 */
	RequestParameterDTO obtainRequestParameterDTO(HttpServletRequest request);

	/**
	 * 获取客户端信息
	 * @param clientId
	 * @return
	 */
	OpenapiClient getOpenapiClient(String clientId,boolean includeSecret,boolean includeAuthorities);

	/**
	 * 获取接口信息
	 * @param apiUrl
	 * @param appId
	 * @return
	 */
	ApiInfo getApiInfo(String apiUrl,String appId);

	/**
	 * 签名，用于响应场景
	 * @param basicHeaderDTO
	 * @param responseDataDTO
	 * @param openapiClient
	 * @return 签名字符串
	 */
	String signature(BasicHeaderDTO basicHeaderDTO, ResponseDataDTO responseDataDTO, OpenapiClient openapiClient);

	/**
	 * 检查时间戳
	 * @param basicHeaderDTO
	 * @return
	 */
	boolean verifyTimestamp(BasicHeaderDTO basicHeaderDTO);

	/**
	 * 检查随机字符串
	 * @param basicHeaderDTO
	 * @return
	 */
	boolean verifyNonce(BasicHeaderDTO basicHeaderDTO);


	/**
	 * 签名校验，用于请求场景
	 * @param basicHeaderDTO
	 * @param requestParameterDTO
	 * @param openapiClient
	 * @return
	 */
	boolean verifySignature(BasicHeaderDTO basicHeaderDTO, RequestParameterDTO requestParameterDTO, OpenapiClient openapiClient);
}
