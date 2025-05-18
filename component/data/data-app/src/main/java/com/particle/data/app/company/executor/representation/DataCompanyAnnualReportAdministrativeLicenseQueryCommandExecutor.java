package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicenseQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportAdministrativeLicenseDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportAdministrativeLicenseService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportAdministrativeLicensePageQueryCommand;
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
 * 企业年报行政许可 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService;

	/**
	 * 执行 企业年报行政许可 列表查询指令
	 * @param dataCompanyAnnualReportAdministrativeLicenseQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportAdministrativeLicenseVO> execute(@Valid DataCompanyAnnualReportAdministrativeLicenseQueryListCommand dataCompanyAnnualReportAdministrativeLicenseQueryListCommand) {
		List<DataCompanyAnnualReportAdministrativeLicenseDO> dataCompanyAnnualReportAdministrativeLicenseDO = iDataCompanyAnnualReportAdministrativeLicenseService.list(dataCompanyAnnualReportAdministrativeLicenseQueryListCommand);
		List<DataCompanyAnnualReportAdministrativeLicenseVO> dataCompanyAnnualReportAdministrativeLicenseVOs = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOsToDataCompanyAnnualReportAdministrativeLicenseVOs(dataCompanyAnnualReportAdministrativeLicenseDO);
		return MultiResponse.of(dataCompanyAnnualReportAdministrativeLicenseVOs);
	}
	/**
	 * 执行 企业年报行政许可 分页查询指令
	 * @param dataCompanyAnnualReportAdministrativeLicensePageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAdministrativeLicenseVO> execute(@Valid DataCompanyAnnualReportAdministrativeLicensePageQueryCommand dataCompanyAnnualReportAdministrativeLicensePageQueryCommand) {
		Page<DataCompanyAnnualReportAdministrativeLicenseDO> page = iDataCompanyAnnualReportAdministrativeLicenseService.listPage(dataCompanyAnnualReportAdministrativeLicensePageQueryCommand);
		return DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报行政许可 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportAdministrativeLicenseDO byId = iDataCompanyAnnualReportAdministrativeLicenseService.getById(detailCommand.getId());
		DataCompanyAnnualReportAdministrativeLicenseVO dataCompanyAnnualReportAdministrativeLicenseVO = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportAdministrativeLicenseVO);
	}
	/**
	 * 执行 企业年报行政许可 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportAdministrativeLicenseDO byId = iDataCompanyAnnualReportAdministrativeLicenseService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportAdministrativeLicenseVO dataCompanyAnnualReportAdministrativeLicenseVO = DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.dataCompanyAnnualReportAdministrativeLicenseDOToDataCompanyAnnualReportAdministrativeLicenseVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportAdministrativeLicenseVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportAdministrativeLicenseService(IDataCompanyAnnualReportAdministrativeLicenseService iDataCompanyAnnualReportAdministrativeLicenseService) {
		this.iDataCompanyAnnualReportAdministrativeLicenseService = iDataCompanyAnnualReportAdministrativeLicenseService;
	}
}
