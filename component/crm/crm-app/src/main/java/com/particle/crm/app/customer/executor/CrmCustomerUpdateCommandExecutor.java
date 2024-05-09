package com.particle.crm.app.customer.executor;

import com.particle.crm.app.customer.structmapping.CrmCustomerAppStructMapping;
import com.particle.crm.client.customer.dto.command.CrmCustomerUpdateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;
import com.particle.crm.domain.customer.gateway.CrmCustomerGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 客户 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerGateway crmCustomerGateway;

	/**
	 * 执行 客户 更新指令
	 * @param crmCustomerUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerVO> execute(@Valid CrmCustomerUpdateCommand crmCustomerUpdateCommand) {
		CrmCustomer crmCustomer = createByCrmCustomerUpdateCommand(crmCustomerUpdateCommand);
		crmCustomer.setUpdateControl(crmCustomerUpdateCommand);
		boolean save = crmCustomerGateway.save(crmCustomer);
		if (save) {
			return SingleResponse.of(CrmCustomerAppStructMapping.instance.toCrmCustomerVO(crmCustomer));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户更新指令创建客户模型
	 * @param crmCustomerUpdateCommand
	 * @return
	 */
	private CrmCustomer createByCrmCustomerUpdateCommand(CrmCustomerUpdateCommand crmCustomerUpdateCommand){
		CrmCustomer crmCustomer = CrmCustomer.create();
		CrmCustomerUpdateCommandToCrmCustomerMapping.instance.fillCrmCustomerByCrmCustomerUpdateCommand(crmCustomer, crmCustomerUpdateCommand);
		return crmCustomer;
	}

	@Mapper
	interface CrmCustomerUpdateCommandToCrmCustomerMapping{
		CrmCustomerUpdateCommandToCrmCustomerMapping instance = Mappers.getMapper(CrmCustomerUpdateCommandToCrmCustomerMapping.class );

		default CrmCustomerId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomer
		 * @param crmCustomerUpdateCommand
		 */
		void fillCrmCustomerByCrmCustomerUpdateCommand(@MappingTarget CrmCustomer crmCustomer, CrmCustomerUpdateCommand crmCustomerUpdateCommand);
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
