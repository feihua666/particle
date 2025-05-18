package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyDiscreditedJudgmentDebtorAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.data.infrastructure.company.service.IDataCompanyDiscreditedJudgmentDebtorService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorPageQueryCommand;
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
 * 企业失信被执行人 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService;

	/**
	 * 执行 企业失信被执行人 列表查询指令
	 * @param dataCompanyDiscreditedJudgmentDebtorQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyDiscreditedJudgmentDebtorVO> execute(@Valid DataCompanyDiscreditedJudgmentDebtorQueryListCommand dataCompanyDiscreditedJudgmentDebtorQueryListCommand) {
		List<DataCompanyDiscreditedJudgmentDebtorDO> dataCompanyDiscreditedJudgmentDebtorDO = iDataCompanyDiscreditedJudgmentDebtorService.list(dataCompanyDiscreditedJudgmentDebtorQueryListCommand);
		List<DataCompanyDiscreditedJudgmentDebtorVO> dataCompanyDiscreditedJudgmentDebtorVOs = DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorDOsToDataCompanyDiscreditedJudgmentDebtorVOs(dataCompanyDiscreditedJudgmentDebtorDO);
		return MultiResponse.of(dataCompanyDiscreditedJudgmentDebtorVOs);
	}
	/**
	 * 执行 企业失信被执行人 分页查询指令
	 * @param dataCompanyDiscreditedJudgmentDebtorPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyDiscreditedJudgmentDebtorVO> execute(@Valid DataCompanyDiscreditedJudgmentDebtorPageQueryCommand dataCompanyDiscreditedJudgmentDebtorPageQueryCommand) {
		Page<DataCompanyDiscreditedJudgmentDebtorDO> page = iDataCompanyDiscreditedJudgmentDebtorService.listPage(dataCompanyDiscreditedJudgmentDebtorPageQueryCommand);
		return DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业失信被执行人 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> executeDetail(IdCommand detailCommand) {
		DataCompanyDiscreditedJudgmentDebtorDO byId = iDataCompanyDiscreditedJudgmentDebtorService.getById(detailCommand.getId());
		DataCompanyDiscreditedJudgmentDebtorVO dataCompanyDiscreditedJudgmentDebtorVO = DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorVO(byId);
		return SingleResponse.of(dataCompanyDiscreditedJudgmentDebtorVO);
	}
	/**
	 * 执行 企业失信被执行人 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyDiscreditedJudgmentDebtorVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyDiscreditedJudgmentDebtorDO byId = iDataCompanyDiscreditedJudgmentDebtorService.getById(detailForUpdateCommand.getId());
		DataCompanyDiscreditedJudgmentDebtorVO dataCompanyDiscreditedJudgmentDebtorVO = DataCompanyDiscreditedJudgmentDebtorAppStructMapping.instance.dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorVO(byId);
		return SingleResponse.of(dataCompanyDiscreditedJudgmentDebtorVO);
	}


	@Autowired
	public void setIDataCompanyDiscreditedJudgmentDebtorService(IDataCompanyDiscreditedJudgmentDebtorService iDataCompanyDiscreditedJudgmentDebtorService) {
		this.iDataCompanyDiscreditedJudgmentDebtorService = iDataCompanyDiscreditedJudgmentDebtorService;
	}
}
