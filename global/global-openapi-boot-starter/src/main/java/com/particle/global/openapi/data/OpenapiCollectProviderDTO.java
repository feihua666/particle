package com.particle.global.openapi.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 供应商数据收集
 * </p>
 *
 * @author yangwei
 * @since 2023-08-17 11:33
 */
@Data
public class OpenapiCollectProviderDTO {

	/**
	 * 请求开始时间
	 */
	private LocalDateTime requestStartAt;
	/**
	 * 请求结束时间
	 */
	private LocalDateTime requestEndAt;
	/**
	 * 接口名称
	 */
	private String requestName;
	/**
	 * 请求地址
	 */
	private String requestUrl;

	/**
	 * 处理时长，单位毫秒
	 */
	private Integer handleDuration;

	/**
	 * 是否包含有效响应数据
	 */
	private Boolean isResponseHasEffectiveValue;


	/**
	 * 响应http状态码
	 */
	private Integer responseHttpStatus;


	/**
	 * 响应业务状态码
	 */
	private String responseBusinessStatus;

	/**
	 * 请求数据对象
	 */
	private Object requestParam;

	/**
	 * 查询参数，查询字符串
	 */
	private String queryString;

	/**
	 * 响应结果
	 */
	private Object responseResult;

	/**
	 * 供应商标识符，唯一确定一个供应商
	 */
	private String providerIdentifier;

	/**
	 * 是否命中缓存
	 */
	private Boolean isCacheHit;

	/**
	 * 客户id
	 */
	private Long customerId;
	/**
	 * 描述说明，备注
	 */
	private String remark;

	public static OpenapiCollectProviderDTO create(LocalDateTime requestStartAt,
												   LocalDateTime requestEndAt,
												   String requestName,
												   String requestUrl,
												   Integer handleDuration,
												   Boolean isResponseHasEffectiveValue,
												   Integer responseHttpStatus,
												   String responseBusinessStatus,
												   Object requestParam,
												   String queryString,
												   Object responseResult,
												   String providerIdentifier,
												   Boolean isCacheHit,Long customerId) {
		OpenapiCollectProviderDTO openapiCollectProviderDTO = new OpenapiCollectProviderDTO();

		openapiCollectProviderDTO.requestStartAt = requestStartAt;
		openapiCollectProviderDTO.requestEndAt = requestEndAt;
		openapiCollectProviderDTO.requestName = requestName;
		openapiCollectProviderDTO.requestUrl = requestUrl;
		openapiCollectProviderDTO.handleDuration = handleDuration;
		openapiCollectProviderDTO.isResponseHasEffectiveValue = isResponseHasEffectiveValue;
		openapiCollectProviderDTO.responseHttpStatus = responseHttpStatus;
		openapiCollectProviderDTO.responseBusinessStatus = responseBusinessStatus;
		openapiCollectProviderDTO.requestParam = requestParam;
		openapiCollectProviderDTO.queryString = queryString;
		openapiCollectProviderDTO.responseResult = responseResult;
		openapiCollectProviderDTO.providerIdentifier = providerIdentifier;
		openapiCollectProviderDTO.isCacheHit = isCacheHit;
		openapiCollectProviderDTO.customerId = customerId;

		return openapiCollectProviderDTO;
	}
}
