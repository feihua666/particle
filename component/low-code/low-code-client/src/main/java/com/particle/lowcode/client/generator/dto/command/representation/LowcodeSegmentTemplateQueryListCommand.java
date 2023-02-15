package com.particle.lowcode.client.generator.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@ApiModel
public class LowcodeSegmentTemplateQueryListCommand extends AbstractBaseTreeQueryCommand {


    @Like
    @ApiModelProperty("编码，唯一")
    private String code;

    @Like
    @ApiModelProperty("模板名称，仅做展示")
    private String name;

    @ApiModelProperty("输出类型字典id，file=文件，dir=目录，segment=片段")
    private Long outputTypeDictId;

    @ApiModelProperty("名称输出变量名")
    private String nameOutputVariable;

    @ApiModelProperty("内容输出变量名")
    private String outputVariable;

    @ApiModelProperty("引用模板id")
    private Long referenceSegmentTemplateId;

}
