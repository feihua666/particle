package com.particle.user.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.user.client.api.IUserExtraInfoApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户扩展信息前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Tag(name = "用户扩展信息wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/user_extra_info")
public class UserExtraInfoFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IUserExtraInfoApplicationService iUserExtraInfoApplicationService;


}