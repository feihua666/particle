package com.particle.report.client.template.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 报告片段模板 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Data
@Schema
public class ReportSegmentTemplateUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "编码，唯一")
    private String code;

    @NotEmpty(message = "模板名称不能为空")
    @Schema(description = "模板名称，仅做展示",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

	@Schema(description = "模板权限码")
	private String permissions;

    @Schema(description = "数据获取脚本，目前仅支持groovy脚本")
    private String dataResolveScript;

    @Schema(description = "渲染条件脚本，目前仅支持groovy脚本")
    private String renderConditionScript;

    @Schema(description = "计算模板")
    private String computeTemplate;

    @Schema(description = "名称模板")
    private String nameTemplate;

    @Schema(description = "名称输出变量名")
    private String nameOutputVariable;

    @Schema(description = "内容模板")
    private String contentTemplate;

    @SetNullWhenNull
    @Schema(description = "引用模板id")
    private Long referenceSegmentTemplateId;

    @NotNull(message = "输出类型字典id不能为空")
    @Schema(description = "输出类型字典id，file=文件，dir=目录，segment=片段",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long outputTypeDictId;

    @Schema(description = "内容输出变量名")
    private String outputVariable;

    @Schema(description = "共享变量名，多个以逗号分隔，变量类型为Set<String>")
    private String shareVariables;

    @NotNull(message = "排序不能为空")
	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;

    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级id")
    private Long parentId;

}
