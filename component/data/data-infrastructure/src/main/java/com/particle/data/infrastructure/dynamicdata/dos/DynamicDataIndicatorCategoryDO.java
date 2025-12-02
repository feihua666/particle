package com.particle.data.infrastructure.dynamicdata.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 动态数据指标分类表
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_data_indicator_category")
public class DynamicDataIndicatorCategoryDO extends BaseDO {

    /**
    * 动态数据分类id
    */
    private Long dynamicDataCategoryId;

    /**
    * 动态数据指标分类名称
    */
    private String name;

    /**
    * 是否禁用，1=是，0=否
    */
    private Boolean isDisabled;

    /**
    * 动态数据指标分类类型，字典id
    */
    private Long typeDictId;

	/**
	 * 指标数量
	 */
	private Integer dynamicDataIndicatorNum;

	/**
	 * 数据数量
	 */
	private Integer dynamicDataIndicatorCategoryDataNum;

    /**
    * 备注信息
    */
    private String remark;


}