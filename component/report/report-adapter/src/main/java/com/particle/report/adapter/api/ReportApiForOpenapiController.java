package com.particle.report.adapter.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.particle.common.adapter.api.AbstractBaseApiAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.api.IReportApiApplicationService;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告服务开放接口
 * 基础路径 {@link ReportApiForOpenapiController#API_REQUEST_MAPPING} 中 re 表示 report 的缩写，为了缩短路径
 * </p>
 *
 * @author yw
 * @since 2023-09-26 15:46:59
 */
@Tag(name = "报告服务开放接口")
@RestController
@RequestMapping(ReportApiForOpenapiController.API_REQUEST_MAPPING)
public class ReportApiForOpenapiController extends AbstractBaseApiAdapter {
	public static final String API_REQUEST_MAPPING = "/openapi/re";
	public static final String API_ENTRY = "";
	public static final String API_ENTRY_PREFIX = API_REQUEST_MAPPING + API_ENTRY;
	public static final String API_ENTRY_REQUEST_MAPPING = API_ENTRY + "/**";

	@Autowired
	private IReportApiApplicationService iReportApiApplicationService;

	@PreAuthorize("@pms.hasPermission('report:openapi')")
	@Operation(summary = "报告服务开放接口入口")
	@PostMapping(API_ENTRY_REQUEST_MAPPING)
	public SingleResponse<ReportApiGenerateVO> reportApiEntry(@RequestBody Object param, HttpServletRequest request){

		ReportApiGenerateCommand reportApiGenerateCommand = new ReportApiGenerateCommand();
		reportApiGenerateCommand.setUrl(getReportReportApiUrl(request));
		reportApiGenerateCommand.setParam(param);
		reportApiGenerateCommand.setQueryString(request.getQueryString());

		return iReportApiApplicationService.reportApiGenerate(reportApiGenerateCommand);
	}

	/**
	 * 获取 reportReportApi 中的自定义地址
	 * @param request
	 * @return
	 */
	private String getReportReportApiUrl(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		requestURI = URLUtil.decode(requestURI);

		String dataApiUrl = StrUtil.subAfter(requestURI, getPrefix(), false);

		return dataApiUrl;
	}

	/**
	 * 获取地址请求前缀
	 * @return
	 */
	public String getPrefix(){
		return API_ENTRY_PREFIX;
	}
}
