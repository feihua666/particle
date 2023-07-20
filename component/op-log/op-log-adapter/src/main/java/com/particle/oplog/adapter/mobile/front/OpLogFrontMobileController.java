package com.particle.oplog.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.oplog.client.api.IOpLogApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Tag(name = "操作日志移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/op_log")
public class OpLogFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpLogApplicationService iOpLogApplicationService;


}