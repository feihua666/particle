package com.particle.tenant.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.common.client.dto.command.BatchIdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.TenantTool;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.command.representation.TenantUserQueryListCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 租户用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Tag(name = "租户用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_user")
public class TenantUserRpcController extends AbstractBaseRpcAdapter implements TenantUserRpcFeignClient  {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;
    @Autowired
    private ITenantUserService iTenantUserService;

    @Operation(summary = "添加租户用户")
    @PostMapping("/create")
    public SingleResponse<TenantUserVO> create(@RequestBody TenantUserCreateCommand tenantUserCreateCommand){
        if (tenantUserCreateCommand.getTenantId() == null) {
            tenantUserCreateCommand.setTenantId(TenantTool.getTenantId());
        }
        return iTenantUserApplicationService.create(tenantUserCreateCommand);
    }

    @Operation(summary = "根据用户id查询租户用户，不限制租户")
    @Override
    public MultiResponse<TenantUserVO> queryListByUserIdIgnoreTenantLimit(IdCommand userIdCommand){
        List<TenantUserDO> tenantUserDOS = iTenantUserService.getByUserIdIgnoreTenantLimit(userIdCommand.getId());
        List<TenantUserVO> tenantUserVOS = TenantUserAppStructMapping.instance.tenantUserDOsToTenantUserVOs(tenantUserDOS);
        return MultiResponse.of(tenantUserVOS);
    }
    @Operation(summary = "根据用户ids查询租户用户")
    @Override
    public MultiResponse<TenantUserVO> queryListByUserIds(BatchIdCommand batchIdCommand) {
        List<TenantUserDO> tenantUserDOS = iTenantUserService.getByUserIds(batchIdCommand.getIds());
        List<TenantUserVO> tenantUserVOS = TenantUserAppStructMapping.instance.tenantUserDOsToTenantUserVOs(tenantUserDOS);
        return MultiResponse.of(tenantUserVOS);
    }
}
