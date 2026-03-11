package com.particle.audit.adapter.auditrecord.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotAttachmentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录附件快照前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Tag(name = "审核记录附件快照pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/audit_record_snapshot_attachment")
public class AuditRecordSnapshotAttachmentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAuditRecordSnapshotAttachmentApplicationService iAuditRecordSnapshotAttachmentApplicationService;


}