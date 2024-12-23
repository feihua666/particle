package com.particle.crm.app.customer.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.customer.structmapping.CrmCustomerContactAppStructMapping;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactUpdateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.CrmCustomerContactId;
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
 * 客户联系方式 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerContactUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerContactGateway crmCustomerContactGateway;

	/**
	 * 执行 客户联系方式 更新指令
	 * @param crmCustomerContactUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerContactVO> execute(@Valid CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand) {
		CrmCustomerContact crmCustomerContact = createByCrmCustomerContactUpdateCommand(crmCustomerContactUpdateCommand);
		crmCustomerContact.setUpdateControl(crmCustomerContactUpdateCommand);
		boolean save = crmCustomerContactGateway.save(crmCustomerContact);
		if (save) {
			return SingleResponse.of(CrmCustomerContactAppStructMapping.instance.toCrmCustomerContactVO(crmCustomerContact));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户联系方式更新指令创建客户联系方式模型
	 * @param crmCustomerContactUpdateCommand
	 * @return
	 */
	private CrmCustomerContact createByCrmCustomerContactUpdateCommand(CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand){
		CrmCustomerContact crmCustomerContact = CrmCustomerContact.create();
		CrmCustomerContactUpdateCommandToCrmCustomerContactMapping.instance.fillCrmCustomerContactByCrmCustomerContactUpdateCommand(crmCustomerContact, crmCustomerContactUpdateCommand);
		return crmCustomerContact;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrmCustomerContactUpdateCommandToCrmCustomerContactMapping{
		CrmCustomerContactUpdateCommandToCrmCustomerContactMapping instance = Mappers.getMapper(CrmCustomerContactUpdateCommandToCrmCustomerContactMapping.class );

		default CrmCustomerContactId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerContactId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerContact
		 * @param crmCustomerContactUpdateCommand
		 */
		void fillCrmCustomerContactByCrmCustomerContactUpdateCommand(@MappingTarget CrmCustomerContact crmCustomerContact, CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand);
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
