package com.particle.tracking.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.executor.TrackingPageCreateCommandExecutor;
import com.particle.tracking.app.executor.TrackingPageDeleteCommandExecutor;
import com.particle.tracking.app.executor.TrackingPageUpdateCommandExecutor;
import com.particle.tracking.client.api.ITrackingPageApplicationService;
import com.particle.tracking.client.dto.command.TrackingPageCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 埋点页面 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Transactional
@Service
@CatchAndLog
public class TrackingPageApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITrackingPageApplicationService {

	private TrackingPageCreateCommandExecutor trackingPageCreateCommandExecutor;

	private TrackingPageDeleteCommandExecutor trackingPageDeleteCommandExecutor;

	private TrackingPageUpdateCommandExecutor trackingPageUpdateCommandExecutor;


	@Override
	public SingleResponse<TrackingPageVO> create(TrackingPageCreateCommand trackingPageCreateCommand) {
		return trackingPageCreateCommandExecutor.execute(trackingPageCreateCommand);
	}

	@Override
	public SingleResponse<TrackingPageVO> delete(IdCommand deleteCommand) {
		return trackingPageDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TrackingPageVO> update(TrackingPageUpdateCommand trackingPageUpdateCommand) {
		return trackingPageUpdateCommandExecutor.execute(trackingPageUpdateCommand);
	}

	@Autowired
	public void setTrackingPageCreateCommandExecutor(TrackingPageCreateCommandExecutor trackingPageCreateCommandExecutor) {
		this.trackingPageCreateCommandExecutor = trackingPageCreateCommandExecutor;
	}

	@Autowired
	public void setTrackingPageDeleteCommandExecutor(TrackingPageDeleteCommandExecutor trackingPageDeleteCommandExecutor) {
		this.trackingPageDeleteCommandExecutor = trackingPageDeleteCommandExecutor;
	}
	@Autowired
	public void setTrackingPageUpdateCommandExecutor(TrackingPageUpdateCommandExecutor trackingPageUpdateCommandExecutor) {
		this.trackingPageUpdateCommandExecutor = trackingPageUpdateCommandExecutor;
	}

}
