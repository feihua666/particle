package com.particle.data.domain.dynamictable;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据表格 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Data
@Entity
public class DynamicTable extends AggreateRoot {

    private DynamicTableId id;

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


    public void initForAdd(){
        this.isCreatedTable = true;
        this.dynamicTableFieldNum = 0;
        this.dynamicTableDataNum = 0;
    }

    /**
     * 创建动态数据表格领域模型对象
     * @return 动态数据表格领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicTable create(){
        return DomainFactory.create(DynamicTable.class);
    }
}
