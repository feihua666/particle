package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.common.client.dto.command.AbstractIdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站静态部署 部署指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Data
@Schema
public class NavigationStaticDeployDoDeployCommand extends AbstractIdCommand {

    @NotNull(message = "是否增量部署 不能为空")
    @Schema(description = "是否增量部署",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isIncrementDeploy;

}
