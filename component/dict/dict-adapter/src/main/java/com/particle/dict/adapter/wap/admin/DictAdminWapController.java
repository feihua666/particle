package com.particle.dict.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "字典wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/dict")
public class DictAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;









}