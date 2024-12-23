package com.particle.dataconstraint.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.app.executor.DataScopeCreateCommandExecutor;
import com.particle.dataconstraint.app.executor.DataScopeDeleteCommandExecutor;
import com.particle.dataconstraint.app.executor.DataScopeUpdateCommandExecutor;
import com.particle.dataconstraint.client.api.IDataScopeApplicationService;
import com.particle.dataconstraint.client.dto.command.DataScopeCreateCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 数据范围 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Transactional
@Service
@CatchAndLog
public class DataScopeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataScopeApplicationService {

	private DataScopeCreateCommandExecutor dataScopeCreateCommandExecutor;

	private DataScopeDeleteCommandExecutor dataScopeDeleteCommandExecutor;

	private DataScopeUpdateCommandExecutor dataScopeUpdateCommandExecutor;


	@Override
	public SingleResponse<DataScopeVO> create(DataScopeCreateCommand dataScopeCreateCommand) {
		return dataScopeCreateCommandExecutor.execute(dataScopeCreateCommand);
	}

	@Override
	public SingleResponse<DataScopeVO> delete(IdCommand deleteCommand) {
		return dataScopeDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataScopeVO> update(DataScopeUpdateCommand dataScopeUpdateCommand) {
		return dataScopeUpdateCommandExecutor.execute(dataScopeUpdateCommand);
	}

	@Autowired
	public void setDataScopeCreateCommandExecutor(DataScopeCreateCommandExecutor dataScopeCreateCommandExecutor) {
		this.dataScopeCreateCommandExecutor = dataScopeCreateCommandExecutor;
	}

	@Autowired
	public void setDataScopeDeleteCommandExecutor(DataScopeDeleteCommandExecutor dataScopeDeleteCommandExecutor) {
		this.dataScopeDeleteCommandExecutor = dataScopeDeleteCommandExecutor;
	}
	@Autowired
	public void setDataScopeUpdateCommandExecutor(DataScopeUpdateCommandExecutor dataScopeUpdateCommandExecutor) {
		this.dataScopeUpdateCommandExecutor = dataScopeUpdateCommandExecutor;
	}

}
