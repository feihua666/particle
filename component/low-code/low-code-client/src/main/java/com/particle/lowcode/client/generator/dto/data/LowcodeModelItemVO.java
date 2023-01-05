package com.particle.lowcode.client.generator.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelItemVO extends AbstractBaseIdVO {


    @ApiModelProperty("字段名称")
    private String columnName;

    @ApiModelProperty("实体属性名称")
    private String propertyName;

    @ApiModelProperty("数据库类型")
    private String jdbcType;

    @ApiModelProperty("实体属性类型")
    private String propertyType;

    @ApiModelProperty("字段注释,完整的注释")
    private String commentFull;

    @ApiModelProperty("字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger")
    private String commentSimple;

    @ApiModelProperty("默认值")
    private String defaultValue;

    @ApiModelProperty("是否唯一，一般有唯一索引就是唯一，不算主键")
    private Boolean isUnique;

    @ApiModelProperty("是否必填")
    private Boolean isRequired;

    @ApiModelProperty("是否主键")
    private Boolean isKey;

    @ApiModelProperty("是否主键自增")
    private Boolean isKeyIdentity;

    @ApiModelProperty("是否为关键字")
    private Boolean isKeyWord;

    @ApiModelProperty("字段长度")
    private Integer columnLength;

    @ApiModelProperty("字段小数位长度")
    private Integer fractionLength;

    @ApiModelProperty("字段是否外键")
    private Boolean isForeignKey;

    @ApiModelProperty("模型id")
    private Long lowcodeModelId;

    @TransBy(tableName = TransTableNameConstants.component_lowcode_model, byFieldName = "lowcodeModelId", mapValueField = "name")
    @ApiModelProperty("模型名称")
    private String lowcodeModelName;

    @ApiModelProperty("描述,注意事项等")
    private String remark;


}
