package com.particle.lowcode.app.generator.executor;

import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderParam;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderResult;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateRenderService;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeSegmentTemplateInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 片段模板渲染指令执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 17:11
 */
@Component
@Validated
public class LowcodeSegmentTemplateRenderCommandExecutor {

	@Autowired
	private ILowcodeSegmentTemplateRenderService iLowcodeSegmentTemplateRenderService;
	/**
	 * 渲染模板测试执行
	 * @param lowcodeSegmentTemplateRenderCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateRenderVO> renderTest(@Valid LowcodeSegmentTemplateRenderCommand lowcodeSegmentTemplateRenderCommand) {
		LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateRenderCommandToLowcodeSegmentTemplateRenderParam(lowcodeSegmentTemplateRenderCommand);
		LowcodeSegmentTemplateRenderResult render = iLowcodeSegmentTemplateRenderService.render(lowcodeSegmentTemplateRenderParam);
		LowcodeSegmentTemplateRenderVO lowcodeSegmentTemplateRenderVO = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateRenderResultToLowcodeSegmentTemplateRenderVO(render);
		return SingleResponse.of(lowcodeSegmentTemplateRenderVO);
	}
}
