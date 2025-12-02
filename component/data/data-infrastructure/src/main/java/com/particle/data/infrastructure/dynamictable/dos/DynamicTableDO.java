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
 * 动态数据表格表
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_table")
public class DynamicTableDO extends BaseDO {

    /**
    * 表名称
    */
    private String name;

    /**
    * 表注释
    */
    private String comment;

    /**
    * 是否已建表，1=是，0=否
    */
    private Boolean isCreatedTable;

	/**
	 * 字段数量
	 */
	private Integer dynamicTableFieldNum;

	/**
	 * 数据数量
	 */
	private Integer dynamicTableDataNum;

	/**
	 * 备注信息
	 */
	private String remark;


}