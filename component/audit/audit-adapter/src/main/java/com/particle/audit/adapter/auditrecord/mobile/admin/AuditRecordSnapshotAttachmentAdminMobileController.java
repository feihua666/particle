package com.particle.audit.adapter.auditrecord.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotAttachmentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录附件快照后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-19 15:37:34
 */
@Tag(name = "审核记录附件快照移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/audit_record_snapshot_attachment")
public class AuditRecordSnapshotAttachmentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAuditRecordSnapshotAttachmentApplicationService iAuditRecordSnapshotAttachmentApplicationService;


}