package com.particle.lowcode.client.generator.dto.command;

import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 低代码模型 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Schema
public class LowcodeModelUpdateCommand extends AbstractBaseUpdateCommand {

	@Schema(description = "额外扩展json，目前添加主要是rel相互分配信息")
	private String extJson;


    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "英文名称不能为空")
    @Schema(description = "英文名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nameEn;

    @NotEmpty(message = "实体名称")
    @Schema(title = "实体名称",description = "首字母大写，符合java类名规范",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nameEnEntity;

    @NotEmpty(message = "实体变量名称")
    @Schema(title = "实体变量名称",description = "nameEnEntity 的首字母小写",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nameEnEntityVar;

    @Schema(description = "表名称")
    private String tableName;

    @NotNull(message = "模型表类型字典id 不能为空")
    @Schema(description = "模型表类型字典id，rel,tree,normal",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long tableTypeDictId;

    @NotEmpty(message = "请求路径不能为空")
    @Schema(description = "请求路径")
    private String requestPath;

    @Schema(description = "描述,注意事项等")
    private String remark;
}