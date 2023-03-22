package com.particle.dataquery.app.dataapi.executor;

import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiCreateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
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
 * 数据查询数据接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
@Validated
public class DataQueryDataApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDataApiGateway dataQueryDataApiGateway;

	/**
	 * 执行数据查询数据接口添加指令
	 * @param dataQueryDataApiCreateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> execute(@Valid DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand) {
		DataQueryDataApi dataQueryDataApi = createByDataQueryDataApiCreateCommand(dataQueryDataApiCreateCommand);
		dataQueryDataApi.setAddControl(dataQueryDataApiCreateCommand);
		// 添加参数校验
		dataQueryDataApi.validateOnCreate();

		boolean save = dataQueryDataApiGateway.save(dataQueryDataApi);
		if (save) {
			return SingleResponse.of(DataQueryDataApiAppStructMapping.instance.toDataQueryDataApiVO(dataQueryDataApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据查询数据接口创建指令创建数据查询数据接口模型
	 * @param dataQueryDataApiCreateCommand
	 * @return
	 */
	private DataQueryDataApi createByDataQueryDataApiCreateCommand(DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand){
		DataQueryDataApi dataQueryDataApi = DataQueryDataApi.create();
		DataQueryDataApiCreateCommandToDataQueryDataApiMapping.instance.fillDataQueryDataApiByDataQueryDataApiCreateCommand(dataQueryDataApi, dataQueryDataApiCreateCommand);
		return dataQueryDataApi;
	}

	@Mapper
	interface  DataQueryDataApiCreateCommandToDataQueryDataApiMapping{
		DataQueryDataApiCreateCommandToDataQueryDataApiMapping instance = Mappers.getMapper( DataQueryDataApiCreateCommandToDataQueryDataApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDataApi
		 * @param dataQueryDataApiCreateCommand
		 */
		void fillDataQueryDataApiByDataQueryDataApiCreateCommand(@MappingTarget DataQueryDataApi dataQueryDataApi, DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand);
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
