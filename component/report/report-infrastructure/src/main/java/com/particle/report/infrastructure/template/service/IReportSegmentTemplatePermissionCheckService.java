package com.particle.report.infrastructure.template.service;

import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;

/**
 * <p>
 * 报告片段模板权限校验检查
 * </p>
 *
 * @author yangwei
 * @since 2023/10/8 15:01
 */
public interface IReportSegmentTemplatePermissionCheckService {

    /**
     * 报告片段模板权限校验检查
     * @param reportSegmentTemplateDO
     * @return true=有权限，false=无权限
     */
    public boolean hasPermission(ReportSegmentTemplateDO reportSegmentTemplateDO);
}
