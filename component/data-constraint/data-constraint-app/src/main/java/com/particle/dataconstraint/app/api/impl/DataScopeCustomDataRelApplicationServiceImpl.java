package com.particle.dataconstraint.app.api.impl;

import com.particle.dataconstraint.app.executor.DataScopeCustomDataRelCommandExecutor;
import com.particle.dataconstraint.app.executor.DataScopeCustomDataRelCreateCommandExecutor;
import com.particle.dataconstraint.app.executor.DataScopeCustomDataRelDeleteCommandExecutor;
import com.particle.dataconstraint.app.executor.DataScopeCustomDataRelUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeAssignCustomDataCommand;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelUpdateCommand;
import com.particle.dataconstraint.client.api.IDataScopeCustomDataRelApplicationService;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.global.dto.response.Response;
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
 * 数据范围自定义数据关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Transactional
@Service
@CatchAndLog
public class DataScopeCustomDataRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataScopeCustomDataRelApplicationService {

	private DataScopeCustomDataRelCreateCommandExecutor dataScopeCustomDataRelCreateCommandExecutor;

	private DataScopeCustomDataRelDeleteCommandExecutor dataScopeCustomDataRelDeleteCommandExecutor;

	private DataScopeCustomDataRelUpdateCommandExecutor dataScopeCustomDataRelUpdateCommandExecutor;

	private DataScopeCustomDataRelCommandExecutor dataScopeCustomDataRelCommandExecutor;

	@Override
	public SingleResponse<DataScopeCustomDataRelVO> create(DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand) {
		return dataScopeCustomDataRelCreateCommandExecutor.execute(dataScopeCustomDataRelCreateCommand);
	}

	@Override
	public SingleResponse<DataScopeCustomDataRelVO> delete(IdCommand deleteCommand) {
		return dataScopeCustomDataRelDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataScopeCustomDataRelVO> update(DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand) {
		return dataScopeCustomDataRelUpdateCommandExecutor.execute(dataScopeCustomDataRelUpdateCommand);
	}

	@Override
	public Response dataScopeAssignCustomData(DataScopeAssignCustomDataCommand cf) {
		return dataScopeCustomDataRelCommandExecutor.dataScopeAssignCustomData(cf);
	}

	@Override
	public Response deleteByDataScopeId(IdCommand dataScopeIdCommand) {
		return dataScopeCustomDataRelDeleteCommandExecutor.deleteByDataScopeId(dataScopeIdCommand);
	}

	@Autowired
	public void setDataScopeCustomDataRelCreateCommandExecutor(DataScopeCustomDataRelCreateCommandExecutor dataScopeCustomDataRelCreateCommandExecutor) {
		this.dataScopeCustomDataRelCreateCommandExecutor = dataScopeCustomDataRelCreateCommandExecutor;
	}

	@Autowired
	public void setDataScopeCustomDataRelDeleteCommandExecutor(DataScopeCustomDataRelDeleteCommandExecutor dataScopeCustomDataRelDeleteCommandExecutor) {
		this.dataScopeCustomDataRelDeleteCommandExecutor = dataScopeCustomDataRelDeleteCommandExecutor;
	}
	@Autowired
	public void setDataScopeCustomDataRelUpdateCommandExecutor(DataScopeCustomDataRelUpdateCommandExecutor dataScopeCustomDataRelUpdateCommandExecutor) {
		this.dataScopeCustomDataRelUpdateCommandExecutor = dataScopeCustomDataRelUpdateCommandExecutor;
	}
	@Autowired
	public void setDataScopeCustomDataRelCommandExecutor(DataScopeCustomDataRelCommandExecutor dataScopeCustomDataRelCommandExecutor) {
		this.dataScopeCustomDataRelCommandExecutor = dataScopeCustomDataRelCommandExecutor;
	}
}
