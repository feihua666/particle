package com.particle.dataquery.app.dataapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.dataapi.structmapping.DataQueryDataApiAppStructMapping;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiUpdateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.DataQueryDataApiId;
import com.particle.dataquery.domain.dataapi.gateway.DataQueryDataApiGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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

		// 如果由未发布改为发布，必须测试通过
		if (dataQueryDataApiUpdateCommand.getIsPublished() != null && dataQueryDataApiUpdateCommand.getIsPublished()) {
			DataQueryDataApi dataQueryDataApi = dataQueryDataApiGateway.getById(DataQueryDataApiId.of(dataQueryDataApiUpdateCommand.getId()));
			// 以前状态是false
			if (!dataQueryDataApi.getIsPublished()) {
				Assert.isTrue(dataQueryDataApi.getIsTestPassed(),"测试通过后才能发布数据查询接口");
			}
		}

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
	 * dev合并到master
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryDataApiVO> devMergeToMaster(@Valid IdCommand deleteCommand) {
		DataQueryDataApiId dataQueryDataApiId = DataQueryDataApiId.of(deleteCommand.getId());
		DataQueryDataApi dataQueryDataApi = dataQueryDataApiGateway.getById(dataQueryDataApiId);
		Assert.notNull(dataQueryDataApi, ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		Assert.isTrue(dataQueryDataApi.getIsTestPassed(),"测试通过后才能提交至master");
		Assert.notNull(dataQueryDataApi.getMasterId(), "master不存在");

		DataQueryDataApiId dataQueryDataApiIdNew = DataQueryDataApiId.of(dataQueryDataApi.getMasterId());
		dataQueryDataApi.changeIdTo(dataQueryDataApiIdNew);
		dataQueryDataApi.devMergeToMaster(dataQueryDataApi.getUrl(),dataQueryDataApi.getName());

		boolean save = dataQueryDataApiGateway.save(dataQueryDataApi);
		if (save) {
			// merge完成后删除本dev数据
			dataQueryDataApiGateway.delete(dataQueryDataApiId);
			return SingleResponse.of(DataQueryDataApiAppStructMapping.instance.toDataQueryDataApiVO(dataQueryDataApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据数据查询数据接口更新指令创建数据查询数据接口模型
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
