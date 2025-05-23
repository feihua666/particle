package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportForeignGuaranteeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportForeignGuaranteeExWarehouseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报对外担保入库 指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:14:10
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;

	/**
	 * 企业年报对外担保入库
	 * @param dataCompanyAnnualReportForeignGuaranteeExWarehouseVOS
	 * @return
	 */
	public void warehouse(List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> dataCompanyAnnualReportForeignGuaranteeExWarehouseVOS,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		for (DataCompanyAnnualReportForeignGuaranteeExWarehouseVO foreignGuarantee : dataCompanyAnnualReportForeignGuaranteeExWarehouseVOS) {
			warehouse(foreignGuarantee, companyId, companyAnnualReportId, year);
		}
	}

	/**
	 * 企业年报对外担保入库
	 * @param dataCompanyAnnualReportForeignGuaranteeExWarehouseVO
	 * @param companyId
	 * @param companyAnnualReportId
	 * @param year
	 */
	public void warehouse(DataCompanyAnnualReportForeignGuaranteeExWarehouseVO dataCompanyAnnualReportForeignGuaranteeExWarehouseVO,
						  Long companyId,
						  Long companyAnnualReportId,
						  Integer year) {
		DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyAnnualReportForeignGuaranteeWarehouseCommand = DataCompanyAnnualReportForeignGuaranteeWarehouseCommand.createByDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(dataCompanyAnnualReportForeignGuaranteeExWarehouseVO);
		if (companyId != null) {
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setCompanyId(companyId);
		}
		if (companyAnnualReportId != null) {
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
		}
		if (year != null) {
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setYear(year);
		}

		// 债务人
		NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getDebtorName(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getDebtorCompanyId(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getDebtorCompanyPersonId(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getIsDebtorNaturalPerson());
		if (legalNaturePerson != null) {
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setDebtorCompanyId(legalNaturePerson.getCompanyId());
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setDebtorCompanyPersonId(legalNaturePerson.getPersonId());
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setIsDebtorNaturalPerson(legalNaturePerson.getIsNaturePerson());
		}
		legalNaturePerson = checkNaturePerson(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getCreditorName(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getCreditorCompanyId(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getCreditorCompanyPersonId(),
				dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.getIsCreditorNaturalPerson());
		if (legalNaturePerson != null) {
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setCreditorCompanyId(legalNaturePerson.getCompanyId());
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setCreditorCompanyPersonId(legalNaturePerson.getPersonId());
			dataCompanyAnnualReportForeignGuaranteeWarehouseCommand.setIsCreditorNaturalPerson(legalNaturePerson.getIsNaturePerson());
		}

		dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand);
	}

	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;
	}
}
