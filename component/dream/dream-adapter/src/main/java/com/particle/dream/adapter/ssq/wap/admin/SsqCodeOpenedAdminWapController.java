package com.particle.dream.adapter.ssq.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dream.client.ssq.api.ISsqCodeOpenedApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 双色球开奖后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Tag(name = "双色球开奖wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/ssq_code_opened")
public class SsqCodeOpenedAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISsqCodeOpenedApplicationService iSsqCodeOpenedApplicationService;


}