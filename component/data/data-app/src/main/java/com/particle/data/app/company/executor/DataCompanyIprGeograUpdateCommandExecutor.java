package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprGeograAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprGeograUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.DataCompanyIprGeograId;
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
 * 企业知识产权地理标识 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprGeograUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprGeograGateway dataCompanyIprGeograGateway;

	/**
	 * 执行 企业知识产权地理标识 更新指令
	 * @param dataCompanyIprGeograUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograVO> execute(@Valid DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand) {
		DataCompanyIprGeogra dataCompanyIprGeogra = createByDataCompanyIprGeograUpdateCommand(dataCompanyIprGeograUpdateCommand);
		dataCompanyIprGeogra.initForUpdate();
		dataCompanyIprGeogra.setUpdateControl(dataCompanyIprGeograUpdateCommand);
		boolean save = dataCompanyIprGeograGateway.save(dataCompanyIprGeogra);
		if (save) {
			return SingleResponse.of(DataCompanyIprGeograAppStructMapping.instance.toDataCompanyIprGeograVO(dataCompanyIprGeogra));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权地理标识更新指令创建企业知识产权地理标识模型
	 * @param dataCompanyIprGeograUpdateCommand
	 * @return
	 */
	private DataCompanyIprGeogra createByDataCompanyIprGeograUpdateCommand(DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand){
		DataCompanyIprGeogra dataCompanyIprGeogra = DataCompanyIprGeogra.create();
		DataCompanyIprGeograUpdateCommandToDataCompanyIprGeograMapping.instance.fillDataCompanyIprGeograByDataCompanyIprGeograUpdateCommand(dataCompanyIprGeogra, dataCompanyIprGeograUpdateCommand);
		return dataCompanyIprGeogra;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprGeograUpdateCommandToDataCompanyIprGeograMapping{
		DataCompanyIprGeograUpdateCommandToDataCompanyIprGeograMapping instance = Mappers.getMapper(DataCompanyIprGeograUpdateCommandToDataCompanyIprGeograMapping.class );

		default DataCompanyIprGeograId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprGeograId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprGeogra
		 * @param dataCompanyIprGeograUpdateCommand
		 */
		void fillDataCompanyIprGeograByDataCompanyIprGeograUpdateCommand(@MappingTarget DataCompanyIprGeogra dataCompanyIprGeogra, DataCompanyIprGeograUpdateCommand dataCompanyIprGeograUpdateCommand);
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
