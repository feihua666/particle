package com.particle.oplog.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.oplog.client.api.IOpLogAuditDataApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志审计数据后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Api(tags = "操作日志审计数据移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/op_log_audit_data")
public class OpLogAuditDataAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpLogAuditDataApplicationService iOpLogAuditDataApplicationService;


}