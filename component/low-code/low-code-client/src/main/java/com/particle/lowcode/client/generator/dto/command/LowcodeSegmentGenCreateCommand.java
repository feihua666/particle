package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 低代码生成 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-02-08
 */
@Data
@Schema
public class LowcodeSegmentGenCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "生成名称不能为空")
    @Schema(description = "生成名称，一般用于显示标识",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotNull(message = "低代码片段模板id 不能为空")
    @Schema(description = "低代码片段模板id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long lowcodeSegmentTemplateId;

    @Schema(description = "低代码模型id")
    private Long lowcodeModelId;

    @Schema(description = "生成类型字典id")
    private Long generateTypeDictId;

    @Schema(description = "引用生成id，主要用来获取引用的相关变量")
    private Long refrenceSegmentGenId;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
