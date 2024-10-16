package com.particle.openplatform.infrastructure.openapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 开放平台开放接口限制规则表
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleDO extends BaseDO {

    /**
    * 限制名称
    */
    private String name;

    /**
    * 限制方式，字典id，如：按条限制，按金额费用限制，不限制有等
    */
    private Long limitTypeDictId;

    /**
    * 限制条数
    */
    private Integer limitCount;

    /**
    * 限制金额费用，单位分
    */
    private Integer limitFee;

    /**
    * 限制周期，按天限制、按周限制等
    */
    private Long limitPeriodDictId;

    /**
    * 限制速率，即qps
    */
    private Integer limitRate;

    /**
    * 描述
    */
    private String remark;


}
