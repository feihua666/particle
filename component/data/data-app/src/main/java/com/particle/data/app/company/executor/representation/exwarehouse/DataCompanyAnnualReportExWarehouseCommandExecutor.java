package com.particle.data.app.company.executor.representation.exwarehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportDO;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业年报出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyAnnualReportExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyAnnualReportService iDataCompanyAnnualReportService;

	/**
	 * 企业年报出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportExWarehouseVO> exWarehouse(@Valid DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyAnnualReportDO> dataCompanyAnnualReportDOPage = iDataCompanyAnnualReportService.listPageByCompanyIdOrderByYearDesc(dataCompanyExWarehouseQueryCommand.getCompanyId(),dataCompanyExWarehouseQueryCommand.getYear(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyAnnualReportDOPage == null || dataCompanyAnnualReportDOPage.getRecords() == null || dataCompanyAnnualReportDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyAnnualReportAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyAnnualReportDOPage);
	}
	/**
	 * 企业年报出库
	 * @param companyId
	 * @param year
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportExWarehouseVO> exWarehouseByCompanyIdAndYear(Long companyId, Integer year) {
		DataCompanyAnnualReportDO dataCompanyAnnualReportDO = iDataCompanyAnnualReportService.getByCompanyIdAndYear(companyId,year);
		if (dataCompanyAnnualReportDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportExWarehouseVO = DataCompanyAnnualReportAppStructMapping.instance.dataCompanyAnnualReportDOToDataCompanyAnnualReportExWarehouseVO(dataCompanyAnnualReportDO);
		return SingleResponse.of(dataCompanyAnnualReportExWarehouseVO);
	}

	/**
	 * 企业年报出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyAnnualReportDO dataCompanyAnnualReportDO = iDataCompanyAnnualReportService.getById(id);
		if (dataCompanyAnnualReportDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportExWarehouseVO = DataCompanyAnnualReportAppStructMapping.instance.dataCompanyAnnualReportDOToDataCompanyAnnualReportExWarehouseVO(dataCompanyAnnualReportDO);
		return SingleResponse.of(dataCompanyAnnualReportExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyAnnualReportService(IDataCompanyAnnualReportService iDataCompanyAnnualReportService) {
		this.iDataCompanyAnnualReportService = iDataCompanyAnnualReportService;
	}
}
