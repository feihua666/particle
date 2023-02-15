package com.particle.lowcode.client.generator.dto.command.representation;


import java.time.LocalDateTime;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class LowcodeSegmentGenQueryListCommand extends AbstractBaseQueryCommand {

    @Like
    @ApiModelProperty("生成名称，左前缀匹配")
    private String name;

    @ApiModelProperty("低代码片段模板id")
    private Long lowcodeSegmentTemplateId;

    @ApiModelProperty("低代码模型id")
    private Long lowcodeModelId;

    @ApiModelProperty("是否已生成")
    private Boolean isGenerated;

    @ApiModelProperty("生成类型字典id")
    private Long generateTypeDictId;

    @ApiModelProperty("引用生成id，主要用来获取引用的相关变量")
    private Long refrenceSegmentGenId;

}
