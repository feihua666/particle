package com.particle.lowcode.adapter.generator.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 低代码模型项目后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Tag(name = "低代码模型项目wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/lowcode-model-item")
public class LowcodeModelItemAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ILowcodeModelItemApplicationService iLowcodeModelItemApplicationService;









}
