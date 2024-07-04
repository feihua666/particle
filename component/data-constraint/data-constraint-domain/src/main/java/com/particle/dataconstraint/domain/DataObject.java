package com.particle.dataconstraint.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 数据对象 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Data
@Entity
public class DataObject extends AggreateRoot {

    private DataObjectId id;

    /**
    * 数据对象编码
    */
    private String code;

    /**
    * 数据对象名称
    */
    private String name;

    /**
    * 数据范围自定义时用来绑定自定义数据的url
    */
    private String customDataUrl;

    /**
    * 自定义数据是否懒加载,否则为一次性加载全部数据
    */
    private Boolean isCustomDataLazy;

    /**
    * 自定义数据交互方式，字典id，如：表格、树形
    */
    private Long customDataTypeDictId;

    /**
    * 数据交互方式内容
    */
    private String customDataConfigJson;

	/**
	 * 是否禁用
	 */
	private Boolean isDisabled;

	/**
	 * 禁用原因
	 */
	private String disabledReason;

    /**
    * 描述
    */
    private String remark;



    /**
     * 创建数据对象领域模型对象
     * @return 数据对象领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataObject create(){
        return DomainFactory.create(DataObject.class);
    }
}