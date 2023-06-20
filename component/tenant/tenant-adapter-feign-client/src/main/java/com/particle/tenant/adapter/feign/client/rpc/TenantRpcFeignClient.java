package com.particle.tenant.adapter.feign.client.rpc;

import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.tenant.client.dto.data.TenantVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 租户远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant")
public interface TenantRpcFeignClient {


	/**
	 * 获取所有租户
	 * @return
	 */
	@GetMapping("listAllTenant")
	MultiResponse<TenantVO> getAllTenant();

	/**
	 * 分页获取所有租户
	 * @return
	 */
	@GetMapping("listAllTenant")
	PageResponse<TenantVO> pageAllTenant(PageQueryCommand pageQueryCommand);

}
