package com.particle.audit.adapter.auditrecord.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Tag(name = "审核记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/audit_record")
public class AuditRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAuditRecordApplicationService iAuditRecordApplicationService;


}