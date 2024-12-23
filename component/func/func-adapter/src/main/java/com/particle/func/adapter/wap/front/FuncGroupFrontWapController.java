package com.particle.func.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.func.client.api.IFuncGroupApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能组前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Tag(name = "功能组wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/func-group")
public class FuncGroupFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFuncGroupApplicationService iFuncGroupApplicationService;









}
