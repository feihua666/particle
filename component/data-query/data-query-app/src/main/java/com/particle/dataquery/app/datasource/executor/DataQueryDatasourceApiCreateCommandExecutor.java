package com.particle.dataquery.app.datasource.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
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
 * 数据查询数据源接口 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Component
@Validated
public class DataQueryDatasourceApiCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway;


	private IDataQueryDatasourceApiService iDataQueryDatasourceApiService;

	/**
	 * 执行数据查询数据源接口添加指令
	 * @param dataQueryDatasourceApiCreateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> execute(@Valid DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand) {
		DataQueryDatasourceApi dataQueryDatasourceApi = createByDataQueryDatasourceApiCreateCommand(dataQueryDatasourceApiCreateCommand);
		dataQueryDatasourceApi.setAddControl(dataQueryDatasourceApiCreateCommand);
		boolean save = dataQueryDatasourceApiGateway.save(dataQueryDatasourceApi);
		if (save) {
			return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.toDataQueryDatasourceApiVO(dataQueryDatasourceApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 复制一个新数据
	 * @param idCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> copy(@Valid IdCommand idCommand) {
		DataQueryDatasourceApiDO copy = iDataQueryDatasourceApiService.copy(idCommand.getId(), item -> {
			String copySuffix = "Copy";
			if (StrUtil.isNotEmpty(item.getCode())) {
				item.setCode(item.getCode() + copySuffix);
			}else {
				item.setCode(null);
			}
			item.setName(item.getName() + copySuffix);

			return item;
		});

		if (copy == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
		}

		return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.dataQueryDatasourceApiDOToDataQueryDatasourceApiVO(copy));
	}
	/**
	 * 根据数据查询数据源接口创建指令创建数据查询数据源接口模型
	 * @param dataQueryDatasourceApiCreateCommand
	 * @return
	 */
	private DataQueryDatasourceApi createByDataQueryDatasourceApiCreateCommand(DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand){
		DataQueryDatasourceApi dataQueryDatasourceApi = DataQueryDatasourceApi.create();
		DataQueryDatasourceApiCreateCommandToDataQueryDatasourceApiMapping.instance.fillDataQueryDatasourceApiByDataQueryDatasourceApiCreateCommand(dataQueryDatasourceApi, dataQueryDatasourceApiCreateCommand);
		return dataQueryDatasourceApi;
	}

	@Mapper
	interface  DataQueryDatasourceApiCreateCommandToDataQueryDatasourceApiMapping{
		DataQueryDatasourceApiCreateCommandToDataQueryDatasourceApiMapping instance = Mappers.getMapper( DataQueryDatasourceApiCreateCommandToDataQueryDatasourceApiMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDatasourceApi
		 * @param dataQueryDatasourceApiCreateCommand
		 */
		void fillDataQueryDatasourceApiByDataQueryDatasourceApiCreateCommand(@MappingTarget DataQueryDatasourceApi dataQueryDatasourceApi, DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceApiGateway
	 */
	@Autowired
	public void setDataQueryDatasourceApiGateway(DataQueryDatasourceApiGateway dataQueryDatasourceApiGateway) {
		this.dataQueryDatasourceApiGateway = dataQueryDatasourceApiGateway;
	}

	@Autowired
	public void setiDataQueryDatasourceApiService(IDataQueryDatasourceApiService iDataQueryDatasourceApiService) {
		this.iDataQueryDatasourceApiService = iDataQueryDatasourceApiService;
	}
}
