package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyDiscreditedJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyDiscreditedJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
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
 * 企业失信被执行人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyDiscreditedJudgmentDebtorGateway dataCompanyDiscreditedJudgmentDebtorGateway;

	/**
	 * 执行企业失信被执行人添加指令
	 * @param dataCompanyDiscreditedJudgmentDebtorCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> execute(@Valid DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand) {
		DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor = createByDataCompanyDiscreditedJudgmentDebtorCreateCommand(dataCompanyDiscreditedJudgmentDebtorCreateCommand);
		dataCompanyDiscreditedJudgmentDebtor.initForAdd();
		dataCompanyDiscreditedJudgmentDebtor.setAddControl(dataCompanyDiscreditedJudgmentDebtorCreateCommand);
		boolean save = dataCompanyDiscreditedJudgmentDebtorGateway.save(dataCompanyDiscreditedJudgmentDebtor);
		if (save) {
			return SingleResponse.of(DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.toDataCompanyDiscreditedJudgmentDebtorVO(dataCompanyDiscreditedJudgmentDebtor));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业失信被执行人创建指令创建企业失信被执行人模型
	 * @param dataCompanyDiscreditedJudgmentDebtorCreateCommand
	 * @return
	 */
	private DataCompanyDiscreditedJudgmentDebtor createByDataCompanyDiscreditedJudgmentDebtorCreateCommand(DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand){
		DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor = DataCompanyDiscreditedJudgmentDebtor.create();
		DataCompanyDiscreditedJudgmentDebtorCreateCommandToDataCompanyDiscreditedJudgmentDebtorMapping.instance.fillDataCompanyDiscreditedJudgmentDebtorByDataCompanyDiscreditedJudgmentDebtorCreateCommand(dataCompanyDiscreditedJudgmentDebtor, dataCompanyDiscreditedJudgmentDebtorCreateCommand);
		return dataCompanyDiscreditedJudgmentDebtor;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyDiscreditedJudgmentDebtorCreateCommandToDataCompanyDiscreditedJudgmentDebtorMapping{
		DataCompanyDiscreditedJudgmentDebtorCreateCommandToDataCompanyDiscreditedJudgmentDebtorMapping instance = Mappers.getMapper( DataCompanyDiscreditedJudgmentDebtorCreateCommandToDataCompanyDiscreditedJudgmentDebtorMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyDiscreditedJudgmentDebtor
		 * @param dataCompanyDiscreditedJudgmentDebtorCreateCommand
		 */
		void fillDataCompanyDiscreditedJudgmentDebtorByDataCompanyDiscreditedJudgmentDebtorCreateCommand(@MappingTarget DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor, DataCompanyDiscreditedJudgmentDebtorCreateCommand dataCompanyDiscreditedJudgmentDebtorCreateCommand);
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
