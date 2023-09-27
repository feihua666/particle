package com.particle.report.adapter.feign.client.reportapi.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 报告接口远程调用
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@FeignClient(name = "${particle.feign-client.name.report:report}",path = "/rpc/report_report_api")
public interface ReportReportApiRpcFeignClient {









}
