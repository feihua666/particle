package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingVO;
import com.particle.data.domain.company.DataCompanyVcFinancing;
import com.particle.data.domain.company.DataCompanyVcFinancingId;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingGateway;
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
 * 企业融资 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyVcFinancingUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway;

	/**
	 * 执行 企业融资 更新指令
	 * @param dataCompanyVcFinancingUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingVO> execute(@Valid DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand) {
		DataCompanyVcFinancing dataCompanyVcFinancing = createByDataCompanyVcFinancingUpdateCommand(dataCompanyVcFinancingUpdateCommand);
		dataCompanyVcFinancing.initForUpdate();
		dataCompanyVcFinancing.setUpdateControl(dataCompanyVcFinancingUpdateCommand);
		boolean save = dataCompanyVcFinancingGateway.save(dataCompanyVcFinancing);
		if (save) {
			return SingleResponse.of(DataCompanyVcFinancingAppStructMapping.instance.toDataCompanyVcFinancingVO(dataCompanyVcFinancing));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资更新指令创建企业融资模型
	 * @param dataCompanyVcFinancingUpdateCommand
	 * @return
	 */
	private DataCompanyVcFinancing createByDataCompanyVcFinancingUpdateCommand(DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand){
		DataCompanyVcFinancing dataCompanyVcFinancing = DataCompanyVcFinancing.create();
		DataCompanyVcFinancingUpdateCommandToDataCompanyVcFinancingMapping.instance.fillDataCompanyVcFinancingByDataCompanyVcFinancingUpdateCommand(dataCompanyVcFinancing, dataCompanyVcFinancingUpdateCommand);
		return dataCompanyVcFinancing;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyVcFinancingUpdateCommandToDataCompanyVcFinancingMapping{
		DataCompanyVcFinancingUpdateCommandToDataCompanyVcFinancingMapping instance = Mappers.getMapper(DataCompanyVcFinancingUpdateCommandToDataCompanyVcFinancingMapping.class );

		default DataCompanyVcFinancingId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyVcFinancingId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcFinancing
		 * @param dataCompanyVcFinancingUpdateCommand
		 */
		void fillDataCompanyVcFinancingByDataCompanyVcFinancingUpdateCommand(@MappingTarget DataCompanyVcFinancing dataCompanyVcFinancing, DataCompanyVcFinancingUpdateCommand dataCompanyVcFinancingUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingGateway(DataCompanyVcFinancingGateway dataCompanyVcFinancingGateway) {
		this.dataCompanyVcFinancingGateway = dataCompanyVcFinancingGateway;
	}
}
