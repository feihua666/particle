package com.particle.crm.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.company.structmapping.CrmDeptAppStructMapping;
import com.particle.crm.client.company.dto.command.CrmDeptUpdateCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.CrmDeptId;
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
 * 客户公司部门 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmDeptUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmDeptGateway crmDeptGateway;

	/**
	 * 执行 客户公司部门 更新指令
	 * @param crmDeptUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmDeptVO> execute(@Valid CrmDeptUpdateCommand crmDeptUpdateCommand) {
		CrmDept crmDept = createByCrmDeptUpdateCommand(crmDeptUpdateCommand);
		crmDept.setUpdateControl(crmDeptUpdateCommand);
		boolean save = crmDeptGateway.save(crmDept);
		if (save) {
			return SingleResponse.of(CrmDeptAppStructMapping.instance.toCrmDeptVO(crmDept));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户公司部门更新指令创建客户公司部门模型
	 * @param crmDeptUpdateCommand
	 * @return
	 */
	private CrmDept createByCrmDeptUpdateCommand(CrmDeptUpdateCommand crmDeptUpdateCommand){
		CrmDept crmDept = CrmDept.create();
		CrmDeptUpdateCommandToCrmDeptMapping.instance.fillCrmDeptByCrmDeptUpdateCommand(crmDept, crmDeptUpdateCommand);
		return crmDept;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrmDeptUpdateCommandToCrmDeptMapping{
		CrmDeptUpdateCommandToCrmDeptMapping instance = Mappers.getMapper(CrmDeptUpdateCommandToCrmDeptMapping.class );

		default CrmDeptId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmDeptId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmDept
		 * @param crmDeptUpdateCommand
		 */
		void fillCrmDeptByCrmDeptUpdateCommand(@MappingTarget CrmDept crmDept, CrmDeptUpdateCommand crmDeptUpdateCommand);
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
