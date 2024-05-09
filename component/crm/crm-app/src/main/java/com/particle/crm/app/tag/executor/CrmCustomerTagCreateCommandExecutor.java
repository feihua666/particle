package com.particle.crm.app.tag.executor;

import com.particle.crm.app.tag.structmapping.CrmCustomerTagAppStructMapping;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagCreateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagGateway;
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
 * 客户标签 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Component
@Validated
public class CrmCustomerTagCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagGateway crmCustomerTagGateway;

	/**
	 * 执行客户标签添加指令
	 * @param crmCustomerTagCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagVO> execute(@Valid CrmCustomerTagCreateCommand crmCustomerTagCreateCommand) {
		CrmCustomerTag crmCustomerTag = createByCrmCustomerTagCreateCommand(crmCustomerTagCreateCommand);
		crmCustomerTag.setAddControl(crmCustomerTagCreateCommand);
		boolean save = crmCustomerTagGateway.save(crmCustomerTag);
		if (save) {
			return SingleResponse.of(CrmCustomerTagAppStructMapping.instance.toCrmCustomerTagVO(crmCustomerTag));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户标签创建指令创建客户标签模型
	 * @param crmCustomerTagCreateCommand
	 * @return
	 */
	private CrmCustomerTag createByCrmCustomerTagCreateCommand(CrmCustomerTagCreateCommand crmCustomerTagCreateCommand){
		CrmCustomerTag crmCustomerTag = CrmCustomerTag.create();
		CrmCustomerTagCreateCommandToCrmCustomerTagMapping.instance.fillCrmCustomerTagByCrmCustomerTagCreateCommand(crmCustomerTag, crmCustomerTagCreateCommand);
		return crmCustomerTag;
	}

	@Mapper
	interface  CrmCustomerTagCreateCommandToCrmCustomerTagMapping{
		CrmCustomerTagCreateCommandToCrmCustomerTagMapping instance = Mappers.getMapper( CrmCustomerTagCreateCommandToCrmCustomerTagMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerTag
		 * @param crmCustomerTagCreateCommand
		 */
		void fillCrmCustomerTagByCrmCustomerTagCreateCommand(@MappingTarget CrmCustomerTag crmCustomerTag, CrmCustomerTagCreateCommand crmCustomerTagCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerTagGateway
	 */
	@Autowired
	public void setCrmCustomerTagGateway(CrmCustomerTagGateway crmCustomerTagGateway) {
		this.crmCustomerTagGateway = crmCustomerTagGateway;
	}
}
