package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业全貌全部入库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-16 16:14:51
 */
@Component
@Validated
public class DataCompanyAllWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyBasicWrapWarehouseCommandExecutor dataCompanyBasicWrapWarehouseCommandExecutor;
	private DataCompanyAnnualReportAllWrapWarehouseCommandExecutor dataCompanyAnnualReportAllWrapWarehouseCommandExecutor;
	private DataCompanyCaseFilingWrapWarehouseCommandExecutor dataCompanyCaseFilingWrapWarehouseCommandExecutor;
	private DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor;
	private DataCompanyHonorQualificationWrapWarehouseCommandExecutor dataCompanyHonorQualificationWrapWarehouseCommandExecutor;
	private DataCompanyIprPatentAllWrapWarehouseCommandExecutor dataCompanyIprPatentAllWrapWarehouseCommandExecutor;
	private DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor;
	private DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor;
	private DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor;
	private DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor;
	private DataCompanyPunishmentWrapWarehouseCommandExecutor dataCompanyPunishmentWrapWarehouseCommandExecutor;
	private DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor;
	private DataCompanySeriousIllegalWrapWarehouseCommandExecutor dataCompanySeriousIllegalWrapWarehouseCommandExecutor;
	private DataCompanyShareholderWrapWarehouseCommandExecutor dataCompanyShareholderWrapWarehouseCommandExecutor;
	private DataCompanyStatisticWrapWarehouseCommandExecutor dataCompanyStatisticWrapWarehouseCommandExecutor;
	private DataCompanyVcFinancingWrapWarehouseCommandExecutor dataCompanyVcFinancingWrapWarehouseCommandExecutor;
	private DataCompanyVcProductWrapWarehouseCommandExecutor dataCompanyVcProductWrapWarehouseCommandExecutor;
	private DataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor;
	private DataCompanyAbnormalWrapWarehouseCommandExecutor dataCompanyAbnormalWrapWarehouseCommandExecutor;

	private DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor;
	private DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor;
	private DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor;
	private DataCompanyIprGeograWrapWarehouseCommandExecutor dataCompanyIprGeograWrapWarehouseCommandExecutor;
	private DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor;
	private DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor;


	private DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor;
	private DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor;
	private DataCompanyEndCaseWrapWarehouseCommandExecutor dataCompanyEndCaseWrapWarehouseCommandExecutor;
	private DataCompanyEquityPledgeWrapWarehouseCommandExecutor dataCompanyEquityPledgeWrapWarehouseCommandExecutor;
	private DataCompanyIprPledgeWrapWarehouseCommandExecutor dataCompanyIprPledgeWrapWarehouseCommandExecutor;
	private DataCompanyPrimeStaffWrapWarehouseCommandExecutor dataCompanyPrimeStaffWrapWarehouseCommandExecutor;
	private DataCompanySpotCheckWrapWarehouseCommandExecutor dataCompanySpotCheckWrapWarehouseCommandExecutor;
	/**
	 * 企业全貌全部入库
	 * @param dataCompanyAllExWarehouseVOPageResponse
	 * @return
	 */
	public void warehouse(SingleResponse<DataCompanyAllExWarehouseVO> dataCompanyAllExWarehouseVOPageResponse,Long paramCompanyId) {
		DataCompanyAllExWarehouseVO dataCompanyAllExWarehouseVO = dataCompanyAllExWarehouseVOPageResponse.getData();
		if (dataCompanyAllExWarehouseVO == null) {
			return;
		}

		// 企业标识信息
		DataCompanyExWarehouseVO companyUnique = dataCompanyAllExWarehouseVO.getCompanyUnique();
		DataCompanyExWarehouseVO dataCompanyExWarehouseVO = warehouseCompany(DataCompanyWarehouseCommand.createByDataCompanyExWarehouseVO(companyUnique));
		Long companyId = dataCompanyExWarehouseVO == null ? null : dataCompanyExWarehouseVO.getId();

		if (companyId == null) {
			companyId = paramCompanyId;
		}
		// 企业基本信息
		DataCompanyBasicExWarehouseVO basic = dataCompanyAllExWarehouseVO.getBasic();
		if (basic != null && basic.getCompanyId() == null) {
			basic.setCompanyId(companyId);
		}
		dataCompanyBasicWrapWarehouseCommandExecutor.warehouse(SingleResponse.of(basic));

		// 企业年报信息
		List<DataCompanyAnnualReportAllExWarehouseVO> annualReportAlls = dataCompanyAllExWarehouseVO.getAnnualReportAlls();
		if (annualReportAlls != null) {
			for (DataCompanyAnnualReportAllExWarehouseVO annualReportAll : annualReportAlls) {
				DataCompanyAnnualReportExWarehouseVO annualReportAllBasic = annualReportAll.getBasic();
				if (annualReportAllBasic != null && annualReportAllBasic.getCompanyId() == null) {
					annualReportAllBasic.setCompanyId(companyId);
				}
			}
		}
		// 企业年报信息总数
		Integer annualReportAllCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getAnnualReportAllCount());
		dataCompanyAnnualReportAllWrapWarehouseCommandExecutor.warehouse(PageResponse.of(annualReportAlls,annualReportAllCount,CollectionUtil.emptyIfNull(annualReportAlls).size(),1));

		// 企业立案信息
		List<DataCompanyCaseFilingExWarehouseVO> caseFilings = dataCompanyAllExWarehouseVO.getCaseFilings();

		// 企业立案信息总数
		Integer caseFilingCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getCaseFilingCount());
		dataCompanyCaseFilingWrapWarehouseCommandExecutor.warehouse(PageResponse.of(caseFilings,caseFilingCount,CollectionUtil.emptyIfNull(caseFilings).size(),1));

		// 企业法院公告信息
		List<DataCompanyCourtAnnouncementExWarehouseVO> courtAnnouncements = dataCompanyAllExWarehouseVO.getCourtAnnouncements();
		// 企业法院公告信息总数
		Integer courtAnnouncementCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getCourtAnnouncementCount());
		dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor.warehouse(PageResponse.of(courtAnnouncements,courtAnnouncementCount,CollectionUtil.emptyIfNull(courtAnnouncements).size(),1));

		// 企业荣誉资质信息
		List<DataCompanyHonorQualificationExWarehouseVO> honorQualifications = dataCompanyAllExWarehouseVO.getHonorQualifications();
		if (honorQualifications != null) {
			for (DataCompanyHonorQualificationExWarehouseVO honorQualification : honorQualifications) {
				if (honorQualification != null && honorQualification.getCompanyId() == null) {
					honorQualification.setCompanyId(companyId);
				}
			}
		}
		// 企业荣誉资质信息总数
		Integer honorQualificationCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getHonorQualificationCount());
		dataCompanyHonorQualificationWrapWarehouseCommandExecutor.warehouse(PageResponse.of(honorQualifications,honorQualificationCount,CollectionUtil.emptyIfNull(honorQualifications).size(),1));

		// 企业知识产权专利信息
		List<DataCompanyIprPatentAllExWarehouseVO> iprPatentAlls = dataCompanyAllExWarehouseVO.getIprPatentAlls();

		// 企业知识产权专利信息总数
		Integer iprPatentAllCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprPatentAllCount());
		dataCompanyIprPatentAllWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprPatentAlls,iprPatentAllCount,CollectionUtil.emptyIfNull(iprPatentAlls).size(),1));

		// 企业被执行人信息
		List<DataCompanyJudgmentDebtorExWarehouseVO> judgmentDebtors = dataCompanyAllExWarehouseVO.getJudgmentDebtors();

		// 企业被执行人信息总数
		Integer judgmentDebtorCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getJudgmentDebtorCount());
		dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor.warehouse(PageResponse.of(judgmentDebtors,judgmentDebtorCount,CollectionUtil.emptyIfNull(judgmentDebtors).size(),1));

		// 企业失信被执行人信息
		List<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtors = dataCompanyAllExWarehouseVO.getDiscreditedJudgmentDebtors();
		if (discreditedJudgmentDebtors != null) {
			for (DataCompanyDiscreditedJudgmentDebtorExWarehouseVO discreditedJudgmentDebtor : discreditedJudgmentDebtors) {
				Boolean isDishonestExecutedPersonNaturalPerson = discreditedJudgmentDebtor.getIsDishonestExecutedPersonNaturalPerson();
				if (discreditedJudgmentDebtor != null
						&& discreditedJudgmentDebtor.getDishonestExecutedPersonCompanyId() == null
						&& isDishonestExecutedPersonNaturalPerson != null && !isDishonestExecutedPersonNaturalPerson) {
					discreditedJudgmentDebtor.setDishonestExecutedPersonCompanyId(companyId);
				}
			}
		}

		// 企业失信被执行人信息总数
		Integer discreditedJudgmentDebtorCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getDiscreditedJudgmentDebtorCount());
		dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor.warehouse(PageResponse.of(discreditedJudgmentDebtors,discreditedJudgmentDebtorCount,CollectionUtil.emptyIfNull(discreditedJudgmentDebtors).size(),1));

		// 企业裁判文书信息
		List<DataCompanyJudgmentDocumentExWarehouseVO> judgmentDocuments = dataCompanyAllExWarehouseVO.getJudgmentDocuments();

		// 企业裁判文书信息总数
		Integer judgmentDocumentCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getJudgmentDocumentCount());
		dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor.warehouse(PageResponse.of(judgmentDocuments,judgmentDocumentCount,CollectionUtil.emptyIfNull(judgmentDocuments).size(),1));

		// 企业开庭公告信息
		List<DataCompanyOpenCourtAnnouncementExWarehouseVO> openCourtAnnouncements = dataCompanyAllExWarehouseVO.getOpenCourtAnnouncements();

		// 企业开庭公告信息总数
		Integer openCourtAnnouncementCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getOpenCourtAnnouncementCount());
		dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor.warehouse(PageResponse.of(openCourtAnnouncements,openCourtAnnouncementCount,CollectionUtil.emptyIfNull(openCourtAnnouncements).size(),1));

		// 企业行政处罚信息
		List<DataCompanyPunishmentExWarehouseVO> punishments = dataCompanyAllExWarehouseVO.getPunishments();
		if (punishments != null) {
			for (DataCompanyPunishmentExWarehouseVO punishment : punishments) {
				if (punishment != null && punishment.getCompanyId() == null) {
					punishment.setCompanyId(companyId);
				}
			}
		}

		// 企业行政处罚信息总数
		Integer punishmentCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getPunishmentCount());
		dataCompanyPunishmentWrapWarehouseCommandExecutor.warehouse(PageResponse.of(punishments,punishmentCount,CollectionUtil.emptyIfNull(punishments).size(),1));

		// 企业限制高消费信息
		List<DataCompanyRestrictHighConsumeExWarehouseVO> restrictHighConsumes = dataCompanyAllExWarehouseVO.getRestrictHighConsumes();
		// 企业限制高消费信息总数
		Integer restrictHighConsumeCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getRestrictHighConsumeCount());
		dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor.warehouse(PageResponse.of(restrictHighConsumes,restrictHighConsumeCount,CollectionUtil.emptyIfNull(restrictHighConsumes).size(),1));

		// 企业严重违法信息
		List<DataCompanySeriousIllegalExWarehouseVO> seriousIllegals = dataCompanyAllExWarehouseVO.getSeriousIllegals();
		if (seriousIllegals != null) {
			for (DataCompanySeriousIllegalExWarehouseVO seriousIllegal : seriousIllegals) {
				if (seriousIllegal != null && seriousIllegal.getCompanyId() == null) {
					seriousIllegal.setCompanyId(companyId);
				}
			}
		}

		// 企业严重违法信息总数
		Integer seriousIllegalCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getSeriousIllegalCount());
		dataCompanySeriousIllegalWrapWarehouseCommandExecutor.warehouse(PageResponse.of(seriousIllegals,seriousIllegalCount,CollectionUtil.emptyIfNull(seriousIllegals).size(),1));

		// 企业股东信息
		List<DataCompanyShareholderExWarehouseVO> shareholders = dataCompanyAllExWarehouseVO.getShareholders();
		if (shareholders != null) {
			for (DataCompanyShareholderExWarehouseVO shareholder : shareholders) {
				if (shareholder != null && shareholder.getCompanyId() == null) {
					shareholder.setCompanyId(companyId);
				}
			}
		}

		// 企业股东信息总数
		Integer shareholderCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getShareholderCount());
		dataCompanyShareholderWrapWarehouseCommandExecutor.warehouse(PageResponse.of(shareholders,shareholderCount,CollectionUtil.emptyIfNull(shareholders).size(),1));

		// 企业统计信息
		DataCompanyStatisticExWarehouseVO statistic = dataCompanyAllExWarehouseVO.getStatistic();
		if (statistic != null && statistic.getCompanyId() == null) {
			statistic.setCompanyId(companyId);
		}
		dataCompanyStatisticWrapWarehouseCommandExecutor.warehouse(SingleResponse.of(statistic));

		// 企业融资信息
		List<DataCompanyVcFinancingExWarehouseVO> vcFinancings = dataCompanyAllExWarehouseVO.getVcFinancings();
		if (vcFinancings != null) {
			for (DataCompanyVcFinancingExWarehouseVO vcFinancing : vcFinancings) {
				if (vcFinancing != null && vcFinancing.getCompanyId() == null) {
					vcFinancing.setCompanyId(companyId);
				}
			}
		}

		// 企业融资信息总数
		Integer vcFinancingCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getVcFinancingCount());
		dataCompanyVcFinancingWrapWarehouseCommandExecutor.warehouse(PageResponse.of(vcFinancings,vcFinancingCount,CollectionUtil.emptyIfNull(vcFinancings).size(),1));

		// 企业融资产品信息
		List<DataCompanyVcProductExWarehouseVO> vcProducts = dataCompanyAllExWarehouseVO.getVcProducts();
		if (vcProducts != null) {
			for (DataCompanyVcProductExWarehouseVO vcProduct : vcProducts) {
				if (vcProduct != null && vcProduct.getCompanyId() == null) {
					vcProduct.setCompanyId(companyId);
				}
			}
		}

		// 企业融资产品信息总数
		Integer vcProductCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getVcProductCount());
		dataCompanyVcProductWrapWarehouseCommandExecutor.warehouse(PageResponse.of(vcProducts,vcProductCount,CollectionUtil.emptyIfNull(vcProducts).size(),1));

		// 企业投资机构信息
		DataCompanyVcInvestInstitutionExWarehouseVO vcInvestInstitution = dataCompanyAllExWarehouseVO.getVcInvestInstitution();
		if (vcInvestInstitution != null && vcInvestInstitution.getCompanyId() == null) {
			vcInvestInstitution.setCompanyId(companyId);
		}
		dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor.warehouse(SingleResponse.of(vcInvestInstitution));

		// 企业经营异常信息
		List<DataCompanyAbnormalExWarehouseVO> abnormals = dataCompanyAllExWarehouseVO.getAbnormals();
		if (abnormals != null) {
			for (DataCompanyAbnormalExWarehouseVO abnormal : abnormals) {
				if (abnormal != null && abnormal.getCompanyId() == null) {
					abnormal.setCompanyId(companyId);
				}
			}
		}

		// 企业经营异常信息总数
		Integer abnormalCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getAbnormalCount());
		dataCompanyAbnormalWrapWarehouseCommandExecutor.warehouse(PageResponse.of(abnormals,abnormalCount,CollectionUtil.emptyIfNull(abnormals).size(),1));

		// 企业商标信息
		List<DataCompanyIprTrademarkAllExWarehouseVO> iprTrademarks = dataCompanyAllExWarehouseVO.getIprTrademarkAlls();

		// 企业商标信息总数
		Integer iprTrademarkCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprTrademarkAllCount());
		dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprTrademarks, iprTrademarkCount, CollectionUtil.emptyIfNull(iprTrademarks).size(), 1));

		// 企业软件著作权信息
		List<DataCompanyIprSoftwareCopyrightExWarehouseVO> iprSoftwareCopyrights = dataCompanyAllExWarehouseVO.getIprSoftwareCopyrights();
		if (iprSoftwareCopyrights != null) {
			for (DataCompanyIprSoftwareCopyrightExWarehouseVO iprSoftwareCopyright : iprSoftwareCopyrights) {
				if (iprSoftwareCopyright != null && iprSoftwareCopyright.getCopyrightOwnerCompanyId() == null) {
					iprSoftwareCopyright.setCopyrightOwnerCompanyId(companyId);
				}
			}
		}

		// 企业软件著作权信息总数
		Integer iprSoftwareCopyrightCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprSoftwareCopyrightCount());
		dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprSoftwareCopyrights, iprSoftwareCopyrightCount, CollectionUtil.emptyIfNull(iprSoftwareCopyrights).size(), 1));

		// 企业作品著作权信息
		List<DataCompanyIprWorkCopyrightExWarehouseVO> iprWorkCopyrights = dataCompanyAllExWarehouseVO.getIprWorkCopyrights();
		if (iprWorkCopyrights != null) {
			for (DataCompanyIprWorkCopyrightExWarehouseVO iprWorkCopyright : iprWorkCopyrights) {
				if (iprWorkCopyright != null && iprWorkCopyright.getCopyrightOwnerCompanyId() == null) {
					iprWorkCopyright.setCopyrightOwnerCompanyId(companyId);
				}
			}
		}

		// 企业作品著作权信息总数
		Integer iprWorkCopyrightCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprWorkCopyrightCount());
		dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprWorkCopyrights, iprWorkCopyrightCount, CollectionUtil.emptyIfNull(iprWorkCopyrights).size(), 1));

		// 企业地理标志信息
		List<DataCompanyIprGeograExWarehouseVO> iprGeogras = dataCompanyAllExWarehouseVO.getIprGeogras();
		if (iprGeogras != null) {
			for (DataCompanyIprGeograExWarehouseVO iprGeograItem : iprGeogras) {
				if (iprGeograItem != null && iprGeograItem.getApplicantNameCompanyId() == null) {
					iprGeograItem.setApplicantNameCompanyId(companyId);
				}
			}
		}

		// 企业地理标志信息总数
		Integer iprGeograCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprGeograCount());
		dataCompanyIprGeograWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprGeogras, iprGeograCount, CollectionUtil.emptyIfNull(iprGeogras).size(), 1));

		// 企业集成电路布图设计信息
		List<DataCompanyIprIntegratedCircuitExWarehouseVO> iprIntegratedCircuits = dataCompanyAllExWarehouseVO.getIprIntegratedCircuits();
		if (iprIntegratedCircuits != null) {
			for (DataCompanyIprIntegratedCircuitExWarehouseVO iprIntegratedCircuit : iprIntegratedCircuits) {
				if (iprIntegratedCircuit != null && iprIntegratedCircuit.getRightHolderCompanyId() == null) {
					iprIntegratedCircuit.setRightHolderCompanyId(companyId);
				}
			}
		}

		// 企业集成电路布图设计信息总数
		Integer iprIntegratedCircuitCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprIntegratedCircuitCount());
		dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprIntegratedCircuits, iprIntegratedCircuitCount, CollectionUtil.emptyIfNull(iprIntegratedCircuits).size(), 1));

		// 企业植物新品种信息
		List<DataCompanyIprPlantVarietyExWarehouseVO> iprPlantVarieties = dataCompanyAllExWarehouseVO.getIprPlantVarieties();
		if (iprPlantVarieties != null) {
			for (DataCompanyIprPlantVarietyExWarehouseVO iprPlantVariety : iprPlantVarieties) {
				if (iprPlantVariety != null && iprPlantVariety.getApplicantNameCompanyId() == null) {
					iprPlantVariety.setApplicantNameCompanyId(companyId);
				}
			}
		}

		// 企业植物新品种信息总数
		Integer iprPlantVarietyCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprPlantVarietyCount());

	// 企业行政许可信息
		List<DataCompanyAdministrativeLicenseExWarehouseVO> administrativeLicenses = dataCompanyAllExWarehouseVO.getAdministrativeLicenses();
		if (administrativeLicenses != null) {
			for (DataCompanyAdministrativeLicenseExWarehouseVO administrativeLicense : administrativeLicenses) {
				if (administrativeLicense != null && administrativeLicense.getCompanyId() == null) {
					administrativeLicense.setCompanyId(companyId);
				}
			}
		}

		// 企业行政许可信息总数
		Integer administrativeLicenseCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getAdministrativeLicenseCount());
		dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor.warehouse(PageResponse.of(administrativeLicenses, administrativeLicenseCount, CollectionUtil.emptyIfNull(administrativeLicenses).size(), 1));

		// 企业送达公告信息
		List<DataCompanyDeliveryAnnouncementExWarehouseVO> deliveryAnnouncements = dataCompanyAllExWarehouseVO.getDeliveryAnnouncements();

		// 企业送达公告信息总数
		Integer deliveryAnnouncementCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getDeliveryAnnouncementCount());
		dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor.warehouse(PageResponse.of(deliveryAnnouncements, deliveryAnnouncementCount, CollectionUtil.emptyIfNull(deliveryAnnouncements).size(), 1));

		// 企业终本案件信息
		List<DataCompanyEndCaseExWarehouseVO> endCases = dataCompanyAllExWarehouseVO.getEndCases();
		if (endCases != null) {
			for (DataCompanyEndCaseExWarehouseVO endCase : endCases) {
				if (endCase != null && endCase.getExecutedPersonCompanyId() == null) {
					endCase.setExecutedPersonCompanyId(companyId);
				}
			}
		}

		// 企业终本案件信息总数
		Integer endCaseCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getEndCaseCount());
		dataCompanyEndCaseWrapWarehouseCommandExecutor.warehouse(PageResponse.of(endCases, endCaseCount, CollectionUtil.emptyIfNull(endCases).size(), 1));

		// 企业股权质押信息
		List<DataCompanyEquityPledgeExWarehouseVO> equityPledges = dataCompanyAllExWarehouseVO.getEquityPledges();
		if (equityPledges != null) {
			for (DataCompanyEquityPledgeExWarehouseVO equityPledge : equityPledges) {
				if (equityPledge != null && equityPledge.getCompanyId() == null) {
					equityPledge.setCompanyId(companyId);
				}
			}
		}

		// 企业股权质押信息总数
		Integer equityPledgeCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getEquityPledgeCount());
		dataCompanyEquityPledgeWrapWarehouseCommandExecutor.warehouse(PageResponse.of(equityPledges, equityPledgeCount, CollectionUtil.emptyIfNull(equityPledges).size(), 1));

		// 企业知识产权质押信息
		List<DataCompanyIprPledgeExWarehouseVO> iprPledges = dataCompanyAllExWarehouseVO.getIprPledges();
		if (iprPledges != null) {
			for (DataCompanyIprPledgeExWarehouseVO iprPledge : iprPledges) {
				if (iprPledge != null && iprPledge.getCompanyId() == null) {
					iprPledge.setCompanyId(companyId);
				}
			}
		}

		// 企业知识产权质押信息总数
		Integer iprPledgeCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getIprPledgeCount());
		dataCompanyIprPledgeWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprPledges, iprPledgeCount, CollectionUtil.emptyIfNull(iprPledges).size(), 1));

		// 企业主要人员信息
		List<DataCompanyPrimeStaffExWarehouseVO> primeStaffs = dataCompanyAllExWarehouseVO.getPrimeStaffs();
		if (primeStaffs != null) {
			for (DataCompanyPrimeStaffExWarehouseVO primeStaff : primeStaffs) {
				if (primeStaff != null && primeStaff.getCompanyId() == null) {
					primeStaff.setCompanyId(companyId);
				}
			}
		}

		// 企业主要人员信息总数
		Integer primeStaffCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getPrimeStaffCount());
		dataCompanyPrimeStaffWrapWarehouseCommandExecutor.warehouse(PageResponse.of(primeStaffs, primeStaffCount, CollectionUtil.emptyIfNull(primeStaffs).size(), 1));

		// 企业抽查检查信息
		List<DataCompanySpotCheckExWarehouseVO> spotChecks = dataCompanyAllExWarehouseVO.getSpotChecks();
		if (spotChecks != null) {
			for (DataCompanySpotCheckExWarehouseVO spotCheck : spotChecks) {
				if (spotCheck != null && spotCheck.getCompanyId() == null) {
					spotCheck.setCompanyId(companyId);
				}
			}
		}

		// 企业抽查检查信息总数
		Integer spotCheckCount = NumberUtil.nullToZero(dataCompanyAllExWarehouseVO.getSpotCheckCount());
		dataCompanySpotCheckWrapWarehouseCommandExecutor.warehouse(PageResponse.of(spotChecks, spotCheckCount, CollectionUtil.emptyIfNull(spotChecks).size(), 1));

		dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor.warehouse(PageResponse.of(iprPlantVarieties, iprPlantVarietyCount, CollectionUtil.emptyIfNull(iprPlantVarieties).size(), 1));

	}

	@Autowired
	public void setDataCompanyBasicWrapWarehouseCommandExecutor(DataCompanyBasicWrapWarehouseCommandExecutor dataCompanyBasicWrapWarehouseCommandExecutor) {
		this.dataCompanyBasicWrapWarehouseCommandExecutor = dataCompanyBasicWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportAllWrapWarehouseCommandExecutor(DataCompanyAnnualReportAllWrapWarehouseCommandExecutor dataCompanyAnnualReportAllWrapWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAllWrapWarehouseCommandExecutor = dataCompanyAnnualReportAllWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCaseFilingWrapWarehouseCommandExecutor(DataCompanyCaseFilingWrapWarehouseCommandExecutor dataCompanyCaseFilingWrapWarehouseCommandExecutor) {
		this.dataCompanyCaseFilingWrapWarehouseCommandExecutor = dataCompanyCaseFilingWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCourtAnnouncementWrapWarehouseCommandExecutor(DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor) {
		this.dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor = dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyHonorQualificationWrapWarehouseCommandExecutor(DataCompanyHonorQualificationWrapWarehouseCommandExecutor dataCompanyHonorQualificationWrapWarehouseCommandExecutor) {
		this.dataCompanyHonorQualificationWrapWarehouseCommandExecutor = dataCompanyHonorQualificationWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPatentAllWrapWarehouseCommandExecutor(DataCompanyIprPatentAllWrapWarehouseCommandExecutor dataCompanyIprPatentAllWrapWarehouseCommandExecutor) {
		this.dataCompanyIprPatentAllWrapWarehouseCommandExecutor = dataCompanyIprPatentAllWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyJudgmentDebtorWrapWarehouseCommandExecutor(DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor = dataCompanyJudgmentDebtorWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor) {
		this.dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentWrapWarehouseCommandExecutor(DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor = dataCompanyJudgmentDocumentWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPunishmentWrapWarehouseCommandExecutor(DataCompanyPunishmentWrapWarehouseCommandExecutor dataCompanyPunishmentWrapWarehouseCommandExecutor) {
		this.dataCompanyPunishmentWrapWarehouseCommandExecutor = dataCompanyPunishmentWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor) {
		this.dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor = dataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanySeriousIllegalWrapWarehouseCommandExecutor(DataCompanySeriousIllegalWrapWarehouseCommandExecutor dataCompanySeriousIllegalWrapWarehouseCommandExecutor) {
		this.dataCompanySeriousIllegalWrapWarehouseCommandExecutor = dataCompanySeriousIllegalWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyShareholderWrapWarehouseCommandExecutor(DataCompanyShareholderWrapWarehouseCommandExecutor dataCompanyShareholderWrapWarehouseCommandExecutor) {
		this.dataCompanyShareholderWrapWarehouseCommandExecutor = dataCompanyShareholderWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyStatisticWrapWarehouseCommandExecutor(DataCompanyStatisticWrapWarehouseCommandExecutor dataCompanyStatisticWrapWarehouseCommandExecutor) {
		this.dataCompanyStatisticWrapWarehouseCommandExecutor = dataCompanyStatisticWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcFinancingWrapWarehouseCommandExecutor(DataCompanyVcFinancingWrapWarehouseCommandExecutor dataCompanyVcFinancingWrapWarehouseCommandExecutor) {
		this.dataCompanyVcFinancingWrapWarehouseCommandExecutor = dataCompanyVcFinancingWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcProductWrapWarehouseCommandExecutor(DataCompanyVcProductWrapWarehouseCommandExecutor dataCompanyVcProductWrapWarehouseCommandExecutor) {
		this.dataCompanyVcProductWrapWarehouseCommandExecutor = dataCompanyVcProductWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor(DataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor) {
		this.dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor = dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAbnormalWrapWarehouseCommandExecutor(DataCompanyAbnormalWrapWarehouseCommandExecutor dataCompanyAbnormalWrapWarehouseCommandExecutor) {
		this.dataCompanyAbnormalWrapWarehouseCommandExecutor = dataCompanyAbnormalWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprTrademarkAllWrapWarehouseCommandExecutor(DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor = dataCompanyIprTrademarkAllWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor) {
		this.dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor(DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor) {
		this.dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor = dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprGeograWrapWarehouseCommandExecutor(DataCompanyIprGeograWrapWarehouseCommandExecutor dataCompanyIprGeograWrapWarehouseCommandExecutor) {
		this.dataCompanyIprGeograWrapWarehouseCommandExecutor = dataCompanyIprGeograWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor) {
		this.dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPlantVarietyWrapWarehouseCommandExecutor(DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor) {
		this.dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor = dataCompanyIprPlantVarietyWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor(DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor) {
		this.dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor = dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor) {
		this.dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyEndCaseWrapWarehouseCommandExecutor(DataCompanyEndCaseWrapWarehouseCommandExecutor dataCompanyEndCaseWrapWarehouseCommandExecutor) {
		this.dataCompanyEndCaseWrapWarehouseCommandExecutor = dataCompanyEndCaseWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyEquityPledgeWrapWarehouseCommandExecutor(DataCompanyEquityPledgeWrapWarehouseCommandExecutor dataCompanyEquityPledgeWrapWarehouseCommandExecutor) {
		this.dataCompanyEquityPledgeWrapWarehouseCommandExecutor = dataCompanyEquityPledgeWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyIprPledgeWrapWarehouseCommandExecutor(DataCompanyIprPledgeWrapWarehouseCommandExecutor dataCompanyIprPledgeWrapWarehouseCommandExecutor) {
		this.dataCompanyIprPledgeWrapWarehouseCommandExecutor = dataCompanyIprPledgeWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyPrimeStaffWrapWarehouseCommandExecutor(DataCompanyPrimeStaffWrapWarehouseCommandExecutor dataCompanyPrimeStaffWrapWarehouseCommandExecutor) {
		this.dataCompanyPrimeStaffWrapWarehouseCommandExecutor = dataCompanyPrimeStaffWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanySpotCheckWrapWarehouseCommandExecutor(DataCompanySpotCheckWrapWarehouseCommandExecutor dataCompanySpotCheckWrapWarehouseCommandExecutor) {
		this.dataCompanySpotCheckWrapWarehouseCommandExecutor = dataCompanySpotCheckWrapWarehouseCommandExecutor;
	}
}
