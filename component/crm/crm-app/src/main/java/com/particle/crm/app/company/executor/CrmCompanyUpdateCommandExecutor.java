package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.company.structmapping.CrmCompanyAppStructMapping;
import com.particle.crm.client.company.dto.command.CrmCompanyUpdateCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.CrmCompanyId;
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
 * 客户公司 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCompanyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCompanyGateway crmCompanyGateway;

	/**
	 * 执行 客户公司 更新指令
	 * @param crmCompanyUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCompanyVO> execute(@Valid CrmCompanyUpdateCommand crmCompanyUpdateCommand) {
		CrmCompany crmCompany = createByCrmCompanyUpdateCommand(crmCompanyUpdateCommand);
		crmCompany.setUpdateControl(crmCompanyUpdateCommand);
		boolean save = crmCompanyGateway.save(crmCompany);
		if (save) {
			return SingleResponse.of(CrmCompanyAppStructMapping.instance.toCrmCompanyVO(crmCompany));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户公司更新指令创建客户公司模型
	 * @param crmCompanyUpdateCommand
	 * @return
	 */
	private CrmCompany createByCrmCompanyUpdateCommand(CrmCompanyUpdateCommand crmCompanyUpdateCommand){
		CrmCompany crmCompany = CrmCompany.create();
		CrmCompanyUpdateCommandToCrmCompanyMapping.instance.fillCrmCompanyByCrmCompanyUpdateCommand(crmCompany, crmCompanyUpdateCommand);
		return crmCompany;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrmCompanyUpdateCommandToCrmCompanyMapping{
		CrmCompanyUpdateCommandToCrmCompanyMapping instance = Mappers.getMapper(CrmCompanyUpdateCommandToCrmCompanyMapping.class );

		default CrmCompanyId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCompanyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCompany
		 * @param crmCompanyUpdateCommand
		 */
		void fillCrmCompanyByCrmCompanyUpdateCommand(@MappingTarget CrmCompany crmCompany, CrmCompanyUpdateCommand crmCompanyUpdateCommand);
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
