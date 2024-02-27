package com.particle.area.app.api.impl.representation;

import com.particle.area.app.executor.representation.AreaQueryCommandExecutor;
import com.particle.area.client.api.representation.IAreaRepresentationApplicationService;
import com.particle.area.client.dto.command.representation.AreaItemsQueryListCommonCommand;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
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
	public SingleResponse<AreaVO> queryDetail(IdCommand areaQueryDetailCommand) {
		return areaQueryCommandExecutor.executeDetail(areaQueryDetailCommand);
	}

	@Override
	public SingleResponse<AreaVO> queryDetailForUpdate(IdCommand areaQueryDetailForUpdateCommand) {
		return areaQueryCommandExecutor.executeDetailForUpdate(areaQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<AreaVO> pageQuery(AreaPageQueryCommand areaPageQueryCommand) {
		return areaQueryCommandExecutor.execute(areaPageQueryCommand);
	}

	@Override
	public MultiResponse<AreaVO> queryList(AreaQueryListCommand areaQueryListCommand) {
		return areaQueryCommandExecutor.execute(areaQueryListCommand);
	}

	@Override
	public MultiResponse<AreaVO> queryItems(AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand) {
		return areaQueryCommandExecutor.queryItems(areaItemsQueryListCommonCommand);
	}

	@Autowired
	public void setAreaQueryCommandExecutor(AreaQueryCommandExecutor areaQueryCommandExecutor) {
		this.areaQueryCommandExecutor = areaQueryCommandExecutor;
	}
}
