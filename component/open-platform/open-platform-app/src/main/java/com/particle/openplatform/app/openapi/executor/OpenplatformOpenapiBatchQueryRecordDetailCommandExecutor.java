package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordDetailGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口批量查询记录明细 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordDetailCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway;
	private IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordDetailGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordDetailGateway(OpenplatformOpenapiBatchQueryRecordDetailGateway openplatformOpenapiBatchQueryRecordDetailGateway) {
		this.openplatformOpenapiBatchQueryRecordDetailGateway = openplatformOpenapiBatchQueryRecordDetailGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordDetailService(IOpenplatformOpenapiBatchQueryRecordDetailService iOpenplatformOpenapiBatchQueryRecordDetailService) {
		this.iOpenplatformOpenapiBatchQueryRecordDetailService = iOpenplatformOpenapiBatchQueryRecordDetailService;
	}
}
