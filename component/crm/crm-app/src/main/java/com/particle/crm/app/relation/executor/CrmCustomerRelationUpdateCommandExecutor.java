package com.particle.crm.app.relation.executor;

import com.particle.crm.app.relation.structmapping.CrmCustomerRelationAppStructMapping;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationUpdateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.CrmCustomerRelationId;
import com.particle.crm.domain.relation.gateway.CrmCustomerRelationGateway;
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
 * 客户与客户关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerRelationUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationGateway crmCustomerRelationGateway;

	/**
	 * 执行 客户与客户关系 更新指令
	 * @param crmCustomerRelationUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationVO> execute(@Valid CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand) {
		CrmCustomerRelation crmCustomerRelation = createByCrmCustomerRelationUpdateCommand(crmCustomerRelationUpdateCommand);
		crmCustomerRelation.setUpdateControl(crmCustomerRelationUpdateCommand);
		boolean save = crmCustomerRelationGateway.save(crmCustomerRelation);
		if (save) {
			return SingleResponse.of(CrmCustomerRelationAppStructMapping.instance.toCrmCustomerRelationVO(crmCustomerRelation));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户与客户关系更新指令创建客户与客户关系模型
	 * @param crmCustomerRelationUpdateCommand
	 * @return
	 */
	private CrmCustomerRelation createByCrmCustomerRelationUpdateCommand(CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand){
		CrmCustomerRelation crmCustomerRelation = CrmCustomerRelation.create();
		CrmCustomerRelationUpdateCommandToCrmCustomerRelationMapping.instance.fillCrmCustomerRelationByCrmCustomerRelationUpdateCommand(crmCustomerRelation, crmCustomerRelationUpdateCommand);
		return crmCustomerRelation;
	}

	@Mapper
	interface CrmCustomerRelationUpdateCommandToCrmCustomerRelationMapping{
		CrmCustomerRelationUpdateCommandToCrmCustomerRelationMapping instance = Mappers.getMapper(CrmCustomerRelationUpdateCommandToCrmCustomerRelationMapping.class );

		default CrmCustomerRelationId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerRelationId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerRelation
		 * @param crmCustomerRelationUpdateCommand
		 */
		void fillCrmCustomerRelationByCrmCustomerRelationUpdateCommand(@MappingTarget CrmCustomerRelation crmCustomerRelation, CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand);
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
