package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站静态部署 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Data
@Schema
public class NavigationStaticDeployUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "部署编码")
    private String code;


    @NotEmpty(message = "部署名称 不能为空")
        @Schema(description = "部署名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "前端域名地址")
    private String frontDomain;


    @Schema(description = "前端上下文路径")
    private String frontContextPath;


    @Schema(description = "前端上下文路径的下一级路径")
    private String frontSubContextPath;


    @NotNull(message = "是否纯静态部署 不能为空")
        @Schema(description = "是否纯静态部署",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isPureStaticDeploy;

	@Schema(description = "部署路径，如：/data/nav")
	private String deployPath;

	@Schema(description = "部署成功后执行的groovy脚本，方便进行额外处理")
	private String deployPostGroovyScript;


    @Schema(description = "上一次部署时间")
    private LocalDateTime lastDeployAt;
    

    @Schema(description = "描述")
    private String remark;









}