package com.particle.component.autoconfigure.user.role;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.particle.common.client.dto.command.CommonBatchIdCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户添加监听，用来添加角色
 * </p>
 *
 * @author yangwei
 * @since 2023-05-26 16:38:47
 */
public class RoleUserServiceListenerImpl implements IAddServiceListener<UserDO> , IUpdateServiceListener<UserDO>, IQueryWrapperHandler<UserDO> {

    @Autowired
    private RoleUserRelRpcFeignClient roleUserRelRpcFeignClient;
    @Autowired
	private RoleRpcFeignClient roleRpcFeignClient;

	@Override
	public void postAdd(UserDO po) {
		List<Long> roleIds = null;
		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof UserCreateCommand) {
				roleIds = ((UserCreateCommand) po.getAddControl()).getRoleIds();
			}
		}
        if (CollectionUtil.isEmpty(roleIds)) {
            return;
        }
        UserAssignRoleCommand userAssignRoleCommand = new UserAssignRoleCommand();
        userAssignRoleCommand.setUserId(po.getId());
        userAssignRoleCommand.setCheckedRoleIds(roleIds);
        roleUserRelRpcFeignClient.userAssignRole(userAssignRoleCommand);
	}

	@Override
	public void postUpdate(UserDO po) {
		List<Long> roleIds = null;
		if (po.getUpdateControl() != null) {
			if (po.getUpdateControl() instanceof UserUpdateCommand) {
				roleIds = ((UserUpdateCommand) po.getUpdateControl()).getRoleIds();
			}
		}
        if (CollectionUtil.isEmpty(roleIds)) {
            return;
        }
        UserAssignRoleCommand userAssignRoleCommand = new UserAssignRoleCommand();
        userAssignRoleCommand.setUserId(po.getId());
        userAssignRoleCommand.setCheckedRoleIds(roleIds);
        roleUserRelRpcFeignClient.userAssignRole(userAssignRoleCommand);

	}

	/**
	 * 查询时，如果存在角色id，添加角色id查询条件，如果存在角色类型字典id，添加角色类型字典id查询条件
	 * @param queryWrapper
	 * @param queryForm
	 */
	@Override
	public void handle(QueryWrapper<UserDO> queryWrapper, QueryCommand queryForm) {
        // 角色id
		Long roleId = null;
        // 角色名称
        String roleName = null;
        // 角色类型字典id
		Long roleTypeDictId = null;

		if (queryForm instanceof UserQueryListCommand) {
			roleId = ((UserQueryListCommand) queryForm).getRoleId();
            roleName = ((UserQueryListCommand) queryForm).getRoleName();
			roleTypeDictId = ((UserQueryListCommand) queryForm).getRoleTypeDictId();
		}else if (queryForm instanceof UserPageQueryCommand) {
			roleId = ((UserPageQueryCommand) queryForm).getRoleId();
            roleName = ((UserPageQueryCommand) queryForm).getRoleName();
			roleTypeDictId = ((UserPageQueryCommand) queryForm).getRoleTypeDictId();
		}

		if (roleId != null) {
			// 存在根据角色id查询，拼接查询条件
            RoleUserRelQueryListCommand roleUserRelQueryListCommand = new RoleUserRelQueryListCommand();
            roleUserRelQueryListCommand.setRoleId(roleId);
            MultiResponse<RoleUserRelVO> roleUserRelVOMultiResponse = roleUserRelRpcFeignClient.queryList(roleUserRelQueryListCommand);
            List<RoleUserRelVO> roleUserRelVOS = roleUserRelVOMultiResponse.getData();
			List<Long> userIds = roleUserRelVOS.stream().map(RoleUserRelVO::getUserId).collect(Collectors.toList());
			if (userIds.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(UserDO.COLUMN_ID,userIds);
			}
		}
        if (StrUtil.isNotEmpty(roleName)) {
            RoleQueryListCommand roleQueryListCommand = new RoleQueryListCommand();
            roleQueryListCommand.setName(roleName);
            MultiResponse<RoleVO> roleVOMultiResponse = roleRpcFeignClient.queryList(roleQueryListCommand);
            List<RoleVO> roleVOS = roleVOMultiResponse.getData();
            if (CollectionUtil.isEmpty(roleVOS)) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
            }else {
                List<Long> roleIds = roleVOS.stream().map(RoleVO::getId).collect(Collectors.toList());
                // 存在根据角色id查询，拼接查询条件
                CommonBatchIdCommand commonBatchIdCommand = CommonBatchIdCommand.create(roleIds);
                MultiResponse<RoleUserRelVO> roleUserRelVOMultiResponse = roleUserRelRpcFeignClient.queryListByRoleIds(commonBatchIdCommand);
                List<RoleUserRelVO> roleUserRelVOS = roleUserRelVOMultiResponse.getData();
                List<Long> userIds = roleUserRelVOS.stream().map(RoleUserRelVO::getUserId).collect(Collectors.toList());
                if (userIds.isEmpty()) {
                    // 为空就是不存在，不存在，得加一个不存在的条件
                    queryWrapper.apply("false");
                }else {
                    queryWrapper.in(UserDO.COLUMN_ID,userIds);
                }
            }
        }
		// 查询某一类角色的 用户
		if (roleTypeDictId != null) {
            RoleQueryListCommand roleQueryListCommand = new RoleQueryListCommand();
            roleQueryListCommand.setTypeDictId(roleTypeDictId);
            MultiResponse<RoleVO> roleVOMultiResponse = roleRpcFeignClient.queryList(roleQueryListCommand);
            List<RoleVO> roleVOS = roleVOMultiResponse.getData();
            if (roleVOS.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
            }else{
                List<Long> roleIds = roleVOS.stream().map(RoleVO::getId).collect(Collectors.toList());
                // 存在根据角色id查询，拼接查询条件
                CommonBatchIdCommand commonBatchIdCommand = CommonBatchIdCommand.create(roleIds);
                MultiResponse<RoleUserRelVO> roleUserRelVOMultiResponse = roleUserRelRpcFeignClient.queryListByRoleIds(commonBatchIdCommand);
                List<RoleUserRelVO> roleUserRelVOS = roleUserRelVOMultiResponse.getData();
                List<Long> userIds = roleUserRelVOS.stream().map(RoleUserRelVO::getUserId).collect(Collectors.toList());
                if (userIds.isEmpty()) {
                    // 为空就是不存在，不存在，得加一个不存在的条件
                    queryWrapper.apply("false");
                }else {
                    queryWrapper.in(UserDO.COLUMN_ID,userIds);
                }
			}

		}


	}
}
