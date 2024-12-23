package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Schema
public class LowcodeModelItemUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "实体属性名称")
    private String propertyName;

    @Schema(description = "数据库类型")
    private String jdbcType;

    @Schema(description = "实体属性类型")
    private String propertyType;

    @Schema(description = "实体属性类型全路径")
    private String propertyFullType;

    @Schema(description = "字段注释,完整的注释")
    private String commentFull;

    @Schema(description = "字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger")
    private String commentSimple;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "是否唯一，一般有唯一索引就是唯一，不算主键")
    private Boolean isUnique;

    @Schema(description = "是否必填")
    private Boolean isRequired;

    @Schema(description = "是否主键")
    private Boolean isKey;

    @Schema(description = "是否主键自增")
    private Boolean isKeyIdentity;

    @Schema(description = "是否为关键字")
    private Boolean isKeyWord;

    @Schema(description = "字段长度")
    private Integer columnLength;

    @Schema(description = "字段小数位长度")
    private Integer fractionLength;

    @Schema(description = "字段是否外键")
    private Boolean isForeignKey;

    @SetNullWhenNull
    @Schema(description = "模型id")
    private Long lowcodeModelId;

    @Schema(title = "设计json数据",description = "包括一般CRUD相关配置信息")
    private String designJson;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
