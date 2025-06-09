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
	/**
	 * 企业全貌全部入库
	 * @param dataCompanyAllExWarehouseVOPageResponse
	 * @return
	 */
	public void warehouse(SingleResponse<DataCompanyAllExWarehouseVO> dataCompanyAllExWarehouseVOPageResponse) {
		DataCompanyAllExWarehouseVO dataCompanyAllExWarehouseVO = dataCompanyAllExWarehouseVOPageResponse.getData();
		if (dataCompanyAllExWarehouseVO == null) {
			return;
		}

		// 企业标识信息
		DataCompanyExWarehouseVO companyUnique = dataCompanyAllExWarehouseVO.getCompanyUnique();
		DataCompanyExWarehouseVO dataCompanyExWarehouseVO = warehouseCompany(DataCompanyWarehouseCommand.createByDataCompanyExWarehouseVO(companyUnique));
		Long companyId = dataCompanyExWarehouseVO == null ? null : dataCompanyExWarehouseVO.getId();

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


		dataCompanyVcInvestInstitutionWrapWarehouseCommandExecutor.warehouse(SingleResponse.of(vcInvestInstitution));

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
}
