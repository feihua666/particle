package com.particle.dict.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/dict")
public class DictFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;









}