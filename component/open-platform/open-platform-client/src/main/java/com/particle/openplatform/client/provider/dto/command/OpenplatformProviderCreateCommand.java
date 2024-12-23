package com.particle.openplatform.client.provider.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 开放平台开放接口供应商 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Data
@Schema
public class OpenplatformProviderCreateCommand extends AbstractBaseCommand {



    @Schema(description = "编码")
    private String code;


    @NotEmpty(message = "供应商名称 不能为空")
        @Schema(description = "供应商名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;


    @Schema(description = "描述")
    private String remark;









}
