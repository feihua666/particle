package com.particle.audit.adapter.auditrecord.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotDataApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录数据快照前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Tag(name = "审核记录数据快照移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/audit_record_snapshot_data")
public class AuditRecordSnapshotDataFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAuditRecordSnapshotDataApplicationService iAuditRecordSnapshotDataApplicationService;


}