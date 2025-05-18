package com.particle.openplatform.infrastructure.openapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口表
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Data
@TableName("component_openplatform_openapi")
public class OpenplatformOpenapiDO extends BaseTreeDO {

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


}
