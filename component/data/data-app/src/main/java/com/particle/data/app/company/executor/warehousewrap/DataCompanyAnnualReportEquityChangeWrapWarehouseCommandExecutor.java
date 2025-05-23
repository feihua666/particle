
package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportEquityChangeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportEquityChangeExWarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报股权变更入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;

	/**
	 * 企业年报股权变更入库
	 * @param dataCompanyAnnualReportEquityChangeExWarehouseVOS
	 * @return
	 */
	public void warehouse(List<DataCompanyAnnualReportEquityChangeExWarehouseVO> dataCompanyAnnualReportEquityChangeExWarehouseVOS,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		for (DataCompanyAnnualReportEquityChangeExWarehouseVO equityChange : dataCompanyAnnualReportEquityChangeExWarehouseVOS) {
			warehouse(equityChange, companyId, companyAnnualReportId, year);
		}
	}

	/**
	 * 企业年报股权变更入库
	 * @param dataCompanyAnnualReportEquityChangeExWarehouseVO
	 * @param companyId
	 * @param companyAnnualReportId
	 * @param year
	 */
	public void warehouse(DataCompanyAnnualReportEquityChangeExWarehouseVO dataCompanyAnnualReportEquityChangeExWarehouseVO,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyAnnualReportEquityChangeWarehouseCommand = DataCompanyAnnualReportEquityChangeWarehouseCommand.createByDataCompanyAnnualReportEquityChangeExWarehouseVO(dataCompanyAnnualReportEquityChangeExWarehouseVO);
		if (companyId != null) {
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setCompanyId(companyId);
		}
		if (companyAnnualReportId != null) {
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
		}
		if (year != null) {
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setYear(year);
		}

		// 股东处理
		NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyAnnualReportEquityChangeWarehouseCommand.getShareholderName(),
				dataCompanyAnnualReportEquityChangeWarehouseCommand.getShareholderCompanyId(),
				dataCompanyAnnualReportEquityChangeWarehouseCommand.getShareholderCompanyPersonId(),
				dataCompanyAnnualReportEquityChangeWarehouseCommand.getIsShareholderNaturalPerson());
		if (legalNaturePerson != null) {
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setShareholderCompanyId(legalNaturePerson.getCompanyId());
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setShareholderCompanyPersonId(legalNaturePerson.getPersonId());
			dataCompanyAnnualReportEquityChangeWarehouseCommand.setIsShareholderNaturalPerson(legalNaturePerson.getIsNaturePerson());
		}

		dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportEquityChangeWarehouseCommand);
	}

	@Autowired
	public void setDataCompanyAnnualReportEquityChangeWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;
	}
}
