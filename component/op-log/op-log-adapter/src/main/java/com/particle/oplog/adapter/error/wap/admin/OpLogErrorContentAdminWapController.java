package com.particle.oplog.adapter.error.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.oplog.client.error.api.IOpLogErrorContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作异常日志内容后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Tag(name = "操作异常日志内容wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/op_log_error_content")
public class OpLogErrorContentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpLogErrorContentApplicationService iOpLogErrorContentApplicationService;


}