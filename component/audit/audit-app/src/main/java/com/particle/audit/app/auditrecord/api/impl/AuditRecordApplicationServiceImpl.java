package com.particle.audit.app.auditrecord.api.impl;

import com.particle.audit.app.auditrecord.executor.AuditRecordCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordCreateCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordDeleteCommandExecutor;
import com.particle.audit.app.auditrecord.executor.AuditRecordUpdateCommandExecutor;
import com.particle.audit.client.auditrecord.api.IAuditRecordApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordUpdateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 审核记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Transactional
@Service
@CatchAndLog
public class AuditRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordApplicationService {

    private AuditRecordCreateCommandExecutor auditRecordCreateCommandExecutor;

    private AuditRecordDeleteCommandExecutor auditRecordDeleteCommandExecutor;

    private AuditRecordUpdateCommandExecutor auditRecordUpdateCommandExecutor;

    private AuditRecordCommandExecutor auditRecordCommandExecutor;


    @Override
    public SingleResponse<AuditRecordVO> create(AuditRecordCreateCommand auditRecordCreateCommand) {
        return auditRecordCreateCommandExecutor.execute(auditRecordCreateCommand);
    }

    @Override
    public SingleResponse<AuditRecordVO> delete(CommonIdCommand deleteCommand) {
        return auditRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<AuditRecordVO> update(AuditRecordUpdateCommand auditRecordUpdateCommand) {
        return auditRecordUpdateCommandExecutor.execute(auditRecordUpdateCommand);
    }


    @Autowired
    public void setAuditRecordCreateCommandExecutor(AuditRecordCreateCommandExecutor auditRecordCreateCommandExecutor) {
        this.auditRecordCreateCommandExecutor = auditRecordCreateCommandExecutor;
    }

    @Autowired
    public void setAuditRecordDeleteCommandExecutor(AuditRecordDeleteCommandExecutor auditRecordDeleteCommandExecutor) {
        this.auditRecordDeleteCommandExecutor = auditRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setAuditRecordUpdateCommandExecutor(AuditRecordUpdateCommandExecutor auditRecordUpdateCommandExecutor) {
        this.auditRecordUpdateCommandExecutor = auditRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setAuditRecordCommandExecutor(AuditRecordCommandExecutor auditRecordCommandExecutor) {
        this.auditRecordCommandExecutor = auditRecordCommandExecutor;
    }
}
