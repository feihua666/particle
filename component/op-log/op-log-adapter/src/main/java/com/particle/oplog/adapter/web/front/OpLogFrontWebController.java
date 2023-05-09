package com.particle.oplog.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.oplog.client.api.IOpLogApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Api(tags = "操作日志pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/op_log")
public class OpLogFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpLogApplicationService iOpLogApplicationService;


}