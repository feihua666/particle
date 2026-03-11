package com.particle.tenant.adapter.rpc;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.app.structmapping.TenantAppStructMapping;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.client.dto.command.representation.TenantQueryAllCommand;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class TenantRpcController extends AbstractBaseRpcAdapter implements TenantRpcFeignClient {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;
	@Autowired
	private ITenantService iTenantService;

	@Operation(summary = "获取所有租户，不加任何条件")
	@Override
	public MultiResponse<TenantRpcVO> getAllTenant(TenantQueryAllCommand tenantQueryAllCommand) {
        List<TenantDO> allIgnoreTenantLimit = iTenantService.getAllIgnoreTenantLimit();
        List<TenantRpcVO> tenantVOS = TenantAppStructMapping.instance.tenantDOsToTenantRpcVOs(allIgnoreTenantLimit);

        List<Long> filterTenantIds = tenantQueryAllCommand.getFilterTenantIds();
        if (CollectionUtil.isNotEmpty(filterTenantIds)) {
            tenantVOS = tenantVOS.stream().filter(tenantVO -> filterTenantIds.contains(tenantVO.getId())).toList();
        }

        return MultiResponse.of(tenantVOS);
	}
    @Operation(summary = "分页获取所有租户，不加任何条件")
	@Override
	public PageResponse<TenantRpcVO> pageAllTenant(PageQueryCommand pageQueryCommand) {
		Page<TenantDO> tenantDOPage = iTenantService.pageAllIgnoreTenantLimit(pageQueryCommand.getPageNo(), pageQueryCommand.getPageSize());
		return TenantAppStructMapping.instance.infrastructurePageToRpcPageResponse(tenantDOPage);
	}

	@Operation(summary = "根据id获取租户，不加任何条件")
	@Override
	public SingleResponse<TenantRpcVO> getById(CommonIdCommand commonIdCommand) {
		TenantDO tenantDO = iTenantService.getByIdIgnoreTenantLimit(commonIdCommand.getId());
		return SingleResponse.of(TenantAppStructMapping.instance.tenantDOToTenantRpcVO(tenantDO));
	}


}
