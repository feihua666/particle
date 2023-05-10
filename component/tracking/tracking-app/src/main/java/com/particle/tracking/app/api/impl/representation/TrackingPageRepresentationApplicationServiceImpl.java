package com.particle.tracking.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.executor.representation.TrackingPageQueryCommandExecutor;
import com.particle.tracking.client.api.representation.ITrackingPageRepresentationApplicationService;
import com.particle.tracking.client.dto.command.representation.TrackingPagePageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 埋点页面 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Service
@CatchAndLog
public class TrackingPageRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITrackingPageRepresentationApplicationService {

    private TrackingPageQueryCommandExecutor trackingPageQueryCommandExecutor;

    @Override
    public SingleResponse<TrackingPageVO> queryDetail(IdCommand detailCommand) {
        return trackingPageQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<TrackingPageVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return trackingPageQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<TrackingPageVO> pageQuery(TrackingPagePageQueryCommand trackingPagePageQueryCommand) {
        return trackingPageQueryCommandExecutor.execute(trackingPagePageQueryCommand);
    }

    @Override
    public MultiResponse<TrackingPageVO> queryList(TrackingPageQueryListCommand trackingPageQueryListCommand) {
        return trackingPageQueryCommandExecutor.execute(trackingPageQueryListCommand);
    }

    @Autowired
    public void setTrackingPageQueryCommandExecutor(TrackingPageQueryCommandExecutor trackingPageQueryCommandExecutor) {
        this.trackingPageQueryCommandExecutor = trackingPageQueryCommandExecutor;
    }
}
