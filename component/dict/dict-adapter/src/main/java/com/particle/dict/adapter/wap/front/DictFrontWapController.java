package com.particle.dict.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/dict")
public class DictFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;









}