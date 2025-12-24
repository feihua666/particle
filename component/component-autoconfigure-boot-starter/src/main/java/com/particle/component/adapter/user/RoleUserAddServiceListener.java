package com.particle.component.adapter.user;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class RoleUserAddServiceListener implements IAddServiceListener<UserDO> , IUpdateServiceListener<UserDO>, IQueryWrapperHandler<UserDO> {

    private static String UserIdentifierDOTableNameCache = null;
    private static String UserDOTableNameCache = null;

	@Autowired
	private IRoleUserRelService roleUserRelService;
	@Autowired
	private IRoleService roleService;
    @Autowired
    private IUserIdentifierService userIdentifierService;

	@Override
	public void postAdd(UserDO po) {
		List<Long> roleIds = null;
		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof UserCreateCommand) {
				roleIds = ((UserCreateCommand) po.getAddControl()).getRoleIds();
				if (CollectionUtil.isEmpty(roleIds)) {
					return;
				}
			}
		}


		roleUserRelService.assignRel(po.getId(), roleIds, (relDto) -> new RoleUserRelDO().setUserId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
	}

	@Override
	public void postUpdate(UserDO po) {
		List<Long> roleIds = null;
		if (po.getUpdateControl() != null) {
			if (po.getUpdateControl() instanceof UserUpdateCommand) {
				roleIds = ((UserUpdateCommand) po.getUpdateControl()).getRoleIds();
				if (CollectionUtil.isEmpty(roleIds)) {
					return;
				}
			}
		}

		roleUserRelService.removeAndAssignRel(po.getId(), roleIds,RoleUserRelDO::getUserId, (relDto) -> new RoleUserRelDO().setUserId(relDto.getMainId()).setRoleId(relDto.getOtherId()));
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

        // 用户标识id
        Long identifierId = null;
        // 登录标识，模糊匹配
        String userIdentifier = null;
        boolean isRoleNameUseLike = true;
        boolean isUserIdentifierUseLike = true;

		if (queryForm instanceof UserQueryListCommand) {
			roleId = ((UserQueryListCommand) queryForm).getRoleId();
            roleName = ((UserQueryListCommand) queryForm).getRoleName();
			roleTypeDictId = ((UserQueryListCommand) queryForm).getRoleTypeDictId();
            identifierId = ((UserQueryListCommand) queryForm).getIdentifierId();
            userIdentifier = ((UserQueryListCommand) queryForm).getUserIdentifier();
		}else if (queryForm instanceof UserPageQueryCommand) {
			roleId = ((UserPageQueryCommand) queryForm).getRoleId();
            roleName = ((UserPageQueryCommand) queryForm).getRoleName();
			roleTypeDictId = ((UserPageQueryCommand) queryForm).getRoleTypeDictId();
            identifierId = ((UserPageQueryCommand) queryForm).getIdentifierId();
            userIdentifier = ((UserPageQueryCommand) queryForm).getUserIdentifier();
		}

		if (roleId != null) {
			// 存在根据角色id查询，拼接查询条件
			List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.list(Wrappers.<RoleUserRelDO>lambdaQuery().eq(RoleUserRelDO::getRoleId, roleId));
			List<Long> collect = roleUserRelDOS.stream().map(RoleUserRelDO::getUserId).collect(Collectors.toList());
			if (collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(UserDO.COLUMN_ID,collect);
			}
		}
        if (StrUtil.isNotEmpty(roleName)) {
            List<RoleDO> roleDOs = null;
            if (isRoleNameUseLike) {
                roleDOs = roleService.listByLikeName(roleName);
            }else {
                roleDOs = roleService.listByName(roleName);

            }
            if (CollectionUtil.isEmpty(roleDOs)) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
            }else {
                List<Long> roleIds = roleDOs.stream().map(RoleDO::getId).collect(Collectors.toList());
                // 存在根据角色id查询，拼接查询条件
                List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.list(Wrappers.<RoleUserRelDO>lambdaQuery().in(RoleUserRelDO::getRoleId, roleIds));
                List<Long> collect = roleUserRelDOS.stream().map(RoleUserRelDO::getUserId).collect(Collectors.toList());
                if (collect.isEmpty()) {
                    // 为空就是不存在，不存在，得加一个不存在的条件
                    queryWrapper.apply("false");
                }else {
                    queryWrapper.in(UserDO.COLUMN_ID,collect);
                }
            }
        }
		// 查询某一类角色的 用户
		if (roleTypeDictId != null) {
			List<RoleDO> roleDOS = roleService.getByRoleTypeDictId(roleTypeDictId, null);
            if (roleDOS.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
            }else{
				List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.getByRoleIds(roleDOS.stream().map(RoleDO::getId).collect(Collectors.toList()));
				List<Long> collect = roleUserRelDOS.stream().map(RoleUserRelDO::getUserId).collect(Collectors.toList());
				if (collect.isEmpty()) {
					// 为空就是不存在，不存在，得加一个不存在的条件
					queryWrapper.apply("false");
				}else {
					queryWrapper.in(UserDO.COLUMN_ID,collect);
				}
			}

		}
        if (identifierId != null) {
            UserIdentifierDO userIdentifierDO = userIdentifierService.getById(identifierId);
            if (userIdentifierDO == null) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
            }else {
                queryWrapper.eq(UserDO.COLUMN_ID,userIdentifierDO.getUserId());
            }
        }
        if (StrUtil.isNotEmpty(userIdentifier)) {
            if (isUserIdentifierUseLike) {
                if (UserIdentifierDOTableNameCache == null) {
                    TableName annotation = AnnotationUtil.getAnnotation(UserIdentifierDO.class, TableName.class);
                    UserIdentifierDOTableNameCache = annotation.value();
                }
                if (UserDOTableNameCache == null) {
                    TableName annotation = AnnotationUtil.getAnnotation(UserDO.class, TableName.class);
                    UserDOTableNameCache = annotation.value();
                }

                // select id from component_user_identifier where user_id = component_user.id and identifier like '%222%'
                String sql = "select id from " + UserIdentifierDOTableNameCache + " where user_id = " + UserDOTableNameCache + ".id" + " and identifier like {0}";
                queryWrapper.exists(sql, SqlUtils.concatLike(userIdentifier, SqlLike.DEFAULT));
            }else {
                UserIdentifierDO userIdentifierDO = userIdentifierService.getByIdentifier(userIdentifier);
                if (userIdentifierDO == null) {
                    // 为空就是不存在，不存在，得加一个不存在的条件
                    queryWrapper.apply("false");
                }else {
                    queryWrapper.eq(UserDO.COLUMN_ID,userIdentifierDO.getUserId());
                }
            }
        }


	}
}
