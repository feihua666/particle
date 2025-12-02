package com.particle.data.app.dynamicdata.executor;

import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据指标 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
@Validated
public class DynamicDataIndicatorCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorGateway dynamicDataIndicatorGateway;
	private IDynamicDataIndicatorService iDynamicDataIndicatorService;
	/**
	 * 注入使用set方法
	 * @param dynamicDataIndicatorGateway
	 */
	@Autowired
	public void setDynamicDataIndicatorGateway(DynamicDataIndicatorGateway dynamicDataIndicatorGateway) {
		this.dynamicDataIndicatorGateway = dynamicDataIndicatorGateway;
	}
	@Autowired
	public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
		this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
	}
}
