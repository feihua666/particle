package com.particle.dataquery.app.datasource.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceCreateCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceDeleteCommandExecutor;
import com.particle.dataquery.app.datasource.executor.DataQueryDatasourceUpdateCommandExecutor;
import com.particle.dataquery.client.datasource.api.IDataQueryDatasourceApplicationService;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceCreateCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceReloadCommand;
import com.particle.dataquery.client.datasource.dto.command.DataQueryDatasourceUpdateCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 数据查询数据源 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Transactional
@Service
@CatchAndLog
public class DataQueryDatasourceApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDatasourceApplicationService {

	private DataQueryDatasourceCreateCommandExecutor dataQueryDatasourceCreateCommandExecutor;

	private DataQueryDatasourceDeleteCommandExecutor dataQueryDatasourceDeleteCommandExecutor;

	private DataQueryDatasourceUpdateCommandExecutor dataQueryDatasourceUpdateCommandExecutor;


	@Override
	public SingleResponse<DataQueryDatasourceVO> create(DataQueryDatasourceCreateCommand dataQueryDatasourceCreateCommand) {
		return dataQueryDatasourceCreateCommandExecutor.execute(dataQueryDatasourceCreateCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceVO> delete(IdCommand deleteCommand) {
		return dataQueryDatasourceDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceVO> update(DataQueryDatasourceUpdateCommand dataQueryDatasourceUpdateCommand) {
		return dataQueryDatasourceUpdateCommandExecutor.execute(dataQueryDatasourceUpdateCommand);
	}

	@Override
	public SingleResponse<String> reload(DataQueryDatasourceReloadCommand dataQueryDatasourceReloadCommand) {
		return dataQueryDatasourceUpdateCommandExecutor.reload(dataQueryDatasourceReloadCommand);
	}

	@Override
	public SingleResponse<DataQueryDatasourceVO> copy(IdCommand copyCommand) {
		return dataQueryDatasourceCreateCommandExecutor.copy(copyCommand);
	}

	@Autowired
	public void setDataQueryDatasourceCreateCommandExecutor(DataQueryDatasourceCreateCommandExecutor dataQueryDatasourceCreateCommandExecutor) {
		this.dataQueryDatasourceCreateCommandExecutor = dataQueryDatasourceCreateCommandExecutor;
	}

	@Autowired
	public void setDataQueryDatasourceDeleteCommandExecutor(DataQueryDatasourceDeleteCommandExecutor dataQueryDatasourceDeleteCommandExecutor) {
		this.dataQueryDatasourceDeleteCommandExecutor = dataQueryDatasourceDeleteCommandExecutor;
	}
	@Autowired
	public void setDataQueryDatasourceUpdateCommandExecutor(DataQueryDatasourceUpdateCommandExecutor dataQueryDatasourceUpdateCommandExecutor) {
		this.dataQueryDatasourceUpdateCommandExecutor = dataQueryDatasourceUpdateCommandExecutor;
	}

}
