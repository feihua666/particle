package com.particle.oplog.app.error.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.oplog.domain.error.gateway.OpLogErrorContentGateway;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 操作异常日志内容 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
@Validated
public class OpLogErrorContentCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorContentGateway opLogErrorContentGateway;
	private IOpLogErrorContentService iOpLogErrorContentService;
	/**
	 * 注入使用set方法
	 * @param opLogErrorContentGateway
	 */
	@Autowired
	public void setOpLogErrorContentGateway(OpLogErrorContentGateway opLogErrorContentGateway) {
		this.opLogErrorContentGateway = opLogErrorContentGateway;
	}
	@Autowired
	public void setIOpLogErrorContentService(IOpLogErrorContentService iOpLogErrorContentService) {
		this.iOpLogErrorContentService = iOpLogErrorContentService;
	}
}
