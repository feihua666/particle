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
 * 动态数据分类表
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_data_category")
public class DynamicDataCategoryDO extends BaseDO {

    /**
    * 数据类型名称
    */
    private String name;

    /**
    * 是否禁用，1=是，0=否
    */
    private Boolean isDisabled;

    /**
    * 动态数据分类类型，字典id
    */
    private Long typeDictId;

    /**
    * 备注信息
    */
    private String remark;


}
