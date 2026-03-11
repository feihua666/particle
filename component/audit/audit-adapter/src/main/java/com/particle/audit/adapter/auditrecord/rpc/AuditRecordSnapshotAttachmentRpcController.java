package com.particle.audit.adapter.auditrecord.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotAttachmentApplicationService;
import com.particle.audit.adapter.feign.client.auditrecord.rpc.AuditRecordSnapshotAttachmentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录附件快照远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Tag(name = "审核记录附件快照远程调用相关接口")
@RestController
@RequestMapping("/rpc/audit_record_snapshot_attachment")
public class AuditRecordSnapshotAttachmentRpcController extends AbstractBaseRpcAdapter implements AuditRecordSnapshotAttachmentRpcFeignClient  {

	@Autowired
	private IAuditRecordSnapshotAttachmentApplicationService iAuditRecordSnapshotAttachmentApplicationService;


}