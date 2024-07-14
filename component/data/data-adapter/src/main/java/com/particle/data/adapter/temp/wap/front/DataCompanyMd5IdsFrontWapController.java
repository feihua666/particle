package com.particle.data.adapter.temp.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.data.client.temp.api.IDataCompanyMd5IdsApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业md5ids前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Tag(name = "企业md5idswap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_company_md5_ids")
public class DataCompanyMd5IdsFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataCompanyMd5IdsApplicationService iDataCompanyMd5IdsApplicationService;


}