package com.particle.oplog.app.error.executor.representation;

import com.particle.oplog.app.error.structmapping.OpLogErrorAppStructMapping;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorQueryListCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorService;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 操作异常日志 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
@Validated
public class OpLogErrorQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpLogErrorService iOpLogErrorService;

	/**
	 * 执行 操作异常日志 列表查询指令
	 * @param opLogErrorQueryListCommand
	 * @return
	 */
	public MultiResponse<OpLogErrorVO> execute(@Valid OpLogErrorQueryListCommand opLogErrorQueryListCommand) {
		List<OpLogErrorDO> opLogErrorDO = iOpLogErrorService.list(opLogErrorQueryListCommand);
		List<OpLogErrorVO> opLogErrorVOs = OpLogErrorAppStructMapping.instance.opLogErrorDOsToOpLogErrorVOs(opLogErrorDO);
		return MultiResponse.of(opLogErrorVOs);
	}
	/**
	 * 执行 操作异常日志 分页查询指令
	 * @param opLogErrorPageQueryCommand
	 * @return
	 */
	public PageResponse<OpLogErrorVO> execute(@Valid OpLogErrorPageQueryCommand opLogErrorPageQueryCommand) {
		Page<OpLogErrorDO> page = iOpLogErrorService.listPage(opLogErrorPageQueryCommand);
		return OpLogErrorAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 操作异常日志 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorVO> executeDetail(IdCommand detailCommand) {
		OpLogErrorDO byId = iOpLogErrorService.getById(detailCommand.getId());
		OpLogErrorVO opLogErrorVO = OpLogErrorAppStructMapping.instance.opLogErrorDOToOpLogErrorVO(byId);
		return SingleResponse.of(opLogErrorVO);
	}


	@Autowired
	public void setIOpLogErrorService(IOpLogErrorService iOpLogErrorService) {
		this.iOpLogErrorService = iOpLogErrorService;
	}
}
