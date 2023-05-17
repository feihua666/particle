package com.particle.component.adapter.user;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.service.IDeptUserRelService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.mybatis.plus.crud.IQueryWrapperHandler;
import com.particle.global.mybatis.plus.crud.IUpdateServiceListener;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户添加监听，用来添加部门
 * </p>
 *
 * @author yangwei
 * @since 2023-05-17 09:50:35
 */
public class DeptUserServiceListener implements IAddServiceListener<UserDO>, IUpdateServiceListener<UserDO>, IQueryWrapperHandler<UserDO> {

	@Autowired
	private IDeptUserRelService deptUserRelService;

	@Override
	public void postAdd(UserDO po) {
		// 如果部门id不为空，添加部门关系
		if (po.getAddControl() instanceof UserCreateCommand) {
			UserCreateCommand userCreateCommand = (UserCreateCommand) po.getAddControl();
			if (userCreateCommand.getDeptId() != null) {
				DeptUserRelDO deptUserRelDO = new DeptUserRelDO();
				deptUserRelDO.setDeptId(userCreateCommand.getDeptId());
				deptUserRelDO.setUserId(po.getId());
				deptUserRelService.add(deptUserRelDO);
			}
		}
	}

	@Override
	public void postUpdate(UserDO po) {
		if (po.getUpdateControl() instanceof UserUpdateCommand) {
			UserUpdateCommand userUpdateCommand = (UserUpdateCommand) po.getUpdateControl();
			// 如果部门为空兼容一下 SetNullWhenNull 注解
			if (userUpdateCommand.getDeptId() == null) {
				Field deptIdField = ReflectUtil.getField(userUpdateCommand.getClass(), "deptId");
				SetNullWhenNull setNullWhenNull = AnnotationUtil.getAnnotation(deptIdField, SetNullWhenNull.class);
				if (setNullWhenNull != null) {
				//	删除用户的部门
					deptUserRelService.deleteByColumn(po.getId(), DeptUserRelDO::getUserId);
				}
			}else {
				// 如果不为空，判断是否变更了部门，如果变更需要更新
				// 一个用户只能有一个部门，这里直接接收一条数据
				DeptUserRelDO userRelDO = deptUserRelService.getOneByColumn(po.getId(), DeptUserRelDO::getUserId);
				if (userRelDO == null) {
					// 如果之前没有，直接添加
					DeptUserRelDO deptUserRelDO = new DeptUserRelDO();
					deptUserRelDO.setDeptId(userUpdateCommand.getDeptId());
					deptUserRelDO.setUserId(po.getId());
					deptUserRelService.add(deptUserRelDO);
				}else {
					// 如果之前有判断是否有变化
					if (!userRelDO.getDeptId().equals(userUpdateCommand.getDeptId())) {
					//	有变化，更新新的部门
						userRelDO.setDeptId(userUpdateCommand.getDeptId());
						deptUserRelService.updateById(userRelDO);
					}
				}
			}
		}

	}

	/**
	 * 查询时，如果存在部门id，添加部门id查询条件
	 * @param queryWrapper
	 * @param queryForm
	 */
	@Override
	public void handle(QueryWrapper<UserDO> queryWrapper, QueryCommand queryForm) {
		Long deptId = null;
		if (queryForm instanceof UserQueryListCommand) {
			deptId = ((UserQueryListCommand) queryForm).getDeptId();
		}else if (queryForm instanceof UserPageQueryCommand) {
			deptId = ((UserPageQueryCommand) queryForm).getDeptId();
		}

		if (deptId != null) {
			// 存在根据部门id查询，拼接查询条件
			List<DeptUserRelDO> deptUserRelDOS = deptUserRelService.list(Wrappers.<DeptUserRelDO>lambdaQuery().eq(DeptUserRelDO::getDeptId, deptId));
			List<Long> collect = deptUserRelDOS.stream().map(DeptUserRelDO::getUserId).collect(Collectors.toList());
			if (collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(UserDO.COLUMN_ID,collect);
			}
		}
	}
}
