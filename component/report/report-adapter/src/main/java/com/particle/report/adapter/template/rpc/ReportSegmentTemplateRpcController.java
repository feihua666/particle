package com.particle.report.adapter.template.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.report.adapter.feign.client.template.rpc.ReportSegmentTemplateRpcFeignClient;
import com.particle.report.client.template.api.IReportSegmentTemplateApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报告片段模板远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Tag(name = "报告片段模板远程调用相关接口")
@RestController
@RequestMapping("/rpc/report_segment_template")
public class ReportSegmentTemplateRpcController extends AbstractBaseRpcAdapter implements ReportSegmentTemplateRpcFeignClient  {

	@Autowired
	private IReportSegmentTemplateApplicationService iReportSegmentTemplateApplicationService;


}
