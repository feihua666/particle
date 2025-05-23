package com.particle.openplatform.domain.app;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台应用与开放接口配置 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Data
@Entity
public class OpenplatformAppOpenapi extends AggreateRoot {

    private OpenplatformAppOpenapiId id;

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 开放接口id，这里只存储接口，不存储分组
    */
    private Long openplatformOpenapiId;

    /**
    * 计费id，不配置不计费
    */
    private Long openplatformOpenapiFeeId;

	/**
	 * 限制规则id，不配置不限制，应用和接口级限制
	 */
	private Long openplatformOpenapiLimitRuleId;

	/**
	 * 指定供应商配置json
	 */
	private String specifyProviderConfigJson;

	/**
	 * 描述
	 */
	private String remark;



    /**
     * 创建开放平台应用与开放接口配置领域模型对象
     * @return 开放平台应用与开放接口配置领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformAppOpenapi create(){
        return DomainFactory.create(OpenplatformAppOpenapi.class);
    }
}
