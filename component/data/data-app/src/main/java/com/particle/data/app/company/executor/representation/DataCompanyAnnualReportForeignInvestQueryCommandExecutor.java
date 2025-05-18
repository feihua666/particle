package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignInvestAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportForeignInvestDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportForeignInvestService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportForeignInvestPageQueryCommand;
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
 * 企业年报对外投资 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService;

	/**
	 * 执行 企业年报对外投资 列表查询指令
	 * @param dataCompanyAnnualReportForeignInvestQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportForeignInvestVO> execute(@Valid DataCompanyAnnualReportForeignInvestQueryListCommand dataCompanyAnnualReportForeignInvestQueryListCommand) {
		List<DataCompanyAnnualReportForeignInvestDO> dataCompanyAnnualReportForeignInvestDO = iDataCompanyAnnualReportForeignInvestService.list(dataCompanyAnnualReportForeignInvestQueryListCommand);
		List<DataCompanyAnnualReportForeignInvestVO> dataCompanyAnnualReportForeignInvestVOs = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOsToDataCompanyAnnualReportForeignInvestVOs(dataCompanyAnnualReportForeignInvestDO);
		return MultiResponse.of(dataCompanyAnnualReportForeignInvestVOs);
	}
	/**
	 * 执行 企业年报对外投资 分页查询指令
	 * @param dataCompanyAnnualReportForeignInvestPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportForeignInvestVO> execute(@Valid DataCompanyAnnualReportForeignInvestPageQueryCommand dataCompanyAnnualReportForeignInvestPageQueryCommand) {
		Page<DataCompanyAnnualReportForeignInvestDO> page = iDataCompanyAnnualReportForeignInvestService.listPage(dataCompanyAnnualReportForeignInvestPageQueryCommand);
		return DataCompanyAnnualReportForeignInvestAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报对外投资 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportForeignInvestDO byId = iDataCompanyAnnualReportForeignInvestService.getById(detailCommand.getId());
		DataCompanyAnnualReportForeignInvestVO dataCompanyAnnualReportForeignInvestVO = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportForeignInvestVO);
	}
	/**
	 * 执行 企业年报对外投资 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportForeignInvestDO byId = iDataCompanyAnnualReportForeignInvestService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportForeignInvestVO dataCompanyAnnualReportForeignInvestVO = DataCompanyAnnualReportForeignInvestAppStructMapping.instance.dataCompanyAnnualReportForeignInvestDOToDataCompanyAnnualReportForeignInvestVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportForeignInvestVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportForeignInvestService(IDataCompanyAnnualReportForeignInvestService iDataCompanyAnnualReportForeignInvestService) {
		this.iDataCompanyAnnualReportForeignInvestService = iDataCompanyAnnualReportForeignInvestService;
	}
}
