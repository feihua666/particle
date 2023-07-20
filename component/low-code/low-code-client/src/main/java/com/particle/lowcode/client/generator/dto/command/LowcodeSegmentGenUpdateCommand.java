package com.particle.lowcode.client.generator.dto.command;

import java.time.LocalDateTime;
import java.util.Map;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 低代码生成 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2023-02-08
 */
@Data
@Schema
public class LowcodeSegmentGenUpdateCommand extends AbstractBaseUpdateCommand {

    @NotEmpty(message = "生成名称不能为空")
    @Schema(description = "生成名称，一般用于显示标识",required = true)
    private String name;

    @NotNull(message = "低代码片段模板id 不能为空")
    @Schema(description = "低代码片段模板id",required = true)
    private Long lowcodeSegmentTemplateId;

    @SetNullWhenNull
    @Schema(description = "低代码模型id")
    private Long lowcodeModelId;

    @NotNull(message = "生成类型字典id 不能为空")
    @Schema(description = "生成类型字典id",required = true)
    private Long generateTypeDictId;

    @Schema(description = "引用生成id，主要用来获取引用的相关变量")
    private Long refrenceSegmentGenId;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
