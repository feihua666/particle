package com.particle.dataquery.app.datasource.executor;

import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
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
 * 数据查询数据源接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataQueryDatasourceApiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;

	/**
	 * 执行 数据查询数据源接口 更新指令
	 * @param dataQueryDatasourceApiUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> execute(@Valid DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand) {
		DataQueryDatasourceApi dataQueryDatasourceApi = createByDataQueryDatasourceApiUpdateCommand(dataQueryDatasourceApiUpdateCommand);
		dataQueryDatasourceApi.setUpdateControl(dataQueryDatasourceApiUpdateCommand);
		boolean save = dataQueryDatasourceApiGateway.save(dataQueryDatasourceApi);
		if (save) {
			return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.toDataQueryDatasourceApiVO(dataQueryDatasourceApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据查询数据源接口更新指令创建数据查询数据源接口模型
	 * @param dataQueryDatasourceApiUpdateCommand
	 * @return
	 */
	private DataQueryDatasourceApi createByDataQueryDatasourceApiUpdateCommand(DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand){
		DataQueryDatasourceApi dataQueryDatasourceApi = DataQueryDatasourceApi.create();
		DataQueryDatasourceApiUpdateCommandToDataQueryDatasourceApiMapping.instance.fillDataQueryDatasourceApiByDataQueryDatasourceApiUpdateCommand(dataQueryDatasourceApi, dataQueryDatasourceApiUpdateCommand);
		return dataQueryDatasourceApi;
	}

	@Mapper
	interface DataQueryDatasourceApiUpdateCommandToDataQueryDatasourceApiMapping{
		DataQueryDatasourceApiUpdateCommandToDataQueryDatasourceApiMapping instance = Mappers.getMapper(DataQueryDatasourceApiUpdateCommandToDataQueryDatasourceApiMapping.class );

		default DataQueryDatasourceApiId map(Long id){
			if (id == null) {
				return null;
			}
			return DataQueryDatasourceApiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDatasourceApi
		 * @param dataQueryDatasourceApiUpdateCommand
		 */
		void fillDataQueryDatasourceApiByDataQueryDatasourceApiUpdateCommand(@MappingTarget DataQueryDatasourceApi dataQueryDatasourceApi, DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceApiGateway
	 */
	@Autowired
	public void setDataQueryDatasourceApiGateway(DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway) {
		this.dataQueryDatasourceApiGateway = dataQueryDatasourceApiGateway;
	}
}
