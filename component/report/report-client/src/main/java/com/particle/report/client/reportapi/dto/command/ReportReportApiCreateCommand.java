package com.particle.report.client.reportapi.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 报告接口 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Data
@Schema
public class ReportReportApiCreateCommand extends AbstractBaseCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotNull(message = "是否为组 不能为空")
    @Schema(description = "是否为组",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isGroup;


    @Schema(description = "接口权限码")
    private String permissions;


    @PropValid.DependCondition(message = "接口地址 不能为空",dependProp = "isGroup",ifEqual = "false")
    @Schema(description = "接口地址,类型为接口时必填")
    private String url;

    @PropValid.DependCondition(message = "报告片段模板id 不能为空",dependProp = "isGroup",ifEqual = "false")
    @Schema(description = "报告片段模板id,类型为接口时必填")
    private Long reportSegmentTemplateId;


    @Schema(description = "入参示例")
    private String inParamExampleConfigJson;

	@Schema(description = "后置处理脚本，可以用来对渲染的结果进一步处理，如转为pdf，目前仅支持groovy脚本")
	private String postScript;


    @Schema(description = "描述")
    private String remark;


    @Schema(description = "父级id")
    private Long parentId;

}
