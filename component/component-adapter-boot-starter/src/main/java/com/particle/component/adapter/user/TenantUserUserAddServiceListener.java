package com.particle.component.adapter.user;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.security.tenant.TenantTool;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyAuditCommandExecutor;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
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
public class TenantUserUserAddServiceListener implements IAddServiceListener<UserDO> {

	@Autowired
	private ITenantUserService iTenantUserService;

	@Override
	public void postAdd(UserDO po) {

		if (po.getAddControl() != null) {
			if (po.getAddControl() instanceof AbstractBaseCommand) {
				// 租户申请创建用户时，不绑定当前租户
				if (TenantCreateApplyAuditCommandExecutor.tenantCreateApplyUserAddScene.equals(((AbstractBaseCommand) po.getAddControl()).getScene())
						|| TenantUserCreateCommandExecutor.tenantUserAddScene.equals(((AbstractBaseCommand) po.getAddControl()).getScene())) {
					return;
				}
			}
		}

		if (TenantTool.isTenantEnable()) {
			Long tenantId = TenantTool.getTenantId();
			if (tenantId != null) {
				TenantUserDO tenantUserDO = new TenantUserDO();
				tenantUserDO.setUserId(po.getId());
				tenantUserDO.setTenantId(tenantId);
				tenantUserDO.setIsExpired(false);
				tenantUserDO.setIsLeave(false);
				tenantUserDO.setName(po.getName());
				tenantUserDO.setJoinAt(LocalDateTime.now());
				// 默认true
				tenantUserDO.setIsFormal(true);

				iTenantUserService.add(tenantUserDO);
			}
		}
	}
}
