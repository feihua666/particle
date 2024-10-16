package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口限制规则 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Data
@Entity
public class OpenplatformOpenapiLimitRule extends AggreateRoot {

    private OpenplatformOpenapiLimitRuleId id;

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



    /**
     * 创建开放平台开放接口限制规则领域模型对象
     * @return 开放平台开放接口限制规则领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiLimitRule create(){
        return DomainFactory.create(OpenplatformOpenapiLimitRule.class);
    }
}
