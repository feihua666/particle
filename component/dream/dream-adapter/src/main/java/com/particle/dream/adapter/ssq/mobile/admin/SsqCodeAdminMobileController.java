package com.particle.dream.adapter.ssq.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 双色球号码后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Tag(name = "双色球号码移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/ssq_code")
public class SsqCodeAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISsqCodeApplicationService iSsqCodeApplicationService;


}