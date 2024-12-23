package com.particle.openplatform.infrastructure.app.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放平台应用表
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Data
@TableName("component_openplatform_app")
public class OpenplatformAppDO extends BaseDO {

    /**
    * 名称
    */
    private String name;

    /**
    * appId，冗余ouath2的clientId
    */
    private String appId;

    /**
    * 归属用户id，预留
    */
    private Long ownerUserId;

    /**
    * 归属客户id，预留
    */
    private Long ownerCustomerId;

    /**
    * 请求算法与密钥等相关配置，最终对应 com.particle.global.openapi.data.OpenapiAlgorithmSecret
    */
    private String requestAlgorithmSecretJson;

    /**
    * 响应算法与密钥等相关配置，最终对应 com.particle.global.openapi.data.OpenapiAlgorithmSecret
    */
    private String responseAlgorithmSecretJson;

    /**
    * 访问范围配置，方便单独指定
    */
    private String scopes;

    /**
    * 计费id，不配置不计费
    */
    private Long openplatformOpenapiFeeId;

	/**
	 * 限制规则id，不配置不限制，应用级限制
	 */
	private Long openplatformOpenapiLimitRuleId;

	/**
	 * 是否禁用
	 */
	private Boolean isDisabled;

	/**
	 * 禁用原因
	 */
	private String disabledReason;

	/**
	 * 是否检查签名,主要用于在oauth2 token时可以不检查
	 */
	private Boolean isCheckSignature;


}
