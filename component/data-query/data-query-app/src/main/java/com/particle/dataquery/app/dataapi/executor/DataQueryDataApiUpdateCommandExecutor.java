package com.particle.dataquery.app.dataapi.executor;

import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiUpdateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
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
 * 数据查询数据接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataQueryDataApiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDataApiGateway dataQueryDataApiGateway;

	/**
	 * 执行 数据查询数据接口 更新指令
	 * @param dataQueryDataApiUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> execute(@Valid DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand) {
		DataQueryDataApi dataQueryDataApi = createByDataQueryDataApiUpdateCommand(dataQueryDataApiUpdateCommand);
		dataQueryDataApi.setUpdateControl(dataQueryDataApiUpdateCommand);

		/**
		 * 验证一些字段
		 */
		dataQueryDataApi.validateOnFullUpdate();

		boolean save = dataQueryDataApiGateway.save(dataQueryDataApi);
		if (save) {
			return SingleResponse.of(DataQueryDataApiAppStructMapping.instance.toDataQueryDataApiVO(dataQueryDataApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param dataQueryDataApiUpdateCommand
	 * @return
	 */
	private DataQueryDataApi createByDataQueryDataApiUpdateCommand(DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand){
		DataQueryDataApi dataQueryDataApi = DataQueryDataApi.create();
		DataQueryDataApiUpdateCommandToDataQueryDataApiMapping.instance.fillDataQueryDataApiByDataQueryDataApiUpdateCommand(dataQueryDataApi, dataQueryDataApiUpdateCommand);
		return dataQueryDataApi;
	}

	@Mapper
	interface DataQueryDataApiUpdateCommandToDataQueryDataApiMapping{
		DataQueryDataApiUpdateCommandToDataQueryDataApiMapping instance = Mappers.getMapper(DataQueryDataApiUpdateCommandToDataQueryDataApiMapping.class );

		default DataQueryDataApiId map(Long id){
			if (id == null) {
				return null;
			}
			return DataQueryDataApiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDataApi
		 * @param dataQueryDataApiUpdateCommand
		 */
		void fillDataQueryDataApiByDataQueryDataApiUpdateCommand(@MappingTarget DataQueryDataApi dataQueryDataApi, DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDataApiGateway
	 */
	@Autowired
	public void setDataQueryDataApiGateway(DataQueryDataApiGateway dataQueryDataApiGateway) {
		this.dataQueryDataApiGateway = dataQueryDataApiGateway;
	}
}
