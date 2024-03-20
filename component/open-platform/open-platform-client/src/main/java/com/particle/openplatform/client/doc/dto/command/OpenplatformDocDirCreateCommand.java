package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 开放接口目录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Data
@Schema
public class OpenplatformDocDirCreateCommand extends AbstractBaseCommand {



    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "简称")
    private String nameSimple;


    @NotNull(message = "开放接口文档目录名称id 不能为空")
        @Schema(description = "开放接口文档目录名称id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocDirNameId;


    @Schema(description = "备注")
    private String remark;


    @NotNull(message = "排序 不能为空")
        @Schema(description = "排序",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer seq;


    @Schema(description = "父级")
    private Long parentId;


}
