package com.particle.lowcode.app.generator.executor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.json.JsonTool;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenRenderGenCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenRenderGenVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderParam;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderResult;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateRenderService;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeSegmentTemplateInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

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
	@Autowired
	private LowcodeSegmentGenGateway lowcodeSegmentGenGateway;

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

	/**
	 * 设计和渲染
	 * @param lowcodeSegmentGenRenderGenCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenRenderGenVO> renderGen(@Valid LowcodeSegmentGenRenderGenCommand lowcodeSegmentGenRenderGenCommand) {
		// 先执行渲染条件保存
		LowcodeSegmentGen lowcodeSegmentGen = lowcodeSegmentGenGateway.getById(LowcodeSegmentGenId.of(lowcodeSegmentGenRenderGenCommand.getLowcodeSegmentGenId()));
		lowcodeSegmentGen.changeRenderParam(
				JsonTool.toJsonStr(lowcodeSegmentGenRenderGenCommand.getGlobal()),
				JsonTool.toJsonStr(lowcodeSegmentGenRenderGenCommand.getExt()),
				lowcodeSegmentGenRenderGenCommand.getOutputFileParentAbsoluteDir(),
				Optional.ofNullable(lowcodeSegmentGenRenderGenCommand.getJavaPackageKeys()).map(i -> i.stream().collect(Collectors.joining(","))).orElse(null)
				);

		lowcodeSegmentGen.changeToGenerated();
		lowcodeSegmentGenGateway.save(lowcodeSegmentGen);

		LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentGenRenderGenCommandToLowcodeSegmentTemplateRenderParam(lowcodeSegmentGenRenderGenCommand);

		// 改变值使用新对象
		lowcodeSegmentTemplateRenderParam = lowcodeSegmentTemplateRenderParam.changeRootSegmentTemplateId(lowcodeSegmentGen.getLowcodeSegmentTemplateId());

		// 添加模型相关全局数据
		if (StrUtil.isNotEmpty(lowcodeSegmentGen.getLowcodeModelJson())) {
			Map map = JSONUtil.toBean(lowcodeSegmentGen.getLowcodeModelJson(), Map.class);
			if (map != null) {
				Map<String, Object> ext = lowcodeSegmentTemplateRenderParam.getExt();
				if (ext == null) {
					ext = new HashMap<>();
					lowcodeSegmentTemplateRenderParam.setExt(ext);
				}
				ext.putAll(map);
			}
		}


		// 执行渲染
		LowcodeSegmentTemplateRenderResult render = iLowcodeSegmentTemplateRenderService.render(lowcodeSegmentTemplateRenderParam);

		LowcodeSegmentGenRenderGenVO lowcodeSegmentGenRenderGenVO = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateRenderResultToLowcodeSegmentGenRenderGenVO(render);
		return SingleResponse.of(lowcodeSegmentGenRenderGenVO);
	}
}
