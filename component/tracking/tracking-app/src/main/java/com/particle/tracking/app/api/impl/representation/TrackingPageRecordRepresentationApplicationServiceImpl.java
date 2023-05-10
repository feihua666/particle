package com.particle.tracking.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tracking.app.executor.representation.TrackingPageRecordQueryCommandExecutor;
import com.particle.tracking.client.api.representation.ITrackingPageRecordRepresentationApplicationService;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordPageQueryCommand;
import com.particle.tracking.client.dto.command.representation.TrackingPageRecordQueryListCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 页面埋点记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Service
@CatchAndLog
public class TrackingPageRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ITrackingPageRecordRepresentationApplicationService {

    private TrackingPageRecordQueryCommandExecutor trackingPageRecordQueryCommandExecutor;

    @Override
    public SingleResponse<TrackingPageRecordVO> queryDetail(IdCommand detailCommand) {
        return trackingPageRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public PageResponse<TrackingPageRecordVO> pageQuery(TrackingPageRecordPageQueryCommand trackingPageRecordPageQueryCommand) {
        return trackingPageRecordQueryCommandExecutor.execute(trackingPageRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<TrackingPageRecordVO> queryList(TrackingPageRecordQueryListCommand trackingPageRecordQueryListCommand) {
        return trackingPageRecordQueryCommandExecutor.execute(trackingPageRecordQueryListCommand);
    }

    @Autowired
    public void setTrackingPageRecordQueryCommandExecutor(TrackingPageRecordQueryCommandExecutor trackingPageRecordQueryCommandExecutor) {
        this.trackingPageRecordQueryCommandExecutor = trackingPageRecordQueryCommandExecutor;
    }
}
