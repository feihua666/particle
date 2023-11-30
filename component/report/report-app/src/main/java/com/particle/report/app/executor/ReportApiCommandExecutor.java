package com.particle.report.app.executor;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.WeakCache;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import com.particle.report.infrastructure.reportapi.service.IReportReportApiService;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderParam;
import com.particle.report.infrastructure.template.dto.ReportSegmentTemplateRenderResult;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateRenderService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.script.Bindings;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 报告接口指令执行器
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 14:52
 */
@Component
@Validated
public class ReportApiCommandExecutor extends AbstractBaseExecutor {

	public static WeakCache<String, ReportReportApiDO> reportReportApiDOCache = CacheUtil.newWeakCache(14 * 1 * 60000);


	@Autowired
	private IReportSegmentTemplateRenderService iReportSegmentTemplateRenderService;

	@Autowired
	private IReportReportApiService iReportReportApiService;

	@Autowired(required = false)
	private IReportApiGenerateDataInjector iReportApiGenerateDataInjector;

	@Autowired
	private IReportApiGenerateResultHandler iReportApiGenerateResultHandler;

	/**
	 * 生成报告
	 * @param reportApiGenerateCommand
	 * @return
	 */
	@SneakyThrows
	public SingleResponse<ReportApiGenerateVO> reportApiGenerate(ReportApiGenerateCommand reportApiGenerateCommand) {
		Map<String, Object> global = new HashMap<>();
		// 接口参数，这里记为 body
		global.put("body", reportApiGenerateCommand.getParam());
		// 查询字符串，转为map，经查看源码，不会为null
		global.put("queryStringMap", UrlQuery.of(reportApiGenerateCommand.getQueryString(), CharsetUtil.CHARSET_UTF_8).getQueryMap());
		global.put("queryString", reportApiGenerateCommand.getQueryString());

		ReportReportApiDO reportReportApiDO = reportReportApiDOCache.get(reportApiGenerateCommand.getUrl(), () -> iReportReportApiService.getByUrl(reportApiGenerateCommand.getUrl()));

		Assert.notNull(reportReportApiDO,"接口地址不存在" + reportApiGenerateCommand.getUrl());

		ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam = ReportSegmentTemplateRenderParam.create(
				global,
				new HashMap<>(),
				new HashMap<>(),
				reportReportApiDO.getReportSegmentTemplateId(),
				FilePathTool.ensureNotEndFileSeparator(FileUtil.getTmpDirPath()) + File.separator + "particle_report" + File.separator + LocalDateTimeUtil.format(LocalDateTimeUtil.now(), DatePattern.PURE_DATETIME_PATTERN) + SnowflakeIdTool.nextId()
		);

		// 注入数据处理
		if (iReportApiGenerateDataInjector != null) {
			iReportApiGenerateDataInjector.dataInject(reportSegmentTemplateRenderParam,reportReportApiDO);
		}

		ReportSegmentTemplateRenderResult reportSegmentTemplateRenderResult = iReportSegmentTemplateRenderService.render(reportSegmentTemplateRenderParam);

		if (reportSegmentTemplateRenderResult == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.UNKNOWN_ERROR, "报告生成失败，返回渲染结果为空，可能系统内部错误");
		}
		String fileAbsolutePath = Optional.ofNullable(reportSegmentTemplateRenderResult.getTemplateNameContentResultFile()).map(item -> item.getAbsolutePath()).orElse(null);

		SingleResponse<ReportApiGenerateVO> result = null;
		// 后置脚本处理
		String postScript = reportReportApiDO.getPostScript();
		if (StrUtil.isNotEmpty(postScript)) {
			Bindings bindings = GroovyTool.createBindings();
			// 添加两个绑定参数，以供脚本使用
			bindings.put("reportSegmentTemplateRenderParam",reportSegmentTemplateRenderParam);
			bindings.put("reportSegmentTemplateRenderResult",reportSegmentTemplateRenderResult);
			Object o = GroovyTool.compileAndEval(postScript, bindings, true);
			// 如果返回null，也直接返回null
			if (o == null) {
				result =  SingleResponse.of(ReportApiGenerateVO.create(null));
			} else if (o instanceof String) {
				result = SingleResponse.of(ReportApiGenerateVO.create(((String) o)));
			}else {
				throw new RuntimeException("the returned value type " + o.getClass().getName() + " can not support,only String support from script");
			}
		}
		if (result == null) {
			result = SingleResponse.of(ReportApiGenerateVO.create(fileAbsolutePath));
		}
		iReportApiGenerateResultHandler.handle(result.getData());

		return result;
	}

	@Autowired
	public void setiReportSegmentTemplateRenderService(IReportSegmentTemplateRenderService iReportSegmentTemplateRenderService) {
		this.iReportSegmentTemplateRenderService = iReportSegmentTemplateRenderService;
	}
}
