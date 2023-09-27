package com.particle.report.adapter.feign.client.template.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 报告片段模板远程调用
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@FeignClient(name = "${particle.feign-client.name.report:report}",path = "/rpc/report_segment_template")
public interface ReportSegmentTemplateRpcFeignClient {









}
