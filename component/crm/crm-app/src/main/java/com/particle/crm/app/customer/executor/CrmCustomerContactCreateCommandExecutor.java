package com.particle.crm.app.customer.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.customer.structmapping.CrmCustomerContactAppStructMapping;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactCreateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.gateway.CrmCustomerContactGateway;
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
 * 客户联系方式 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Component
@Validated
public class CrmCustomerContactCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerContactGateway crmCustomerContactGateway;

	/**
	 * 执行客户联系方式添加指令
	 * @param crmCustomerContactCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerContactVO> execute(@Valid CrmCustomerContactCreateCommand crmCustomerContactCreateCommand) {
		CrmCustomerContact crmCustomerContact = createByCrmCustomerContactCreateCommand(crmCustomerContactCreateCommand);
		crmCustomerContact.setAddControl(crmCustomerContactCreateCommand);
		boolean save = crmCustomerContactGateway.save(crmCustomerContact);
		if (save) {
			return SingleResponse.of(CrmCustomerContactAppStructMapping.instance.toCrmCustomerContactVO(crmCustomerContact));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户联系方式创建指令创建客户联系方式模型
	 * @param crmCustomerContactCreateCommand
	 * @return
	 */
	private CrmCustomerContact createByCrmCustomerContactCreateCommand(CrmCustomerContactCreateCommand crmCustomerContactCreateCommand){
		CrmCustomerContact crmCustomerContact = CrmCustomerContact.create();
		CrmCustomerContactCreateCommandToCrmCustomerContactMapping.instance.fillCrmCustomerContactByCrmCustomerContactCreateCommand(crmCustomerContact, crmCustomerContactCreateCommand);
		return crmCustomerContact;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCustomerContactCreateCommandToCrmCustomerContactMapping{
		CrmCustomerContactCreateCommandToCrmCustomerContactMapping instance = Mappers.getMapper( CrmCustomerContactCreateCommandToCrmCustomerContactMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerContact
		 * @param crmCustomerContactCreateCommand
		 */
		void fillCrmCustomerContactByCrmCustomerContactCreateCommand(@MappingTarget CrmCustomerContact crmCustomerContact, CrmCustomerContactCreateCommand crmCustomerContactCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerContactGateway
	 */
	@Autowired
	public void setCrmCustomerContactGateway(CrmCustomerContactGateway crmCustomerContactGateway) {
		this.crmCustomerContactGateway = crmCustomerContactGateway;
	}
}
