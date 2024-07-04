package com.particle.dataconstraint.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 数据对象表
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Data
@TableName("component_data_object")
public class DataObjectDO extends BaseDO {

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


}