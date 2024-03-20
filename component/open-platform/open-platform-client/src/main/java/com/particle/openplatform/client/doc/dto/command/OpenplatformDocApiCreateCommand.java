package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口文档接口 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Data
@Schema
public class OpenplatformDocApiCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "编码 不能为空")
    @Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;


    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "简称")
    private String nameSimple;


    @Schema(description = "每次价格")
    private Double pricePerTime;


    @Schema(description = "价格文本")
    private String pricePerTimeDesc;


    @Schema(description = "描述")
    private String description;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "开放接口文档目录id")
    private Long openplatformDocDirId;






}
