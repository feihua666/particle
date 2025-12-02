package com.particle.data.domain.dynamictable;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 动态数据表格字段 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Data
@Entity
public class DynamicTableField extends AggreateRoot {

    private DynamicTableFieldId id;

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



    /**
     * 创建动态数据表格字段领域模型对象
     * @return 动态数据表格字段领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DynamicTableField create(){
        return DomainFactory.create(DynamicTableField.class);
    }
}