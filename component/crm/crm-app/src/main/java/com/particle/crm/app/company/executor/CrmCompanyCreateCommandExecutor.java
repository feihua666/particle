package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.company.structmapping.CrmCompanyAppStructMapping;
import com.particle.crm.client.company.dto.command.CrmCompanyCreateCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.gateway.CrmCompanyGateway;
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
 * 客户公司 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Component
@Validated
public class CrmCompanyCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCompanyGateway crmCompanyGateway;

	/**
	 * 执行客户公司添加指令
	 * @param crmCompanyCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCompanyVO> execute(@Valid CrmCompanyCreateCommand crmCompanyCreateCommand) {
		CrmCompany crmCompany = createByCrmCompanyCreateCommand(crmCompanyCreateCommand);
		crmCompany.setAddControl(crmCompanyCreateCommand);
		boolean save = crmCompanyGateway.save(crmCompany);
		if (save) {
			return SingleResponse.of(CrmCompanyAppStructMapping.instance.toCrmCompanyVO(crmCompany));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户公司创建指令创建客户公司模型
	 * @param crmCompanyCreateCommand
	 * @return
	 */
	private CrmCompany createByCrmCompanyCreateCommand(CrmCompanyCreateCommand crmCompanyCreateCommand){
		CrmCompany crmCompany = CrmCompany.create();
		CrmCompanyCreateCommandToCrmCompanyMapping.instance.fillCrmCompanyByCrmCompanyCreateCommand(crmCompany, crmCompanyCreateCommand);
		return crmCompany;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCompanyCreateCommandToCrmCompanyMapping{
		CrmCompanyCreateCommandToCrmCompanyMapping instance = Mappers.getMapper( CrmCompanyCreateCommandToCrmCompanyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCompany
		 * @param crmCompanyCreateCommand
		 */
		void fillCrmCompanyByCrmCompanyCreateCommand(@MappingTarget CrmCompany crmCompany, CrmCompanyCreateCommand crmCompanyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCompanyGateway
	 */
	@Autowired
	public void setCrmCompanyGateway(CrmCompanyGateway crmCompanyGateway) {
		this.crmCompanyGateway = crmCompanyGateway;
	}
}
