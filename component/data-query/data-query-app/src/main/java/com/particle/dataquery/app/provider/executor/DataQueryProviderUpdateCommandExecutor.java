package com.particle.dataquery.app.provider.executor;

import com.particle.dataquery.app.provider.structmapping.DataQueryProviderAppStructMapping;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderUpdateCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.dataquery.domain.provider.DataQueryProvider;
import com.particle.dataquery.domain.provider.DataQueryProviderId;
import com.particle.dataquery.domain.provider.gateway.DataQueryProviderGateway;
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
 * 数据查询供应商 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataQueryProviderUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryProviderGateway dataQueryProviderGateway;

	/**
	 * 执行 数据查询供应商 更新指令
	 * @param dataQueryProviderUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryProviderVO> execute(@Valid DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand) {
		DataQueryProvider dataQueryProvider = createByDataQueryProviderUpdateCommand(dataQueryProviderUpdateCommand);
		dataQueryProvider.setUpdateControl(dataQueryProviderUpdateCommand);
		boolean save = dataQueryProviderGateway.save(dataQueryProvider);
		if (save) {
			return SingleResponse.of(DataQueryProviderAppStructMapping.instance.toDataQueryProviderVO(dataQueryProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据查询供应商更新指令创建数据查询供应商模型
	 * @param dataQueryProviderUpdateCommand
	 * @return
	 */
	private DataQueryProvider createByDataQueryProviderUpdateCommand(DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand){
		DataQueryProvider dataQueryProvider = DataQueryProvider.create();
		DataQueryProviderUpdateCommandToDataQueryProviderMapping.instance.fillDataQueryProviderByDataQueryProviderUpdateCommand(dataQueryProvider, dataQueryProviderUpdateCommand);
		return dataQueryProvider;
	}

	@Mapper
	interface DataQueryProviderUpdateCommandToDataQueryProviderMapping{
		DataQueryProviderUpdateCommandToDataQueryProviderMapping instance = Mappers.getMapper(DataQueryProviderUpdateCommandToDataQueryProviderMapping.class );

		default DataQueryProviderId map(Long id){
			if (id == null) {
				return null;
			}
			return DataQueryProviderId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryProvider
		 * @param dataQueryProviderUpdateCommand
		 */
		void fillDataQueryProviderByDataQueryProviderUpdateCommand(@MappingTarget DataQueryProvider dataQueryProvider, DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataQueryProviderGateway
	 */
	@Autowired
	public void setDataQueryProviderGateway(DataQueryProviderGateway dataQueryProviderGateway) {
		this.dataQueryProviderGateway = dataQueryProviderGateway;
	}
}
