package com.particle.lowcode.client.generator.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreePageQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@Schema
public class LowcodeSegmentTemplatePageQueryCommand extends AbstractBaseTreePageQueryCommand {


    @Like
    @Schema(description = "编码，唯一")
    private String code;

    @Like
    @Schema(description = "模板名称，仅做展示")
    private String name;

    @Schema(description = "输出类型字典id，file=文件，dir=目录，segment=片段")
    private Long outputTypeDictId;

    @Schema(description = "名称输出变量名")
    private String nameOutputVariable;

    @Schema(description = "内容输出变量名")
    private String outputVariable;

    @Schema(description = "引用模板id")
    private Long referenceSegmentTemplateId;

}
