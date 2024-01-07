package com.particle.global.openapi.collect;

import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.api.OpenApi;
import com.particle.global.openapi.api.OpenapiHelper;
import com.particle.global.openapi.data.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 开放接口请求上下文
 * </p>
 *
 * @author yangwei
 * @since 2023-08-15 09:36
 */
@Data
public class OpenapiContext extends DTO {

	/**
	 * 请求开始时间
	 */
	private LocalDateTime requestStartAt;
	/**
	 * 请求结束时间
	 */
	private LocalDateTime requestEndAt;

	/**
	 * 用户使用 请求头参数认证处理，及校验签名
	 */
	private BasicHeaderDTO basicHeaderDTO;

	/**
	 * 针对使用 {@link OpenapiContext#basicHeaderDTO}，做一个判断处理的结果，如果参数不全会忽略，这里收集一下，未必后面有用
	 */
	private OpenapiHelper.IgnoreResult ignoreNormal;
	/**
	 * 针对使用 oauth2 bearer token,做一个判断处理的结果，如果没有认证，则会忽略
	 */
	private OpenapiHelper.IgnoreResult ignoreOauth2;

	/**
	 * 已经收集的所有的参数
	 */
	private RequestParameterDTO requestParameterDTO;

	/**
	 * 请求地址
	 */
	private String requestUrl;
	/**
	 * 请求对应的 客户端信息
	 */
	private OpenapiClient openapiClient;

	/**
	 * 接口配置信息
	 * 在 provider 中可能为空
	 */
	private ApiInfo apiInfo;

	/**
	 * 请求时日志追踪id
	 */
	private String traceId;

	/********************
	 *  以上信息在
	 *  {@link OpenapiHelper#requestStart(javax.servlet.http.HttpServletRequest, com.particle.global.openapi.api.OpenApi) }
	 *  中已处理完毕
	 *  ****************************/
	/********************
	 * 以下信息在
	 * {@link OpenapiHelper#requestStart(javax.servlet.http.HttpServletRequest, com.particle.global.openapi.api.OpenApi)}
	 * 到
	 * {@link OpenapiHelper#requestFinished(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.particle.global.openapi.api.OpenApi)}
	 * 过程中逐步收集
	 * ****************************/

	/**
	 * 响应结果
	 */
	private Object responseResult;

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
	 * 供应商收集结果
	 */
	private List<OpenapiCollectProviderDTO> providerDTOS;

	/**
	 * 异常
	 */
	private Throwable throwable;
	/**
	 * 是否命中缓存
	 */
	private Boolean isCacheHit;
	/**
	 * 描述说明，备注
	 */
	private String remark;

	/**
	 * 初始化供应商集合
	 */
	private void initProviderDTOS() {
		if (providerDTOS == null) {
			providerDTOS = new ArrayList<>();
		}
	}

	/**
	 * 添加供应商
	 * @param providerDTO
	 */
	public void addProviderDTO(OpenapiCollectProviderDTO providerDTO) {
		initProviderDTOS();
		providerDTOS.add(providerDTO);
	}

	public void addRemark(String remark) {
		if (this.remark == null) {
			this.remark = remark;
		}
		this.remark += remark;
	}
}
