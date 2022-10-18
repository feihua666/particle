package com.particle.area.app.api.impl.representation;

import com.particle.area.app.executor.representation.AreaQueryCommandExecutor;
import com.particle.area.client.api.representation.IAreaRepresentationApplicationService;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryDetailCommand;
import com.particle.area.client.dto.command.representation.AreaQueryDetailForUpdateCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AreaRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAreaRepresentationApplicationService {

	private AreaQueryCommandExecutor areaQueryCommandExecutor;

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
	public void setAreaQueryCommandExecutor(AreaQueryCommandExecutor areaQueryCommandExecutor) {
		this.areaQueryCommandExecutor = areaQueryCommandExecutor;
	}
}
