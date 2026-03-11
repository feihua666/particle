package com.particle.role.adapter.feign.client.roleuserrel.rpc;

import com.particle.common.client.dto.command.CommonBatchIdCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色用户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.role.name:role-start}", contextId = "roleUserRelRpcFeignClient", url = "${particle.feign-client.role.url:}", path = "/rpc/roleUserRel")
public interface RoleUserRelRpcFeignClient {

	/**
	 * 添加角色用户关系
	 *
	 * @param roleUserRelCreateCommand
	 * @return
	 */
	@PostMapping("/createWithTenantId")
	public SingleResponse<RoleUserRelVO> createWithTenantId(@RequestBody RoleUserRelWithTenantIdCreateCommand roleUserRelCreateCommand);

    /**
     * 用户分配角色
     *
     * @param cf
     * @return
     */
    @PostMapping("/user/assign/role")
    @ResponseStatus(HttpStatus.CREATED)
    public Response userAssignRole(@RequestBody UserAssignRoleCommand cf);

    /**
     * 根据用户id删除
     *
     * @param userCommonIdCommand
     * @return
     */
    @DeleteMapping("/deleteByUserId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteByUserId(@RequestBody CommonIdCommand userCommonIdCommand);

    /**
     * 列表查询角色用户关系
     *
     * @param roleUserRelQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand);

    /**
     * 根据角色ids列表查询角色用户关系
     *
     * @param commonBatchIdCommand
     * @return
     */
    @GetMapping("/listByRoleIds")
    public MultiResponse<RoleUserRelVO> queryListByRoleIds(CommonBatchIdCommand commonBatchIdCommand);
}
