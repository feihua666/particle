package com.particle.audit.app.auditrecord.api.impl;

import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotDataCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotDataCreateCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotDataDeleteCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotDataUpdateCommandExecutor;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotDataApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotDataUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotDataVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 审核记录数据快照 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Transactional
@Service
@CatchAndLog
public class AuditRecordSnapshotDataApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordSnapshotDataApplicationService {

    private AuditRecordSnapshotDataCreateCommandExecutor auditRecordSnapshotDataCreateCommandExecutor;

    private AuditRecordSnapshotDataDeleteCommandExecutor auditRecordSnapshotDataDeleteCommandExecutor;

    private AuditRecordSnapshotDataUpdateCommandExecutor auditRecordSnapshotDataUpdateCommandExecutor;

    private AuditRecordSnapshotDataCommandExecutor auditRecordSnapshotDataCommandExecutor;


    @Override
    public SingleResponse<AuditRecordSnapshotDataVO> create(AuditRecordSnapshotDataCreateCommand auditRecordSnapshotDataCreateCommand) {
        return auditRecordSnapshotDataCreateCommandExecutor.execute(auditRecordSnapshotDataCreateCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotDataVO> delete(CommonIdCommand deleteCommand) {
        return auditRecordSnapshotDataDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotDataVO> update(AuditRecordSnapshotDataUpdateCommand auditRecordSnapshotDataUpdateCommand) {
        return auditRecordSnapshotDataUpdateCommandExecutor.execute(auditRecordSnapshotDataUpdateCommand);
    }


    @Autowired
    public void setAuditRecordSnapshotDataCreateCommandExecutor(AuditRecordSnapshotDataCreateCommandExecutor auditRecordSnapshotDataCreateCommandExecutor) {
        this.auditRecordSnapshotDataCreateCommandExecutor = auditRecordSnapshotDataCreateCommandExecutor;
    }

    @Autowired
    public void setAuditRecordSnapshotDataDeleteCommandExecutor(AuditRecordSnapshotDataDeleteCommandExecutor auditRecordSnapshotDataDeleteCommandExecutor) {
        this.auditRecordSnapshotDataDeleteCommandExecutor = auditRecordSnapshotDataDeleteCommandExecutor;
    }
    @Autowired
    public void setAuditRecordSnapshotDataUpdateCommandExecutor(AuditRecordSnapshotDataUpdateCommandExecutor auditRecordSnapshotDataUpdateCommandExecutor) {
        this.auditRecordSnapshotDataUpdateCommandExecutor = auditRecordSnapshotDataUpdateCommandExecutor;
    }
    @Autowired
    public void setAuditRecordSnapshotDataCommandExecutor(AuditRecordSnapshotDataCommandExecutor auditRecordSnapshotDataCommandExecutor) {
        this.auditRecordSnapshotDataCommandExecutor = auditRecordSnapshotDataCommandExecutor;
    }
}
