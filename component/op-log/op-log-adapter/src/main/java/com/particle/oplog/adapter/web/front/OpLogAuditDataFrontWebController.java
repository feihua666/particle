package com.particle.oplog.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志审计数据前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Tag(name = "操作日志审计数据pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/op_log_audit_data")
public class OpLogAuditDataFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;


}