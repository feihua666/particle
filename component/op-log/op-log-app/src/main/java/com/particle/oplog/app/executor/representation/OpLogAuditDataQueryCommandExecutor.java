package com.particle.oplog.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.app.structmapping.OpLogAuditDataAppStructMapping;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import com.particle.oplog.infrastructure.service.IOpLogAuditDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 操作日志审计数据 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Component
@Validated
public class OpLogAuditDataQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpLogAuditDataService iOpLogAuditDataService;

	/**
	 * 执行 操作日志审计数据 列表查询指令
	 * @param opLogAuditDataQueryListCommand
	 * @return
	 */
	public MultiResponse<OpLogAuditDataVO> execute(@Valid OpLogAuditDataQueryListCommand opLogAuditDataQueryListCommand) {
		List<OpLogAuditDataDO> opLogAuditDataDO = iOpLogAuditDataService.list(opLogAuditDataQueryListCommand);
		List<OpLogAuditDataVO> opLogAuditDataVOs = OpLogAuditDataAppStructMapping.instance.opLogAuditDataDOsToOpLogAuditDataVOs(opLogAuditDataDO);
		return MultiResponse.of(opLogAuditDataVOs);
	}
	/**
	 * 执行 操作日志审计数据 分页查询指令
	 * @param opLogAuditDataPageQueryCommand
	 * @return
	 */
	public PageResponse<OpLogAuditDataVO> execute(@Valid OpLogAuditDataPageQueryCommand opLogAuditDataPageQueryCommand) {
		Page<OpLogAuditDataDO> page = iOpLogAuditDataService.listPage(opLogAuditDataPageQueryCommand);
		return OpLogAuditDataAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 操作日志审计数据 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpLogAuditDataVO> executeDetail(IdCommand detailCommand) {
		OpLogAuditDataDO byId = iOpLogAuditDataService.getById(detailCommand.getId());
		OpLogAuditDataVO opLogAuditDataVO = OpLogAuditDataAppStructMapping.instance.opLogAuditDataDOToOpLogAuditDataVO(byId);
		return SingleResponse.of(opLogAuditDataVO);
	}


	@Autowired
	public void setIOpLogAuditDataService(IOpLogAuditDataService iOpLogAuditDataService) {
		this.iOpLogAuditDataService = iOpLogAuditDataService;
	}
}
