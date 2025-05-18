package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyJudgmentDebtorUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyJudgmentDebtorId;
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
 * 企业被执行人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyJudgmentDebtorGateway dataCompanyJudgmentDebtorGateway;

	/**
	 * 执行 企业被执行人 更新指令
	 * @param dataCompanyJudgmentDebtorUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorVO> execute(@Valid DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand) {
		DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor = createByDataCompanyJudgmentDebtorUpdateCommand(dataCompanyJudgmentDebtorUpdateCommand);
		dataCompanyJudgmentDebtor.initForUpdate();
		dataCompanyJudgmentDebtor.setUpdateControl(dataCompanyJudgmentDebtorUpdateCommand);
		boolean save = dataCompanyJudgmentDebtorGateway.save(dataCompanyJudgmentDebtor);
		if (save) {
			return SingleResponse.of(DataCompanyJudgmentDebtorAppStructMapping.instance.toDataCompanyJudgmentDebtorVO(dataCompanyJudgmentDebtor));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业被执行人更新指令创建企业被执行人模型
	 * @param dataCompanyJudgmentDebtorUpdateCommand
	 * @return
	 */
	private DataCompanyJudgmentDebtor createByDataCompanyJudgmentDebtorUpdateCommand(DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand){
		DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor = DataCompanyJudgmentDebtor.create();
		DataCompanyJudgmentDebtorUpdateCommandToDataCompanyJudgmentDebtorMapping.instance.fillDataCompanyJudgmentDebtorByDataCompanyJudgmentDebtorUpdateCommand(dataCompanyJudgmentDebtor, dataCompanyJudgmentDebtorUpdateCommand);
		return dataCompanyJudgmentDebtor;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyJudgmentDebtorUpdateCommandToDataCompanyJudgmentDebtorMapping{
		DataCompanyJudgmentDebtorUpdateCommandToDataCompanyJudgmentDebtorMapping instance = Mappers.getMapper(DataCompanyJudgmentDebtorUpdateCommandToDataCompanyJudgmentDebtorMapping.class );

		default DataCompanyJudgmentDebtorId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyJudgmentDebtorId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyJudgmentDebtor
		 * @param dataCompanyJudgmentDebtorUpdateCommand
		 */
		void fillDataCompanyJudgmentDebtorByDataCompanyJudgmentDebtorUpdateCommand(@MappingTarget DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor, DataCompanyJudgmentDebtorUpdateCommand dataCompanyJudgmentDebtorUpdateCommand);
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
