package com.particle.report.infrastructure.template.service.impl;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.cache.impl.WeakCache;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.tool.str.StringTool;
import com.particle.global.tool.template.templatetreerenderengine.OutputType;
import com.particle.global.tool.template.templatetreerenderengine.TemplateTreeRenderEngine;
import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import com.particle.global.tool.template.templatetreerenderengine.render.RenderContext;
import com.particle.global.tool.template.templatetreerenderengine.render.TemplateRenderContext;
import com.particle.global.tool.template.templatetreerenderengine.template.SegmentTemplate;
import com.particle.global.tool.template.templatetreerenderengine.template.impl.GroovyScriptSegmentTemplateRenderDataResolverImpl;
import com.particle.report.domain.gateway.ReportDataQueryDataApiGateway;
import com.particle.report.domain.gateway.ReportDictGateway;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderParam;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderResult;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplatePermissionCheckService;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateRenderService;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 代码片段模板渲染服务实现
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 14:50
 */
@Component
public class ReportSegmentTemplateRenderServiceImpl implements IReportSegmentTemplateRenderService {

	public static TimedCache<Long, ReportSegmentTemplateDO> reportSegmentTemplateDOCache = CacheUtil.newTimedCache(16 * 1 * 60000);
	public static TimedCache<Long, List<ReportSegmentTemplateDO>> childrenReportSegmentTemplateDOsCache = CacheUtil.newTimedCache(17 * 1 * 60000);

	@Autowired
	private IReportSegmentTemplateService iReportSegmentTemplateService;
	
	@Autowired
	private ReportDictGateway reportDictGateway;

	@Autowired
	private ReportDataQueryDataApiGateway reportDataQueryDataApiGateway;

	@Autowired(required = false)
	private IReportSegmentTemplatePermissionCheckService iReportSegmentTemplatePermissionCheckService;
	
	private TemplateTreeRenderEngine templateTreeRenderEngine = new TemplateTreeRenderEngine();
	/**
	 * 用于groovy脚本渲染
	 */
	private GroovyScriptSegmentTemplateRenderDataResolverImpl groovyScriptSegmentTemplateRenderDataResolver = new GroovyScriptSegmentTemplateRenderDataResolverImpl();


	@PostConstruct
	private void init() {
		Map<String, Object> addtionalBindings = new HashMap<>();
		addtionalBindings.put("dataQueryDataApi", reportDataQueryDataApiGateway);
		groovyScriptSegmentTemplateRenderDataResolver.setAddtionalBindings(addtionalBindings);
	}

	@Override
	public ReportSegmentTemplateRenderResult render(ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam) {
		SegmentTemplate segmentTemplate = getSegmentTemplate(reportSegmentTemplateRenderParam.getRootSegmentTemplateId());
		Assert.notNull(segmentTemplate,"无可用模板，请检查是否配置模板权限");
		RenderContext renderContext = templateTreeRenderEngine.renderWithRenderContext(
				configData(reportSegmentTemplateRenderParam),
				segmentTemplate
		);

		List<TemplateRenderContext> templateRenderContexts = renderContext.getTemplateRenderContexts();
		if (!templateRenderContexts.isEmpty()) {
			// 第一个是根节点
			TemplateRenderContext next = templateRenderContexts.iterator().next();
			return ReportSegmentTemplateRenderResult.create(
					next.getSegmentTemplateData().getTemplateNameContentResult(),
					next.getSegmentTemplateData().getTemplateContentResult(),
					next.getSegmentTemplateData().getTemplateNameContentResultFile(),
					renderContext
			);
		}

		return null;
	}

	@Override
	public Boolean clearCache() {
		reportSegmentTemplateDOCache.clear();
		childrenReportSegmentTemplateDOsCache.clear();
		return true;
	}

	@Override
	public Boolean refreshCache(Long reportSegmentTemplateId) {
		reportSegmentTemplateDOCache.remove(reportSegmentTemplateId);
		getReportSegmentTemplateDOById(reportSegmentTemplateId);

		childrenReportSegmentTemplateDOsCache.remove(reportSegmentTemplateId);
		getReportSegmentTemplateChildrenById(reportSegmentTemplateId);
		return true;
	}

	/**
	 * 配置数据
	 * @return
	 */
	private ConfigData configData(ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam){
		ConfigData configData = ConfigData.create(reportSegmentTemplateRenderParam.getGlobal());
		configData.setExt(reportSegmentTemplateRenderParam.getExt());
		configData.setGlobalTemp(reportSegmentTemplateRenderParam.getGlobalTemp());
		configData.setOutputFileParentAbsoluteDir(reportSegmentTemplateRenderParam.getOutputFileParentAbsoluteDir());
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
		ReportSegmentTemplateDO reportSegmentTemplateDO = getReportSegmentTemplateDOById(rootSegmentTemplateId);
		if (reportSegmentTemplateDO == null) {
			return null;
		}
		boolean hasTemplatePermission = hasTemplatePermission(reportSegmentTemplateDO);
		Assert.isTrue(hasTemplatePermission,"您没有报告模板 " + reportSegmentTemplateDO.getName() + " 的权限");

		return getSegmentTemplate(reportSegmentTemplateDO);
	}

	/**
	 * 根据实体转换
	 * @param reportSegmentTemplateDO 必须存在
	 * @return
	 */
	private SegmentTemplate getSegmentTemplate(ReportSegmentTemplateDO reportSegmentTemplateDO){
		// 检查权限
		if (!hasTemplatePermission(reportSegmentTemplateDO)) {
			return null;
		}

		Long rootSegmentTemplateId = reportSegmentTemplateDO.getId();
		// 引用相关
		Long referenceSegmentTemplateId = reportSegmentTemplateDO.getReferenceSegmentTemplateId();
		ReportSegmentTemplateDO referenceSegmentTemplateDO = null;
		List<ReportSegmentTemplateDO> referenceSegmentTemplateDOChildrens = null;
		if (referenceSegmentTemplateId != null) {
			referenceSegmentTemplateDO = getReportSegmentTemplateDOById(referenceSegmentTemplateId);
			if (referenceSegmentTemplateDO != null) {
				referenceSegmentTemplateDOChildrens = getReportSegmentTemplateChildrenById(referenceSegmentTemplateId);
			}
		}

		List<ReportSegmentTemplateDO> segmentTemplateDOChildrens = getReportSegmentTemplateChildrenById(rootSegmentTemplateId);

		if (CollectionUtil.isNotEmpty(referenceSegmentTemplateDOChildrens)) {
			segmentTemplateDOChildrens.addAll(referenceSegmentTemplateDOChildrens);
		}

		//  从小到大排序
		segmentTemplateDOChildrens = CollectionUtil.sort(segmentTemplateDOChildrens, Comparator.comparingInt(ReportSegmentTemplateDO::getSeq));

		// 对象转换并覆盖引用
		SegmentTemplate segmentTemplate = reportSegmentTemplateDOToSegmentTemplate(reportSegmentTemplateDO,referenceSegmentTemplateDO);
		SegmentTemplate segmentTemplateTemp = null;
		for (ReportSegmentTemplateDO segmentTemplateDOChildren : segmentTemplateDOChildrens) {
			segmentTemplateTemp = getSegmentTemplate(segmentTemplateDOChildren);
			if (segmentTemplateTemp != null) {
				segmentTemplate.addSubSegmentTemplate(segmentTemplateTemp);
			}
		}

		return segmentTemplate;
	}


	/**
	 * 对象转换
	 * @param reportSegmentTemplateDO 相同字段该值优先
	 * @param referenceSegmentTemplateDO
	 * @return
	 */
	private SegmentTemplate reportSegmentTemplateDOToSegmentTemplate(ReportSegmentTemplateDO reportSegmentTemplateDO,ReportSegmentTemplateDO referenceSegmentTemplateDO){
		SegmentTemplate segmentTemplate = new SegmentTemplate(reportSegmentTemplateDO.getId().toString());

		segmentTemplate.setTemplateComputeContent(StringTool.referenceStr(reportSegmentTemplateDO.getComputeTemplate(),Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getComputeTemplate).orElse(null)));
		segmentTemplate.setTemplateNameContent(StringTool.referenceStr(reportSegmentTemplateDO.getNameTemplate(),Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getNameTemplate).orElse(null)));
		segmentTemplate.setOutputNameVariableName(Optional.ofNullable(StrUtil.emptyToNull(reportSegmentTemplateDO.getNameOutputVariable())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getNameOutputVariable).orElse(null)));
		segmentTemplate.setTemplateContent(StringTool.referenceStr(reportSegmentTemplateDO.getContentTemplate(),Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getContentTemplate).orElse(null)));
		segmentTemplate.setOutputVariableName(Optional.ofNullable(StrUtil.emptyToNull(reportSegmentTemplateDO.getOutputVariable())).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getOutputVariable).orElse(null)));

		segmentTemplate.setOutputType(OutputType.valueOf(Optional.ofNullable(StrUtil.emptyToNull( reportDictGateway.getDictValueById(reportSegmentTemplateDO.getOutputTypeDictId()))).orElse(Optional.ofNullable(referenceSegmentTemplateDO).map(item->reportDictGateway.getDictValueById(item.getOutputTypeDictId())).orElse(null))));
		List<String> split = StrUtil.split(reportSegmentTemplateDO.getShareVariables(), ',');
		segmentTemplate.setShareVariables(new HashSet<>(split));

		// 合并共享变量
		if (referenceSegmentTemplateDO != null) {
			List<String> referenceSplit = StrUtil.split(referenceSegmentTemplateDO.getShareVariables(), ',');
			segmentTemplate.getShareVariables().addAll(referenceSplit);
		}

		// groovy 脚本处理
		String dataResolveScript = StringTool.referenceStr(reportSegmentTemplateDO.getDataResolveScript(),Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getDataResolveScript).orElse(null));
		String renderConditionScript = StringTool.referenceStr(reportSegmentTemplateDO.getRenderConditionScript(),Optional.ofNullable(referenceSegmentTemplateDO).map(ReportSegmentTemplateDO::getRenderConditionScript).orElse(null));

		SegmentTemplate.ExtConfig extConfig = SegmentTemplate.ExtConfig.create(dataResolveScript,renderConditionScript);
		segmentTemplate.setExtConfig(extConfig);
		segmentTemplate.setSegmentTemplateRenderDataResolver(groovyScriptSegmentTemplateRenderDataResolver);
		return segmentTemplate;
	}

	private boolean hasTemplatePermission(ReportSegmentTemplateDO reportSegmentTemplateDO) {
		if (iReportSegmentTemplatePermissionCheckService != null) {
			boolean hasPermission = iReportSegmentTemplatePermissionCheckService.hasPermission(reportSegmentTemplateDO);
			return hasPermission;
		}
		// 默认为true
		return true;
	}

	/**
	 * 根据id获取
	 * @param reportSegmentTemplateId
	 * @return
	 */
	private ReportSegmentTemplateDO getReportSegmentTemplateDOById(Long reportSegmentTemplateId) {
		return reportSegmentTemplateDOCache.get(reportSegmentTemplateId, () -> iReportSegmentTemplateService.getById(reportSegmentTemplateId));
	}

	/**
	 * 根据id获取子级
	 * @param reportSegmentTemplateId
	 * @return
	 */
	private List<ReportSegmentTemplateDO> getReportSegmentTemplateChildrenById(Long reportSegmentTemplateId) {
		return childrenReportSegmentTemplateDOsCache.get(reportSegmentTemplateId, () -> iReportSegmentTemplateService.getChildren(reportSegmentTemplateId));
	}
}
