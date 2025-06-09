package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAbnormalAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.DataCompanyAbnormalId;
import com.particle.data.domain.company.gateway.DataCompanyAbnormalGateway;
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
 * 企业经营异常 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAbnormalUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAbnormalGateway dataCompanyAbnormalGateway;

	/**
	 * 执行 企业经营异常 更新指令
	 * @param dataCompanyAbnormalUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalVO> execute(@Valid DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand) {
		DataCompanyAbnormal dataCompanyAbnormal = createByDataCompanyAbnormalUpdateCommand(dataCompanyAbnormalUpdateCommand);
		dataCompanyAbnormal.setUpdateControl(dataCompanyAbnormalUpdateCommand);
		boolean save = dataCompanyAbnormalGateway.save(dataCompanyAbnormal);
		if (save) {
			return SingleResponse.of(DataCompanyAbnormalAppStructMapping.instance.toDataCompanyAbnormalVO(dataCompanyAbnormal));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业经营异常更新指令创建企业经营异常模型
	 * @param dataCompanyAbnormalUpdateCommand
	 * @return
	 */
	private DataCompanyAbnormal createByDataCompanyAbnormalUpdateCommand(DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand){
		DataCompanyAbnormal dataCompanyAbnormal = DataCompanyAbnormal.create();
		DataCompanyAbnormalUpdateCommandToDataCompanyAbnormalMapping.instance.fillDataCompanyAbnormalByDataCompanyAbnormalUpdateCommand(dataCompanyAbnormal, dataCompanyAbnormalUpdateCommand);
		return dataCompanyAbnormal;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAbnormalUpdateCommandToDataCompanyAbnormalMapping{
		DataCompanyAbnormalUpdateCommandToDataCompanyAbnormalMapping instance = Mappers.getMapper(DataCompanyAbnormalUpdateCommandToDataCompanyAbnormalMapping.class );

		default DataCompanyAbnormalId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAbnormalId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAbnormal
		 * @param dataCompanyAbnormalUpdateCommand
		 */
		void fillDataCompanyAbnormalByDataCompanyAbnormalUpdateCommand(@MappingTarget DataCompanyAbnormal dataCompanyAbnormal, DataCompanyAbnormalUpdateCommand dataCompanyAbnormalUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAbnormalGateway
	 */
	@Autowired
	public void setDataCompanyAbnormalGateway(DataCompanyAbnormalGateway dataCompanyAbnormalGateway) {
		this.dataCompanyAbnormalGateway = dataCompanyAbnormalGateway;
	}
}
