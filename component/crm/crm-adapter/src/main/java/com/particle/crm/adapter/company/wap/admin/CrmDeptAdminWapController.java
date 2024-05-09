package com.particle.crm.adapter.company.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crm.client.company.api.ICrmDeptApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户公司部门后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Tag(name = "客户公司部门wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crm_dept")
public class CrmDeptAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrmDeptApplicationService iCrmDeptApplicationService;


}