package com.particle.audit.app.auditrecord.api.impl;

import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotAttachmentCreateCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotAttachmentDeleteCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotAttachmentUpdateCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordSnapshotAttachmentCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentUpdateCommand;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotAttachmentApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordSnapshotAttachmentCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 审核记录附件快照 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Transactional
@Service
@CatchAndLog
public class AuditRecordSnapshotAttachmentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordSnapshotAttachmentApplicationService {

    private AuditRecordSnapshotAttachmentCreateCommandExecutor auditRecordSnapshotAttachmentCreateCommandExecutor;

    private AuditRecordSnapshotAttachmentDeleteCommandExecutor auditRecordSnapshotAttachmentDeleteCommandExecutor;

    private AuditRecordSnapshotAttachmentUpdateCommandExecutor auditRecordSnapshotAttachmentUpdateCommandExecutor;

    private AuditRecordSnapshotAttachmentCommandExecutor auditRecordSnapshotAttachmentCommandExecutor;


    @Override
    public SingleResponse<AuditRecordSnapshotAttachmentVO> create(AuditRecordSnapshotAttachmentCreateCommand auditRecordSnapshotAttachmentCreateCommand) {
        return auditRecordSnapshotAttachmentCreateCommandExecutor.execute(auditRecordSnapshotAttachmentCreateCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotAttachmentVO> delete(CommonIdCommand deleteCommand) {
        return auditRecordSnapshotAttachmentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotAttachmentVO> update(AuditRecordSnapshotAttachmentUpdateCommand auditRecordSnapshotAttachmentUpdateCommand) {
        return auditRecordSnapshotAttachmentUpdateCommandExecutor.execute(auditRecordSnapshotAttachmentUpdateCommand);
    }


    @Autowired
    public void setAuditRecordSnapshotAttachmentCreateCommandExecutor(AuditRecordSnapshotAttachmentCreateCommandExecutor auditRecordSnapshotAttachmentCreateCommandExecutor) {
        this.auditRecordSnapshotAttachmentCreateCommandExecutor = auditRecordSnapshotAttachmentCreateCommandExecutor;
    }

    @Autowired
    public void setAuditRecordSnapshotAttachmentDeleteCommandExecutor(AuditRecordSnapshotAttachmentDeleteCommandExecutor auditRecordSnapshotAttachmentDeleteCommandExecutor) {
        this.auditRecordSnapshotAttachmentDeleteCommandExecutor = auditRecordSnapshotAttachmentDeleteCommandExecutor;
    }
    @Autowired
    public void setAuditRecordSnapshotAttachmentUpdateCommandExecutor(AuditRecordSnapshotAttachmentUpdateCommandExecutor auditRecordSnapshotAttachmentUpdateCommandExecutor) {
        this.auditRecordSnapshotAttachmentUpdateCommandExecutor = auditRecordSnapshotAttachmentUpdateCommandExecutor;
    }
    @Autowired
    public void setAuditRecordSnapshotAttachmentCommandExecutor(AuditRecordSnapshotAttachmentCommandExecutor auditRecordSnapshotAttachmentCommandExecutor) {
        this.auditRecordSnapshotAttachmentCommandExecutor = auditRecordSnapshotAttachmentCommandExecutor;
    }
}
