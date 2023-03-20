package com.particle.dataquery.app.datasource.api.impl;

import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiCreateCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiDeleteCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceApiUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiUpdateCommand;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApiApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceApiCreateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
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


	@Override
	public SingleResponse<DataQueryDatasourceApiVO> create(DataQueryDatasourceApiCreateCommand dataQueryDatasourceApiCreateCommand) {
		return dataQueryDatasourceApiCreateCommandExecutor.execute(dataQueryDatasourceApiCreateCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceApiVO> delete(IdCommand deleteCommand) {
		return dataQueryDatasourceApiDeleteCommandExecutor.execute(deleteCommand);
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

}
