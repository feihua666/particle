package com.particle.role.adapter.feign.client.rolefuncrel.rpc;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelDeleteWithTenantIdCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListByFuncIdsCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListByRoleIdsCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 角色菜单功能关系远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.roleFuncRel:roleFuncRel}",path = "/rpc/role-func-rel")
public interface RoleFuncRelRpcFeignClient {


	/**
	 * 删除功能id范围外的角色功能关系数据
	 * 该功能接口主要用于在租户应用分配功能后，可能功能会减少，将减少的功能联动角色一并减少
	 * @param roleFuncRelDeleteWithTenantIdCommand
	 * @return
	 */
    @DeleteMapping("/deleteWithTenantId")
	Response deleteWithTenantId(@RequestBody RoleFuncRelDeleteWithTenantIdCommand roleFuncRelDeleteWithTenantIdCommand);

    /**
     * 列表查询角色菜单功能关系
     * @param roleFuncRelQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand);

    /**
     * 列表查询角色菜单功能关系
     * @param roleFuncRelQueryListByRoleIdsCommand
     * @return
     */
    @GetMapping("/listByRoleIds")
    public MultiResponse<RoleFuncRelVO> queryListByRoleIds(RoleFuncRelQueryListByRoleIdsCommand roleFuncRelQueryListByRoleIdsCommand);


    /**
     * 列表查询角色菜单功能关系
     * @param roleFuncRelQueryListByFuncIdsCommand
     * @return
     */
    @GetMapping("/listByFuncIds")
    public MultiResponse<RoleFuncRelVO> queryListByFuncIds(RoleFuncRelQueryListByFuncIdsCommand roleFuncRelQueryListByFuncIdsCommand);
}
