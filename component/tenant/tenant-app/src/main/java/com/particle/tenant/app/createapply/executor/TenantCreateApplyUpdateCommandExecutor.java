package com.particle.tenant.app.createapply.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
@Slf4j
@Component
@Validated
public class TenantCreateApplyUpdateCommandExecutor extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;


	/**
	 * 执行 租户创建申请 更新指令
	 *
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyUpdateCommand(tenantCreateApplyUpdateCommand);
		if (tenantCreateApplyUpdateCommand.getExtJsonObj() != null) {
			MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = SpringContextHolder.getBean(MappingJackson2HttpMessageConverter.class);

			tenantCreateApply.changeExtJson(JsonTool.toJsonStrForHttp(tenantCreateApplyUpdateCommand.getExtJsonObj(), jackson2HttpMessageConverter.getObjectMapper()));
		}
		// 依赖用户选择，如果没有填写不计算天数
		//tenantCreateApply.changeEffectiveAtNowIfNull();
		tenantCreateApply.calculateDays();

		tenantCreateApply.setUpdateControl(tenantCreateApplyUpdateCommand);
		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据区域创建指令创建区域模型
	 *
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyUpdateCommand(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyUpdateCommand(tenantCreateApply, tenantCreateApplyUpdateCommand);
		return tenantCreateApply;
	}

	/**
	 * 根据区域创建指令创建区域模型
	 *
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyAuditCommand(TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyAuditCommand(tenantCreateApply, tenantCreateApplyAuditCommand);
		return tenantCreateApply;
	}

	@Mapper
	interface TenantCreateApplyUpdateCommandToTenantCreateApplyMapping {
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping instance = Mappers.getMapper(TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.class);

		default TenantCreateApplyId map(Long id) {
			if (id == null) {
				return null;
			}
			return TenantCreateApplyId.of(id);
		}

		/**
		 * 同名属性会自动映射，包括枚举
		 *
		 * @param tenantCreateApply
		 * @param tenantCreateApplyUpdateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyUpdateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);

		void fillTenantCreateApplyByTenantCreateApplyAuditCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand);
	}

	/**
	 * 注入使用set方法
	 *
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}

}
