package com.particle.user.infrastructure.service.impl;

import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	protected UserDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
