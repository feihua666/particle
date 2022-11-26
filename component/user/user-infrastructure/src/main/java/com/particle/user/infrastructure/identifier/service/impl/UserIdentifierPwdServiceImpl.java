package com.particle.user.infrastructure.identifier.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.mapper.UserIdentifierPwdMapper;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户密码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserIdentifierPwdServiceImpl extends IBaseServiceImpl<UserIdentifierPwdMapper, UserIdentifierPwdDO> implements IUserIdentifierPwdService {

	@Autowired
	private IUserIdentifierService iUserIdentifierService;

	private IBaseQueryCommandMapStruct<UserIdentifierPwdDO> queryCommandMapStruct;

	@Override
	protected UserIdentifierPwdDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserIdentifierPwdDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}


	@Override
	public List<UserIdentifierPwdDO> getByUserId(Long userId) {
		Assert.notNull(userId,"userId不能为空");
		List<UserIdentifierDO> byUserId = iUserIdentifierService.getByUserId(userId);
		if (!CollectionUtil.isEmpty(byUserId)) {
			List<UserIdentifierPwdDO> byIdentifierIds = getByIdentifierIds(byUserId.stream().map(UserIdentifierDO::getId).collect(Collectors.toList()));
			return byIdentifierIds;
		}
		return null;
	}

	@Override
	public boolean updatePasswordByUserId(Long userId,String encodedPassword) {
		List<UserIdentifierPwdDO> byUserId = getByUserId(userId);
		if (!CollectionUtil.isEmpty(byUserId)) {
			for (UserIdentifierPwdDO identifierPwd : byUserId) {
				identifierPwd.setPwd(encodedPassword);
				identifierPwd.setIsNeedUpdate(true);
				identifierPwd.setPwdModifiedAt(LocalDateTime.now());
			}
			return updateBatchById(byUserId);
		}
		return false;
	}


	@Override
	public boolean updatePasswordByIdentifierId(Long identifierId,String encodedPassword) {
		UserIdentifierPwdDO identifierPwd = getByIdentifierId(identifierId);
		if (identifierPwd != null) {
			identifierPwd.setPwd(encodedPassword);
			identifierPwd.setIsNeedUpdate(true);
			identifierPwd.setPwdModifiedAt(LocalDateTime.now());
			return updateById(identifierPwd);
		}
		return false;
	}
}
