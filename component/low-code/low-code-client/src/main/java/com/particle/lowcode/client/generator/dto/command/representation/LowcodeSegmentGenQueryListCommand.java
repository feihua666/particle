package com.particle.lowcode.client.generator.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码生成 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-02-08
 */
@Data
@Schema
public class LowcodeSegmentGenQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @Schema(description = "生成名称，左前缀匹配")
    private String name;

    @Schema(description = "低代码片段模板id")
    private Long lowcodeSegmentTemplateId;

    @Schema(description = "低代码模型id")
    private Long lowcodeModelId;

    @Schema(description = "是否已生成")
    private Boolean isGenerated;

    @Schema(description = "生成类型字典id")
    private Long generateTypeDictId;

    @Schema(description = "引用生成id，主要用来获取引用的相关变量")
    private Long refrenceSegmentGenId;

}
