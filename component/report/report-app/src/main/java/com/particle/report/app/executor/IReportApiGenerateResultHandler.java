package com.particle.report.app.executor;

import com.particle.report.client.dto.data.ReportApiGenerateVO;

/**
 * <p>
 * 报告后置处理，旨在方便后置处理一些逻辑，一般生成报告是生成要本地文件，如果需要将文件上传到oss文件中，可能会使用到该结果处理器扩展
 * </p>
 *
 * @author yangwei
 * @since 2023/10/8 15:52
 */
public interface IReportApiGenerateResultHandler {


    void handle(ReportApiGenerateVO reportApiGenerateVO);
}
