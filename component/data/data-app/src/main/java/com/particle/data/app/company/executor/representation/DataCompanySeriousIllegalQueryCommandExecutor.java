package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanySeriousIllegalAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.data.infrastructure.company.service.IDataCompanySeriousIllegalService;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业严重违法 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
@Validated
public class DataCompanySeriousIllegalQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService;

	/**
	 * 执行 企业严重违法 列表查询指令
	 * @param dataCompanySeriousIllegalQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanySeriousIllegalVO> execute(@Valid DataCompanySeriousIllegalQueryListCommand dataCompanySeriousIllegalQueryListCommand) {
		List<DataCompanySeriousIllegalDO> dataCompanySeriousIllegalDO = iDataCompanySeriousIllegalService.list(dataCompanySeriousIllegalQueryListCommand);
		List<DataCompanySeriousIllegalVO> dataCompanySeriousIllegalVOs = DataCompanySeriousIllegalAppStructMapping.instance.dataCompanySeriousIllegalDOsToDataCompanySeriousIllegalVOs(dataCompanySeriousIllegalDO);
		return MultiResponse.of(dataCompanySeriousIllegalVOs);
	}
	/**
	 * 执行 企业严重违法 分页查询指令
	 * @param dataCompanySeriousIllegalPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanySeriousIllegalVO> execute(@Valid DataCompanySeriousIllegalPageQueryCommand dataCompanySeriousIllegalPageQueryCommand) {
		Page<DataCompanySeriousIllegalDO> page = iDataCompanySeriousIllegalService.listPage(dataCompanySeriousIllegalPageQueryCommand);
		return DataCompanySeriousIllegalAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业严重违法 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalVO> executeDetail(IdCommand detailCommand) {
		DataCompanySeriousIllegalDO byId = iDataCompanySeriousIllegalService.getById(detailCommand.getId());
		DataCompanySeriousIllegalVO dataCompanySeriousIllegalVO = DataCompanySeriousIllegalAppStructMapping.instance.dataCompanySeriousIllegalDOToDataCompanySeriousIllegalVO(byId);
		return SingleResponse.of(dataCompanySeriousIllegalVO);
	}
	/**
	 * 执行 企业严重违法 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanySeriousIllegalDO byId = iDataCompanySeriousIllegalService.getById(detailForUpdateCommand.getId());
		DataCompanySeriousIllegalVO dataCompanySeriousIllegalVO = DataCompanySeriousIllegalAppStructMapping.instance.dataCompanySeriousIllegalDOToDataCompanySeriousIllegalVO(byId);
		return SingleResponse.of(dataCompanySeriousIllegalVO);
	}


	@Autowired
	public void setIDataCompanySeriousIllegalService(IDataCompanySeriousIllegalService iDataCompanySeriousIllegalService) {
		this.iDataCompanySeriousIllegalService = iDataCompanySeriousIllegalService;
	}
}
