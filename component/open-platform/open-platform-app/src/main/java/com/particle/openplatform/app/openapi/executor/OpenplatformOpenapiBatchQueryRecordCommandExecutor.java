package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口批量查询记录 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway;
	private IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordGateway(OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway) {
		this.openplatformOpenapiBatchQueryRecordGateway = openplatformOpenapiBatchQueryRecordGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordService(IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService) {
		this.iOpenplatformOpenapiBatchQueryRecordService = iOpenplatformOpenapiBatchQueryRecordService;
	}
}
