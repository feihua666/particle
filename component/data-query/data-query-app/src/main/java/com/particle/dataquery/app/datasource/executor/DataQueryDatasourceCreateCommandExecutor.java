package com.particle.dataquery.app.datasource.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.dataquery.domain.datasource.DataQueryDatasource;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceGateway;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
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
 * 数据查询数据源 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Component
@Validated
public class DataQueryDatasourceCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryDatasourceGateway dataQueryDatasourceGateway;
	private IDataQueryDatasourceService iDataQueryDatasourceService;

	/**
	 * 执行数据查询数据源添加指令
	 * @param dataQueryDatasourceCreateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> execute(@Valid DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand) {
		DataQueryDatasource dataQueryDatasource = createByDataQueryDatasourceCreateCommand(dataQueryDatasourceCreateCommand);
		dataQueryDatasource.setAddControl(dataQueryDatasourceCreateCommand);
		boolean save = dataQueryDatasourceGateway.save(dataQueryDatasource);
		if (save) {
			return SingleResponse.of(DataQueryDatasourceAppStructMapping.instance.toDataQueryDatasourceVO(dataQueryDatasource));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 复制一个新数据
	 * @param idCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceVO> copy(@Valid IdCommand idCommand) {
		DataQueryDatasourceDO copy = iDataQueryDatasourceService.copy(idCommand.getId(), item -> {
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

		return SingleResponse.of(DataQueryDatasourceAppStructMapping.instance.dataQueryDatasourceDOToDataQueryDatasourceVO(copy));
	}

	/**
	 * 根据数据查询数据源创建指令创建数据查询数据源模型
	 * @param dataQueryDatasourceCreateCommand
	 * @return
	 */
	private DataQueryDatasource createByDataQueryDatasourceCreateCommand(DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand){
		DataQueryDatasource dataQueryDatasource = DataQueryDatasource.create();
		DataQueryDatasourceCreateCommandToDataQueryDatasourceMapping.instance.fillDataQueryDatasourceByDataQueryDatasourceCreateCommand(dataQueryDatasource, dataQueryDatasourceCreateCommand);
		return dataQueryDatasource;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataQueryDatasourceCreateCommandToDataQueryDatasourceMapping{
		DataQueryDatasourceCreateCommandToDataQueryDatasourceMapping instance = Mappers.getMapper( DataQueryDatasourceCreateCommandToDataQueryDatasourceMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryDatasource
		 * @param dataQueryDatasourceCreateCommand
		 */
		void fillDataQueryDatasourceByDataQueryDatasourceCreateCommand(@MappingTarget DataQueryDatasource dataQueryDatasource, DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryDatasourceGateway
	 */
	@Autowired
	public void setDataQueryDatasourceGateway(DataQueryDatasourceGateway dataQueryDatasourceGateway) {
		this.dataQueryDatasourceGateway = dataQueryDatasourceGateway;
	}
	@Autowired
	public void setiDataQueryDatasourceService(IDataQueryDatasourceService iDataQueryDatasourceService) {
		this.iDataQueryDatasourceService = iDataQueryDatasourceService;
	}
}
