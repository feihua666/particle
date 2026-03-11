package com.particle.cms.infrastructure.gateway.impl;

import com.particle.audit.adapter.feign.client.auditrecord.rpc.AuditRecordRpcFeignClient;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import com.particle.cms.domain.gateway.CmsAuditGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * 审核 防腐层网关实现
 * </p>
 *
 * @author yangwei
 * @since 2026/1/19 16:20
 */
@Component
public class CmsAuditGatewayImpl implements CmsAuditGateway {

    private AuditRecordRpcFeignClient auditRecordRpcFeignClient;

    @Override
    public Boolean createCmsContentAuditRecord(CmsContentId cmsContentId,
                                               Long auditResultDictId,
                                               Long userId,
                                               Long preStatusDictId,
                                               Long postStatusDictId,
                                               String comment ) {
        AuditRecordCreateCommand auditRecordCreateCommand = new AuditRecordCreateCommand();
        auditRecordCreateCommand.setDataId(cmsContentId.getId());
        auditRecordCreateCommand.setAuditResultDictId(auditResultDictId);
        auditRecordCreateCommand.setAuditComment(comment);
        auditRecordCreateCommand.setAuditAt(LocalDateTime.now());
        auditRecordCreateCommand.setAuditBy(userId);

        auditRecordCreateCommand.setDataPreStatusDictId(preStatusDictId);
        auditRecordCreateCommand.setDataPostStatusDictId(postStatusDictId);
        auditRecordCreateCommand.setGroupFlag("cms_content");
        auditRecordCreateCommand.setGroupFlagMemo("内容管理内容审核");
        // 以下两项暂不支持
        // auditRecordCreateCommand.setSnapshotDataList();
        // auditRecordCreateCommand.setSnapshotAttachmentList();

        auditRecordRpcFeignClient.create(auditRecordCreateCommand);
        return true;
    }


    @Autowired
    public void setAuditRecordRpcFeignClient(AuditRecordRpcFeignClient auditRecordRpcFeignClient) {
        this.auditRecordRpcFeignClient = auditRecordRpcFeignClient;
    }

    @Override
    public Long getPassAuditResultDictId() {
        AuditResultDictVO auditResultDictVO = auditRecordRpcFeignClient.auditResultDict().getData();
        return auditResultDictVO.getPass().getAuditResultDictId();
    }

    @Override
    public Long getUnPassAuditResultDictId() {
        AuditResultDictVO auditResultDictVO = auditRecordRpcFeignClient.auditResultDict().getData();
        return auditResultDictVO.getUnPass().getAuditResultDictId();
    }
}
