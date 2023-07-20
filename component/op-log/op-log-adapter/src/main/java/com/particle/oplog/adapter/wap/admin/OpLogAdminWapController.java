package com.particle.oplog.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.oplog.client.api.IOpLogApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Tag(name = "操作日志wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/op_log")
public class OpLogAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpLogApplicationService iOpLogApplicationService;


}