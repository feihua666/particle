package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 低代码片段模板 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@ApiModel
public class LowcodeSegmentTemplateCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty("编码，唯一")
    private String code;

    @NotEmpty(message = "模板名称不能为空")
    @ApiModelProperty("模板名称，仅做展示")
    private String name;

    @ApiModelProperty("名称模板")
    private String nameTemplate;

    @ApiModelProperty("名称输出变量名")
    private String nameOutputVariable;

    @ApiModelProperty("内容模板")
    private String contentTemplate;

    @ApiModelProperty("引用模板id")
    private Long referenceSegmentTemplateId;

    @NotEmpty(message = "输出类型字典id不能为空")
    @ApiModelProperty("输出类型字典id，file=文件，dir=目录，segment=片段")
    private Long outputTypeDictId;

    @ApiModelProperty("内容输出变量名")
    private String outputVariable;

    @ApiModelProperty("共享变量名，多个以逗号分隔，变量类型为Set<String>")
    private String shareVariables;

    @ApiModelProperty("描述")
    private String remark;


    @ApiModelProperty("父级id")
    private Long parentId;

}
