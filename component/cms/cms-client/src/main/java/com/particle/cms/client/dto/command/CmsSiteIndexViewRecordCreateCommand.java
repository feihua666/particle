package com.particle.cms.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 站点首页访问记录 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Data
@Schema
public class CmsSiteIndexViewRecordCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "站点id 不能为空")
        @Schema(description = "站点id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long cmsSiteId;


    @Schema(description = "访问终端设备id")
    private String deviceId;


    @Schema(description = "访问终端设备ip")
    private String ip;


    @NotNull(message = "访问时间 不能为空")
        @Schema(description = "访问时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime viewAt;
    








}
