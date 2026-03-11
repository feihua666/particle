package com.particle.component.autoconfigure.global.webfilter.tenant;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.response.MultiResponse;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 本地租户id解析实现
 * 主要用来在 租户 模块自己使用，省去远程再调用一次 rpc
 * </p>
 *
 * @author yangwei
 * @since 2026/1/31 15:47
 */
public class TenantIdLocalResolverImpl extends AbstractTenantIdResolverImpl {


    @Autowired
    private ITenantService iTenantService;
    /**
     * 获取所有租户
     * @return
     */
    @Override
    protected List<TenantRpcVO> getAllSimpleIgnoreTenantLimit() {
        MultiResponse<TenantRpcVO> allTenant = getAllTenant(TenantQueryAllCommand.createEmpty());
        return allTenant.getData();
    }
    private MultiResponse<TenantRpcVO> getAllTenant(TenantQueryAllCommand tenantQueryAllCommand) {
        List<TenantDO> allIgnoreTenantLimit = iTenantService.getAllIgnoreTenantLimit();
        List<TenantRpcVO> tenantVOS = TenantAppStructMapping.instance.tenantDOsToTenantRpcVOs(allIgnoreTenantLimit);

        List<Long> filterTenantIds = tenantQueryAllCommand.getFilterTenantIds();
        if (CollectionUtil.isNotEmpty(filterTenantIds)) {
            tenantVOS = tenantVOS.stream().filter(tenantVO -> filterTenantIds.contains(tenantVO.getId())).toList();
        }
        return MultiResponse.of(tenantVOS);
    }
}
