package com.particle.dream.adapter.ssq.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 双色球号码前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Tag(name = "双色球号码wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/ssq_code")
public class SsqCodeFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISsqCodeApplicationService iSsqCodeApplicationService;


}