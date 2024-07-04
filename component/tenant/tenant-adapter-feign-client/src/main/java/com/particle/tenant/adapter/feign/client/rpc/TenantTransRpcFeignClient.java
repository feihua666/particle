package com.particle.tenant.adapter.feign.client.rpc;

import com.particle.global.trans.api.ITransService;
import com.particle.tenant.client.dto.data.TenantTransVO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 租户翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-20 17:03:41
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant")
public interface TenantTransRpcFeignClient extends ITransService<TenantTransVO,Long> {

}
