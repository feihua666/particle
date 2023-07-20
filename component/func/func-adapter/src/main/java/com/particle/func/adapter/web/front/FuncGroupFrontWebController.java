package com.particle.func.adapter.web.front;

import com.particle.func.client.api.IFuncGroupApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Tag(name = "功能组pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/func-group")
public class FuncGroupFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;









}