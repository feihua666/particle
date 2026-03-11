package com.particle.tenant.adapter.feign.client.rpc;

import com.particle.common.client.dto.command.CommonBatchIdCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 租户用户远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@FeignClient(name = "${particle.feign-client.tenant.name:tenant-start}", contextId = "tenantUserRpcFeignClient", url = "${particle.feign-client.tenant.url:}", path = "/rpc/tenant_user")
public interface TenantUserRpcFeignClient {

    /**
     * 添加租户用户
     *
     * @param tenantUserCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<TenantUserVO> create(@RequestBody TenantUserCreateCommand tenantUserCreateCommand);
    /**
     * 根据用户id查询用户租户信息
     * 不限制用户租户
     * @param userCommonIdCommand
     * @return
     */
    @GetMapping("/listByUserIdIgnoreTenantLimit")
    public MultiResponse<TenantUserVO> queryListByUserIdIgnoreTenantLimit(@SpringQueryMap CommonIdCommand userCommonIdCommand);

    /**
     * 根据用户id查询用户租户信息
     * @param commonBatchIdCommand
     * @return
     */
    @GetMapping("/listByUserIds")
    public MultiResponse<TenantUserVO> queryListByUserIds(@SpringQueryMap CommonBatchIdCommand commonBatchIdCommand);




}
