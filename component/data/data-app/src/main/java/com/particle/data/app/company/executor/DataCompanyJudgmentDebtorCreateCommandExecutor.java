package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.gateway.DataCompanyJudgmentDebtorGateway;
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
 * 企业被执行人 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway;

	/**
	 * 执行企业被执行人添加指令
	 * @param dataCompanyJudgmentDebtorCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorVO> execute(@Valid DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand) {
		DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor = createByDataCompanyJudgmentDebtorCreateCommand(dataCompanyJudgmentDebtorCreateCommand);
		dataCompanyJudgmentDebtor.initForAdd();
		dataCompanyJudgmentDebtor.setAddControl(dataCompanyJudgmentDebtorCreateCommand);
		boolean save = dataCompanyJudgmentDebtorGateway.save(dataCompanyJudgmentDebtor);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDebtorAppStructMapping.instance.toDataCompanyJudgmentDebtorVO(dataCompanyJudgmentDebtor));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业被执行人创建指令创建企业被执行人模型
	 * @param dataCompanyJudgmentDebtorCreateCommand
	 * @return
	 */
	private DataCompanyJudgmentDebtor createByDataCompanyJudgmentDebtorCreateCommand(DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand){
		DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor = DataCompanyJudgmentDebtor.create();
		DataCompanyJudgmentDebtorCreateCommandToDataCompanyJudgmentDebtorMapping.instance.fillDataCompanyJudgmentDebtorByDataCompanyJudgmentDebtorCreateCommand(dataCompanyJudgmentDebtor, dataCompanyJudgmentDebtorCreateCommand);
		return dataCompanyJudgmentDebtor;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyJudgmentDebtorCreateCommandToDataCompanyJudgmentDebtorMapping{
		DataCompanyJudgmentDebtorCreateCommandToDataCompanyJudgmentDebtorMapping instance = Mappers.getMapper( DataCompanyJudgmentDebtorCreateCommandToDataCompanyJudgmentDebtorMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDebtor
		 * @param dataCompanyJudgmentDebtorCreateCommand
		 */
		void fillDataCompanyJudgmentDebtorByDataCompanyJudgmentDebtorCreateCommand(@MappingTarget DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor, DataCompanyJudgmentDebtorCreateCommand dataCompanyJudgmentDebtorCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyJudgmentDebtorGateway
	 */
	@Autowired
	public void setDataCompanyJudgmentDebtorGateway(DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway) {
		this.dataCompanyJudgmentDebtorGateway = dataCompanyJudgmentDebtorGateway;
	}
}
