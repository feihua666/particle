package com.particle.audit.app.auditrecord.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.audit.app.auditrecord.executor.representation.AuditRecordSnapshotAttachmentQueryCommandExecutor;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordSnapshotAttachmentRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordSnapshotAttachmentQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordSnapshotAttachmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 审核记录附件快照 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Service
@CatchAndLog
public class AuditRecordSnapshotAttachmentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordSnapshotAttachmentRepresentationApplicationService {

    private AuditRecordSnapshotAttachmentQueryCommandExecutor auditRecordSnapshotAttachmentQueryCommandExecutor;

    @Override
    public SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetail(CommonIdCommand detailCommand) {
        return auditRecordSnapshotAttachmentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AuditRecordSnapshotAttachmentVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return auditRecordSnapshotAttachmentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AuditRecordSnapshotAttachmentVO> pageQuery(AuditRecordSnapshotAttachmentPageQueryCommand auditRecordSnapshotAttachmentPageQueryCommand) {
        return auditRecordSnapshotAttachmentQueryCommandExecutor.execute(auditRecordSnapshotAttachmentPageQueryCommand);
    }

    @Override
    public MultiResponse<AuditRecordSnapshotAttachmentVO> queryList(AuditRecordSnapshotAttachmentQueryListCommand auditRecordSnapshotAttachmentQueryListCommand) {
        return auditRecordSnapshotAttachmentQueryCommandExecutor.execute(auditRecordSnapshotAttachmentQueryListCommand);
    }


    @Autowired
    public void setAuditRecordSnapshotAttachmentQueryCommandExecutor(AuditRecordSnapshotAttachmentQueryCommandExecutor auditRecordSnapshotAttachmentQueryCommandExecutor) {
        this.auditRecordSnapshotAttachmentQueryCommandExecutor = auditRecordSnapshotAttachmentQueryCommandExecutor;
    }
}
