package com.particle.dict.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dict")
public class DictFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;









}