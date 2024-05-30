package com.particle.config.adapter.system.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.config.client.system.api.ISystemConfigApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统参数配置前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Tag(name = "系统参数配置wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/system_config")
public class SystemConfigFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISystemConfigApplicationService iSystemConfigApplicationService;


}