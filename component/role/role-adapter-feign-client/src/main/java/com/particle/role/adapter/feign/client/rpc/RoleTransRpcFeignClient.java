package com.particle.role.adapter.feign.client.rpc;

import com.particle.global.trans.api.ITransService;
import com.particle.component.light.share.role.RoleTransVO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 角色翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.role:role}",path = "/rpc/role")
public interface RoleTransRpcFeignClient extends ITransService<RoleTransVO,Long> {
}
