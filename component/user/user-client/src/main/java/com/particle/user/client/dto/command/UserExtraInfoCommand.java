package com.particle.user.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 用户扩展信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Data
@Schema
public class UserExtraInfoCommand extends AbstractBaseCommand {

    @Schema(description = "单位名称")
    private String orgName;

    @Schema(description = "职称")
    private String jobTitle;

    @Schema(description = "个人简介")
    private String profile;

    @Schema(description = "额外自定义非查询信息")
    private String extraInfoJson;
}
