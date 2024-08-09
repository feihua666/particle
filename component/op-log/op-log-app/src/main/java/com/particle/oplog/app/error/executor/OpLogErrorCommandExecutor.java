package com.particle.oplog.app.error.executor;

import com.particle.oplog.domain.error.gateway.OpLogErrorGateway;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorService;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 操作异常日志 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
@Validated
public class OpLogErrorCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorGateway opLogErrorGateway;
	private IOpLogErrorService iOpLogErrorService;
	/**
	 * 注入使用set方法
	 * @param opLogErrorGateway
	 */
	@Autowired
	public void setOpLogErrorGateway(OpLogErrorGateway opLogErrorGateway) {
		this.opLogErrorGateway = opLogErrorGateway;
	}
	@Autowired
	public void setIOpLogErrorService(IOpLogErrorService iOpLogErrorService) {
		this.iOpLogErrorService = iOpLogErrorService;
	}
}
