package com.particle.data.app.dynamicdata.executor;

import com.particle.data.domain.dynamicdata.gateway.DynamicDataCategoryGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataCategoryService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据分类 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
@Validated
public class DynamicDataCategoryCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataCategoryGateway dynamicDataCategoryGateway;
	private IDynamicDataCategoryService iDynamicDataCategoryService;
	/**
	 * 注入使用set方法
	 * @param dynamicDataCategoryGateway
	 */
	@Autowired
	public void setDynamicDataCategoryGateway(DynamicDataCategoryGateway dynamicDataCategoryGateway) {
		this.dynamicDataCategoryGateway = dynamicDataCategoryGateway;
	}
	@Autowired
	public void setIDynamicDataCategoryService(IDynamicDataCategoryService iDynamicDataCategoryService) {
		this.iDynamicDataCategoryService = iDynamicDataCategoryService;
	}
}
