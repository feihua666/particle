package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@Entity
public class OpenplatformOpenapi extends AggreateRoot {

    private OpenplatformOpenapiId id;

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 是否为组，1=组，0=接口
    */
    private Boolean isGroup;

    /**
    * 接口权限码
    */
    private String permissions;

    /**
    * 接口地址，以/开头，用于匹配
    */
    private String url;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;

    /**
    * 禁用原因
    */
    private String disabledReason;

    /**
    * 默认计费id，不配置不计费
    */
    private Long defaultOpenplatformOpenapiFeeId;

	/**
	 * 供应商配置json
	 */
	private String providerConfigJson;

    /**
    * 描述
    */
    private String remark;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建开放平台开放接口领域模型对象
     * @return 开放平台开放接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapi create(){
        return DomainFactory.create(OpenplatformOpenapi.class);
    }
}
