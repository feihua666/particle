package com.particle.oplog.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.structmapping.OpLogAppStructMapping;
import com.particle.oplog.client.dto.data.OpLogVO;
import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;
import com.particle.oplog.domain.gateway.OpLogGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 操作日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Component
@Validated
public class OpLogDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpLogGateway opLogGateway;

	/**
	 * 执行 操作日志 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpLogVO> execute(@Valid IdCommand deleteCommand) {
		OpLogId opLogId = OpLogId.of(deleteCommand.getId());
		OpLog byId = opLogGateway.getById(opLogId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = opLogGateway.delete(opLogId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpLogAppStructMapping.instance.toOpLogVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param opLogGateway
	 */
	@Autowired
	public void setOpLogGateway(OpLogGateway opLogGateway) {
		this.opLogGateway = opLogGateway;
	}
}
