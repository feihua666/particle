package com.particle.oplog.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.structmapping.OpLogAppStructMapping;
import com.particle.oplog.client.dto.command.representation.OpLogPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.oplog.infrastructure.service.IOpLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 操作日志 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Component
@Validated
public class OpLogQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpLogService iOpLogService;

	/**
	 * 执行 操作日志 列表查询指令
	 * @param opLogQueryListCommand
	 * @return
	 */
	public MultiResponse<OpLogVO> execute(@Valid OpLogQueryListCommand opLogQueryListCommand) {
		List<OpLogDO> opLogDO = iOpLogService.list(opLogQueryListCommand);
		List<OpLogVO> opLogVOs = OpLogAppStructMapping.instance.opLogDOsToOpLogVOs(opLogDO);
		return MultiResponse.of(opLogVOs);
	}
	/**
	 * 执行 操作日志 分页查询指令
	 * @param opLogPageQueryCommand
	 * @return
	 */
	public PageResponse<OpLogVO> execute(@Valid OpLogPageQueryCommand opLogPageQueryCommand) {
		Page<OpLogDO> page = iOpLogService.listPage(opLogPageQueryCommand);
		return OpLogAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 操作日志 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpLogVO> executeDetail(IdCommand detailCommand) {
		OpLogDO byId = iOpLogService.getById(detailCommand.getId());
		OpLogVO opLogVO = OpLogAppStructMapping.instance.opLogDOToOpLogVO(byId);
		return SingleResponse.of(opLogVO);
	}


	@Autowired
	public void setIOpLogService(IOpLogService iOpLogService) {
		this.iOpLogService = iOpLogService;
	}
}
