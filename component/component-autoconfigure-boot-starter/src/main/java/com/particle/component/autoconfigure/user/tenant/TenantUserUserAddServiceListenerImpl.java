package com.particle.component.autoconfigure.user.tenant;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.global.mybatis.plus.crud.IAddServiceListener;
import com.particle.global.tool.tenant.TenantTool;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.app.createapply.executor.TenantCreateApplyAuditCommandExecutor;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.user.infrastructure.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 用户添加监听，用来添加租户
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 14:25
 */
public class TenantUserUserAddServiceListenerImpl implements IAddServiceListener<UserDO> {

	@Autowired
	private TenantUserRpcFeignClient tenantUserRpcFeignClient;

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
                TenantUserCreateCommand tenantUserCreateCommand = new TenantUserCreateCommand();
                tenantUserCreateCommand.setUserId(po.getId());
                tenantUserCreateCommand.setTenantId(tenantId);
                tenantUserCreateCommand.setIsExpired( false);
                tenantUserCreateCommand.setIsLeave(false);
                tenantUserCreateCommand.setName(po.getName());
                // 默认true
                tenantUserCreateCommand.setIsFormal(true);
                tenantUserRpcFeignClient.create(tenantUserCreateCommand);
			}
		}
	}
}
