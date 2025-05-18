package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyOpenCourtAnnouncementPartyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyOpenCourtAnnouncementPartyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementParty;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementPartyId;
import com.particle.data.domain.company.gateway.DataCompanyOpenCourtAnnouncementPartyGateway;
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
 * 企业开庭公告当事人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementPartyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway;

	/**
	 * 执行 企业开庭公告当事人 更新指令
	 * @param dataCompanyOpenCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> execute(@Valid DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand) {
		DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty = createByDataCompanyOpenCourtAnnouncementPartyUpdateCommand(dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
		dataCompanyOpenCourtAnnouncementParty.setUpdateControl(dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
		boolean save = dataCompanyOpenCourtAnnouncementPartyGateway.save(dataCompanyOpenCourtAnnouncementParty);
		if (save) {
			return SingleResponse.of(DataCompanyOpenCourtAnnouncementPartyAppStructMapping.instance.toDataCompanyOpenCourtAnnouncementPartyVO(dataCompanyOpenCourtAnnouncementParty));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业开庭公告当事人更新指令创建企业开庭公告当事人模型
	 * @param dataCompanyOpenCourtAnnouncementPartyUpdateCommand
	 * @return
	 */
	private DataCompanyOpenCourtAnnouncementParty createByDataCompanyOpenCourtAnnouncementPartyUpdateCommand(DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand){
		DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty = DataCompanyOpenCourtAnnouncementParty.create();
		DataCompanyOpenCourtAnnouncementPartyUpdateCommandToDataCompanyOpenCourtAnnouncementPartyMapping.instance.fillDataCompanyOpenCourtAnnouncementPartyByDataCompanyOpenCourtAnnouncementPartyUpdateCommand(dataCompanyOpenCourtAnnouncementParty, dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
		return dataCompanyOpenCourtAnnouncementParty;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyOpenCourtAnnouncementPartyUpdateCommandToDataCompanyOpenCourtAnnouncementPartyMapping{
		DataCompanyOpenCourtAnnouncementPartyUpdateCommandToDataCompanyOpenCourtAnnouncementPartyMapping instance = Mappers.getMapper(DataCompanyOpenCourtAnnouncementPartyUpdateCommandToDataCompanyOpenCourtAnnouncementPartyMapping.class );

		default DataCompanyOpenCourtAnnouncementPartyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyOpenCourtAnnouncementPartyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyOpenCourtAnnouncementParty
		 * @param dataCompanyOpenCourtAnnouncementPartyUpdateCommand
		 */
		void fillDataCompanyOpenCourtAnnouncementPartyByDataCompanyOpenCourtAnnouncementPartyUpdateCommand(@MappingTarget DataCompanyOpenCourtAnnouncementParty dataCompanyOpenCourtAnnouncementParty, DataCompanyOpenCourtAnnouncementPartyUpdateCommand dataCompanyOpenCourtAnnouncementPartyUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyOpenCourtAnnouncementPartyGateway
	 */
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementPartyGateway(DataCompanyOpenCourtAnnouncementPartyGateway dataCompanyOpenCourtAnnouncementPartyGateway) {
		this.dataCompanyOpenCourtAnnouncementPartyGateway = dataCompanyOpenCourtAnnouncementPartyGateway;
	}
}
