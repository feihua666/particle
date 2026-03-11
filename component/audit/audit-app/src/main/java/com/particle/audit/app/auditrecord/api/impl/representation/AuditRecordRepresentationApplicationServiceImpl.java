package com.particle.audit.app.auditrecord.api.impl.representation;

import com.particle.audit.app.auditrecord.executor.representation.AuditRecordQueryCommandExecutor;
import com.particle.audit.client.auditrecord.api.representation.IAuditRecordRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
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
 * 审核记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Service
@CatchAndLog
public class AuditRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IAuditRecordRepresentationApplicationService {

    private AuditRecordQueryCommandExecutor auditRecordQueryCommandExecutor;

    @Override
    public SingleResponse<AuditRecordVO> queryDetail(CommonIdCommand detailCommand) {
        return auditRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<AuditRecordVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return auditRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<AuditRecordVO> pageQuery(AuditRecordPageQueryCommand auditRecordPageQueryCommand) {
        return auditRecordQueryCommandExecutor.execute(auditRecordPageQueryCommand);
    }

    @Override
    public SingleResponse<AuditResultDictVO> auditResultDict() {
        return auditRecordQueryCommandExecutor.auditResultDict();
    }

    @Override
    public MultiResponse<AuditRecordVO> queryList(AuditRecordQueryListCommand auditRecordQueryListCommand) {
        return auditRecordQueryCommandExecutor.execute(auditRecordQueryListCommand);
    }


    @Autowired
    public void setAuditRecordQueryCommandExecutor(AuditRecordQueryCommandExecutor auditRecordQueryCommandExecutor) {
        this.auditRecordQueryCommandExecutor = auditRecordQueryCommandExecutor;
    }
}
