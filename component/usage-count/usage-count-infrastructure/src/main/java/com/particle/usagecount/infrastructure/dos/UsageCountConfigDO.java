package com.particle.usagecount.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 使用次数配置表
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Data
@TableName("component_usage_count_config")
public class UsageCountConfigDO extends BaseDO {

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
	 * 超出提示信息
	 */
	private String exceedTip;

    /**
    * 备注
    */
    private String remark;


}