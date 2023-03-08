package com.particle.dataquery.app.provider.executor;

import com.particle.dataquery.app.provider.structmapping.DataQueryProviderAppStructMapping;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderCreateCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.dataquery.domain.provider.DataQueryProvider;
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
 * 数据查询供应商 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Component
@Validated
public class DataQueryProviderCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataQueryProviderGateway dataQueryProviderGateway;

	/**
	 * 执行数据查询供应商添加指令
	 * @param dataQueryProviderCreateCommand
	 * @return
	 */
	public SingleResponse<DataQueryProviderVO> execute(@Valid DataQueryProviderCreateCommand dataQueryProviderCreateCommand) {
		DataQueryProvider dataQueryProvider = createByDataQueryProviderCreateCommand(dataQueryProviderCreateCommand);
		dataQueryProvider.setAddControl(dataQueryProviderCreateCommand);
		boolean save = dataQueryProviderGateway.save(dataQueryProvider);
		if (save) {
			return SingleResponse.of(DataQueryProviderAppStructMapping.instance.toDataQueryProviderVO(dataQueryProvider));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据查询供应商创建指令创建数据查询供应商模型
	 * @param dataQueryProviderCreateCommand
	 * @return
	 */
	private DataQueryProvider createByDataQueryProviderCreateCommand(DataQueryProviderCreateCommand dataQueryProviderCreateCommand){
		DataQueryProvider dataQueryProvider = DataQueryProvider.create();
		DataQueryProviderCreateCommandToDataQueryProviderMapping.instance.fillDataQueryProviderByDataQueryProviderCreateCommand(dataQueryProvider, dataQueryProviderCreateCommand);
		return dataQueryProvider;
	}

	@Mapper
	interface  DataQueryProviderCreateCommandToDataQueryProviderMapping{
		DataQueryProviderCreateCommandToDataQueryProviderMapping instance = Mappers.getMapper( DataQueryProviderCreateCommandToDataQueryProviderMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataQueryProvider
		 * @param dataQueryProviderCreateCommand
		 */
		void fillDataQueryProviderByDataQueryProviderCreateCommand(@MappingTarget DataQueryProvider dataQueryProvider, DataQueryProviderCreateCommand dataQueryProviderCreateCommand);
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
