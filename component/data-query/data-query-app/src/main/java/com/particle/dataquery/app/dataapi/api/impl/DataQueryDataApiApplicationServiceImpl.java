package com.particle.dataquery.app.dataapi.api.impl;

import com.particle.dataquery.app.dataapi.executor.DataQueryDataApiCreateCommandExecutor;
import com.particle.dataquery.app.dataapi.executor.DataQueryDataApiDeleteCommandExecutor;
import com.particle.dataquery.app.dataapi.executor.DataQueryDataApiUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiUpdateCommand;
import com.particle.dataquery.client.dataapi.api.IDataQueryDataApiApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.DataQueryDataApiCreateCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
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
 * 数据查询数据接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Transactional
@Service
@CatchAndLog
public class DataQueryDataApiApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDataApiApplicationService {

	private DataQueryDataApiCreateCommandExecutor dataQueryDataApiCreateCommandExecutor;

	private DataQueryDataApiDeleteCommandExecutor dataQueryDataApiDeleteCommandExecutor;

	private DataQueryDataApiUpdateCommandExecutor dataQueryDataApiUpdateCommandExecutor;


	@Override
	public SingleResponse<DataQueryDataApiVO> create(DataQueryDataApiCreateCommand dataQueryDataApiCreateCommand) {
		return dataQueryDataApiCreateCommandExecutor.execute(dataQueryDataApiCreateCommand);
	}

	@Override
	public SingleResponse<DataQueryDataApiVO> delete(IdCommand deleteCommand) {
		return dataQueryDataApiDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataQueryDataApiVO> update(DataQueryDataApiUpdateCommand dataQueryDataApiUpdateCommand) {
		return dataQueryDataApiUpdateCommandExecutor.execute(dataQueryDataApiUpdateCommand);
	}

	@Autowired
	public void setDataQueryDataApiCreateCommandExecutor(DataQueryDataApiCreateCommandExecutor dataQueryDataApiCreateCommandExecutor) {
		this.dataQueryDataApiCreateCommandExecutor = dataQueryDataApiCreateCommandExecutor;
	}

	@Autowired
	public void setDataQueryDataApiDeleteCommandExecutor(DataQueryDataApiDeleteCommandExecutor dataQueryDataApiDeleteCommandExecutor) {
		this.dataQueryDataApiDeleteCommandExecutor = dataQueryDataApiDeleteCommandExecutor;
	}
	@Autowired
	public void setDataQueryDataApiUpdateCommandExecutor(DataQueryDataApiUpdateCommandExecutor dataQueryDataApiUpdateCommandExecutor) {
		this.dataQueryDataApiUpdateCommandExecutor = dataQueryDataApiUpdateCommandExecutor;
	}

}