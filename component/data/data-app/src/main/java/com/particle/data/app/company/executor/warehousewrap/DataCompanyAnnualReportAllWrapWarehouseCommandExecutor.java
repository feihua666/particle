package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.*;
import com.particle.data.client.company.dto.command.warehouse.*;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业年报全部入库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-16 16:14:51
 */
@Component
@Validated
public class DataCompanyAnnualReportAllWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor;
	private DataCompanyAnnualReportAssetsWarehouseCommandExecutor dataCompanyAnnualReportAssetsWarehouseCommandExecutor;
	private DataCompanyAnnualReportChangeWarehouseCommandExecutor dataCompanyAnnualReportChangeWarehouseCommandExecutor;
	private DataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor dataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
	private DataCompanyAnnualReportWrapWarehouseCommandExecutor dataCompanyAnnualReportWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportWebsiteWarehouseCommandExecutor dataCompanyAnnualReportWebsiteWarehouseCommandExecutor;

	/**
	 * 企业年报全部入库
	 * @param dataCompanyAnnualReportAllExWarehouseVOPageResponse
	 * @return
	 */
	public void warehouse(PageResponse<DataCompanyAnnualReportAllExWarehouseVO> dataCompanyAnnualReportAllExWarehouseVOPageResponse) {
		List<DataCompanyAnnualReportAllExWarehouseVO> data = dataCompanyAnnualReportAllExWarehouseVOPageResponse.getData();
		if (CollectionUtil.isNotEmpty(data)) {
			for (DataCompanyAnnualReportAllExWarehouseVO companyAnnualReportAllExWarehouseVO : data) {
				DataCompanyAnnualReportExWarehouseVO basic = companyAnnualReportAllExWarehouseVO.getBasic();
				Long companyId = null;
				Long companyAnnualReportId = null;
				Integer year = null;
				if (basic != null) {
					SingleResponse<DataCompanyAnnualReportExWarehouseVO> warehouse = dataCompanyAnnualReportWrapWarehouseCommandExecutor.warehouse(basic);
					companyId = warehouse.getData().getCompanyId();
					companyAnnualReportId = warehouse.getData().getId();
					year = warehouse.getData().getYear();
				}
				DataCompanyAnnualReportSocialSecurityExWarehouseVO socialSecurity = companyAnnualReportAllExWarehouseVO.getSocialSecurity();
				if (socialSecurity != null) {
					DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyAnnualReportSocialSecurityWarehouseCommand = DataCompanyAnnualReportSocialSecurityWarehouseCommand.createByDataCompanyAnnualReportSocialSecurityExWarehouseVO(socialSecurity);
					dataCompanyAnnualReportSocialSecurityWarehouseCommand.setCompanyId(companyId);
					dataCompanyAnnualReportSocialSecurityWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
					dataCompanyAnnualReportSocialSecurityWarehouseCommand.setYear(year);
					dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportSocialSecurityWarehouseCommand);
				}
				DataCompanyAnnualReportAssetsExWarehouseVO assets = companyAnnualReportAllExWarehouseVO.getAssets();
				if (assets != null) {
					DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyAnnualReportAssetsWarehouseCommand = DataCompanyAnnualReportAssetsWarehouseCommand.createByDataCompanyAnnualReportAssetsExWarehouseVO(assets);
					dataCompanyAnnualReportAssetsWarehouseCommand.setCompanyId(companyId);
					dataCompanyAnnualReportAssetsWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
					dataCompanyAnnualReportAssetsWarehouseCommand.setYear(year);
					dataCompanyAnnualReportAssetsWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportAssetsWarehouseCommand);
				}

				List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> administrativeLicenses = companyAnnualReportAllExWarehouseVO.getAdministrativeLicenses();
				if (CollectionUtil.isNotEmpty(administrativeLicenses)) {
					for (DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO administrativeLicense : administrativeLicenses) {
						DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand = DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand.createByDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(administrativeLicense);
						dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand.setCompanyId(companyId);
						dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
						dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand.setYear(year);
						dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand);
					}
				}
				List<DataCompanyAnnualReportChangeExWarehouseVO> changes = companyAnnualReportAllExWarehouseVO.getChanges();
				if (CollectionUtil.isNotEmpty(changes)) {
					for (DataCompanyAnnualReportChangeExWarehouseVO change : changes) {
						DataCompanyAnnualReportChangeWarehouseCommand dataCompanyAnnualReportChangeWarehouseCommand = DataCompanyAnnualReportChangeWarehouseCommand.createByDataCompanyAnnualReportChangeExWarehouseVO(change);
						dataCompanyAnnualReportChangeWarehouseCommand.setCompanyId(companyId);
						dataCompanyAnnualReportChangeWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
						dataCompanyAnnualReportChangeWarehouseCommand.setYear(year);
						dataCompanyAnnualReportChangeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportChangeWarehouseCommand);
					}
				}

				List<DataCompanyAnnualReportEquityChangeExWarehouseVO> equityChanges = companyAnnualReportAllExWarehouseVO.getEquityChanges();
				if (CollectionUtil.isNotEmpty(equityChanges)) {
					dataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor.warehouse(equityChanges,companyId,companyAnnualReportId,year);
				}
				List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> foreignGuarantees = companyAnnualReportAllExWarehouseVO.getForeignGuarantees();
				if (CollectionUtil.isNotEmpty(foreignGuarantees)) {
					dataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor.warehouse(foreignGuarantees,companyId,companyAnnualReportId,year);
				}
				List<DataCompanyAnnualReportForeignInvestExWarehouseVO> foreignInvest = companyAnnualReportAllExWarehouseVO.getForeignInvests();
				if (CollectionUtil.isNotEmpty(foreignInvest)) {
					dataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor.warehouse(foreignInvest, companyId, companyAnnualReportId, year);
				}
				List<DataCompanyAnnualReportShareholderExWarehouseVO> shareholders = companyAnnualReportAllExWarehouseVO.getShareholders();
				if (CollectionUtil.isNotEmpty(shareholders)) {
					dataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor.warehouse(shareholders, companyId, companyAnnualReportId, year);
				}

				List<DataCompanyAnnualReportWebsiteExWarehouseVO> websites = companyAnnualReportAllExWarehouseVO.getWebsites();
				if (CollectionUtil.isNotEmpty(websites)) {
					for (DataCompanyAnnualReportWebsiteExWarehouseVO website : websites) {
						DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyAnnualReportWebsiteWarehouseCommand = DataCompanyAnnualReportWebsiteWarehouseCommand.createByDataCompanyAnnualReportWebsiteExWarehouseVO(website);
						dataCompanyAnnualReportWebsiteWarehouseCommand.setCompanyId(companyId);
						dataCompanyAnnualReportWebsiteWarehouseCommand.setCompanyAnnualReportId(companyAnnualReportId);
						dataCompanyAnnualReportWebsiteWarehouseCommand.setYear(year);
						dataCompanyAnnualReportWebsiteWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportWebsiteWarehouseCommand);
					}
				}

			}
		}

	}

	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportAssetsWarehouseCommandExecutor(DataCompanyAnnualReportAssetsWarehouseCommandExecutor dataCompanyAnnualReportAssetsWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAssetsWarehouseCommandExecutor = dataCompanyAnnualReportAssetsWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportChangeWarehouseCommandExecutor(DataCompanyAnnualReportChangeWarehouseCommandExecutor dataCompanyAnnualReportChangeWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportChangeWarehouseCommandExecutor = dataCompanyAnnualReportChangeWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor(DataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor dataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor = dataCompanyAnnualReportShareholderWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyAnnualReportWrapWarehouseCommandExecutor(DataCompanyAnnualReportWrapWarehouseCommandExecutor dataCompanyAnnualReportWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWrapWarehouseCommandExecutor = dataCompanyAnnualReportWrapWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyAnnualReportWebsiteWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteWarehouseCommandExecutor dataCompanyAnnualReportWebsiteWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteWarehouseCommandExecutor = dataCompanyAnnualReportWebsiteWarehouseCommandExecutor;
	}
}
