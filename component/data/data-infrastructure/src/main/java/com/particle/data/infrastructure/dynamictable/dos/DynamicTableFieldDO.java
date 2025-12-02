package com.particle.data.infrastructure.dynamictable.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 动态数据表格字段表
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_table_field")
public class DynamicTableFieldDO extends BaseDO {

    /**
    * 动态数据表格id
    */
    private Long dynamicTableId;

    /**
    * 字段名称
    */
    private String name;

    /**
    * 字段注释
    */
    private String comment;

    /**
    * 字段类型,如：varchar(100)
    */
    private String type;

    /**
    * 是否必填,1=是，0=否
    */
    private Boolean isRequired;

	/**
	 * 字段默认值
	 */
	private String defaultValue;


}