package com.particle.lowcode.client.generator.dto.command.representation;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@ApiModel
public class LowcodeModelItemPageQueryCommand extends AbstractBasePageQueryCommand {


    @Like
    @ApiModelProperty("字段名称")
    private String columnName;

    @Like
    @ApiModelProperty("实体属性名称")
    private String propertyName;

    @Like
    @ApiModelProperty("数据库类型")
    private String jdbcType;

    @Like
    @ApiModelProperty("实体属性类型")
    private String propertyType;

    @Like
    @ApiModelProperty("字段注释,完整的注释")
    private String commentFull;

    @Like
    @ApiModelProperty("字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger")
    private String commentSimple;

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

    @ApiModelProperty("字段是否外键")
    private Boolean isForeignKey;

    @ApiModelProperty("模型id")
    private Long lowcodeModelId;


}
