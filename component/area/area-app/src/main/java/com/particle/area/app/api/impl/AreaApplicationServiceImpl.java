package com.particle.area.app.api.impl;

import com.particle.area.app.executor.AreaCreateCommandExecutor;
import com.particle.area.app.executor.AreaDeleteCommandExecutor;
import com.particle.area.app.executor.AreaUpdateCommandExecutor;
import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.command.AreaDeleteCommand;
import com.particle.area.client.dto.command.AreaUpdateCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 区域 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Service
@CatchAndLog
@Transactional
public class AreaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAreaApplicationService {

	private AreaCreateCommandExecutor areaCreateCommandExecutor;

	private AreaDeleteCommandExecutor areaDeleteCommandExecutor;

	private AreaUpdateCommandExecutor areaUpdateCommandExecutor;

	@Override
	public SingleResponse<AreaVO> create(AreaCreateCommand areaCreateCommand) {
		return areaCreateCommandExecutor.execute(areaCreateCommand);
	}

	@Override
	public SingleResponse<AreaVO> delete(AreaDeleteCommand areaDeleteCommand) {
		return areaDeleteCommandExecutor.execute(areaDeleteCommand);
	}

	@Override
	public SingleResponse<AreaVO> update(AreaUpdateCommand areaUpdateCommand) {
		return areaUpdateCommandExecutor.execute(areaUpdateCommand);
	}

	@Autowired
	public void setAreaCreateCommandExecutor(AreaCreateCommandExecutor areaCreateCommandExecutor) {
		this.areaCreateCommandExecutor = areaCreateCommandExecutor;
	}

	@Autowired
	public void setAreaDeleteCommandExecutor(AreaDeleteCommandExecutor areaDeleteCommandExecutor) {
		this.areaDeleteCommandExecutor = areaDeleteCommandExecutor;
	}
	@Autowired
	public void setAreaUpdateCommandExecutor(AreaUpdateCommandExecutor areaUpdateCommandExecutor) {
		this.areaUpdateCommandExecutor = areaUpdateCommandExecutor;
	}
}
