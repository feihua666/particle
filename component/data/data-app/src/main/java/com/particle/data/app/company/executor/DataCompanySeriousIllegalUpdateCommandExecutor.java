package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanySeriousIllegalAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.DataCompanySeriousIllegalId;
import com.particle.data.domain.company.gateway.DataCompanySeriousIllegalGateway;
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
 * 企业严重违法 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanySeriousIllegalUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway;

	/**
	 * 执行 企业严重违法 更新指令
	 * @param dataCompanySeriousIllegalUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalVO> execute(@Valid DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand) {
		DataCompanySeriousIllegal dataCompanySeriousIllegal = createByDataCompanySeriousIllegalUpdateCommand(dataCompanySeriousIllegalUpdateCommand);
		dataCompanySeriousIllegal.initForUpdate();
		dataCompanySeriousIllegal.setUpdateControl(dataCompanySeriousIllegalUpdateCommand);
		boolean save = dataCompanySeriousIllegalGateway.save(dataCompanySeriousIllegal);
		if (save) {
			return SingleResponse.of(DataCompanySeriousIllegalAppStructMapping.instance.toDataCompanySeriousIllegalVO(dataCompanySeriousIllegal));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业严重违法更新指令创建企业严重违法模型
	 * @param dataCompanySeriousIllegalUpdateCommand
	 * @return
	 */
	private DataCompanySeriousIllegal createByDataCompanySeriousIllegalUpdateCommand(DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand){
		DataCompanySeriousIllegal dataCompanySeriousIllegal = DataCompanySeriousIllegal.create();
		DataCompanySeriousIllegalUpdateCommandToDataCompanySeriousIllegalMapping.instance.fillDataCompanySeriousIllegalByDataCompanySeriousIllegalUpdateCommand(dataCompanySeriousIllegal, dataCompanySeriousIllegalUpdateCommand);
		return dataCompanySeriousIllegal;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanySeriousIllegalUpdateCommandToDataCompanySeriousIllegalMapping{
		DataCompanySeriousIllegalUpdateCommandToDataCompanySeriousIllegalMapping instance = Mappers.getMapper(DataCompanySeriousIllegalUpdateCommandToDataCompanySeriousIllegalMapping.class );

		default DataCompanySeriousIllegalId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanySeriousIllegalId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanySeriousIllegal
		 * @param dataCompanySeriousIllegalUpdateCommand
		 */
		void fillDataCompanySeriousIllegalByDataCompanySeriousIllegalUpdateCommand(@MappingTarget DataCompanySeriousIllegal dataCompanySeriousIllegal, DataCompanySeriousIllegalUpdateCommand dataCompanySeriousIllegalUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanySeriousIllegalGateway
	 */
	@Autowired
	public void setDataCompanySeriousIllegalGateway(DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway) {
		this.dataCompanySeriousIllegalGateway = dataCompanySeriousIllegalGateway;
	}
}
