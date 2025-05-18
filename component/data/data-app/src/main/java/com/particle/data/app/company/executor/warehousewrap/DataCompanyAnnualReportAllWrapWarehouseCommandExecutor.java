package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.*;
import com.particle.data.client.company.dto.command.warehouse.*;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.PageResponse;
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
	private DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;
	private DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyAnnualReportShareholderWarehouseCommandExecutor;
	private DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
	private DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyAnnualReportWarehouseCommandExecutor;
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
				if (basic != null) {
					DataCompanyAnnualReportWarehouseCommand dataCompanyAnnualReportWarehouseCommand = DataCompanyAnnualReportWarehouseCommand.createByDataCompanyAnnualReportExWarehouseVO(basic);
					dataCompanyAnnualReportWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportWarehouseCommand);
				}
				DataCompanyAnnualReportSocialSecurityExWarehouseVO socialSecurity = companyAnnualReportAllExWarehouseVO.getSocialSecurity();
				if (socialSecurity != null) {
					DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyAnnualReportSocialSecurityWarehouseCommand = DataCompanyAnnualReportSocialSecurityWarehouseCommand.createByDataCompanyAnnualReportSocialSecurityExWarehouseVO(socialSecurity);
					dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportSocialSecurityWarehouseCommand);
				}
				DataCompanyAnnualReportAssetsExWarehouseVO assets = companyAnnualReportAllExWarehouseVO.getAssets();
				if (assets != null) {
					DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyAnnualReportAssetsWarehouseCommand = DataCompanyAnnualReportAssetsWarehouseCommand.createByDataCompanyAnnualReportAssetsExWarehouseVO(assets);
					dataCompanyAnnualReportAssetsWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportAssetsWarehouseCommand);
				}

				List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> administrativeLicenses = companyAnnualReportAllExWarehouseVO.getAdministrativeLicenses();
				if (CollectionUtil.isNotEmpty(administrativeLicenses)) {
					for (DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO administrativeLicense : administrativeLicenses) {
						DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand = DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand.createByDataCompanyAnnualReportAdministrativeLicenseExWarehouseVO(administrativeLicense);
						dataCompanyAnnualReportAdministrativeLicenseWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportAdministrativeLicenseWarehouseCommand);
					}
				}
				List<DataCompanyAnnualReportChangeExWarehouseVO> changes = companyAnnualReportAllExWarehouseVO.getChanges();
				if (CollectionUtil.isNotEmpty(changes)) {
					for (DataCompanyAnnualReportChangeExWarehouseVO change : changes) {
						DataCompanyAnnualReportChangeWarehouseCommand dataCompanyAnnualReportChangeWarehouseCommand = DataCompanyAnnualReportChangeWarehouseCommand.createByDataCompanyAnnualReportChangeExWarehouseVO(change);
						dataCompanyAnnualReportChangeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportChangeWarehouseCommand);
					}
				}

				List<DataCompanyAnnualReportEquityChangeExWarehouseVO> equityChanges = companyAnnualReportAllExWarehouseVO.getEquityChanges();
				if (CollectionUtil.isNotEmpty(equityChanges)) {
					for (DataCompanyAnnualReportEquityChangeExWarehouseVO equityChange : equityChanges) {
						DataCompanyAnnualReportEquityChangeWarehouseCommand dataCompanyAnnualReportEquityChangeWarehouseCommand = DataCompanyAnnualReportEquityChangeWarehouseCommand.createByDataCompanyAnnualReportEquityChangeExWarehouseVO(equityChange);
						dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportEquityChangeWarehouseCommand);
					}
				}
				List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> foreignGuarantees = companyAnnualReportAllExWarehouseVO.getForeignGuarantees();
				if (CollectionUtil.isNotEmpty(foreignGuarantees)) {
					for (DataCompanyAnnualReportForeignGuaranteeExWarehouseVO foreignGuarantee : foreignGuarantees) {
						DataCompanyAnnualReportForeignGuaranteeWarehouseCommand dataCompanyAnnualReportForeignGuaranteeWarehouseCommand = DataCompanyAnnualReportForeignGuaranteeWarehouseCommand.createByDataCompanyAnnualReportForeignGuaranteeExWarehouseVO(foreignGuarantee);
						dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportForeignGuaranteeWarehouseCommand);
					}
				}
				List<DataCompanyAnnualReportForeignInvestExWarehouseVO> foreignInvest = companyAnnualReportAllExWarehouseVO.getForeignInvests();
				if (CollectionUtil.isNotEmpty(foreignInvest)) {
					for (DataCompanyAnnualReportForeignInvestExWarehouseVO foreignInvestExWarehouseVO : foreignInvest) {
						DataCompanyAnnualReportForeignInvestWarehouseCommand dataCompanyAnnualReportForeignInvestWarehouseCommand = DataCompanyAnnualReportForeignInvestWarehouseCommand.createByDataCompanyAnnualReportForeignInvestExWarehouseVO(foreignInvestExWarehouseVO);
						dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportForeignInvestWarehouseCommand);
					}
				}
				List<DataCompanyAnnualReportShareholderExWarehouseVO> shareholders = companyAnnualReportAllExWarehouseVO.getShareholders();
				if (CollectionUtil.isNotEmpty(shareholders)) {
					for (DataCompanyAnnualReportShareholderExWarehouseVO shareholder : shareholders) {
						DataCompanyAnnualReportShareholderWarehouseCommand dataCompanyAnnualReportShareholderWarehouseCommand = DataCompanyAnnualReportShareholderWarehouseCommand.createByDataCompanyAnnualReportShareholderExWarehouseVO(shareholder);
						dataCompanyAnnualReportShareholderWarehouseCommandExecutor.warehouse(dataCompanyAnnualReportShareholderWarehouseCommand);
					}
				}

				List<DataCompanyAnnualReportWebsiteExWarehouseVO> websites = companyAnnualReportAllExWarehouseVO.getWebsites();
				if (CollectionUtil.isNotEmpty(websites)) {
					for (DataCompanyAnnualReportWebsiteExWarehouseVO website : websites) {
						DataCompanyAnnualReportWebsiteWarehouseCommand dataCompanyAnnualReportWebsiteWarehouseCommand = DataCompanyAnnualReportWebsiteWarehouseCommand.createByDataCompanyAnnualReportWebsiteExWarehouseVO(website);
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
	public void setDataCompanyAnnualReportEquityChangeWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportShareholderWarehouseCommandExecutor(DataCompanyAnnualReportShareholderWarehouseCommandExecutor dataCompanyAnnualReportShareholderWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportShareholderWarehouseCommandExecutor = dataCompanyAnnualReportShareholderWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportWarehouseCommandExecutor(DataCompanyAnnualReportWarehouseCommandExecutor dataCompanyAnnualReportWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWarehouseCommandExecutor = dataCompanyAnnualReportWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportWebsiteWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteWarehouseCommandExecutor dataCompanyAnnualReportWebsiteWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteWarehouseCommandExecutor = dataCompanyAnnualReportWebsiteWarehouseCommandExecutor;
	}
}
