package com.particle.openplatform.infrastructure.app.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放平台应用与开放接口配置表
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Data
@TableName("component_openplatform_app_openapi")
public class OpenplatformAppOpenapiDO extends BaseDO {

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
	 * 指定供应商，如果不指定将按默认编码调用
	 */
	private Long openplatformProviderId;

	/**
	 * 描述
	 */
	private String remark;


}
