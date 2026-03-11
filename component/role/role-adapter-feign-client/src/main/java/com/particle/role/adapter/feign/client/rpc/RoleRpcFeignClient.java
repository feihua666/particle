package com.particle.role.adapter.feign.client.rpc;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.command.representation.*;
import com.particle.role.client.dto.data.RoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 角色远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.role.name:role-start}", contextId = "roleRpcFeignClient", url = "${particle.feign-client.role.url:}", path = "/rpc/role")
public interface RoleRpcFeignClient {


	/**
	 * 添加角色
	 * @param roleCreateWithTenantIdCommand
	 * @return
	 */
	@PostMapping("/createWithTenantId")
	public SingleResponse<RoleVO> createWithTenantId(@RequestBody RoleCreateWithTenantIdCommand roleCreateWithTenantIdCommand);

    /**
     * 根据用户id查询角色
     * @param roleQueryListByUserIdCommand
     * @return
     */

    @GetMapping("/listByUserId")
    public MultiResponse<RoleVO> queryListByUserId(@SpringQueryMap RoleQueryListByUserCommonIdCommand roleQueryListByUserIdCommand);
    /**
     * 根据用户id查询角色
     * @param roleQueryListByUserIdsCommand
     * @return
     */

    @GetMapping("/listByUserIds")
    public MultiResponse<RoleVO> queryListByUserIds(@SpringQueryMap RoleQueryListByUserIdsCommand roleQueryListByUserIdsCommand);


    /**
     * 根据用户id查询角色
     * @param roleQueryListByRoleIdCommand
     * @return
     */

    @GetMapping("/listByRoleId")
    public SingleResponse<RoleVO> queryListByRoleId(@SpringQueryMap RoleQueryListByRoleCommonIdCommand roleQueryListByRoleIdCommand);


    /**
     * 根据用户id查询角色
     * @param roleQueryListByRoleIdsCommand
     * @return
     */

    @GetMapping("/listByRoleIds")
    public MultiResponse<RoleVO> queryListByRoleIds(@SpringQueryMap RoleQueryListByRoleIdsCommand roleQueryListByRoleIdsCommand);

    /**
     * 列表查询角色
     * @param roleQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<RoleVO> queryList(@SpringQueryMap RoleQueryListCommand roleQueryListCommand);
}
