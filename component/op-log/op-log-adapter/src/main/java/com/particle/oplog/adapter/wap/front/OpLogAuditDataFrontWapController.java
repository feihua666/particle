package com.particle.oplog.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志审计数据前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Tag(name = "操作日志审计数据wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/op_log_audit_data")
public class OpLogAuditDataFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;


}