package com.particle.user.infrastructure.login.service.impl;

import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.mapper.UserLoginRecordMapper;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户登录记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Component
public class UserLoginRecordServiceImpl extends IBaseServiceImpl<UserLoginRecordMapper, UserLoginRecordDO> implements IUserLoginRecordService {
	private IBaseQueryCommandMapStruct<UserLoginRecordDO> queryCommandMapStruct;

	@Override
	protected UserLoginRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserLoginRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
}
