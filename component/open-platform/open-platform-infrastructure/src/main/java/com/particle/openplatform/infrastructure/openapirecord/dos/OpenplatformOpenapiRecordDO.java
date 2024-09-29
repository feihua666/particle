package com.particle.openplatform.infrastructure.openapirecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
/**
 * <p>
 * 开放平台开放接口调用记录表
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Data
@TableName("component_openplatform_openapi_record")
public class OpenplatformOpenapiRecordDO extends BaseDO {

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 应用id
    */
    private String appId;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 是否为app调用，1=appId调用（app_id字段有值），0=用户页面调用（user_id字段有值）
    */
    private Boolean isApp;

    /**
    * 客户id
    */
    private Long customerId;

    /**
    * 开放接口id，这里只存储接口，不存储分组
    */
    private Long openplatformOpenapiId;

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
	 * 开始处理时间
	 */
	private LocalDateTime requestHandleAt;

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
	 * 是否存在供应商调用记录
	 */
	private Boolean isExistProviderRecord;

	/**
	 * 消费金额，单位分
	 */
	private Integer feeAmount;

	/**
	 * 消费金额缘由，字典id
	 */
	private Long feeReasonDictId;

	/**
	 * 描述
	 */
	private String remark;


}