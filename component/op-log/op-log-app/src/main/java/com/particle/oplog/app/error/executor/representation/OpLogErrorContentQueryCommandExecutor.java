package com.particle.oplog.app.error.executor.representation;

import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.error.structmapping.OpLogErrorContentAppStructMapping;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 操作异常日志内容 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
@Validated
public class OpLogErrorContentQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpLogErrorContentService iOpLogErrorContentService;


	/**
	 * 执行 操作异常日志内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorContentVO> executeDetail(IdCommand detailCommand) {
		OpLogErrorContentDO byId = iOpLogErrorContentService.getById(detailCommand.getId());
		OpLogErrorContentVO opLogErrorContentVO = OpLogErrorContentAppStructMapping.instance.opLogErrorContentDOToOpLogErrorContentVO(byId);
		return SingleResponse.of(opLogErrorContentVO);
	}
	/**
	 * 执行 操作异常日志内容 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorContentVO> detailByOpLogErrorId(IdCommand detailCommand) {
		OpLogErrorContentDO byId = iOpLogErrorContentService.getByOpLogErrorId(detailCommand.getId());
		OpLogErrorContentVO opLogErrorContentVO = OpLogErrorContentAppStructMapping.instance.opLogErrorContentDOToOpLogErrorContentVO(byId);
		return SingleResponse.of(opLogErrorContentVO);
	}


	@Autowired
	public void setIOpLogErrorContentService(IOpLogErrorContentService iOpLogErrorContentService) {
		this.iOpLogErrorContentService = iOpLogErrorContentService;
	}
}
