package com.particle.tenant.adapter.feign.client.tenantfunc.rpc;

import com.particle.global.dto.response.MultiResponse;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 租户功能菜单远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@FeignClient(name = "${particle.feign-client.tenant.name:tenant-start}", contextId = "tenantFuncRpcFeignClient", url = "${particle.feign-client.tenant.url:}", path = "/rpc/tenant_func")
public interface TenantFuncRpcFeignClient {


    /**
     * 列表查询租户功能菜单
     * @param tenantFuncQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<TenantFuncVO> queryList(@SpringQueryMap TenantFuncQueryListCommand tenantFuncQueryListCommand);





}
