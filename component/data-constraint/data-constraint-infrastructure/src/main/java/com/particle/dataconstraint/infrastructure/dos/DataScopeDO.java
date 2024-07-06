package com.particle.dataconstraint.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 数据范围表
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Data
@TableName("component_data_scope")
public class DataScopeDO extends BaseDO {

    /**
    * 数据范围编码
    */
    private String code;

    /**
    * 数据范围名称
    */
    private String name;

    /**
    * 数据对象id
    */
    private Long dataObjectId;

	/**
	 * 约束条件内容类型，字典id
	 */
	private Long constraintContentTypeDictId;

    /**
    * 约束条件内容，暂时想到的用sql模板
    */
    private String constraintContent;

    /**
    * 是否自定义，如果自定义=1，否则为0
    */
    private Boolean isCustom;

    /**
    * 是否用于删除
    */
    private Boolean isForDelete;

    /**
    * 是否用于修改
    */
    private Boolean isForUpdate;

    /**
    * 是否用于查询
    */
    private Boolean isForQuery;

    /**
    * 是否用于其它，如：执行等除增、删、改、查的其它数据影响
    */
    private Boolean isForOther;

    /**
    * 描述
    */
    private String remark;


}