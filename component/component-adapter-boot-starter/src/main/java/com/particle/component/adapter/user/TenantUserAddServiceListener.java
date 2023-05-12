package com.particle.component.adapter.user;

import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.security.tenant.TenantTool;
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
public class TenantUserAddServiceListener implements IAddServiceListener<UserDO> {

	@Autowired
	private ITenantUserApplicationService iTenantUserService;

	@Override
	public void postAdd(UserDO po) {

		if (TenantTool.isTenantEnable()) {
			Long tenantId = TenantTool.getTenantId();
			if (tenantId != null) {
				TenantUserCreateCommand userCreateCommand = new TenantUserCreateCommand();
				userCreateCommand.setUserId(po.getId());
				userCreateCommand.setTenantId(tenantId);
				userCreateCommand.setIsExpired(false);
				userCreateCommand.setIsLeave(false);

				iTenantUserService.create(userCreateCommand);
			}
		}
	}
}
