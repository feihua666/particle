package com.particle.crm.app.customer.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.customer.structmapping.CrmCustomerAppStructMapping;
import com.particle.crm.client.customer.dto.command.CrmCustomerCreateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.gateway.CrmCustomerGateway;
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
 * 客户 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Component
@Validated
public class CrmCustomerCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerGateway crmCustomerGateway;

	/**
	 * 执行客户添加指令
	 * @param crmCustomerCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerVO> execute(@Valid CrmCustomerCreateCommand crmCustomerCreateCommand) {
		CrmCustomer crmCustomer = createByCrmCustomerCreateCommand(crmCustomerCreateCommand);
		crmCustomer.setAddControl(crmCustomerCreateCommand);
		boolean save = crmCustomerGateway.save(crmCustomer);
		if (save) {
			return SingleResponse.of(CrmCustomerAppStructMapping.instance.toCrmCustomerVO(crmCustomer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户创建指令创建客户模型
	 * @param crmCustomerCreateCommand
	 * @return
	 */
	private CrmCustomer createByCrmCustomerCreateCommand(CrmCustomerCreateCommand crmCustomerCreateCommand){
		CrmCustomer crmCustomer = CrmCustomer.create();
		CrmCustomerCreateCommandToCrmCustomerMapping.instance.fillCrmCustomerByCrmCustomerCreateCommand(crmCustomer, crmCustomerCreateCommand);
		return crmCustomer;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCustomerCreateCommandToCrmCustomerMapping{
		CrmCustomerCreateCommandToCrmCustomerMapping instance = Mappers.getMapper( CrmCustomerCreateCommandToCrmCustomerMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomer
		 * @param crmCustomerCreateCommand
		 */
		void fillCrmCustomerByCrmCustomerCreateCommand(@MappingTarget CrmCustomer crmCustomer, CrmCustomerCreateCommand crmCustomerCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerGateway
	 */
	@Autowired
	public void setCrmCustomerGateway(CrmCustomerGateway crmCustomerGateway) {
		this.crmCustomerGateway = crmCustomerGateway;
	}
}
