package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyHonorQualificationAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyHonorQualificationCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.domain.company.DataCompanyHonorQualification;
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
 * 企业荣誉资质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Component
@Validated
public class DataCompanyHonorQualificationCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyHonorQualificationGateway dataCompanyHonorQualificationGateway;

	/**
	 * 执行企业荣誉资质添加指令
	 * @param dataCompanyHonorQualificationCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyHonorQualificationVO> execute(@Valid DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand) {
		DataCompanyHonorQualification dataCompanyHonorQualification = createByDataCompanyHonorQualificationCreateCommand(dataCompanyHonorQualificationCreateCommand);
		dataCompanyHonorQualification.initForAdd();
		dataCompanyHonorQualification.setAddControl(dataCompanyHonorQualificationCreateCommand);
		boolean save = dataCompanyHonorQualificationGateway.save(dataCompanyHonorQualification);
		if (save) {
			return SingleResponse.of(DataCompanyHonorQualificationAppStructMapping.instance.toDataCompanyHonorQualificationVO(dataCompanyHonorQualification));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业荣誉资质创建指令创建企业荣誉资质模型
	 * @param dataCompanyHonorQualificationCreateCommand
	 * @return
	 */
	private DataCompanyHonorQualification createByDataCompanyHonorQualificationCreateCommand(DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand){
		DataCompanyHonorQualification dataCompanyHonorQualification = DataCompanyHonorQualification.create();
		DataCompanyHonorQualificationCreateCommandToDataCompanyHonorQualificationMapping.instance.fillDataCompanyHonorQualificationByDataCompanyHonorQualificationCreateCommand(dataCompanyHonorQualification, dataCompanyHonorQualificationCreateCommand);
		return dataCompanyHonorQualification;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyHonorQualificationCreateCommandToDataCompanyHonorQualificationMapping{
		DataCompanyHonorQualificationCreateCommandToDataCompanyHonorQualificationMapping instance = Mappers.getMapper( DataCompanyHonorQualificationCreateCommandToDataCompanyHonorQualificationMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyHonorQualification
		 * @param dataCompanyHonorQualificationCreateCommand
		 */
		void fillDataCompanyHonorQualificationByDataCompanyHonorQualificationCreateCommand(@MappingTarget DataCompanyHonorQualification dataCompanyHonorQualification, DataCompanyHonorQualificationCreateCommand dataCompanyHonorQualificationCreateCommand);
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
