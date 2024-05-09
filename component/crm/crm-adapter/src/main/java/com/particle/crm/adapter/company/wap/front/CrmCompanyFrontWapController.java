package com.particle.crm.adapter.company.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crm.client.company.api.ICrmCompanyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户公司前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Tag(name = "客户公司wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crm_company")
public class CrmCompanyFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrmCompanyApplicationService iCrmCompanyApplicationService;


}