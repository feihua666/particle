package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 低代码数据源 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@Schema
public class LowcodeDatasourceCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "编码不能为空")
    @Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @NotEmpty(message = "名称不能为空")
    @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "驱动类名不能为空")
    @Schema(description = "驱动类名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String driverClassName;

    @NotEmpty(message = "地址不能为空")
    @Schema(description = "地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;

    @NotEmpty(message = "用户名不能为空")
    @Schema(description = "用户名",requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Schema(description = "密码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "描述,注意事项等")
    private String remark;


}
