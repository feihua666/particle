package com.particle.lowcode.app.generator.api.impl;

import com.particle.lowcode.app.generator.executor.LowcodeDatasourceCreateCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeDatasourceDeleteCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeDatasourceUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceUpdateCommand;
import com.particle.lowcode.client.generator.api.ILowcodeDatasourceApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeDatasourceCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
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
 * 低代码数据源 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Transactional
@Service
@CatchAndLog
public class LowcodeDatasourceApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeDatasourceApplicationService {

	private LowcodeDatasourceCreateCommandExecutor lowcodeDatasourceCreateCommandExecutor;

	private LowcodeDatasourceDeleteCommandExecutor lowcodeDatasourceDeleteCommandExecutor;

	private LowcodeDatasourceUpdateCommandExecutor lowcodeDatasourceUpdateCommandExecutor;


	@Override
	public SingleResponse<LowcodeDatasourceVO> create(LowcodeDatasourceCreateCommand lowcodeDatasourceCreateCommand) {
		return lowcodeDatasourceCreateCommandExecutor.execute(lowcodeDatasourceCreateCommand);
	}

	@Override
	public SingleResponse<LowcodeDatasourceVO> delete(IdCommand deleteCommand) {
		return lowcodeDatasourceDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<LowcodeDatasourceVO> update(LowcodeDatasourceUpdateCommand lowcodeDatasourceUpdateCommand) {
		return lowcodeDatasourceUpdateCommandExecutor.execute(lowcodeDatasourceUpdateCommand);
	}

	@Autowired
	public void setLowcodeDatasourceCreateCommandExecutor(LowcodeDatasourceCreateCommandExecutor lowcodeDatasourceCreateCommandExecutor) {
		this.lowcodeDatasourceCreateCommandExecutor = lowcodeDatasourceCreateCommandExecutor;
	}

	@Autowired
	public void setLowcodeDatasourceDeleteCommandExecutor(LowcodeDatasourceDeleteCommandExecutor lowcodeDatasourceDeleteCommandExecutor) {
		this.lowcodeDatasourceDeleteCommandExecutor = lowcodeDatasourceDeleteCommandExecutor;
	}
	@Autowired
	public void setLowcodeDatasourceUpdateCommandExecutor(LowcodeDatasourceUpdateCommandExecutor lowcodeDatasourceUpdateCommandExecutor) {
		this.lowcodeDatasourceUpdateCommandExecutor = lowcodeDatasourceUpdateCommandExecutor;
	}

}
