package com.particle.data.app.company.executor.representation;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAppStructMapping;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.data.client.company.dto.command.representation.DataCompanyAnnualReportPageQueryCommand;
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
 * 企业年报 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Component
@Validated
public class DataCompanyAnnualReportQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;

	/**
	 * 执行 企业年报 列表查询指令
	 * @param dataCompanyAnnualReportQueryListCommand
	 * @return
	 */
	public MultiResponse<DataCompanyAnnualReportVO> execute(@Valid DataCompanyAnnualReportQueryListCommand dataCompanyAnnualReportQueryListCommand) {
		List<DataCompanyAnnualReportDO> dataCompanyAnnualReportDO = iDataCompanyAnnualReportService.list(dataCompanyAnnualReportQueryListCommand);
		List<DataCompanyAnnualReportVO> dataCompanyAnnualReportVOs = DataCompanyAnnualReportAppStructMapping.instance.dataCompanyAnnualReportDOsToDataCompanyAnnualReportVOs(dataCompanyAnnualReportDO);
		return MultiResponse.of(dataCompanyAnnualReportVOs);
	}
	/**
	 * 执行 企业年报 分页查询指令
	 * @param dataCompanyAnnualReportPageQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportVO> execute(@Valid DataCompanyAnnualReportPageQueryCommand dataCompanyAnnualReportPageQueryCommand) {
		Page<DataCompanyAnnualReportDO> page = iDataCompanyAnnualReportService.listPage(dataCompanyAnnualReportPageQueryCommand);
		return DataCompanyAnnualReportAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 企业年报 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportVO> executeDetail(IdCommand detailCommand) {
		DataCompanyAnnualReportDO byId = iDataCompanyAnnualReportService.getById(detailCommand.getId());
		DataCompanyAnnualReportVO dataCompanyAnnualReportVO = DataCompanyAnnualReportAppStructMapping.instance.dataCompanyAnnualReportDOToDataCompanyAnnualReportVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportVO);
	}
	/**
	 * 执行 企业年报 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DataCompanyAnnualReportDO byId = iDataCompanyAnnualReportService.getById(detailForUpdateCommand.getId());
		DataCompanyAnnualReportVO dataCompanyAnnualReportVO = DataCompanyAnnualReportAppStructMapping.instance.dataCompanyAnnualReportDOToDataCompanyAnnualReportVO(byId);
		return SingleResponse.of(dataCompanyAnnualReportVO);
	}


	@Autowired
	public void setIDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
		this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
	}
}
