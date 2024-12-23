package com.particle.global.openapi.endpoint.vo;

import com.particle.global.dto.basic.VO;
import com.particle.global.openapi.endpoint.command.OpenapiTestCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放接口测试响应对象
 * </p>
 *
 * @author yangwei
 * @since 2023-08-04 10:04
 */
@Data
@Schema
public class OpenapiTestVO extends VO {

	@Schema(description = "testField1")
	private String testField1;

	@Schema(description = "testField1")
	private String testField2;


	public static OpenapiTestVO createByOpenapiTestCommand(OpenapiTestCommand testCommand) {
		OpenapiTestVO openapiTestVO = new OpenapiTestVO();

		openapiTestVO.testField1 = testCommand.getTestField1();
		openapiTestVO.testField2 = testCommand.getTestField2();

		return openapiTestVO;
	}
}
