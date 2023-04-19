package com.particle.func.app.application.executor.representation;

import com.particle.func.app.application.structmapping.FuncApplicationAppStructMapping;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.func.infrastructure.application.service.IFuncApplicationService;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能应用 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Component
@Validated
public class FuncApplicationQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IFuncApplicationService iFuncApplicationService;

	/**
	 * 执行 功能应用 列表查询指令
	 * @param funcApplicationQueryListCommand
	 * @return
	 */
	public MultiResponse<FuncApplicationVO> execute(@Valid FuncApplicationQueryListCommand funcApplicationQueryListCommand) {
		List<FuncApplicationDO> funcApplicationDO = iFuncApplicationService.list(funcApplicationQueryListCommand);
		List<FuncApplicationVO> funcApplicationVOs = FuncApplicationAppStructMapping.instance.funcApplicationDOsToFuncApplicationVOs(funcApplicationDO);
		return MultiResponse.of(funcApplicationVOs);
	}
	/**
	 * 执行 功能应用 分页查询指令
	 * @param funcApplicationPageQueryCommand
	 * @return
	 */
	public PageResponse<FuncApplicationVO> execute(@Valid FuncApplicationPageQueryCommand funcApplicationPageQueryCommand) {
		Page<FuncApplicationDO> page = iFuncApplicationService.listPage(funcApplicationPageQueryCommand);
		return FuncApplicationAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 功能应用 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationVO> executeDetail(IdCommand detailCommand) {
		FuncApplicationDO byId = iFuncApplicationService.getById(detailCommand.getId());
		FuncApplicationVO funcApplicationVO = FuncApplicationAppStructMapping.instance.funcApplicationDOToFuncApplicationVO(byId);
		return SingleResponse.of(funcApplicationVO);
	}
	/**
	 * 执行 功能应用 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<FuncApplicationVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		FuncApplicationDO byId = iFuncApplicationService.getById(detailForUpdateCommand.getId());
		FuncApplicationVO funcApplicationVO = FuncApplicationAppStructMapping.instance.funcApplicationDOToFuncApplicationVO(byId);
		return SingleResponse.of(funcApplicationVO);
	}

	@Autowired
	public void setIFuncApplicationService(IFuncApplicationService iFuncApplicationService) {
		this.iFuncApplicationService = iFuncApplicationService;
	}
}
