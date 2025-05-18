package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDebtorService;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorPageQueryCommand;
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
 * 企业被执行人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService;

	/**
	 * 执行 企业被执行人 列表查询指令
	 * @param dataCompanyJudgmentDebtorQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDebtorVO> execute(@Valid DataCompanyJudgmentDebtorQueryListCommand dataCompanyJudgmentDebtorQueryListCommand) {
		List<DataCompanyJudgmentDebtorDO> dataCompanyJudgmentDebtorDO = iDataCompanyJudgmentDebtorService.list(dataCompanyJudgmentDebtorQueryListCommand);
		List<DataCompanyJudgmentDebtorVO> dataCompanyJudgmentDebtorVOs = DataCompanyJudgmentDebtorAppStructMapping.instance.dataCompanyJudgmentDebtorDOsToDataCompanyJudgmentDebtorVOs(dataCompanyJudgmentDebtorDO);
		return MultiResponse.of(dataCompanyJudgmentDebtorVOs);
	}
	/**
	 * 执行 企业被执行人 分页查询指令
	 * @param dataCompanyJudgmentDebtorPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDebtorVO> execute(@Valid DataCompanyJudgmentDebtorPageQueryCommand dataCompanyJudgmentDebtorPageQueryCommand) {
		Page<DataCompanyJudgmentDebtorDO> page = iDataCompanyJudgmentDebtorService.listPage(dataCompanyJudgmentDebtorPageQueryCommand);
		return DataCompanyJudgmentDebtorAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业被执行人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorVO> executeDetail(IdCommand detailCommand) {
		DataCompanyJudgmentDebtorDO byId = iDataCompanyJudgmentDebtorService.getById(detailCommand.getId());
		DataCompanyJudgmentDebtorVO dataCompanyJudgmentDebtorVO = DataCompanyJudgmentDebtorAppStructMapping.instance.dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDebtorVO);
	}
	/**
	 * 执行 企业被执行人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDebtorVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyJudgmentDebtorDO byId = iDataCompanyJudgmentDebtorService.getById(detailForUpdateCommand.getId());
		DataCompanyJudgmentDebtorVO dataCompanyJudgmentDebtorVO = DataCompanyJudgmentDebtorAppStructMapping.instance.dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorVO(byId);
		return SingleResponse.of(dataCompanyJudgmentDebtorVO);
	}


	@Autowired
	public void setIDataCompanyJudgmentDebtorService(IDataCompanyJudgmentDebtorService iDataCompanyJudgmentDebtorService) {
		this.iDataCompanyJudgmentDebtorService = iDataCompanyJudgmentDebtorService;
	}
}
