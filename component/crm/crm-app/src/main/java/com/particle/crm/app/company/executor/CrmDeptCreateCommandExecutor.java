package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.company.structmapping.CrmDeptAppStructMapping;
import com.particle.crm.client.company.dto.command.CrmDeptCreateCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.gateway.CrmDeptGateway;
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
 * 客户公司部门 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Component
@Validated
public class CrmDeptCreateCommandExecutor  extends AbstractBaseExecutor {

	private CrmDeptGateway crmDeptGateway;

	/**
	 * 执行客户公司部门添加指令
	 * @param crmDeptCreateCommand
	 * @return
	 */
	public SingleResponse<CrmDeptVO> execute(@Valid CrmDeptCreateCommand crmDeptCreateCommand) {
		CrmDept crmDept = createByCrmDeptCreateCommand(crmDeptCreateCommand);
		crmDept.setAddControl(crmDeptCreateCommand);
		boolean save = crmDeptGateway.save(crmDept);
		if (save) {
			return SingleResponse.of(CrmDeptAppStructMapping.instance.toCrmDeptVO(crmDept));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户公司部门创建指令创建客户公司部门模型
	 * @param crmDeptCreateCommand
	 * @return
	 */
	private CrmDept createByCrmDeptCreateCommand(CrmDeptCreateCommand crmDeptCreateCommand){
		CrmDept crmDept = CrmDept.create();
		CrmDeptCreateCommandToCrmDeptMapping.instance.fillCrmDeptByCrmDeptCreateCommand(crmDept, crmDeptCreateCommand);
		return crmDept;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CrmDeptCreateCommandToCrmDeptMapping{
		CrmDeptCreateCommandToCrmDeptMapping instance = Mappers.getMapper( CrmDeptCreateCommandToCrmDeptMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmDept
		 * @param crmDeptCreateCommand
		 */
		void fillCrmDeptByCrmDeptCreateCommand(@MappingTarget CrmDept crmDept, CrmDeptCreateCommand crmDeptCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmDeptGateway
	 */
	@Autowired
	public void setCrmDeptGateway(CrmDeptGateway crmDeptGateway) {
		this.crmDeptGateway = crmDeptGateway;
	}
}
