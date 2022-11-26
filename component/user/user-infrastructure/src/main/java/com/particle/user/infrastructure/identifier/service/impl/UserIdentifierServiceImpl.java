package com.particle.user.infrastructure.identifier.service.impl;

import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.mapper.UserIdentifierMapper;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户登录标识 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserIdentifierServiceImpl extends IBaseServiceImpl<UserIdentifierMapper, UserIdentifierDO> implements IUserIdentifierService {
	private IBaseQueryCommandMapStruct<UserIdentifierDO> queryCommandMapStruct;

	@Override
	protected UserIdentifierDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserIdentifierDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
