package com.particle.openplatform.domain.providerrecord;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Data
@Entity
public class OpenplatformProviderRecord extends AggreateRoot {

    private OpenplatformProviderRecordId id;

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
    /**
     * 创建时间
     */
    private LocalDateTime createAt;


    /**
     * 创建开放平台开放接口供应商调用记录领域模型对象
     * @return 开放平台开放接口供应商调用记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformProviderRecord create(){
        return DomainFactory.create(OpenplatformProviderRecord.class);
    }
}