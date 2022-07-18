package com.particle.area.app.api.impl;

import com.particle.area.app.executor.*;
import com.particle.area.client.api.IAreaApplicationService;
import com.particle.area.client.dto.command.*;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Service
@CatchAndLog
public class AreaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAreaApplicationService {

	private AreaCreateCommandExecutor areaCreateCommandExecutor;

	private AreaDeleteCommandExecutor areaDeleteCommandExecutor;

	private AreaUpdateCommandExecutor areaUpdateCommandExecutor;

	private AreaQueryCommandExecutor areaQueryCommandExecutor;


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

	@Override
	public SingleResponse<AreaVO> queryDetail(AreaQueryDetailCommand areaQueryDetailCommand) {
		return areaQueryCommandExecutor.execute(areaQueryDetailCommand);
	}

	@Override
	public SingleResponse<AreaVO> queryDetailForUpdate(AreaQueryDetailForUpdateCommand areaQueryDetailForUpdateCommand) {
		return areaQueryCommandExecutor.execute(areaQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<AreaVO> pageQuery(AreaPageQueryCommand areaPageQueryCommand) {
		return areaQueryCommandExecutor.execute(areaPageQueryCommand);
	}

	@Override
	public MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand) {
		return areaQueryCommandExecutor.execute(areaQueryListCommand);
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
	@Autowired
	public void setAreaQueryCommandExecutor(AreaQueryCommandExecutor areaQueryCommandExecutor) {
		this.areaQueryCommandExecutor = areaQueryCommandExecutor;
	}
}
