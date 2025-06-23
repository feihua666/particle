package com.particle.openplatform.domain.event;

import com.particle.global.dto.basic.DTO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeValue;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 请求调用记录
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 17:24
 */
@Data
public class OpenplatformOpenapiRecordDomainEventContentRecord extends DTO {


	/**
	 * 记录唯一标识id
	 */
	private Long id;
	/**
	 * appId
	 */
	private String appId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 客户id
	 */
	private Long customerId;

	/**
	 * 是否为app调用，1=appId调用（app_id字段有值），0=用户页面调用（user_id字段有值）
	 */
	private Boolean isApp;

	/**
	 * 接口地址，空余 openplatform_openapi_id对应的url
	 */
	private String requestUrl;

	/**
	 * 请求时间戳，单位毫秒
	 */
	private Long requestTimestamp;

	/**
	 * 请求流水号
	 */
	private String requestNonce;

	/**
	 * 请求签名,不校验时可以不传，oauth2方式可以不传
	 */
	private String requestSignature;

	/**
	 * 请求参数md5
	 */
	private String requestParameterMd5;
	/**
	 * 请求开始时间
	 */
	private LocalDateTime requestStartAt;
	/**
	 * 请求结束时间
	 */
	private LocalDateTime requestEndAt;
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
	 * 响应http状态码
	 */
	private Integer responseHttpStatus;

	/**
	 * 响应业务状态码
	 */
	private String responseBusinessStatus;

	/**
	 * 描述说明，备注
	 */
	private String remark;

	/**
	 * 接口费用信息
	 */
	OpenplatformOpenapiFeeValue openplatformOpenapiFee;
	/**
	 * 请求调用记录参数
	 */
	private OpenplatformOpenapiRecordDomainEventContentRecordParam recordParam;
}
