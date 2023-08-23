package com.particle.global.openapi.endpoint.command;

import com.particle.global.dto.basic.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 开放接口测试指令
 * </p>
 *
 * @author yangwei
 * @since 2023-08-04 10:00
 */
@Data
@Schema
public class OpenapiTestCommand extends Command {


	/**
	 * 测试内容一个必填字段
	 */
	@NotEmpty(message = "testField1 不能为空")
	@Schema(description = "testField1",requiredMode = Schema.RequiredMode.REQUIRED)
	private String testField1;

	/**
	 * 测试内容一个选填字段
	 */
	@Schema(description = "testField2")
	private String testField2;
}
