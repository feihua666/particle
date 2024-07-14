package com.particle.data.adapter.company.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.data.client.company.api.IDataCompanyMd5ApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业md5前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Tag(name = "企业md5pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_company_md5")
public class DataCompanyMd5FrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataCompanyMd5ApplicationService iDataCompanyMd5ApplicationService;


}