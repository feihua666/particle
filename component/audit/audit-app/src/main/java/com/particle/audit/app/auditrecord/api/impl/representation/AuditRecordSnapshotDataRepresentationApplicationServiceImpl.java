package com.particle.audit.app.auditrecord.api.impl.representation;

import com.particle.audit.app.auditrecord.executor.representation.AuditRecordSnapshotDataQueryCommandExecutor;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordSnapshotDataRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotDataQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 审核记录数据快照 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Service
@CatchAndLog
public class AuditRecordSnapshotDataRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordSnapshotDataRepresentationApplicationService {

    private AuditRecordSnapshotDataQueryCommandExecutor auditRecordSnapshotDataQueryCommandExecutor;

    @Override
    public SingleResponse<AuditRecordSnapshotDataVO> queryDetail(CommonIdCommand detailCommand) {
        return auditRecordSnapshotDataQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotDataVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return auditRecordSnapshotDataQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AuditRecordSnapshotDataVO> pageQuery(AuditRecordSnapshotDataPageQueryCommand auditRecordSnapshotDataPageQueryCommand) {
        return auditRecordSnapshotDataQueryCommandExecutor.execute(auditRecordSnapshotDataPageQueryCommand);
    }

    @Override
    public MultiResponse<AuditRecordSnapshotDataVO> queryList(AuditRecordSnapshotDataQueryListCommand auditRecordSnapshotDataQueryListCommand) {
        return auditRecordSnapshotDataQueryCommandExecutor.execute(auditRecordSnapshotDataQueryListCommand);
    }


    @Autowired
    public void setAuditRecordSnapshotDataQueryCommandExecutor(AuditRecordSnapshotDataQueryCommandExecutor auditRecordSnapshotDataQueryCommandExecutor) {
        this.auditRecordSnapshotDataQueryCommandExecutor = auditRecordSnapshotDataQueryCommandExecutor;
    }
}
