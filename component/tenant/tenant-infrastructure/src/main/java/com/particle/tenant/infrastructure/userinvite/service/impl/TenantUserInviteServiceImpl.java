package com.particle.tenant.infrastructure.userinvite.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.tenant.infrastructure.userinvite.dos.TenantUserInviteDO;
import com.particle.tenant.infrastructure.userinvite.mapper.TenantUserInviteMapper;
import com.particle.tenant.infrastructure.userinvite.service.ITenantUserInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 租户用户邀请 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Component
public class TenantUserInviteServiceImpl extends IBaseServiceImpl<TenantUserInviteMapper, TenantUserInviteDO> implements ITenantUserInviteService {
	private IBaseQueryCommandMapStruct<TenantUserInviteDO> queryCommandMapStruct;

	@Override
	protected TenantUserInviteDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<TenantUserInviteDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(TenantUserInviteDO po) {
	    // 邀请码 已存在不能添加
	    assertByColumn(po.getInviteCode(),TenantUserInviteDO::getInviteCode,false);

	}

	@Override
	protected void preUpdate(TenantUserInviteDO po) {

	    TenantUserInviteDO byId = null;
	    if (StrUtil.isNotEmpty(po.getInviteCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果邀请码有改动
	        if (!po.getInviteCode().equals(byId.getInviteCode())) {
	            // 邀请码已存在不能修改
	            assertByColumn(po.getInviteCode(),TenantUserInviteDO::getInviteCode,false);
	        }
	    }


	}
}
