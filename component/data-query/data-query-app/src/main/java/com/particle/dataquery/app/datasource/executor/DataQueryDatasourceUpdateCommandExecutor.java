package com.particle.dataquery.app.datasource.executor;

import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
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
 * 数据查询数据源 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataQueryDatasourceUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceGateway dataQueryDatasourceGateway;

	/**
	 * 执行 数据查询数据源 更新指令
	 * @param dataQueryDatasourceUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> execute(@Valid DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand) {
		DataQueryDatasource dataQueryDatasource = createByDataQueryDatasourceUpdateCommand(dataQueryDatasourceUpdateCommand);
		dataQueryDatasource.setUpdateControl(dataQueryDatasourceUpdateCommand);
		boolean save = dataQueryDatasourceGateway.save(dataQueryDatasource);
		if (save) {
			return SingleResponse.of(DataQueryDatasourceAppStructMapping.instance.toDataQueryDatasourceVO(dataQueryDatasource));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据查询数据源更新指令创建数据查询数据源模型
	 * @param dataQueryDatasourceUpdateCommand
	 * @return
	 */
	private DataQueryDatasource createByDataQueryDatasourceUpdateCommand(DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand){
		DataQueryDatasource dataQueryDatasource = DataQueryDatasource.create();
		DataQueryDatasourceUpdateCommandToDataQueryDatasourceMapping.instance.fillDataQueryDatasourceByDataQueryDatasourceUpdateCommand(dataQueryDatasource, dataQueryDatasourceUpdateCommand);
		return dataQueryDatasource;
	}

	@Mapper
	interface DataQueryDatasourceUpdateCommandToDataQueryDatasourceMapping{
		DataQueryDatasourceUpdateCommandToDataQueryDatasourceMapping instance = Mappers.getMapper(DataQueryDatasourceUpdateCommandToDataQueryDatasourceMapping.class );

		default DataQueryDatasourceId map(Long id){
			if (id == null) {
				return null;
			}
			return DataQueryDatasourceId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDatasource
		 * @param dataQueryDatasourceUpdateCommand
		 */
		void fillDataQueryDatasourceByDataQueryDatasourceUpdateCommand(@MappingTarget DataQueryDatasource dataQueryDatasource, DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceGateway
	 */
	@Autowired
	public void setDataQueryDatasourceGateway(DataQueryDatasourceGateway dataQueryDatasourceGateway) {
		this.dataQueryDatasourceGateway = dataQueryDatasourceGateway;
	}
}
