package com.particle.crm.app.relation.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.relation.structmapping.CrmCustomerRelationAppStructMapping;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationCreateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationGateway;
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
 * 客户与客户关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Component
@Validated
public class CrmCustomerRelationCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationGateway crmCustomerRelationGateway;

	/**
	 * 执行客户与客户关系添加指令
	 * @param crmCustomerRelationCreateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationVO> execute(@Valid CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand) {
		CrmCustomerRelation crmCustomerRelation = createByCrmCustomerRelationCreateCommand(crmCustomerRelationCreateCommand);
		crmCustomerRelation.setAddControl(crmCustomerRelationCreateCommand);
		boolean save = crmCustomerRelationGateway.save(crmCustomerRelation);
		if (save) {
			return SingleResponse.of(CrmCustomerRelationAppStructMapping.instance.toCrmCustomerRelationVO(crmCustomerRelation));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户与客户关系创建指令创建客户与客户关系模型
	 * @param crmCustomerRelationCreateCommand
	 * @return
	 */
	private CrmCustomerRelation createByCrmCustomerRelationCreateCommand(CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand){
		CrmCustomerRelation crmCustomerRelation = CrmCustomerRelation.create();
		CrmCustomerRelationCreateCommandToCrmCustomerRelationMapping.instance.fillCrmCustomerRelationByCrmCustomerRelationCreateCommand(crmCustomerRelation, crmCustomerRelationCreateCommand);
		return crmCustomerRelation;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmCustomerRelationCreateCommandToCrmCustomerRelationMapping{
		CrmCustomerRelationCreateCommandToCrmCustomerRelationMapping instance = Mappers.getMapper( CrmCustomerRelationCreateCommandToCrmCustomerRelationMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerRelation
		 * @param crmCustomerRelationCreateCommand
		 */
		void fillCrmCustomerRelationByCrmCustomerRelationCreateCommand(@MappingTarget CrmCustomerRelation crmCustomerRelation, CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerRelationGateway
	 */
	@Autowired
	public void setCrmCustomerRelationGateway(CrmCustomerRelationGateway crmCustomerRelationGateway) {
		this.crmCustomerRelationGateway = crmCustomerRelationGateway;
	}
}
