package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDiscreditedJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtorId;
import com.particle.data.domain.company.gateway.DataCompanyDiscreditedJudgmentDebtorGateway;
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
 * 企业失信被执行人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway;

	/**
	 * 执行 企业失信被执行人 更新指令
	 * @param dataCompanyDiscreditedJudgmentDebtorUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> execute(@Valid DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand) {
		DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor = createByDataCompanyDiscreditedJudgmentDebtorUpdateCommand(dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
		dataCompanyDiscreditedJudgmentDebtor.initForUpdate();
		dataCompanyDiscreditedJudgmentDebtor.setUpdateControl(dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
		boolean save = dataCompanyDiscreditedJudgmentDebtorGateway.save(dataCompanyDiscreditedJudgmentDebtor);
		if (save) {
			return SingleResponse.of(DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.toDataCompanyDiscreditedJudgmentDebtorVO(dataCompanyDiscreditedJudgmentDebtor));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业失信被执行人更新指令创建企业失信被执行人模型
	 * @param dataCompanyDiscreditedJudgmentDebtorUpdateCommand
	 * @return
	 */
	private DataCompanyDiscreditedJudgmentDebtor createByDataCompanyDiscreditedJudgmentDebtorUpdateCommand(DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand){
		DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor = DataCompanyDiscreditedJudgmentDebtor.create();
		DataCompanyDiscreditedJudgmentDebtorUpdateCommandToDataCompanyDiscreditedJudgmentDebtorMapping.instance.fillDataCompanyDiscreditedJudgmentDebtorByDataCompanyDiscreditedJudgmentDebtorUpdateCommand(dataCompanyDiscreditedJudgmentDebtor, dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
		return dataCompanyDiscreditedJudgmentDebtor;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyDiscreditedJudgmentDebtorUpdateCommandToDataCompanyDiscreditedJudgmentDebtorMapping{
		DataCompanyDiscreditedJudgmentDebtorUpdateCommandToDataCompanyDiscreditedJudgmentDebtorMapping instance = Mappers.getMapper(DataCompanyDiscreditedJudgmentDebtorUpdateCommandToDataCompanyDiscreditedJudgmentDebtorMapping.class );

		default DataCompanyDiscreditedJudgmentDebtorId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyDiscreditedJudgmentDebtorId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDiscreditedJudgmentDebtor
		 * @param dataCompanyDiscreditedJudgmentDebtorUpdateCommand
		 */
		void fillDataCompanyDiscreditedJudgmentDebtorByDataCompanyDiscreditedJudgmentDebtorUpdateCommand(@MappingTarget DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor, DataCompanyDiscreditedJudgmentDebtorUpdateCommand dataCompanyDiscreditedJudgmentDebtorUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyDiscreditedJudgmentDebtorGateway
	 */
	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorGateway(DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway) {
		this.dataCompanyDiscreditedJudgmentDebtorGateway = dataCompanyDiscreditedJudgmentDebtorGateway;
	}
}
