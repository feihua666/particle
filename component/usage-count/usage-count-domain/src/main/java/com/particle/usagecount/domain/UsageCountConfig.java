package com.particle.usagecount.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 使用次数配置 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Data
@Entity
public class UsageCountConfig extends AggreateRoot {

    private UsageCountConfigId id;

    /**
    * 名称,模糊查询
    */
    private String name;

    /**
    * 使用次数定义id
    */
    private Long usageCountDefineId;

    /**
    * 限制的最大使用次数，0或不填写不限制
    */
    private Integer limitCount;

    /**
    * 限制规则类型字典id
    */
    private Long limitRuleTypeDictId;

    /**
    * 限制周期字典id
    */
    private Long limitPeriodDictId;

	/**
	 * 限制租户id，如果为空代表是全局的设置
	 */
	private Long limitTenantId;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建使用次数配置领域模型对象
     * @return 使用次数配置领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static UsageCountConfig create(){
        return DomainFactory.create(UsageCountConfig.class);
    }
}