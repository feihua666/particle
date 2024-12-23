package com.particle.crm.app.tag.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.crm.app.tag.structmapping.CrmCustomerTagRelAppStructMapping;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelUpdateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.CrmCustomerTagRelId;
import com.particle.crm.domain.tag.gateway.CrmCustomerTagRelGateway;
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
 * 客户标签关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerTagRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagRelGateway crmCustomerTagRelGateway;

	/**
	 * 执行 客户标签关系 更新指令
	 * @param crmCustomerTagRelUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagRelVO> execute(@Valid CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand) {
		CrmCustomerTagRel crmCustomerTagRel = createByCrmCustomerTagRelUpdateCommand(crmCustomerTagRelUpdateCommand);
		crmCustomerTagRel.setUpdateControl(crmCustomerTagRelUpdateCommand);
		boolean save = crmCustomerTagRelGateway.save(crmCustomerTagRel);
		if (save) {
			return SingleResponse.of(CrmCustomerTagRelAppStructMapping.instance.toCrmCustomerTagRelVO(crmCustomerTagRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户标签关系更新指令创建客户标签关系模型
	 * @param crmCustomerTagRelUpdateCommand
	 * @return
	 */
	private CrmCustomerTagRel createByCrmCustomerTagRelUpdateCommand(CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand){
		CrmCustomerTagRel crmCustomerTagRel = CrmCustomerTagRel.create();
		CrmCustomerTagRelUpdateCommandToCrmCustomerTagRelMapping.instance.fillCrmCustomerTagRelByCrmCustomerTagRelUpdateCommand(crmCustomerTagRel, crmCustomerTagRelUpdateCommand);
		return crmCustomerTagRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CrmCustomerTagRelUpdateCommandToCrmCustomerTagRelMapping{
		CrmCustomerTagRelUpdateCommandToCrmCustomerTagRelMapping instance = Mappers.getMapper(CrmCustomerTagRelUpdateCommandToCrmCustomerTagRelMapping.class );

		default CrmCustomerTagRelId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerTagRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerTagRel
		 * @param crmCustomerTagRelUpdateCommand
		 */
		void fillCrmCustomerTagRelByCrmCustomerTagRelUpdateCommand(@MappingTarget CrmCustomerTagRel crmCustomerTagRel, CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param crmCustomerTagRelGateway
	 */
	@Autowired
	public void setCrmCustomerTagRelGateway(CrmCustomerTagRelGateway crmCustomerTagRelGateway) {
		this.crmCustomerTagRelGateway = crmCustomerTagRelGateway;
	}
}
