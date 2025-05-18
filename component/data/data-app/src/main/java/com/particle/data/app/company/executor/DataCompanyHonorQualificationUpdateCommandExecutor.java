package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyHonorQualificationAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.domain.company.DataCompanyHonorQualification;
import com.particle.data.domain.company.DataCompanyHonorQualificationId;
import com.particle.data.domain.company.gateway.DataCompanyHonorQualificationGateway;
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
 * 企业荣誉资质 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyHonorQualificationUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway;

	/**
	 * 执行 企业荣誉资质 更新指令
	 * @param dataCompanyHonorQualificationUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationVO> execute(@Valid DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand) {
		DataCompanyHonorQualification dataCompanyHonorQualification = createByDataCompanyHonorQualificationUpdateCommand(dataCompanyHonorQualificationUpdateCommand);
		dataCompanyHonorQualification.initForUpdate();
		dataCompanyHonorQualification.setUpdateControl(dataCompanyHonorQualificationUpdateCommand);
		boolean save = dataCompanyHonorQualificationGateway.save(dataCompanyHonorQualification);
		if (save) {
			return SingleResponse.of(DataCompanyHonorQualificationAppStructMapping.instance.toDataCompanyHonorQualificationVO(dataCompanyHonorQualification));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业荣誉资质更新指令创建企业荣誉资质模型
	 * @param dataCompanyHonorQualificationUpdateCommand
	 * @return
	 */
	private DataCompanyHonorQualification createByDataCompanyHonorQualificationUpdateCommand(DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand){
		DataCompanyHonorQualification dataCompanyHonorQualification = DataCompanyHonorQualification.create();
		DataCompanyHonorQualificationUpdateCommandToDataCompanyHonorQualificationMapping.instance.fillDataCompanyHonorQualificationByDataCompanyHonorQualificationUpdateCommand(dataCompanyHonorQualification, dataCompanyHonorQualificationUpdateCommand);
		return dataCompanyHonorQualification;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyHonorQualificationUpdateCommandToDataCompanyHonorQualificationMapping{
		DataCompanyHonorQualificationUpdateCommandToDataCompanyHonorQualificationMapping instance = Mappers.getMapper(DataCompanyHonorQualificationUpdateCommandToDataCompanyHonorQualificationMapping.class );

		default DataCompanyHonorQualificationId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyHonorQualificationId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyHonorQualification
		 * @param dataCompanyHonorQualificationUpdateCommand
		 */
		void fillDataCompanyHonorQualificationByDataCompanyHonorQualificationUpdateCommand(@MappingTarget DataCompanyHonorQualification dataCompanyHonorQualification, DataCompanyHonorQualificationUpdateCommand dataCompanyHonorQualificationUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyHonorQualificationGateway
	 */
	@Autowired
	public void setDataCompanyHonorQualificationGateway(DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway) {
		this.dataCompanyHonorQualificationGateway = dataCompanyHonorQualificationGateway;
	}
}
