package com.particle.audit.adapter.auditrecord.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Tag(name = "审核记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/audit_record")
public class AuditRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAuditRecordApplicationService iAuditRecordApplicationService;


}