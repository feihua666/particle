package com.particle.openplatform.domain.event;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 供应商调用记录
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 17:24
 */
@Data
public class OpenplatformOpenapiRecordDomainEventContentProviderRecord extends DTO {

	/**
	 * 接口地址，一般为http开头的绝对地址
	 */
	private String requestUrl;

	/**
	 * 请求参数md5
	 */
	private String requestParameterMd5;

	/**
	 * 响应结果md5
	 */
	private String responseResultMd5;

	/**
	 * 日志追踪id
	 */
	private String traceId;

	/**
	 * 处理时长，单位毫秒
	 */
	private Integer handleDuration;

	/**
	 * 是否包含有效响应数据
	 */
	private Boolean isResponseHasEffectiveValue;

	/**
	 * 响应http状态码，如果是http调用一般有值
	 */
	private Integer responseHttpStatus;

	/**
	 * 响应业务编码或业务状态码
	 */
	private String responseBusinessStatus;

	/**
	 * 供应商标识，唯一确定一个供应商
	 */
	private String providerIdentifier;

	/**
	 * 是否命中缓存
	 */
	private Boolean isCacheHit;
	/**
	 * 描述说明，备注
	 */
	private String remark;

	/**
	 * 供应商调用记录参数
	 */
	private OpenplatformOpenapiRecordDomainEventContentRecordParam recordParam;
}
