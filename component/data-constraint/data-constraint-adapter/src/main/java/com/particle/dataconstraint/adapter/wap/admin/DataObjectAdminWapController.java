package com.particle.dataconstraint.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据对象后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/data_object")
public class DataObjectAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;


}