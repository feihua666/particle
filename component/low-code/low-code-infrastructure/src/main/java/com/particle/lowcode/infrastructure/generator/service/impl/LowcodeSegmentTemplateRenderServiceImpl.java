package com.particle.lowcode.infrastructure.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.str.StringTool;
import com.particle.global.tool.template.templatetreerenderengine.OutputType;
import com.particle.global.tool.template.templatetreerenderengine.TemplateTreeRenderEngine;
import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import com.particle.global.tool.template.templatetreerenderengine.render.RenderContext;
import com.particle.global.tool.template.templatetreerenderengine.render.TemplateRenderContext;
import com.particle.global.tool.template.templatetreerenderengine.template.SegmentTemplate;
import com.particle.lowcode.domain.generator.gateway.LowcodeDictGateway;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderParam;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderResult;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateRenderService;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 * 代码片段模板渲染服务实现
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 15:21
 */
@Component
public class LowcodeSegmentTemplateRenderServiceImpl implements ILowcodeSegmentTemplateRenderService {

	@Autowired
	private ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService;

	@Autowired
	private LowcodeDictGateway lowcodeDictGateway;

	private TemplateTreeRenderEngine templateTreeRenderEngine = new TemplateTreeRenderEngine();

	@Override
	public LowcodeSegmentTemplateRenderResult render(LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam) {
		ConfigData configData = configData(lowcodeSegmentTemplateRenderParam);
		SegmentTemplate segmentTemplate = getSegmentTemplate(lowcodeSegmentTemplateRenderParam.getRootSegmentTemplateId());
		if (segmentTemplate == null) {
			return null;
		}

		RenderContext renderContext = templateTreeRenderEngine.renderWithRenderContext(configData, segmentTemplate);
		List<TemplateRenderContext> templateRenderContexts = renderContext.getTemplateRenderContexts();
		if (!templateRenderContexts.isEmpty()) {
			TemplateRenderContext next = templateRenderContexts.iterator().next();
			return LowcodeSegmentTemplateRenderResult.create(
					next.getSegmentTemplateData().getTemplateNameContentResult(),
					next.getSegmentTemplateData().getTemplateContentResult(),
					next.getSegmentTemplateData().getTemplateNameContentResultFile()
			);
		}

		return null;
	}
	/**
	 * 配置数据
	 * @return
	 */
	private ConfigData configData(LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam){
		ConfigData configData = ConfigData.create(lowcodeSegmentTemplateRenderParam.getGlobal());

		configData.setExt(lowcodeSegmentTemplateRenderParam.getExt());

		configData.setOutputFileParentAbsoluteDir(lowcodeSegmentTemplateRenderParam.getOutputFileParentAbsoluteDir());
		return configData;
	}
	/**
	 * 根据 id 转换
	 * @param rootSegmentTemplateId
	 * @return
	 */
	private SegmentTemplate getSegmentTemplate(Long rootSegmentTemplateId){
		if (rootSegmentTemplateId == null) {
			return null;
		}
		LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO = iLowcodeSegmentTemplateService.getById(rootSegmentTemplateId);
		if (lowcodeSegmentTemplateDO == null) {
			return null;
		}
		return getSegmentTemplate(lowcodeSegmentTemplateDO);
	}

	/**
	 * 根据实体转换
	 * @param lowcodeSegmentTemplateDO
	 * @return
	 */
	private SegmentTemplate getSegmentTemplate(LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO){
		Long rootSegmentTemplateId = lowcodeSegmentTemplateDO.getId();
		// 引用相关
		Long referenceSegmentTemplateId = lowcodeSegmentTemplateDO.getReferenceSegmentTemplateId();
		LowcodeSegmentTemplateDO referenceSegmentTemplateDO = null;
		List<LowcodeSegmentTemplateDO> referenceSegmentTemplateDOChildrens = null;
		if (referenceSegmentTemplateId != null) {
			referenceSegmentTemplateDO = iLowcodeSegmentTemplateService.getById(referenceSegmentTemplateId);
			if (referenceSegmentTemplateDO != null) {
				referenceSegmentTemplateDOChildrens = iLowcodeSegmentTemplateService.getChildren(referenceSegmentTemplateId);
			}
		}

		List<LowcodeSegmentTemplateDO> segmentTemplateDOChildrens = iLowcodeSegmentTemplateService.getChildren(rootSegmentTemplateId);

		if (CollectionUtil.isNotEmpty(referenceSegmentTemplateDOChildrens)) {
			segmentTemplateDOChildrens.addAll(referenceSegmentTemplateDOChildrens);
		}

		// 对象转换并覆盖引用
		SegmentTemplate segmentTemplate = lowcodeSegmentTemplateDOToSegmentTemplate(lowcodeSegmentTemplateDO,referenceSegmentTemplateDO);
		SegmentTemplate segmentTemplateTemp = null;
		for (LowcodeSegmentTemplateDO segmentTemplateDOChildren : segmentTemplateDOChildrens) {
			segmentTemplateTemp = getSegmentTemplate(segmentTemplateDOChildren);
			if (segmentTemplateTemp != null) {
				segmentTemplate.addSubSegmentTemplate(segmentTemplateTemp);
			}
		}

		return segmentTemplate;
	}


	/**
	 * 对象转换
	 * @param lowcodeSegmentTemplateDO 相同字段该值优先
	 * @param referenceSegmentTemplateDO
	 * @return
	 */
	private SegmentTemplate lowcodeSegmentTemplateDOToSegmentTemplate(LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO,LowcodeSegmentTemplateDO referenceSegmentTemplateDO){
		SegmentTemplate segmentTemplate = new SegmentTemplate(lowcodeSegmentTemplateDO.getId().toString());

		segmentTemplate.setTemplateNameContent(Optional.ofNullable(StrUtil.emptyToNull(lowcodeSegmentTemplateDO.getNameTemplate())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(LowcodeSegmentTemplateDO::getNameTemplate).orElse(null)));
		segmentTemplate.setOutputNameVariableName(Optional.ofNullable(StrUtil.emptyToNull(lowcodeSegmentTemplateDO.getNameOutputVariable())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(LowcodeSegmentTemplateDO::getNameOutputVariable).orElse(null)));
		segmentTemplate.setTemplateContent(Optional.ofNullable(StrUtil.emptyToNull(lowcodeSegmentTemplateDO.getContentTemplate())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(LowcodeSegmentTemplateDO::getContentTemplate).orElse(null)));
		segmentTemplate.setOutputVariableName(Optional.ofNullable(StrUtil.emptyToNull(lowcodeSegmentTemplateDO.getOutputVariable())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(LowcodeSegmentTemplateDO::getOutputVariable).orElse(null)));

		segmentTemplate.setOutputType(OutputType.valueOf(Optional.ofNullable(StrUtil.emptyToNull( lowcodeDictGateway.getDictValueById(lowcodeSegmentTemplateDO.getOutputTypeDictId()))).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(item->lowcodeDictGateway.getDictValueById(item.getOutputTypeDictId())).orElse(null))));
		List<String> split = StrUtil.split(lowcodeSegmentTemplateDO.getShareVariables(), ',');
		segmentTemplate.setShareVariables(new HashSet<>(split));

		// 合并共享变量
		if (referenceSegmentTemplateDO != null) {
			List<String> referenceSplit = StrUtil.split(referenceSegmentTemplateDO.getShareVariables(), ',');
			segmentTemplate.getShareVariables().addAll(referenceSplit);
		}

		return segmentTemplate;
	}
}
