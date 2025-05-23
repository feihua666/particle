package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignInvestWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignInvestExWarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报对外投资入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;

	/**
	 * 企业年报对外投资入库
	 * @param dataCompanyAnnualReportForeignInvestExWarehouseVOS
	 * @return
	 */
	public void warehouse(List<DataCompanyAnnualReportForeignInvestExWarehouseVO> dataCompanyAnnualReportForeignInvestExWarehouseVOS,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		for (DataCompanyAnnualReportForeignInvestExWarehouseVO foreignInvest : dataCompanyAnnualReportForeignInvestExWarehouseVOS) {
			warehouse(foreignInvest, companyId, companyAnnualReportId, year);
		}
	}

	/**
	 * 企业年报对外投资入库
	 * @param dataCompanyAnnualReportForeignInvestExWarehouseVO
	 * @param companyId
	 * @param companyAnnualReportId
	 * @param year
	 */
	public void warehouse(DataCompanyAnnualReportForeignInvestExWarehouseVO dataCompanyAnnualReportForeignInvestExWarehouseVO,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyAnnualReportForeignInvestWarehouseCommand = DataCompanyAnnualReportForeignInvestWarehouseCommand.createByDataCompanyAnnualReportForeignInvestExWarehouseVO(dataCompanyAnnualReportForeignInvestExWarehouseVO);
		if (companyId != null) {
			dataCompanyAnnualReportForeignInvestWarehouseCommand.setCompanyId(companyId);
		}
		if (companyAnnualReportId != null) {
			dataCompanyAnnualReportForeignInvestWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
		}
		if (year != null) {
			dataCompanyAnnualReportForeignInvestWarehouseCommand.setYear(year);
		}

		if (dataCompanyAnnualReportForeignInvestWarehouseCommand.getInvestCompanyId() == null) {
			Long investCompanyId = warehouseCompanyGetCompanyId(dataCompanyAnnualReportForeignInvestWarehouseCommand.getInvestCompanyName(),
					dataCompanyAnnualReportForeignInvestWarehouseCommand.getInvestCompanyUscc(),
					dataCompanyAnnualReportForeignInvestWarehouseCommand.getInvestCompanyRegNo());
			dataCompanyAnnualReportForeignInvestWarehouseCommand.setInvestCompanyId(investCompanyId);
		}

		dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportForeignInvestWarehouseCommand);
	}

	@Autowired
	public void setDataCompanyAnnualReportForeignInvestWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;
	}
}
