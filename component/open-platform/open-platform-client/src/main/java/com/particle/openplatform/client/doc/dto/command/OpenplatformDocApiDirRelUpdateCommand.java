package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 开放接口文档接口与目录关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@Schema
public class OpenplatformDocApiDirRelUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "开放接口文档接口id 不能为空")
        @Schema(description = "开放接口文档接口id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocApiId;


    @NotNull(message = "开放接口文档目录id 不能为空")
        @Schema(description = "开放接口文档目录id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long openplatformDocDirId;









}
