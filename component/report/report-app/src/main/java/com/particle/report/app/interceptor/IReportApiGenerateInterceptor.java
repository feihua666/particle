package com.particle.report.app.interceptor;

import com.particle.global.dto.response.SingleResponse;
import com.particle.report.client.dto.command.ReportApiGenerateCommand;
import com.particle.report.client.dto.data.ReportApiGenerateVO;

/**
 * <p>
 * 报告生成前置和后置处理
 * </p>
 *
 * @author yangwei
 * @since 2023/10/26 14:55
 */
public interface IReportApiGenerateInterceptor {

    /**
     * 报告生成前置处理
     * @param reportApiGenerateCommand
     */
    void pre(ReportApiGenerateCommand reportApiGenerateCommand);
    /**
     * 报告生成后置处理
     * @param result
     * @param reportApiGenerateCommand
     */
    void post(SingleResponse<ReportApiGenerateVO> result, ReportApiGenerateCommand reportApiGenerateCommand);
}
