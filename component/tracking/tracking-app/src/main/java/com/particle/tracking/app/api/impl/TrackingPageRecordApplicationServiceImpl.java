package com.particle.tracking.app.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.executor.TrackingPageRecordCreateCommandExecutor;
import com.particle.tracking.app.executor.TrackingPageRecordDeleteCommandExecutor;
import com.particle.tracking.app.executor.TrackingPageRecordUpdateCommandExecutor;
import com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 页面埋点记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Transactional
@Service
@CatchAndLog
public class TrackingPageRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITrackingPageRecordApplicationService {

	private TrackingPageRecordCreateCommandExecutor trackingPageRecordCreateCommandExecutor;

	private TrackingPageRecordDeleteCommandExecutor trackingPageRecordDeleteCommandExecutor;

	private TrackingPageRecordUpdateCommandExecutor trackingPageRecordUpdateCommandExecutor;


	@Override
	public SingleResponse<AbstractBaseIdVO> create(TrackingPageRecordCreateCommand trackingPageRecordCreateCommand) {
		return trackingPageRecordCreateCommandExecutor.execute(trackingPageRecordCreateCommand);
	}

	@Override
	public SingleResponse<TrackingPageRecordVO> delete(IdCommand deleteCommand) {
		return trackingPageRecordDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<TrackingPageRecordVO> update(TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand) {
		return trackingPageRecordUpdateCommandExecutor.execute(trackingPageRecordUpdateCommand);
	}

	@Autowired
	public void setTrackingPageRecordCreateCommandExecutor(TrackingPageRecordCreateCommandExecutor trackingPageRecordCreateCommandExecutor) {
		this.trackingPageRecordCreateCommandExecutor = trackingPageRecordCreateCommandExecutor;
	}

	@Autowired
	public void setTrackingPageRecordDeleteCommandExecutor(TrackingPageRecordDeleteCommandExecutor trackingPageRecordDeleteCommandExecutor) {
		this.trackingPageRecordDeleteCommandExecutor = trackingPageRecordDeleteCommandExecutor;
	}
	@Autowired
	public void setTrackingPageRecordUpdateCommandExecutor(TrackingPageRecordUpdateCommandExecutor trackingPageRecordUpdateCommandExecutor) {
		this.trackingPageRecordUpdateCommandExecutor = trackingPageRecordUpdateCommandExecutor;
	}

}
