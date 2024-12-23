package com.particle.oplog.adapter.error.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.oplog.client.error.api.IOpLogErrorApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作异常日志后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Tag(name = "操作异常日志移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/op_log_error")
public class OpLogErrorAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpLogErrorApplicationService iOpLogErrorApplicationService;


}
