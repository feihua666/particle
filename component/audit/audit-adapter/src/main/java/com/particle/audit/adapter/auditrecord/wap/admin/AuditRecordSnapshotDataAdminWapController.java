package com.particle.audit.adapter.auditrecord.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotDataApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录数据快照后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Tag(name = "审核记录数据快照wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/audit_record_snapshot_data")
public class AuditRecordSnapshotDataAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IAuditRecordSnapshotDataApplicationService iAuditRecordSnapshotDataApplicationService;


}