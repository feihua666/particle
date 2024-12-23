package com.particle.crm.app.tag.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.tag.structmapping.CrmCustomerTagRelAppStructMapping;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelCreateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagRelGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 客户标签关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Component
@Validated
public class CrmCustomerTagRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagRelGateway crmCustomerTagRelGateway;

	/**
	 * 执行客户标签关系添加指令
	 * @param crmCustomerTagRelCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagRelVO> execute(@Valid CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand) {
		CrmCustomerTagRel crmCustomerTagRel = createByCrmCustomerTagRelCreateCommand(crmCustomerTagRelCreateCommand);
		crmCustomerTagRel.setAddControl(crmCustomerTagRelCreateCommand);
		boolean save = crmCustomerTagRelGateway.save(crmCustomerTagRel);
		if (save) {
			return SingleResponse.of(CrmCustomerTagRelAppStructMapping.instance.toCrmCustomerTagRelVO(crmCustomerTagRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户标签关系创建指令创建客户标签关系模型
	 * @param crmCustomerTagRelCreateCommand
	 * @return
	 */
	private CrmCustomerTagRel createByCrmCustomerTagRelCreateCommand(CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand){
		CrmCustomerTagRel crmCustomerTagRel = CrmCustomerTagRel.create();
		CrmCustomerTagRelCreateCommandToCrmCustomerTagRelMapping.instance.fillCrmCustomerTagRelByCrmCustomerTagRelCreateCommand(crmCustomerTagRel, crmCustomerTagRelCreateCommand);
		return crmCustomerTagRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCustomerTagRelCreateCommandToCrmCustomerTagRelMapping{
		CrmCustomerTagRelCreateCommandToCrmCustomerTagRelMapping instance = Mappers.getMapper( CrmCustomerTagRelCreateCommandToCrmCustomerTagRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerTagRel
		 * @param crmCustomerTagRelCreateCommand
		 */
		void fillCrmCustomerTagRelByCrmCustomerTagRelCreateCommand(@MappingTarget CrmCustomerTagRel crmCustomerTagRel, CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerTagRelGateway
	 */
	@Autowired
	public void setCrmCustomerTagRelGateway(CrmCustomerTagRelGateway crmCustomerTagRelGateway) {
		this.crmCustomerTagRelGateway = crmCustomerTagRelGateway;
	}
}
