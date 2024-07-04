package com.particle.dataconstraint.adapter.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据对象前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/data_object")
public class DataObjectFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;


}