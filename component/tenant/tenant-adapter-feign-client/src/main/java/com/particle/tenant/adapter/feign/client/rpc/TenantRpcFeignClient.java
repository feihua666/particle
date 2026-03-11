package com.particle.tenant.adapter.feign.client.rpc;

import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import com.particle.tenant.client.dto.data.TenantVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 租户远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@FeignClient(name = "${particle.feign-client.tenant.name:tenant-start}", contextId = "tenantRpcFeignClient", url = "${particle.feign-client.tenant.url:}", path = "/rpc/tenant")
public interface TenantRpcFeignClient {


	/**
	 * 获取所有租户,没有任何限制
	 * @return
	 */
	@GetMapping("getAllTenant")
	MultiResponse<TenantRpcVO> getAllTenant(@SpringQueryMap TenantQueryAllCommand tenantQueryAllCommand);

	/**
	 * 分页获取所有租户，没有任何限制
	 * @return
	 */
	@GetMapping("pageAllTenant")
	PageResponse<TenantRpcVO> pageAllTenant(@SpringQueryMap PageQueryCommand pageQueryCommand);

	/**
	 * 根据id获取租户,没有任何限制
	 * @return
	 */
	@GetMapping("getById")
	SingleResponse<TenantRpcVO> getById(@SpringQueryMap CommonIdCommand commonIdCommand);

}
