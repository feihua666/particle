package com.particle.oplog.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.structmapping.OpLogAuditDataAppStructMapping;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.OpLogAuditDataId;
import com.particle.oplog.domain.gateway.OpLogAuditDataGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 操作日志审计数据 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Component
@Validated
public class OpLogAuditDataDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpLogAuditDataGateway opLogAuditDataGateway;

	/**
	 * 执行 操作日志审计数据 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpLogAuditDataVO> execute(@Valid IdCommand deleteCommand) {
		OpLogAuditDataId opLogAuditDataId = OpLogAuditDataId.of(deleteCommand.getId());
		OpLogAuditData byId = opLogAuditDataGateway.getById(opLogAuditDataId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = opLogAuditDataGateway.delete(opLogAuditDataId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpLogAuditDataAppStructMapping.instance.toOpLogAuditDataVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param opLogAuditDataGateway
	 */
	@Autowired
	public void setOpLogAuditDataGateway(OpLogAuditDataGateway opLogAuditDataGateway) {
		this.opLogAuditDataGateway = opLogAuditDataGateway;
	}
}
