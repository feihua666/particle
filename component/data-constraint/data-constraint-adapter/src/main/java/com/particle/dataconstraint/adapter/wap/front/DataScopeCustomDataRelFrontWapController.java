package com.particle.dataconstraint.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataconstraint.client.api.IDataScopeCustomDataRelApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据范围自定义数据关系前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Tag(name = "数据范围自定义数据关系wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_scope_custom_data_rel")
public class DataScopeCustomDataRelFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataScopeCustomDataRelApplicationService iDataScopeCustomDataRelApplicationService;


}