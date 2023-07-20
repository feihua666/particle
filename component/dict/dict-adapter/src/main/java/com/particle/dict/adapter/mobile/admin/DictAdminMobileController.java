package com.particle.dict.adapter.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "字典移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/dict")
public class DictAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;








}