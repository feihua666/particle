package com.particle.data.app.temp.executor;

import com.particle.data.app.temp.structmapping.DataCompanyMd5IdsAppStructMapping;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsCreateCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.gateway.DataCompanyMd5IdsGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 企业md5ids 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Component
@Validated
public class DataCompanyMd5IdsCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway;

	/**
	 * 执行企业md5ids添加指令
	 * @param dataCompanyMd5IdsCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5IdsVO> execute(@Valid DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand) {
		DataCompanyMd5Ids dataCompanyMd5Ids = createByDataCompanyMd5IdsCreateCommand(dataCompanyMd5IdsCreateCommand);
		dataCompanyMd5Ids.setAddControl(dataCompanyMd5IdsCreateCommand);
		boolean save = dataCompanyMd5IdsGateway.save(dataCompanyMd5Ids);
		if (save) {
			return SingleResponse.of(DataCompanyMd5IdsAppStructMapping.instance.toDataCompanyMd5IdsVO(dataCompanyMd5Ids));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业md5ids创建指令创建企业md5ids模型
	 * @param dataCompanyMd5IdsCreateCommand
	 * @return
	 */
	private DataCompanyMd5Ids createByDataCompanyMd5IdsCreateCommand(DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand){
		DataCompanyMd5Ids dataCompanyMd5Ids = DataCompanyMd5Ids.create();
		DataCompanyMd5IdsCreateCommandToDataCompanyMd5IdsMapping.instance.fillDataCompanyMd5IdsByDataCompanyMd5IdsCreateCommand(dataCompanyMd5Ids, dataCompanyMd5IdsCreateCommand);
		return dataCompanyMd5Ids;
	}

	@Mapper
	interface  DataCompanyMd5IdsCreateCommandToDataCompanyMd5IdsMapping{
		DataCompanyMd5IdsCreateCommandToDataCompanyMd5IdsMapping instance = Mappers.getMapper( DataCompanyMd5IdsCreateCommandToDataCompanyMd5IdsMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyMd5Ids
		 * @param dataCompanyMd5IdsCreateCommand
		 */
		void fillDataCompanyMd5IdsByDataCompanyMd5IdsCreateCommand(@MappingTarget DataCompanyMd5Ids dataCompanyMd5Ids, DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyMd5IdsGateway
	 */
	@Autowired
	public void setDataCompanyMd5IdsGateway(DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway) {
		this.dataCompanyMd5IdsGateway = dataCompanyMd5IdsGateway;
	}
}
