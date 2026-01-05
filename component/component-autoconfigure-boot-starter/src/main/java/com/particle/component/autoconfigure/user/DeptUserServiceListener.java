package com.particle.component.autoconfigure.user;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.adapter.feign.client.deptuserrel.rpc.DeptUserRelRpcFeignClient;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelCreateCommand;
import com.particle.dept.client.deptuserrel.dto.command.DeptUserRelUpdateCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.MultiResponse;
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
    private DeptUserRelRpcFeignClient deptUserRelRpcFeignClient;

	@Override
	public void postAdd(UserDO po) {
		// 如果部门id不为空，添加部门关系
		if (po.getAddControl() instanceof UserCreateCommand) {
			UserCreateCommand userCreateCommand = (UserCreateCommand) po.getAddControl();
			if (userCreateCommand.getDeptId() != null) {
                DeptUserRelCreateCommand deptUserRelCreateCommand = new DeptUserRelCreateCommand();
                deptUserRelCreateCommand.setDeptId(userCreateCommand.getDeptId());
                deptUserRelCreateCommand.setUserId(po.getId());
                deptUserRelRpcFeignClient.create(deptUserRelCreateCommand);
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
                    IdCommand deleteCommand = IdCommand.create(po.getId());
                    deptUserRelRpcFeignClient.deleteByUserId(deleteCommand);
				}
			}else {
				// 如果不为空，判断是否变更了部门，如果变更需要更新
				// 一个用户只能有一个部门，这里直接接收一条数据
                DeptUserRelVO deptUserRelVO = null ;
                DeptUserRelQueryListCommand deptUserRelQueryListCommand = new DeptUserRelQueryListCommand();
                deptUserRelQueryListCommand.setUserId(po.getId());
                MultiResponse<DeptUserRelVO> deptUserRelVOMultiResponse = deptUserRelRpcFeignClient.queryList(deptUserRelQueryListCommand);
                List<DeptUserRelVO> deptUserRelVOS = deptUserRelVOMultiResponse.getData();
                if (CollectionUtil.isNotEmpty(deptUserRelVOS)){
                    deptUserRelVO = deptUserRelVOS.get(0);
                }
                if (deptUserRelVO == null) {
					// 如果之前没有，直接添加
                    DeptUserRelCreateCommand deptUserRelCreateCommand = new DeptUserRelCreateCommand();
                    deptUserRelCreateCommand.setDeptId(userUpdateCommand.getDeptId());
                    deptUserRelCreateCommand.setUserId(po.getId());
                    deptUserRelRpcFeignClient.create(deptUserRelCreateCommand);
				}else {
					// 如果之前有判断是否有变化
					if (!deptUserRelVO.getDeptId().equals(userUpdateCommand.getDeptId())) {
					    //	有变化，更新新的部门
                        DeptUserRelUpdateCommand deptUserRelUpdateCommand = new DeptUserRelUpdateCommand();
                        deptUserRelUpdateCommand.setId(deptUserRelVO.getId());
                        deptUserRelUpdateCommand.setDeptId(userUpdateCommand.getDeptId());
                        deptUserRelUpdateCommand.setUserId(po.getId());
                        deptUserRelUpdateCommand.setVersion(deptUserRelVO.getVersion());
                        deptUserRelRpcFeignClient.update(deptUserRelUpdateCommand);
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
            DeptUserRelQueryListCommand deptUserRelQueryListCommand = new DeptUserRelQueryListCommand();
            deptUserRelQueryListCommand.setDeptId(deptId);
            MultiResponse<DeptUserRelVO> deptUserRelVOMultiResponse = deptUserRelRpcFeignClient.queryList(deptUserRelQueryListCommand);
            List<DeptUserRelVO> deptUserRelVOS = deptUserRelVOMultiResponse.getData();
            if (CollectionUtil.isEmpty(deptUserRelVOS)) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
                return;
            }else {
                List<Long> userIds = deptUserRelVOS.stream().map(DeptUserRelVO::getUserId).collect(Collectors.toList());
                queryWrapper.in(UserDO.COLUMN_ID,userIds);
            }
		}
	}
}
