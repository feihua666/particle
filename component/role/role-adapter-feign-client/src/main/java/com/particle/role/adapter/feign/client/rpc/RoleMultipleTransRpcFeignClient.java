package com.particle.role.adapter.feign.client.rpc;

import com.particle.global.trans.api.ITransService;
import com.particle.role.client.dto.data.RoleTransVO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * <p>
 * 部门翻译远程调用 后缀 mt 代表MultipleTrans，表示多个结果翻译
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.role:role}",path = "/rpc/role/mt")
public interface RoleMultipleTransRpcFeignClient extends ITransService<List<RoleTransVO>,Long> {
}
