package com.particle.data.app.company.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.DataCompanyId;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyGateway dataCompanyGateway;

	/**
	 * 执行 企业 更新指令
	 * @param dataCompanyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVO> execute(@Valid DataCompanyUpdateCommand dataCompanyUpdateCommand) {
		DataCompany dataCompany = createByDataCompanyUpdateCommand(dataCompanyUpdateCommand);
		dataCompany.setUpdateControl(dataCompanyUpdateCommand);
		dataCompany.initForUpdate();

		boolean save = dataCompanyGateway.save(dataCompany);
		if (save) {
			return SingleResponse.of(DataCompanyAppStructMapping.instance.toDataCompanyVO(dataCompany));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业更新指令创建企业模型
	 * @param dataCompanyUpdateCommand
	 * @return
	 */
	private DataCompany createByDataCompanyUpdateCommand(DataCompanyUpdateCommand dataCompanyUpdateCommand){
		DataCompany dataCompany = DataCompany.create();
		DataCompanyUpdateCommandToDataCompanyMapping.instance.fillDataCompanyByDataCompanyUpdateCommand(dataCompany, dataCompanyUpdateCommand);
		return dataCompany;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyUpdateCommandToDataCompanyMapping{
		DataCompanyUpdateCommandToDataCompanyMapping instance = Mappers.getMapper(DataCompanyUpdateCommandToDataCompanyMapping.class );

		default DataCompanyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompany
		 * @param dataCompanyUpdateCommand
		 */
		void fillDataCompanyByDataCompanyUpdateCommand(@MappingTarget DataCompany dataCompany, DataCompanyUpdateCommand dataCompanyUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyGateway
	 */
	@Autowired
	public void setDataCompanyGateway(DataCompanyGateway dataCompanyGateway) {
		this.dataCompanyGateway = dataCompanyGateway;
	}
}
