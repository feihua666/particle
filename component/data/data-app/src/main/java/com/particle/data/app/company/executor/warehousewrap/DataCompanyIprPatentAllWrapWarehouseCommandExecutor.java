package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.*;
import com.particle.data.client.company.dto.command.warehouse.*;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.data.domain.gateway.DataDictGateway;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权专利全部入库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentAllWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyIprPatentCertificateWarehouseCommandExecutor dataCompanyIprPatentCertificateWarehouseCommandExecutor;
	private DataCompanyIprPatentCitedWarehouseCommandExecutor dataCompanyIprPatentCitedWarehouseCommandExecutor;
	private DataCompanyIprPatentContentWarehouseCommandExecutor dataCompanyIprPatentContentWarehouseCommandExecutor;
	private DataCompanyIprPatentFamilyWarehouseCommandExecutor dataCompanyIprPatentFamilyWarehouseCommandExecutor;
	private DataCompanyIprPatentLegalStatusWarehouseCommandExecutor dataCompanyIprPatentLegalStatusWarehouseCommandExecutor;
	private DataCompanyIprPatentLicenseWarehouseCommandExecutor dataCompanyIprPatentLicenseWarehouseCommandExecutor;
	private DataCompanyIprPatentNoticeWarehouseCommandExecutor dataCompanyIprPatentNoticeWarehouseCommandExecutor;
	private DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyIprPatentPartyWarehouseCommandExecutor;
	private DataCompanyIprPatentPaymentWarehouseCommandExecutor dataCompanyIprPatentPaymentWarehouseCommandExecutor;
	private DataCompanyIprPatentPledgeWarehouseCommandExecutor dataCompanyIprPatentPledgeWarehouseCommandExecutor;
	private DataCompanyIprPatentQuoteWarehouseCommandExecutor dataCompanyIprPatentQuoteWarehouseCommandExecutor;
	private DataCompanyIprPatentStatisticWarehouseCommandExecutor dataCompanyIprPatentStatisticWarehouseCommandExecutor;
	private DataCompanyIprPatentTransferWarehouseCommandExecutor dataCompanyIprPatentTransferWarehouseCommandExecutor;
	private DataCompanyIprPatentWrapWarehouseCommandExecutor dataCompanyIprPatentWrapWarehouseCommandExecutor;

	private DataCompanyIprPatentPartyWrapWarehouseCommandExecutor dataCompanyIprPatentPartyWrapWarehouseCommandExecutor;

	private DataDictGateway dataDictGateway;
	/**
	 * 企业知识产权专利全部入库
	 * @param dataCompanyIprPatentAllExWarehouseVOPageResponse
	 * @return
	 */
	public void warehouse(PageResponse<DataCompanyIprPatentAllExWarehouseVO> dataCompanyIprPatentAllExWarehouseVOPageResponse) {
		List<DataCompanyIprPatentAllExWarehouseVO> data = dataCompanyIprPatentAllExWarehouseVOPageResponse.getData();
		if (CollectionUtil.isNotEmpty(data)) {
			for (DataCompanyIprPatentAllExWarehouseVO companyIprPatentAllExWarehouseVO : data) {
				DataCompanyIprPatentExWarehouseVO basic = companyIprPatentAllExWarehouseVO.getBasic();
				Long dataCompanyIprPatentId = null;
				if (basic != null) {
					SingleResponse<DataCompanyIprPatentExWarehouseVO> dataCompanyIprPatentExWarehouseVOSingleResponse = dataCompanyIprPatentWrapWarehouseCommandExecutor.warehouse(basic);
					dataCompanyIprPatentId = dataCompanyIprPatentExWarehouseVOSingleResponse.getData().getId();
				}

				List<DataCompanyIprPatentPartyExWarehouseVO> parties = companyIprPatentAllExWarehouseVO.getParties();
				if (CollectionUtil.isNotEmpty(parties)) {
					dataCompanyIprPatentPartyWrapWarehouseCommandExecutor.warehouse(parties, dataCompanyIprPatentId);
				}

				List<DataCompanyIprPatentCertificateExWarehouseVO> certificates = companyIprPatentAllExWarehouseVO.getCertificates();
				if (CollectionUtil.isNotEmpty(certificates)) {
					for (DataCompanyIprPatentCertificateExWarehouseVO certificate : certificates) {
						DataCompanyIprPatentCertificateWarehouseCommand dataCompanyIprPatentCertificateWarehouseCommand = DataCompanyIprPatentCertificateWarehouseCommand.createByDataCompanyIprPatentCertificateExWarehouseVO(certificate);
						dataCompanyIprPatentCertificateWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentCertificateWarehouseCommandExecutor.warehouse(dataCompanyIprPatentCertificateWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentCitedExWarehouseVO> cites = companyIprPatentAllExWarehouseVO.getCites();
				if (CollectionUtil.isNotEmpty(cites)) {
					for (DataCompanyIprPatentCitedExWarehouseVO cite : cites) {
						DataCompanyIprPatentCitedWarehouseCommand dataCompanyIprPatentCitedWarehouseCommand = DataCompanyIprPatentCitedWarehouseCommand.createByDataCompanyIprPatentCitedExWarehouseVO(cite);
						dataCompanyIprPatentCitedWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentCitedWarehouseCommandExecutor.warehouse(dataCompanyIprPatentCitedWarehouseCommand);
					}
				}

				DataCompanyIprPatentContentExWarehouseVO content = companyIprPatentAllExWarehouseVO.getContent();
				if (content != null) {
					DataCompanyIprPatentContentWarehouseCommand dataCompanyIprPatentContentWarehouseCommand = DataCompanyIprPatentContentWarehouseCommand.createByDataCompanyIprPatentContentExWarehouseVO(content);
					dataCompanyIprPatentContentWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
					dataCompanyIprPatentContentWarehouseCommandExecutor.warehouse(dataCompanyIprPatentContentWarehouseCommand);
				}

				List<DataCompanyIprPatentFamilyExWarehouseVO> families = companyIprPatentAllExWarehouseVO.getFamilies();
				if (CollectionUtil.isNotEmpty(families)) {
					for (DataCompanyIprPatentFamilyExWarehouseVO family : families) {
						DataCompanyIprPatentFamilyWarehouseCommand dataCompanyIprPatentFamilyWarehouseCommand = DataCompanyIprPatentFamilyWarehouseCommand.createByDataCompanyIprPatentFamilyExWarehouseVO(family);
						dataCompanyIprPatentFamilyWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentFamilyWarehouseCommandExecutor.warehouse(dataCompanyIprPatentFamilyWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentLegalStatusExWarehouseVO> legalStatuses = companyIprPatentAllExWarehouseVO.getLegalStatuses();
				if (CollectionUtil.isNotEmpty(legalStatuses)) {
					for (DataCompanyIprPatentLegalStatusExWarehouseVO legalStatus : legalStatuses) {
						DataCompanyIprPatentLegalStatusWarehouseCommand dataCompanyIprPatentLegalStatusWarehouseCommand = DataCompanyIprPatentLegalStatusWarehouseCommand.createByDataCompanyIprPatentLegalStatusExWarehouseVO(legalStatus);
						dataCompanyIprPatentLegalStatusWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentLegalStatusWarehouseCommandExecutor.warehouse(dataCompanyIprPatentLegalStatusWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentLicenseExWarehouseVO> licenses = companyIprPatentAllExWarehouseVO.getLicenses();
				if (CollectionUtil.isNotEmpty(licenses)) {
					for (DataCompanyIprPatentLicenseExWarehouseVO license : licenses) {
						DataCompanyIprPatentLicenseWarehouseCommand dataCompanyIprPatentLicenseWarehouseCommand = DataCompanyIprPatentLicenseWarehouseCommand.createByDataCompanyIprPatentLicenseExWarehouseVO(license);
						dataCompanyIprPatentLicenseWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentLicenseWarehouseCommandExecutor.warehouse(dataCompanyIprPatentLicenseWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentNoticeExWarehouseVO> notices = companyIprPatentAllExWarehouseVO.getNotices();
				if (CollectionUtil.isNotEmpty(notices)) {
					for (DataCompanyIprPatentNoticeExWarehouseVO notice : notices) {
						DataCompanyIprPatentNoticeWarehouseCommand dataCompanyIprPatentNoticeWarehouseCommand = DataCompanyIprPatentNoticeWarehouseCommand.createByDataCompanyIprPatentNoticeExWarehouseVO(notice);
						dataCompanyIprPatentNoticeWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentNoticeWarehouseCommandExecutor.warehouse(dataCompanyIprPatentNoticeWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentPaymentExWarehouseVO> payments = companyIprPatentAllExWarehouseVO.getPayments();
				if (CollectionUtil.isNotEmpty(payments)) {
					for (DataCompanyIprPatentPaymentExWarehouseVO payment : payments) {
						DataCompanyIprPatentPaymentWarehouseCommand dataCompanyIprPatentPaymentWarehouseCommand = DataCompanyIprPatentPaymentWarehouseCommand.createByDataCompanyIprPatentPaymentExWarehouseVO(payment);
						dataCompanyIprPatentPaymentWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentPaymentWarehouseCommandExecutor.warehouse(dataCompanyIprPatentPaymentWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentPledgeExWarehouseVO> pledges = companyIprPatentAllExWarehouseVO.getPledges();
				if (CollectionUtil.isNotEmpty(pledges)) {
					for (DataCompanyIprPatentPledgeExWarehouseVO pledge : pledges) {
						DataCompanyIprPatentPledgeWarehouseCommand dataCompanyIprPatentPledgeWarehouseCommand = DataCompanyIprPatentPledgeWarehouseCommand.createByDataCompanyIprPatentPledgeExWarehouseVO(pledge);
						dataCompanyIprPatentPledgeWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentPledgeWarehouseCommandExecutor.warehouse(dataCompanyIprPatentPledgeWarehouseCommand);
					}
				}

				List<DataCompanyIprPatentQuoteExWarehouseVO> quotes = companyIprPatentAllExWarehouseVO.getQuotes();
				if (CollectionUtil.isNotEmpty(quotes)) {
					for (DataCompanyIprPatentQuoteExWarehouseVO quote : quotes) {
						DataCompanyIprPatentQuoteWarehouseCommand dataCompanyIprPatentQuoteWarehouseCommand = DataCompanyIprPatentQuoteWarehouseCommand.createByDataCompanyIprPatentQuoteExWarehouseVO(quote);
						dataCompanyIprPatentQuoteWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentQuoteWarehouseCommandExecutor.warehouse(dataCompanyIprPatentQuoteWarehouseCommand);
					}
				}

				DataCompanyIprPatentStatisticExWarehouseVO statistic = companyIprPatentAllExWarehouseVO.getStatistic();
				if (statistic != null) {
					DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand = DataCompanyIprPatentStatisticWarehouseCommand.createByDataCompanyIprPatentStatisticExWarehouseVO(statistic);
					dataCompanyIprPatentStatisticWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
					dataCompanyIprPatentStatisticWarehouseCommandExecutor.warehouse(dataCompanyIprPatentStatisticWarehouseCommand);
				}

				List<DataCompanyIprPatentTransferExWarehouseVO> transfers = companyIprPatentAllExWarehouseVO.getTransfers();
				if (CollectionUtil.isNotEmpty(transfers)) {
					for (DataCompanyIprPatentTransferExWarehouseVO transfer : transfers) {
						DataCompanyIprPatentTransferWarehouseCommand dataCompanyIprPatentTransferWarehouseCommand = DataCompanyIprPatentTransferWarehouseCommand.createByDataCompanyIprPatentTransferExWarehouseVO(transfer);
						dataCompanyIprPatentTransferWarehouseCommand.setCompanyIprPatentId(dataCompanyIprPatentId);
						dataCompanyIprPatentTransferWarehouseCommandExecutor.warehouse(dataCompanyIprPatentTransferWarehouseCommand);
					}
				}
			}

		}
	}

	@Autowired
	public void setDataCompanyIprPatentCertificateWarehouseCommandExecutor(DataCompanyIprPatentCertificateWarehouseCommandExecutor dataCompanyIprPatentCertificateWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCertificateWarehouseCommandExecutor = dataCompanyIprPatentCertificateWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentCitedWarehouseCommandExecutor(DataCompanyIprPatentCitedWarehouseCommandExecutor dataCompanyIprPatentCitedWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCitedWarehouseCommandExecutor = dataCompanyIprPatentCitedWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentContentWarehouseCommandExecutor(DataCompanyIprPatentContentWarehouseCommandExecutor dataCompanyIprPatentContentWarehouseCommandExecutor) {
		this.dataCompanyIprPatentContentWarehouseCommandExecutor = dataCompanyIprPatentContentWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentFamilyWarehouseCommandExecutor(DataCompanyIprPatentFamilyWarehouseCommandExecutor dataCompanyIprPatentFamilyWarehouseCommandExecutor) {
		this.dataCompanyIprPatentFamilyWarehouseCommandExecutor = dataCompanyIprPatentFamilyWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentLegalStatusWarehouseCommandExecutor(DataCompanyIprPatentLegalStatusWarehouseCommandExecutor dataCompanyIprPatentLegalStatusWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLegalStatusWarehouseCommandExecutor = dataCompanyIprPatentLegalStatusWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentLicenseWarehouseCommandExecutor(DataCompanyIprPatentLicenseWarehouseCommandExecutor dataCompanyIprPatentLicenseWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLicenseWarehouseCommandExecutor = dataCompanyIprPatentLicenseWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentNoticeWarehouseCommandExecutor(DataCompanyIprPatentNoticeWarehouseCommandExecutor dataCompanyIprPatentNoticeWarehouseCommandExecutor) {
		this.dataCompanyIprPatentNoticeWarehouseCommandExecutor = dataCompanyIprPatentNoticeWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPartyWarehouseCommandExecutor(DataCompanyIprPatentPartyWarehouseCommandExecutor dataCompanyIprPatentPartyWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPartyWarehouseCommandExecutor = dataCompanyIprPatentPartyWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPaymentWarehouseCommandExecutor(DataCompanyIprPatentPaymentWarehouseCommandExecutor dataCompanyIprPatentPaymentWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPaymentWarehouseCommandExecutor = dataCompanyIprPatentPaymentWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPledgeWarehouseCommandExecutor(DataCompanyIprPatentPledgeWarehouseCommandExecutor dataCompanyIprPatentPledgeWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPledgeWarehouseCommandExecutor = dataCompanyIprPatentPledgeWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentQuoteWarehouseCommandExecutor(DataCompanyIprPatentQuoteWarehouseCommandExecutor dataCompanyIprPatentQuoteWarehouseCommandExecutor) {
		this.dataCompanyIprPatentQuoteWarehouseCommandExecutor = dataCompanyIprPatentQuoteWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentStatisticWarehouseCommandExecutor(DataCompanyIprPatentStatisticWarehouseCommandExecutor dataCompanyIprPatentStatisticWarehouseCommandExecutor) {
		this.dataCompanyIprPatentStatisticWarehouseCommandExecutor = dataCompanyIprPatentStatisticWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentTransferWarehouseCommandExecutor(DataCompanyIprPatentTransferWarehouseCommandExecutor dataCompanyIprPatentTransferWarehouseCommandExecutor) {
		this.dataCompanyIprPatentTransferWarehouseCommandExecutor = dataCompanyIprPatentTransferWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentWrapWarehouseCommandExecutor(DataCompanyIprPatentWrapWarehouseCommandExecutor dataCompanyIprPatentWrapWarehouseCommandExecutor) {
		this.dataCompanyIprPatentWrapWarehouseCommandExecutor = dataCompanyIprPatentWrapWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPartyWrapWarehouseCommandExecutor(DataCompanyIprPatentPartyWrapWarehouseCommandExecutor dataCompanyIprPatentPartyWrapWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPartyWrapWarehouseCommandExecutor = dataCompanyIprPatentPartyWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataDictGateway(DataDictGateway dataDictGateway) {
		this.dataDictGateway = dataDictGateway;
	}
}
