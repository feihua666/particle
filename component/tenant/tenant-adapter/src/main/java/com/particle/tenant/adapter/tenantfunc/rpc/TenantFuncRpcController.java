package com.particle.tenant.adapter.tenantfunc.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.tenant.adapter.feign.client.tenantfunc.rpc.TenantFuncRpcFeignClient;
import com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import com.particle.tenant.client.tenantfunc.api.representation.ITenantFuncRepresentationApplicationService;
import com.particle.tenant.client.tenantfunc.dto.command.representation.TenantFuncQueryListCommand;
import com.particle.tenant.client.tenantfunc.dto.data.TenantFuncVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能菜单远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Tag(name = "租户功能菜单远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_func")
public class TenantFuncRpcController extends AbstractBaseRpcAdapter implements TenantFuncRpcFeignClient  {

	@Autowired
	private ITenantFuncApplicationService iTenantFuncApplicationService;
    @Autowired
    private ITenantFuncRepresentationApplicationService iTenantFuncRepresentationApplicationService;

    @Operation(summary = "列表查询租户功能菜单")
    @Override
    public MultiResponse<TenantFuncVO> queryList(TenantFuncQueryListCommand tenantFuncQueryListCommand) {
        return iTenantFuncRepresentationApplicationService.queryList(tenantFuncQueryListCommand);
    }
}
