package com.particle.oplog.app.error.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.error.structmapping.OpLogErrorAppStructMapping;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.OpLogErrorId;
import com.particle.oplog.domain.error.gateway.OpLogErrorGateway;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 操作异常日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
@Validated
public class OpLogErrorDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorGateway opLogErrorGateway;
	private IOpLogErrorService iOpLogErrorService;

	/**
	 * 执行 操作异常日志 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorVO> execute(@Valid IdCommand deleteCommand) {
		OpLogErrorId opLogErrorId = OpLogErrorId.of(deleteCommand.getId());
		OpLogError byId = opLogErrorGateway.getById(opLogErrorId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = opLogErrorGateway.delete(opLogErrorId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpLogErrorAppStructMapping.instance.toOpLogErrorVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
