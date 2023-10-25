package com.particle.tenant.adapter.rpc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.trans.result.TransResult;
import com.particle.tenant.adapter.feign.client.rpc.TenantTransRpcFeignClient;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.client.dto.data.TenantTransVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 租户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Tag(name = "租户远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant")
public class TenantRpcController extends AbstractBaseRpcAdapter implements TenantRpcFeignClient, TenantTransRpcFeignClient {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;
	@Autowired
	private ITenantService iTenantService;
	@Autowired
	private TenantTransServiceImpl tenantTransService;

	@Override
	public boolean supportBatch(String type) {
		return tenantTransService.supportBatch(type);
	}

	@Override
	public List<TransResult<TenantTransVO, Long>> transBatch(String type, Set<Long> keys) {
		return tenantTransService.transBatch(type, keys);
	}

	@Operation(summary = "获取所有租户，不加任何条件")
	@Override
	public MultiResponse<TenantVO> getAllTenant() {
		List<TenantDO> allIgnoreTenantLimit = iTenantService.getAllIgnoreTenantLimit();
		List<TenantVO> tenantVOS = TenantAppStructMapping.instance.tenantDOsToTenantVOs(allIgnoreTenantLimit);
		return MultiResponse.of(tenantVOS);
	}

	@Operation(summary = "分页获取所有租户，不加任何条件")
	@Override
	public PageResponse<TenantVO> pageAllTenant(PageQueryCommand pageQueryCommand) {
		Page<TenantDO> tenantDOPage = iTenantService.pageAllIgnoreTenantLimit(pageQueryCommand.getPageNo(), pageQueryCommand.getPageSize());
		return TenantAppStructMapping.instance.infrastructurePageToPageResponse(tenantDOPage);
	}


}