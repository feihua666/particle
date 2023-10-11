package com.particle.report.adapter.api.permission;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.security.PermissionService;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplatePermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 基于spring security的权限校验器
 * 此类是可选的，参见配置 {@link com.particle.report.ReportAutoConfiguration#reportSegmentTemplatePermissionCheckService()}
 * </p>
 *
 * @author yangwei
 * @since 2023/10/8 15:30
 */
public class SecurityPermissionReportSegmentTemplatePermissionCheckServiceImpl implements IReportSegmentTemplatePermissionCheckService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean hasPermission(ReportSegmentTemplateDO reportSegmentTemplateDO) {
        String permissions = reportSegmentTemplateDO.getPermissions();
        // 如果未配置权限
        if (StrUtil.isEmpty(permissions)) {
            return true;
        }
        return permissionService.hasPermission(permissions);
    }
}
