package com.particle.user.infrastructure.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
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
