package com.particle.dataquery.app.datasource.executor;

import cn.hutool.core.net.NetUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.structmapping.DataQueryDatasourceApiAppStructMapping;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApi;
import com.particle.dataquery.domain.datasource.DataQueryDatasourceApiId;
import com.particle.dataquery.domain.datasource.gateway.DataQueryDatasourceApiGateway;
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
	private DataApiQueryGateway dataApiQueryGateway;
	/**
	 * 执行 数据查询数据源接口 更新指令
	 * @param dataQueryDatasourceApiUpdateCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> execute(@Valid DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand) {

		boolean changeTestPassedToFalse = true;

		// 如果由未发布改为发布，必须测试通过
		if (dataQueryDatasourceApiUpdateCommand.getIsPublished() != null && dataQueryDatasourceApiUpdateCommand.getIsPublished()) {
			DataQueryDatasourceApi queryDatasourceApiDb = dataQueryDatasourceApiGateway.getById(DataQueryDatasourceApiId.of(dataQueryDatasourceApiUpdateCommand.getId()));
			// 以前状态是false
			if (!queryDatasourceApiDb.getIsPublished()) {
				Assert.isTrue(queryDatasourceApiDb.getIsTestPassed(),"测试通过后才能发布数据源接口");
				changeTestPassedToFalse = false;
			}
		}


		DataQueryDatasourceApi dataQueryDatasourceApi = createByDataQueryDatasourceApiUpdateCommand(dataQueryDatasourceApiUpdateCommand);

		// 变为测试不通过
		if (changeTestPassedToFalse) {
			dataQueryDatasourceApi.changeTestNotPassed();
		}

		dataQueryDatasourceApi.setUpdateControl(dataQueryDatasourceApiUpdateCommand);
		boolean save = dataQueryDatasourceApiGateway.save(dataQueryDatasourceApi);
		if (save) {
			return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.toDataQueryDatasourceApiVO(dataQueryDatasourceApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 刷新缓存
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<String> refreshCache(@Valid IdCommand deleteCommand) {
		DataQueryDatasourceApiId dataQueryDatasourceApiId = DataQueryDatasourceApiId.of(deleteCommand.getId());
		DataQueryDatasourceApi byId = dataQueryDatasourceApiGateway.getById(dataQueryDatasourceApiId);

		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		dataApiQueryGateway.refreshCache(dataQueryDatasourceApiId);
		return SingleResponse.of(NetUtil.getLocalhostStr());
	}


	/**
	 * dev合并到主分支
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DataQueryDatasourceApiVO> devMergeToMaster(@Valid IdCommand deleteCommand) {
		DataQueryDatasourceApiId devDataQueryDatasourceApiId = DataQueryDatasourceApiId.of(deleteCommand.getId());
		DataQueryDatasourceApi devDataQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(devDataQueryDatasourceApiId);

		Assert.notNull(devDataQueryDatasourceApi,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		Assert.isTrue(devDataQueryDatasourceApi.getIsTestPassed(),"测试通过后才能提交至master");
		Assert.notNull(devDataQueryDatasourceApi.getMasterId(), "master不存在");

		DataQueryDatasourceApiId masterDataQueryDatasourceApiIdNew = DataQueryDatasourceApiId.of(devDataQueryDatasourceApi.getMasterId());
		devDataQueryDatasourceApi.changeIdTo(masterDataQueryDatasourceApiIdNew);
		devDataQueryDatasourceApi.devMergeToMaster(devDataQueryDatasourceApi.getCode(),devDataQueryDatasourceApi.getName());


		// 获取数据版本以更新
		DataQueryDatasourceApi masterQueryDatasourceApi = dataQueryDatasourceApiGateway.getById(masterDataQueryDatasourceApiIdNew);
		devDataQueryDatasourceApi.changeVersionTo(masterQueryDatasourceApi.getVersion());

		boolean save = dataQueryDatasourceApiGateway.save(devDataQueryDatasourceApi);
		if (save) {
			// merge完成后删除本dev数据
			dataQueryDatasourceApiGateway.delete(devDataQueryDatasourceApiId,deleteCommand);
			return SingleResponse.of(DataQueryDatasourceApiAppStructMapping.instance.toDataQueryDatasourceApiVO(devDataQueryDatasourceApi));
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
	@Autowired
	public void setDataApiQueryGateway(DataApiQueryGateway dataApiQueryGateway) {
		this.dataApiQueryGateway = dataApiQueryGateway;
	}
}
