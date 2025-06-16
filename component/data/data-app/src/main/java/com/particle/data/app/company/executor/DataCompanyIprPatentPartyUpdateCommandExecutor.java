package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPartyVO;
import com.particle.data.domain.company.DataCompanyIprPatentParty;
import com.particle.data.domain.company.DataCompanyIprPatentPartyId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPartyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 企业知识产权专利当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway;

	/**
	 * 执行 企业知识产权专利当事人 更新指令
	 * @param dataCompanyIprPatentPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPartyVO> execute(@Valid DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand) {
		DataCompanyIprPatentParty dataCompanyIprPatentParty = createByDataCompanyIprPatentPartyUpdateCommand(dataCompanyIprPatentPartyUpdateCommand);
		dataCompanyIprPatentParty.setUpdateControl(dataCompanyIprPatentPartyUpdateCommand);
		boolean save = dataCompanyIprPatentPartyGateway.save(dataCompanyIprPatentParty);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPartyAppStructMapping.instance.toDataCompanyIprPatentPartyVO(dataCompanyIprPatentParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利当事人更新指令创建企业知识产权专利当事人模型
	 * @param dataCompanyIprPatentPartyUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentParty createByDataCompanyIprPatentPartyUpdateCommand(DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand){
		DataCompanyIprPatentParty dataCompanyIprPatentParty = DataCompanyIprPatentParty.create();
		DataCompanyIprPatentPartyUpdateCommandToDataCompanyIprPatentPartyMapping.instance.fillDataCompanyIprPatentPartyByDataCompanyIprPatentPartyUpdateCommand(dataCompanyIprPatentParty, dataCompanyIprPatentPartyUpdateCommand);
		return dataCompanyIprPatentParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentPartyUpdateCommandToDataCompanyIprPatentPartyMapping{
		DataCompanyIprPatentPartyUpdateCommandToDataCompanyIprPatentPartyMapping instance = Mappers.getMapper(DataCompanyIprPatentPartyUpdateCommandToDataCompanyIprPatentPartyMapping.class );

		default DataCompanyIprPatentPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentParty
		 * @param dataCompanyIprPatentPartyUpdateCommand
		 */
		void fillDataCompanyIprPatentPartyByDataCompanyIprPatentPartyUpdateCommand(@MappingTarget DataCompanyIprPatentParty dataCompanyIprPatentParty, DataCompanyIprPatentPartyUpdateCommand dataCompanyIprPatentPartyUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPartyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPartyGateway(DataCompanyIprPatentPartyGateway dataCompanyIprPatentPartyGateway) {
		this.dataCompanyIprPatentPartyGateway = dataCompanyIprPatentPartyGateway;
	}
}
