package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportShareholderWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportShareholderExWarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报股东入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyAnnualReportShareholderWarehouseCommandExecutor;

	/**
	 * 企业年报股东入库
	 * @param dataCompanyAnnualReportShareholderExWarehouseVOS
	 * @return
	 */
	public void warehouse(List<DataCompanyAnnualReportShareholderExWarehouseVO> dataCompanyAnnualReportShareholderExWarehouseVOS,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		for (DataCompanyAnnualReportShareholderExWarehouseVO shareholder : dataCompanyAnnualReportShareholderExWarehouseVOS) {
			warehouse(shareholder, companyId, companyAnnualReportId, year);
		}
	}

	/**
	 * 企业年报股东入库
	 * @param dataCompanyAnnualReportShareholderExWarehouseVO
	 * @param companyId
	 * @param companyAnnualReportId
	 * @param year
	 */
	public void warehouse(DataCompanyAnnualReportShareholderExWarehouseVO dataCompanyAnnualReportShareholderExWarehouseVO,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyAnnualReportShareholderWarehouseCommand = DataCompanyAnnualReportShareholderWarehouseCommand.createByDataCompanyAnnualReportShareholderExWarehouseVO(dataCompanyAnnualReportShareholderExWarehouseVO);
		if (companyId != null) {
			dataCompanyAnnualReportShareholderWarehouseCommand.setCompanyId(companyId);
		}
		if (companyAnnualReportId != null) {
			dataCompanyAnnualReportShareholderWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
		}
		if (year != null) {
			dataCompanyAnnualReportShareholderWarehouseCommand.setYear(year);
		}

		// 股东名称
		NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyAnnualReportShareholderWarehouseCommand.getShareholderName(),
				dataCompanyAnnualReportShareholderWarehouseCommand.getShareholderCompanyId(),
				dataCompanyAnnualReportShareholderWarehouseCommand.getShareholderCompanyPersonId(),
				dataCompanyAnnualReportShareholderWarehouseCommand.getIsShareholderNaturalPerson());
		if (legalNaturePerson != null) {
			dataCompanyAnnualReportShareholderWarehouseCommand.setShareholderCompanyId(legalNaturePerson.getCompanyId());
			dataCompanyAnnualReportShareholderWarehouseCommand.setShareholderCompanyPersonId(legalNaturePerson.getPersonId());
			dataCompanyAnnualReportShareholderWarehouseCommand.setIsShareholderNaturalPerson(legalNaturePerson.getIsNaturePerson());
		}

		dataCompanyAnnualReportShareholderWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportShareholderWarehouseCommand);
	}

	@Autowired
	public void setDataCompanyAnnualReportShareholderWarehouseCommandExecutor(DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyAnnualReportShareholderWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportShareholderWarehouseCommandExecutor = dataCompanyAnnualReportShareholderWarehouseCommandExecutor;
	}
}
