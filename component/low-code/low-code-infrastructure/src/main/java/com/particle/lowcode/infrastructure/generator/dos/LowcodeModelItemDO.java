package com.particle.lowcode.infrastructure.generator.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目表
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@TableName("component_lowcode_model_item")
public class LowcodeModelItemDO extends BaseDO {
    /**
    * 字段名称
    */
    private String columnName;
    /**
    * 实体属性名称
    */
    private String propertyName;
    /**
    * 数据库类型
    */
    private String jdbcType;
    /**
    * 实体属性类型
    */
    private String propertyType;
    /**
     * 实体属性类型全路径
     */
    private String propertyFullType;
    /**
    * 字段注释,完整的注释
    */
    private String commentFull;
    /**
    * 字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger
    */
    private String commentSimple;
    /**
    * 默认值
    */
    private String defaultValue;
    /**
    * 是否唯一，一般有唯一索引就是唯一，不算主键
    */
    private Boolean isUnique;
    /**
    * 是否必填
    */
    private Boolean isRequired;
    /**
    * 是否主键
    */
    private Boolean isKey;
    /**
    * 是否主键自增
    */
    private Boolean isKeyIdentity;
    /**
    * 是否为关键字
    */
    private Boolean isKeyWord;
    /**
    * 字段长度
    */
    private Integer columnLength;
    /**
    * 字段小数位长度
    */
    private Integer fractionLength;
    /**
    * 字段是否外键
    */
    private Boolean isForeignKey;
    /**
    * 模型id
    */
    private Long lowcodeModelId;
    /**
     * 设计json数据，包括一般CRUD相关配置信息
     */
    private String designJson;
    /**
    * 描述,注意事项等
    */
    private String remark;

}
