package com.particle.tenant.infrastructure.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.mapper.TenantUserMapper;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 租户用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
public class TenantUserServiceImpl extends IBaseServiceImpl<TenantUserMapper, TenantUserDO> implements ITenantUserService {
	private IBaseQueryCommandMapStruct<TenantUserDO> queryCommandMapStruct;


	@Override
	protected TenantUserDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantUserDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantUserDO po) {

		TenantUserDO byUserId = null;
		if (po.getTenantId() == null) {
			byUserId = getByUserId(po.getUserId());
		}else {
			byUserId = getByUserIdAndTenantId(po.getUserId(),po.getTenantId());
		}

		Assert.isTrue(byUserId == null,"用户已存在，请匆重复添加");
	}

	@Override
	protected void preUpdate(TenantUserDO po) {

	}

}
