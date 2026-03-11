package com.particle.component.autoconfigure.global.webfilter.tenant;

import com.particle.global.dto.response.MultiResponse;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 本地租户id解析实现
 * 主要用来在非 租户 模块使用，使用远程调用获取租户信息
 * </p>
 *
 * @author yangwei
 * @since 2026/1/31 15:47
 */
public class TenantIdRpcResolverImpl extends AbstractTenantIdResolverImpl {

    @Autowired
    private TenantRpcFeignClient tenantRpcFeignClient;

    /**
     * 获取所有租户
     * @return
     */
    @Override
    protected List<TenantRpcVO> getAllSimpleIgnoreTenantLimit() {
        MultiResponse<TenantRpcVO> allTenant = tenantRpcFeignClient.getAllTenant(TenantQueryAllCommand.createEmpty());
        return allTenant.getData();
    }
}
