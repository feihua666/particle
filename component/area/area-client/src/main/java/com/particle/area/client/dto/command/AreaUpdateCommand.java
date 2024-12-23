package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 区域 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@Schema
public class AreaUpdateCommand extends AbstractBaseUpdateCommand {


    @NotEmpty(message = "编码不能为空")
    @Schema(description = "编码，唯一",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @NotEmpty(message = "编码不能为空")
    @Schema(description = "区域名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "区域简称")
    private String nameSimple;

    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型，字典id")
    private Long typeDictId;

    @Schema(description = "经度")
    private String longitude;

    @Schema(description = "纬度")
    private String latitude;

    @Schema(description = "备注")
    private String remark;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序,默认按该字段升序排序")
    private Integer seq;


    @Schema(description = "父级")
    private Long parentId;

}
