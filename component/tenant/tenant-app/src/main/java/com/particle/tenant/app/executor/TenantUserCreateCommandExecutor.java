package com.particle.tenant.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.tenant.app.structmapping.TenantUserAppStructMapping;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.exception.ErrorCodeTenantEnum;
import com.particle.tenant.domain.TenantUser;
import com.particle.tenant.domain.gateway.TenantUserGateway;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 租户用户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Component
@Validated
public class TenantUserCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantUserGateway tenantUserGateway;

	private ITenantService tenantService;

	private ITenantUserService tenantUserService;
	/**
	 * 执行租户用户添加指令
	 * @param tenantUserCreateCommand
	 * @return
	 */
	public SingleResponse<TenantUserVO> execute(@Valid TenantUserCreateCommand tenantUserCreateCommand) {
		// 判断用户是否已超过限制

		//	校验是否超过人数限制
		Long tenantId = tenantUserCreateCommand.getTenantId();
		TenantDO byIdIgnoreTenantLimit = tenantService.getByIdIgnoreTenantLimit(tenantId);
		if (byIdIgnoreTenantLimit.getUserLimitCount() != null && byIdIgnoreTenantLimit.getUserLimitCount() > 0) {

			//	统计总人数
			long count = tenantUserService.countByTenantIdIgnoreTenantLimit(tenantId, false);
			// 实际人数已经达到限制，异常处理
			if (count >= byIdIgnoreTenantLimit.getUserLimitCount()) {

				throw ExceptionFactory.bizException(ErrorCodeTenantEnum.tenant_user_exceed);
			}
		}



		TenantUser tenantUser = createByTenantUserCreateCommand(tenantUserCreateCommand);
		tenantUser.changeJoinAtToCurrent();
		tenantUser.changeIsLeaveToFalseIfNull();

		tenantUser.setAddControl(tenantUserCreateCommand);
		boolean save = tenantUserGateway.save(tenantUser);
		if (save) {
			return SingleResponse.of(TenantUserAppStructMapping.instance.toTenantUserVO(tenantUser));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户用户创建指令创建租户用户模型
	 * @param tenantUserCreateCommand
	 * @return
	 */
	private TenantUser createByTenantUserCreateCommand(TenantUserCreateCommand tenantUserCreateCommand){
		TenantUser tenantUser = TenantUser.create();
		TenantUserCreateCommandToTenantUserMapping.instance.fillTenantUserByTenantUserCreateCommand(tenantUser, tenantUserCreateCommand);
		return tenantUser;
	}

	@Mapper
	interface  TenantUserCreateCommandToTenantUserMapping{
		TenantUserCreateCommandToTenantUserMapping instance = Mappers.getMapper( TenantUserCreateCommandToTenantUserMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantUser
		 * @param tenantUserCreateCommand
		 */
		void fillTenantUserByTenantUserCreateCommand(@MappingTarget TenantUser tenantUser, TenantUserCreateCommand tenantUserCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantUserGateway
	 */
	@Autowired
	public void setTenantUserGateway(TenantUserGateway tenantUserGateway) {
		this.tenantUserGateway = tenantUserGateway;
	}

	@Autowired
	public void setTenantService(ITenantService tenantService) {
		this.tenantService = tenantService;
	}

	@Autowired
	public void setTenantUserService(ITenantUserService tenantUserService) {
		this.tenantUserService = tenantUserService;
	}
}
