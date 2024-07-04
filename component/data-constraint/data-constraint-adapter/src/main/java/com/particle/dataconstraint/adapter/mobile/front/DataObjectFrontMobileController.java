package com.particle.dataconstraint.adapter.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.dataconstraint.client.api.IDataObjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据对象前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Tag(name = "数据对象移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/data_object")
public class DataObjectFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IDataObjectApplicationService iDataObjectApplicationService;


}