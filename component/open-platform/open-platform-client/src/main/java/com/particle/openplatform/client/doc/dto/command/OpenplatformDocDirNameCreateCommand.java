package com.particle.openplatform.client.doc.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 开放接口目录名称 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Data
@Schema
public class OpenplatformDocDirNameCreateCommand extends AbstractBaseCommand {


    @NotEmpty(message = "编码 不能为空")
	@Schema(description = "编码",requiredMode = Schema.RequiredMode.REQUIRED)
	private String code;



    @NotEmpty(message = "名称 不能为空")
        @Schema(description = "名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @Schema(description = "描述")
    private String remark;









}