package com.particle.report.adapter.reportapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.report.adapter.feign.client.reportapi.rpc.ReportReportApiRpcFeignClient;
import com.particle.report.client.reportapi.api.IReportReportApiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告接口远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Tag(name = "报告接口远程调用相关接口")
@RestController
@RequestMapping("/rpc/report_report_api")
public class ReportReportApiRpcController extends AbstractBaseRpcAdapter implements ReportReportApiRpcFeignClient  {

	@Autowired
	private IReportReportApiApplicationService iReportReportApiApplicationService;


}
