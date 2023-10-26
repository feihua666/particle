package com.particle.report.adapter.api.interceptor;

import com.particle.report.client.dto.command.ReportApiGenerateCommand;

/**
 * <p>
 * 报告生成前置和后置处理
 * </p>
 *
 * @author yangwei
 * @since 2023/10/26 14:55
 */
public interface IReportApiGenerateInterceptor {

    void pre(ReportApiGenerateCommand reportApiGenerateCommand);
    void post(Object result,ReportApiGenerateCommand reportApiGenerateCommand);
}
