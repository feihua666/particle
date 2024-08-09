package com.particle.oplog.adapter.error.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.oplog.client.error.api.IOpLogErrorContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作异常日志内容前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Tag(name = "操作异常日志内容wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/op_log_error_content")
public class OpLogErrorContentFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpLogErrorContentApplicationService iOpLogErrorContentApplicationService;


}