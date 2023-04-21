package com.particle.tenant.app.createapply.executor;

import cn.hutool.core.util.IdUtil;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.app.executor.TenantCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyAuditDomainEvent;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户创建申请 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class TenantCreateApplyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;

	private TenantCreateCommandExecutor tenantCreateCommandExecutor;

	private TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor;

	private TenantRoleGateway tenantRoleGateway;

	/**
	 * 执行 租户创建申请 更新指令
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyUpdateCommand(tenantCreateApplyUpdateCommand);
		tenantCreateApply.setUpdateControl(tenantCreateApplyUpdateCommand);
		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 租户创建申请 审核指令
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> audit(@Valid TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyAuditCommand(tenantCreateApplyAuditCommand);
		tenantCreateApply.setUpdateControl(tenantCreateApplyAuditCommand);

		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			// 如果审核通过，
			// 1 添加租户
			// 2 将用户绑定到租户下
			// 3 添加租户下超级管理员角色
			// 4 绑定用户
			TenantCreateApply byId = tenantCreateApplyGateway.getById(tenantCreateApply.getId());
			TenantCreateCommand tenantCreateCommand = new TenantCreateCommand();
			tenantCreateCommand.setName(byId.getName());
			tenantCreateCommand.setCode(IdUtil.fastSimpleUUID());
			tenantCreateCommand.setIsDisabled(false);
			tenantCreateCommand.setContactUserName(byId.getContactUserName());
			tenantCreateCommand.setContactUserPhone(byId.getContactUserPhone());
			tenantCreateCommand.setContactUserEmail(byId.getContactUserEmail());
			// 1 添加租户
			SingleResponse<TenantVO> tenantSave = tenantCreateCommandExecutor.execute(tenantCreateCommand);
			if (tenantSave.isSuccess()) {
				// 2 将用户绑定到租户下
				TenantUserCreateCommand tenantUserCreateCommand = new TenantUserCreateCommand();
				tenantUserCreateCommand.setUserId(byId.getApplyUserId());
				tenantUserCreateCommand.setIsExpired(false);
				tenantUserCreateCommand.setIsLeave(false);
				tenantUserCreateCommand.setTenantId(tenantSave.getData().getId());
				SingleResponse<TenantUserVO> tenantUserSave = tenantUserCreateCommandExecutor.execute(tenantUserCreateCommand);
				if (tenantUserSave.isSuccess()) {

					// 3 添加租户下超级管理员角色
					Long roleId = tenantRoleGateway.createRole(tenantCreateApplyAuditCommand.getTenantSuperAdminRoleCode(), "超级管理员", false, tenantSave.getData().getId());
					// 4 绑定用户
					tenantRoleGateway.createRoleUserRel(roleId,byId.getApplyUserId(), tenantSave.getData().getId());
				}
			}

			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyUpdateCommand(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyUpdateCommand(tenantCreateApply, tenantCreateApplyUpdateCommand);
		return tenantCreateApply;
	}
	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyAuditCommand(TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyAuditCommand(tenantCreateApply, tenantCreateApplyAuditCommand);
		return tenantCreateApply;
	}
	@Mapper
	interface TenantCreateApplyUpdateCommandToTenantCreateApplyMapping{
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping instance = Mappers.getMapper(TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.class );

		default TenantCreateApplyId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantCreateApplyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantCreateApply
		 * @param tenantCreateApplyUpdateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyUpdateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);
		void fillTenantCreateApplyByTenantCreateApplyAuditCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}
	@Autowired
	public void setTenantCreateCommandExecutor(TenantCreateCommandExecutor tenantCreateCommandExecutor) {
		this.tenantCreateCommandExecutor = tenantCreateCommandExecutor;
	}
	@Autowired
	public void setTenantUserCreateCommandExecutor(TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor) {
		this.tenantUserCreateCommandExecutor = tenantUserCreateCommandExecutor;
	}

	@Autowired
	public void setTenantRoleGateway(TenantRoleGateway tenantRoleGateway) {
		this.tenantRoleGateway = tenantRoleGateway;
	}
}
