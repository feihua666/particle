package com.particle.component.autoconfigure.global.webfilter.tenant;

import cn.hutool.core.util.StrUtil;
import com.particle.global.web.filter.TenantContextFilter;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

/**
 * <p>
 * 抽象租户id解析实现类
 * </p>
 *
 * @author yangwei
 * @since 2026/1/31 15:47
 */
public abstract class AbstractTenantIdResolverImpl implements TenantContextFilter.TenantIdResolver {

    @Override
    public Long resolveTenantId(String domainWithPort) {
        List<TenantRpcVO> tenantVOS = getAllSimpleIgnoreTenantLimit();
        // 如果存在一个租户，则返回
        if (tenantVOS.size() == 1) {
            return tenantVOS.iterator().next().getId();
        }
        for (TenantRpcVO tenantVO : tenantVOS) {
            String tenantDomain = tenantVO.getTenantDomain();
            if (Strings.isEmpty(tenantDomain)) {
                continue;
            }
            for (String domain : tenantDomain.split(",")) {
                if (StrUtil.equals(domain,domainWithPort)) {
                    return tenantVO.getId();
                }
            }
        }

        return null;
    }

    /**
     * 获取所有租户
     * @return
     */
    protected abstract List<TenantRpcVO> getAllSimpleIgnoreTenantLimit();
}
