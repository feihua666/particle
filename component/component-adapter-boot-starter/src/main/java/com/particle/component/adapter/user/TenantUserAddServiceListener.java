package com.particle.component.adapter.user;

import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.security.tenant.TenantTool;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户添加监听，用来添加租户
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 14:25
 */
public class TenantUserAddServiceListener implements IAddServiceListener<UserDO> {

	@Autowired
	private ITenantUserService iTenantUserService;

	@Override
	public void postAdd(UserDO po) {

		if (TenantTool.isTenantEnable()) {
			Long tenantId = TenantTool.getTenantId();
			if (tenantId != null) {
				TenantUserDO tenantUserDO = new TenantUserDO();
				tenantUserDO.setUserId(po.getId());
				tenantUserDO.setTenantId(tenantId);
				tenantUserDO.setIsExpired(false);
				tenantUserDO.setIsLeave(false);
				tenantUserDO.setJoinAt(LocalDateTime.now());

				iTenantUserService.add(tenantUserDO);
			}
		}
	}
}
