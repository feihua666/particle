package com.particle.role.adapter.roledatascoperel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.role.adapter.feign.client.roledatascoperel.rpc.RoleDataScopeRelRpcFeignClient;
import com.particle.role.app.roledatascoperel.structmapping.RoleDataScopeRelAppStructMapping;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色数据范围关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role_data_scope_rel")
public class RoleDataScopeRelRpcController extends AbstractBaseRpcAdapter implements RoleDataScopeRelRpcFeignClient  {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;
    @Autowired
    private IRoleDataScopeRelService roleDataScopeRelService;

    @Operation(summary = "根据字典roleId查询")
    @Override
    public MultiResponse<RoleDataScopeRelVO> getByRoleId(Long roleId) {
        List<RoleDataScopeRelDO> roleDataScopeRelDOS = roleDataScopeRelService.getByRoleId(roleId);
        List<RoleDataScopeRelVO> roleDataScopeRelVOS = RoleDataScopeRelAppStructMapping.instance.roleDataScopeRelDOsToRoleDataScopeRelVOs(roleDataScopeRelDOS);
        return MultiResponse.of(roleDataScopeRelVOS);
    }
}
