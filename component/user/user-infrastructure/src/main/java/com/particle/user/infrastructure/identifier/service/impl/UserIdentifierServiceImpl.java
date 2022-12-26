package com.particle.user.infrastructure.identifier.service.impl;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.mapper.UserIdentifierMapper;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


	private IUserIdentifierPwdService iUserIdentifierPwdService;

	@Override
	protected UserIdentifierDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserIdentifierDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	/**
	 * 删除后，删除 identifier
	 * @param id
	 * @param DO
	 */
	@Override
	protected void postDeleteById(Long id, UserIdentifierDO DO) {
		iUserIdentifierPwdService.deleteByIdentifierId(id);
	}

	/**
	 * 删除后，删除 identifier
	 * @param columnId
	 * @param column
	 * @param DOS
	 */
	@Override
	protected void postDeleteByColumn(Object columnId, SFunction<UserIdentifierDO, ?> column, List<UserIdentifierDO> DOS) {
		for (UserIdentifierDO aDo : DOS) {
			iUserIdentifierPwdService.deleteByIdentifierId(aDo.getUserId());
		}
	}

	@Autowired
	public void setiUserIdentifierPwdService(IUserIdentifierPwdService iUserIdentifierPwdService) {
		this.iUserIdentifierPwdService = iUserIdentifierPwdService;
	}
}
