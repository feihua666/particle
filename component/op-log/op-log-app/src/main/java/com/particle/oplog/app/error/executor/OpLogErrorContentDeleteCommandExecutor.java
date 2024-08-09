package com.particle.oplog.app.error.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.error.structmapping.OpLogErrorContentAppStructMapping;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.OpLogErrorContentId;
import com.particle.oplog.domain.error.gateway.OpLogErrorContentGateway;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorContentService;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 操作异常日志内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
@Validated
public class OpLogErrorContentDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorContentGateway opLogErrorContentGateway;
	private IOpLogErrorContentService iOpLogErrorContentService;

	/**
	 * 执行 操作异常日志内容 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorContentVO> execute(@Valid IdCommand deleteCommand) {
		OpLogErrorContentId opLogErrorContentId = OpLogErrorContentId.of(deleteCommand.getId());
		OpLogErrorContent byId = opLogErrorContentGateway.getById(opLogErrorContentId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = opLogErrorContentGateway.delete(opLogErrorContentId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpLogErrorContentAppStructMapping.instance.toOpLogErrorContentVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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
