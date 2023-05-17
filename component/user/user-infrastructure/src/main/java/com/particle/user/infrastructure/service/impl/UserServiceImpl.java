package com.particle.user.infrastructure.service.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserServiceImpl extends IBaseServiceImpl<UserMapper, UserDO> implements IUserService {
	private IBaseQueryCommandMapStruct<UserDO> queryCommandMapStruct;

	@Autowired
	private IUserIdentifierService iUserIdentifierService;
	@Autowired
	private IUserIdentifierPwdService iUserIdentifierPwdService;

	@Override
	protected UserDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	/**
	 * 删除后，删除 identifier
	 * @param id
	 * @param DO
	 */
	@Override
	protected void postDeleteById(Long id, UserDO DO) {
		onUserDelete(id);
	}

	/**
	 * 删除后，删除 identifier
	 * @param columnId
	 * @param column
	 * @param DOS
	 */
	@Override
	protected void postDeleteByColumn(Object columnId, SFunction<UserDO, ?> column, List<UserDO> DOS) {
		for (UserDO aDo : DOS) {
			onUserDelete(aDo.getId());
		}
	}

	/**
	 * 统一删除联动删除方法
	 * @param userId
	 */
	private void onUserDelete(Long userId) {
		iUserIdentifierService.deleteByUserId(userId);
		iUserIdentifierPwdService.deleteByUserId(userId);
	}

}
