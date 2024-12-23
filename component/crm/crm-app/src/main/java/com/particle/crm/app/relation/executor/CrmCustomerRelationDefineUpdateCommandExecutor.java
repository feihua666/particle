package com.particle.crm.app.relation.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.relation.structmapping.CrmCustomerRelationDefineAppStructMapping;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineUpdateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.CrmCustomerRelationDefineId;
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
 * 客户关系定义 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerRelationDefineUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerRelationDefineGateway crmCustomerRelationDefineGateway;

	/**
	 * 执行 客户关系定义 更新指令
	 * @param crmCustomerRelationDefineUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerRelationDefineVO> execute(@Valid CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand) {
		CrmCustomerRelationDefine crmCustomerRelationDefine = createByCrmCustomerRelationDefineUpdateCommand(crmCustomerRelationDefineUpdateCommand);
		crmCustomerRelationDefine.setUpdateControl(crmCustomerRelationDefineUpdateCommand);
		boolean save = crmCustomerRelationDefineGateway.save(crmCustomerRelationDefine);
		if (save) {
			return SingleResponse.of(CrmCustomerRelationDefineAppStructMapping.instance.toCrmCustomerRelationDefineVO(crmCustomerRelationDefine));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户关系定义更新指令创建客户关系定义模型
	 * @param crmCustomerRelationDefineUpdateCommand
	 * @return
	 */
	private CrmCustomerRelationDefine createByCrmCustomerRelationDefineUpdateCommand(CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand){
		CrmCustomerRelationDefine crmCustomerRelationDefine = CrmCustomerRelationDefine.create();
		CrmCustomerRelationDefineUpdateCommandToCrmCustomerRelationDefineMapping.instance.fillCrmCustomerRelationDefineByCrmCustomerRelationDefineUpdateCommand(crmCustomerRelationDefine, crmCustomerRelationDefineUpdateCommand);
		return crmCustomerRelationDefine;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrmCustomerRelationDefineUpdateCommandToCrmCustomerRelationDefineMapping{
		CrmCustomerRelationDefineUpdateCommandToCrmCustomerRelationDefineMapping instance = Mappers.getMapper(CrmCustomerRelationDefineUpdateCommandToCrmCustomerRelationDefineMapping.class );

		default CrmCustomerRelationDefineId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerRelationDefineId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerRelationDefine
		 * @param crmCustomerRelationDefineUpdateCommand
		 */
		void fillCrmCustomerRelationDefineByCrmCustomerRelationDefineUpdateCommand(@MappingTarget CrmCustomerRelationDefine crmCustomerRelationDefine, CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand);
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
