package com.particle.openplatform.domain.app;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台应用 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Data
@Entity
public class OpenplatformApp extends AggreateRoot {

    private OpenplatformAppId id;

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



    /**
     * 创建开放平台应用领域模型对象
     * @return 开放平台应用领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformApp create(){
        return DomainFactory.create(OpenplatformApp.class);
    }
}