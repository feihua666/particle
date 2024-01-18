package com.particle.dataquery.app.datasource.api.impl;

import cn.hutool.core.util.BooleanUtil;
import com.particle.dataquery.app.dataapi.executor.DataQueryDataApiCreateCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiCreateCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiDeleteCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiAdaptType;
import com.particle.dataquery.domain.gateway.DataQueryDictGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 数据查询数据源接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Transactional
@Service
@CatchAndLog
public class DataQueryDatasourceApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDatasourceApiApplicationService {

	private DataQueryDatasourceApiCreateCommandExecutor dataQueryDatasourceApiCreateCommandExecutor;

	private DataQueryDatasourceApiDeleteCommandExecutor dataQueryDatasourceApiDeleteCommandExecutor;

	private DataQueryDatasourceApiUpdateCommandExecutor dataQueryDatasourceApiUpdateCommandExecutor;

	private DataQueryDataApiCreateCommandExecutor dataQueryDataApiCreateCommandExecutor;

	private DataQueryDictGateway dataQueryDictGateway;

	@Override
	public SingleResponse<DataQueryDatasourceApiVO> create(DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand) {
		SingleResponse<DataQueryDatasourceApiVO> execute = dataQueryDatasourceApiCreateCommandExecutor.execute(dataQueryDatasourceApiCreateCommand);

		if (execute.isSuccess()) {
			if (BooleanUtil.isTrue(dataQueryDatasourceApiCreateCommand.getIsAddSingleDirect())) {
				Long singleDirectDictId = dataQueryDictGateway.getDictIdByGroupCodeAndItemValue(DataQueryDataApiAdaptType.Group.dataquery_data_api_adapt_type.groupCode(), DataQueryDataApiAdaptType.single_direct.itemValue());
				Long dataQueryDatasourceApiId = execute.getData().getId();

				DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand = new DataQueryDataApiCreateCommand();
				dataQueryDataApiCreateCommand.setUrl(dataQueryDatasourceApiCreateCommand.getDataQueryDataApiUrl());
				dataQueryDataApiCreateCommand.setName(dataQueryDatasourceApiCreateCommand.getName());
				dataQueryDataApiCreateCommand.setDataQueryDatasourceApiId(dataQueryDatasourceApiId);
				dataQueryDataApiCreateCommand.setAdaptTypeDictId(singleDirectDictId);

				dataQueryDataApiCreateCommandExecutor.execute(dataQueryDataApiCreateCommand);
			}
		}


		return execute;
	}

	@Override
	public SingleResponse<DataQueryDatasourceApiVO> copy(IdCommand copyCommand) {
		return dataQueryDatasourceApiCreateCommandExecutor.copy(copyCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceApiVO> delete(IdCommand deleteCommand) {
		return dataQueryDatasourceApiDeleteCommandExecutor.execute(deleteCommand);
	}
	@Override
	public SingleResponse<String> deleteCache(IdCommand deleteCommand) {
		return dataQueryDatasourceApiDeleteCommandExecutor.deleteCache(deleteCommand);
	}
	@Override
	public SingleResponse<String> refreshCache(IdCommand deleteCommand) {
		return dataQueryDatasourceApiDeleteCommandExecutor.refreshCache(deleteCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceApiVO> update(DataQueryDatasourceApiUpdateCommand dataQueryDatasourceApiUpdateCommand) {
		return dataQueryDatasourceApiUpdateCommandExecutor.execute(dataQueryDatasourceApiUpdateCommand);
	}

	@Autowired
	public void setDataQueryDatasourceApiCreateCommandExecutor(DataQueryDatasourceApiCreateCommandExecutor dataQueryDatasourceApiCreateCommandExecutor) {
		this.dataQueryDatasourceApiCreateCommandExecutor = dataQueryDatasourceApiCreateCommandExecutor;
	}

	@Autowired
	public void setDataQueryDatasourceApiDeleteCommandExecutor(DataQueryDatasourceApiDeleteCommandExecutor dataQueryDatasourceApiDeleteCommandExecutor) {
		this.dataQueryDatasourceApiDeleteCommandExecutor = dataQueryDatasourceApiDeleteCommandExecutor;
	}
	@Autowired
	public void setDataQueryDatasourceApiUpdateCommandExecutor(DataQueryDatasourceApiUpdateCommandExecutor dataQueryDatasourceApiUpdateCommandExecutor) {
		this.dataQueryDatasourceApiUpdateCommandExecutor = dataQueryDatasourceApiUpdateCommandExecutor;
	}
	@Autowired
	public void setDataQueryDataApiCreateCommandExecutor(DataQueryDataApiCreateCommandExecutor dataQueryDataApiCreateCommandExecutor) {
		this.dataQueryDataApiCreateCommandExecutor = dataQueryDataApiCreateCommandExecutor;
	}
	@Autowired
	public void setDataQueryDictGateway(DataQueryDictGateway dataQueryDictGateway) {
		this.dataQueryDictGateway = dataQueryDictGateway;
	}
}
