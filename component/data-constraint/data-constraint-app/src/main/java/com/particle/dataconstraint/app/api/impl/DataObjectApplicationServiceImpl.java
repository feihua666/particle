package com.particle.dataconstraint.app.api.impl;

import com.particle.dataconstraint.app.executor.DataObjectCreateCommandExecutor;
import com.particle.dataconstraint.app.executor.DataObjectDeleteCommandExecutor;
import com.particle.dataconstraint.app.executor.DataObjectUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.client.dto.command.DataObjectUpdateCommand;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import com.particle.dataconstraint.client.dto.command.DataObjectCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
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
 * 数据对象 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Transactional
@Service
@CatchAndLog
public class DataObjectApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataObjectApplicationService {

	private DataObjectCreateCommandExecutor dataObjectCreateCommandExecutor;

	private DataObjectDeleteCommandExecutor dataObjectDeleteCommandExecutor;

	private DataObjectUpdateCommandExecutor dataObjectUpdateCommandExecutor;


	@Override
	public SingleResponse<DataObjectVO> create(DataObjectCreateCommand dataObjectCreateCommand) {
		return dataObjectCreateCommandExecutor.execute(dataObjectCreateCommand);
	}

	@Override
	public SingleResponse<DataObjectVO> delete(IdCommand deleteCommand) {
		return dataObjectDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataObjectVO> update(DataObjectUpdateCommand dataObjectUpdateCommand) {
		return dataObjectUpdateCommandExecutor.execute(dataObjectUpdateCommand);
	}

	@Autowired
	public void setDataObjectCreateCommandExecutor(DataObjectCreateCommandExecutor dataObjectCreateCommandExecutor) {
		this.dataObjectCreateCommandExecutor = dataObjectCreateCommandExecutor;
	}

	@Autowired
	public void setDataObjectDeleteCommandExecutor(DataObjectDeleteCommandExecutor dataObjectDeleteCommandExecutor) {
		this.dataObjectDeleteCommandExecutor = dataObjectDeleteCommandExecutor;
	}
	@Autowired
	public void setDataObjectUpdateCommandExecutor(DataObjectUpdateCommandExecutor dataObjectUpdateCommandExecutor) {
		this.dataObjectUpdateCommandExecutor = dataObjectUpdateCommandExecutor;
	}

}
