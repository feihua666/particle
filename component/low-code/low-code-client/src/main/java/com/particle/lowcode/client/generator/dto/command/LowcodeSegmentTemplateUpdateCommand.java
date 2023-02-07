package com.particle.lowcode.client.generator.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 低代码片段模板 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@ApiModel
public class LowcodeSegmentTemplateUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "编码不能为空")
    @ApiModelProperty("编码，唯一")
    private String code;

    @NotEmpty(message = "模板名称不能为空")
    @ApiModelProperty("模板名称，仅做展示")
    private String name;

    @ApiModelProperty("名称模板")
    private String nameTemplate;

    @ApiModelProperty("内容模板")
    private String contentTemplate;

    @ApiModelProperty("引用模板id")
    private Long referenceSegmentTemplateId;

    @NotEmpty(message = "输出类型不能为空")
    @ApiModelProperty("输出类型，file=文件，dir=目录，segment=片段")
    private String outputType;

    @ApiModelProperty("输出变量名")
    private String outputVariable;

    @ApiModelProperty("共享变量名，多个以逗号分隔，变量类型为List<String>")
    private String shareVariables;

    @ApiModelProperty("描述")
    private String remark;

    @ApiModelProperty("父级id")
    private Long parentId;

}
