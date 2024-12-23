package com.particle.crm.app.relation.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.relation.structmapping.CrmCustomerRelationDefineAppStructMapping;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineCreateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationDefineGateway;
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
 * 客户关系定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Component
@Validated
public class CrmCustomerRelationDefineCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationDefineGateway crmCustomerRelationDefineGateway;

	/**
	 * 执行客户关系定义添加指令
	 * @param crmCustomerRelationDefineCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationDefineVO> execute(@Valid CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand) {
		CrmCustomerRelationDefine crmCustomerRelationDefine = createByCrmCustomerRelationDefineCreateCommand(crmCustomerRelationDefineCreateCommand);
		crmCustomerRelationDefine.setAddControl(crmCustomerRelationDefineCreateCommand);
		boolean save = crmCustomerRelationDefineGateway.save(crmCustomerRelationDefine);
		if (save) {
			return SingleResponse.of(CrmCustomerRelationDefineAppStructMapping.instance.toCrmCustomerRelationDefineVO(crmCustomerRelationDefine));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户关系定义创建指令创建客户关系定义模型
	 * @param crmCustomerRelationDefineCreateCommand
	 * @return
	 */
	private CrmCustomerRelationDefine createByCrmCustomerRelationDefineCreateCommand(CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand){
		CrmCustomerRelationDefine crmCustomerRelationDefine = CrmCustomerRelationDefine.create();
		CrmCustomerRelationDefineCreateCommandToCrmCustomerRelationDefineMapping.instance.fillCrmCustomerRelationDefineByCrmCustomerRelationDefineCreateCommand(crmCustomerRelationDefine, crmCustomerRelationDefineCreateCommand);
		return crmCustomerRelationDefine;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCustomerRelationDefineCreateCommandToCrmCustomerRelationDefineMapping{
		CrmCustomerRelationDefineCreateCommandToCrmCustomerRelationDefineMapping instance = Mappers.getMapper( CrmCustomerRelationDefineCreateCommandToCrmCustomerRelationDefineMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerRelationDefine
		 * @param crmCustomerRelationDefineCreateCommand
		 */
		void fillCrmCustomerRelationDefineByCrmCustomerRelationDefineCreateCommand(@MappingTarget CrmCustomerRelationDefine crmCustomerRelationDefine, CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerRelationDefineGateway
	 */
	@Autowired
	public void setCrmCustomerRelationDefineGateway(CrmCustomerRelationDefineGateway crmCustomerRelationDefineGateway) {
		this.crmCustomerRelationDefineGateway = crmCustomerRelationDefineGateway;
	}
}
