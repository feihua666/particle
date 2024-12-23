package com.particle.data.app.temp.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.data.app.temp.structmapping.DataCompanyMd5IdsAppStructMapping;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsUpdateCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.DataCompanyMd5IdsId;
import com.particle.data.domain.temp.gateway.DataCompanyMd5IdsGateway;
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
 * 企业md5ids 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyMd5IdsUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyMd5IdsGateway dataCompanyMd5IdsGateway;

	/**
	 * 执行 企业md5ids 更新指令
	 * @param dataCompanyMd5IdsUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyMd5IdsVO> execute(@Valid DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand) {
		DataCompanyMd5Ids dataCompanyMd5Ids = createByDataCompanyMd5IdsUpdateCommand(dataCompanyMd5IdsUpdateCommand);
		dataCompanyMd5Ids.setUpdateControl(dataCompanyMd5IdsUpdateCommand);
		boolean save = dataCompanyMd5IdsGateway.save(dataCompanyMd5Ids);
		if (save) {
			return SingleResponse.of(DataCompanyMd5IdsAppStructMapping.instance.toDataCompanyMd5IdsVO(dataCompanyMd5Ids));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业md5ids更新指令创建企业md5ids模型
	 * @param dataCompanyMd5IdsUpdateCommand
	 * @return
	 */
	private DataCompanyMd5Ids createByDataCompanyMd5IdsUpdateCommand(DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand){
		DataCompanyMd5Ids dataCompanyMd5Ids = DataCompanyMd5Ids.create();
		DataCompanyMd5IdsUpdateCommandToDataCompanyMd5IdsMapping.instance.fillDataCompanyMd5IdsByDataCompanyMd5IdsUpdateCommand(dataCompanyMd5Ids, dataCompanyMd5IdsUpdateCommand);
		return dataCompanyMd5Ids;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyMd5IdsUpdateCommandToDataCompanyMd5IdsMapping{
		DataCompanyMd5IdsUpdateCommandToDataCompanyMd5IdsMapping instance = Mappers.getMapper(DataCompanyMd5IdsUpdateCommandToDataCompanyMd5IdsMapping.class );

		default DataCompanyMd5IdsId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyMd5IdsId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyMd5Ids
		 * @param dataCompanyMd5IdsUpdateCommand
		 */
		void fillDataCompanyMd5IdsByDataCompanyMd5IdsUpdateCommand(@MappingTarget DataCompanyMd5Ids dataCompanyMd5Ids, DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand);
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
