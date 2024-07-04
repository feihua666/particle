package com.particle.dataconstraint.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据对象前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/data_object")
public class DataObjectFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;


}