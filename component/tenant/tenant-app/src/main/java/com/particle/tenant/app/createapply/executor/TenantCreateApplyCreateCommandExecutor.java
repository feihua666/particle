package com.particle.tenant.app.createapply.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyCreateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 租户创建申请 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Component
@Validated
public class TenantCreateApplyCreateCommandExecutor  extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;

	/**
	 * 执行租户创建申请添加指令
	 * @param tenantCreateApplyCreateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyCreateCommand(tenantCreateApplyCreateCommand);
		tenantCreateApply.initIsSendMobileNotice();
		tenantCreateApply.initIsSendMobileNotice();
		if (tenantCreateApplyCreateCommand.getExtJsonObj() != null) {
			MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = SpringContextHolder.getBean(MappingJackson2HttpMessageConverter.class);

			tenantCreateApply.changeExtJson(JsonTool.toJsonStrForHttp(tenantCreateApplyCreateCommand.getExtJsonObj(),jackson2HttpMessageConverter.getObjectMapper()));
		}
		/**
		 * 默认为未审核
		 */
		tenantCreateApply.chanageAuditStatusToUnAudit();
		// 依赖用户选择，如果没有填写不计算天数
		//tenantCreateApply.changeEffectiveAtNowIfNull();
		tenantCreateApply.calculateDays();

		tenantCreateApply.setAddControl(tenantCreateApplyCreateCommand);
		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据租户创建申请创建指令创建租户创建申请模型
	 * @param tenantCreateApplyCreateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyCreateCommand(TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyCreateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyCreateCommand(tenantCreateApply, tenantCreateApplyCreateCommand);
		return tenantCreateApply;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  TenantCreateApplyCreateCommandToTenantCreateApplyMapping{
		TenantCreateApplyCreateCommandToTenantCreateApplyMapping instance = Mappers.getMapper( TenantCreateApplyCreateCommandToTenantCreateApplyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantCreateApply
		 * @param tenantCreateApplyCreateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyCreateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyCreateCommand tenantCreateApplyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}
}
