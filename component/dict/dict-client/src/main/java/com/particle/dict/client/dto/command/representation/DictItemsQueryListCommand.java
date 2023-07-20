package com.particle.dict.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.OrderBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 字典项或字典组查询指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@OrderBy("seq")
@Data
@Schema
public class DictItemsQueryListCommand extends AbstractBaseQueryCommand {

    @NotEmpty(message="字典组编码不能为空")
    @Schema(description = "字典组编码",required = true)
    private String groupCode;

    @Schema(description = "私有标识")
    private String privateFlag;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "标签，多个以逗号分隔，用来区分字典项")
    private String tags;


    @Schema(description = "使用id数据对应的value作为tag，可以根据标签过滤，如果tag有值，该字段不生效")
    private Long valueAsTagId;
}
