package com.particle.openplatform.infrastructure.providerrecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商调用记录表
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Data
@TableName("component_openplatform_provider_record")
public class OpenplatformProviderRecordDO extends BaseDO {

    /**
    * 调用记录id
    */
    private Long openplatformOpenapiRecordId;

    /**
    * 客户id
    */
    private Long customerId;

    /**
    * 接口地址，一般为http开头的绝对地址
    */
    private String requestUrl;

    /**
    * 请求参数md5
    */
    private String requestParameterMd5;

	/**
	 * 开始请求时间
	 */
	private LocalDateTime requestAt;

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
    * 供应商id
    */
    private Long openplatformProviderId;

    /**
    * 数据查询供应商id，冗余openplatform_provider同名字段
    */
    private Long dataQueryProviderId;

	/**
	 * 是否命中缓存
	 */
	private Boolean isCacheHit;

	/**
	 * 描述
	 */
	private String remark;


}