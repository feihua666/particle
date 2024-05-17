package com.particle.dream.adapter.ssq.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 双色球号码前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Tag(name = "双色球号码pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/ssq_code")
public class SsqCodeFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISsqCodeApplicationService iSsqCodeApplicationService;


}