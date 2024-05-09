package com.particle.crm.app.tag.executor;

import com.particle.crm.app.tag.structmapping.CrmCustomerTagAppStructMapping;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagUpdateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.CrmCustomerTagId;
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
 * 客户标签 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CrmCustomerTagUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CrmCustomerTagGateway crmCustomerTagGateway;

	/**
	 * 执行 客户标签 更新指令
	 * @param crmCustomerTagUpdateCommand
	 * @return
	 */
	public SingleResponse<CrmCustomerTagVO> execute(@Valid CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand) {
		CrmCustomerTag crmCustomerTag = createByCrmCustomerTagUpdateCommand(crmCustomerTagUpdateCommand);
		crmCustomerTag.setUpdateControl(crmCustomerTagUpdateCommand);
		boolean save = crmCustomerTagGateway.save(crmCustomerTag);
		if (save) {
			return SingleResponse.of(CrmCustomerTagAppStructMapping.instance.toCrmCustomerTagVO(crmCustomerTag));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据客户标签更新指令创建客户标签模型
	 * @param crmCustomerTagUpdateCommand
	 * @return
	 */
	private CrmCustomerTag createByCrmCustomerTagUpdateCommand(CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand){
		CrmCustomerTag crmCustomerTag = CrmCustomerTag.create();
		CrmCustomerTagUpdateCommandToCrmCustomerTagMapping.instance.fillCrmCustomerTagByCrmCustomerTagUpdateCommand(crmCustomerTag, crmCustomerTagUpdateCommand);
		return crmCustomerTag;
	}

	@Mapper
	interface CrmCustomerTagUpdateCommandToCrmCustomerTagMapping{
		CrmCustomerTagUpdateCommandToCrmCustomerTagMapping instance = Mappers.getMapper(CrmCustomerTagUpdateCommandToCrmCustomerTagMapping.class );

		default CrmCustomerTagId map(Long id){
			if (id == null) {
				return null;
			}
			return CrmCustomerTagId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param crmCustomerTag
		 * @param crmCustomerTagUpdateCommand
		 */
		void fillCrmCustomerTagByCrmCustomerTagUpdateCommand(@MappingTarget CrmCustomerTag crmCustomerTag, CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand);
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
