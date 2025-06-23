package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.gateway.DataCompanyIprGeograGateway;
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
 * 企业知识产权地理标识 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Component
@Validated
public class DataCompanyIprGeograCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograGateway dataCompanyIprGeograGateway;

	/**
	 * 执行企业知识产权地理标识添加指令
	 * @param dataCompanyIprGeograCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograVO> execute(@Valid DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand) {
		DataCompanyIprGeogra dataCompanyIprGeogra = createByDataCompanyIprGeograCreateCommand(dataCompanyIprGeograCreateCommand);
		dataCompanyIprGeogra.initForAdd();
		dataCompanyIprGeogra.setAddControl(dataCompanyIprGeograCreateCommand);
		boolean save = dataCompanyIprGeograGateway.save(dataCompanyIprGeogra);
		if (save) {
			return SingleResponse.of(DataCompanyIprGeograAppStructMapping.instance.toDataCompanyIprGeograVO(dataCompanyIprGeogra));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权地理标识创建指令创建企业知识产权地理标识模型
	 * @param dataCompanyIprGeograCreateCommand
	 * @return
	 */
	private DataCompanyIprGeogra createByDataCompanyIprGeograCreateCommand(DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand){
		DataCompanyIprGeogra dataCompanyIprGeogra = DataCompanyIprGeogra.create();
		DataCompanyIprGeograCreateCommandToDataCompanyIprGeograMapping.instance.fillDataCompanyIprGeograByDataCompanyIprGeograCreateCommand(dataCompanyIprGeogra, dataCompanyIprGeograCreateCommand);
		return dataCompanyIprGeogra;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprGeograCreateCommandToDataCompanyIprGeograMapping{
		DataCompanyIprGeograCreateCommandToDataCompanyIprGeograMapping instance = Mappers.getMapper( DataCompanyIprGeograCreateCommandToDataCompanyIprGeograMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprGeogra
		 * @param dataCompanyIprGeograCreateCommand
		 */
		void fillDataCompanyIprGeograByDataCompanyIprGeograCreateCommand(@MappingTarget DataCompanyIprGeogra dataCompanyIprGeogra, DataCompanyIprGeograCreateCommand dataCompanyIprGeograCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprGeograGateway
	 */
	@Autowired
	public void setDataCompanyIprGeograGateway(DataCompanyIprGeograGateway dataCompanyIprGeograGateway) {
		this.dataCompanyIprGeograGateway = dataCompanyIprGeograGateway;
	}
}
