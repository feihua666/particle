package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.*;
import com.particle.data.app.company.executor.warehousewrap.*;
import com.particle.data.client.company.dto.command.representation.exwarehouse.*;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业全貌出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-06-05 10:13:21
 */
@Component
@Validated
public class DataCompanyAllWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

	private DataCompanyBasicWrapExWarehouseCommandExecutor dataCompanyBasicWrapExWarehouseCommandExecutor;
	private DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor;
	private DataCompanyCaseFilingWrapExWarehouseCommandExecutor dataCompanyCaseFilingWrapExWarehouseCommandExecutor;
	private DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor;
	private DataCompanyHonorQualificationWrapExWarehouseCommandExecutor dataCompanyHonorQualificationWrapExWarehouseCommandExecutor;
	private DataCompanyIprPatentAllWrapExWarehouseCommandExecutor dataCompanyIprPatentAllWrapExWarehouseCommandExecutor;
	private DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor;
	private DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor;
	private DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor;
	private DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor;
	private DataCompanyPunishmentWrapExWarehouseCommandExecutor dataCompanyPunishmentWrapExWarehouseCommandExecutor;
	private DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor;
	private DataCompanySeriousIllegalWrapExWarehouseCommandExecutor dataCompanySeriousIllegalWrapExWarehouseCommandExecutor;
	private DataCompanyShareholderWrapExWarehouseCommandExecutor dataCompanyShareholderWrapExWarehouseCommandExecutor;
	private DataCompanyStatisticWrapExWarehouseCommandExecutor dataCompanyStatisticWrapExWarehouseCommandExecutor;
	private DataCompanyVcFinancingWrapExWarehouseCommandExecutor dataCompanyVcFinancingWrapExWarehouseCommandExecutor;
	private DataCompanyVcProductWrapExWarehouseCommandExecutor dataCompanyVcProductWrapExWarehouseCommandExecutor;
	private DataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor dataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor;
	private DataCompanyAbnormalWrapExWarehouseCommandExecutor dataCompanyAbnormalWrapExWarehouseCommandExecutor;
	/**
	 * 企业全貌全部出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAllExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
																		  DataCompanyAllExWarehouseQueryCommand dataCompanyAllExWarehouseQueryCommand) {

		if (dataCompanyAllExWarehouseQueryCommand.getCompanyId() == null) {
			dataCompanyAllExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
		}
        if (dataCompanyAllExWarehouseQueryCommand.getCompanyId() == null) {
			Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
			dataCompanyAllExWarehouseQueryCommand.setCompanyId(companyId);
			dataCompanyExWarehouseQueryCommand.setId(companyId);
		}
		if (dataCompanyAllExWarehouseQueryCommand.getCompanyId() == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}

		boolean hasValue  = false;
		DataCompanyAllExWarehouseVO dataCompanyAllExWarehouseVO = new DataCompanyAllExWarehouseVO();
		// 企业标识信息
        if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeCompanyUnique())) {
			DataCompanyExWarehouseVO companyUnique = getCompany(dataCompanyExWarehouseQueryCommand);
			dataCompanyAllExWarehouseVO.setCompanyUnique(companyUnique);
			hasValue = true;
        }


		// 企业基本信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeBasic())) {
			SingleResponse<DataCompanyBasicExWarehouseVO> basicSingleResponse = dataCompanyBasicWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, DataCompanyBasicExWarehouseQueryCommand.create(null));
			DataCompanyBasicExWarehouseVO basic = basicSingleResponse.getData();
			dataCompanyAllExWarehouseVO.setBasic(basic);
			hasValue = true;
		}

		// 企业年报信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeAnnualReportAll())) {
			PageResponse<DataCompanyAnnualReportAllExWarehouseVO> annualReportAllPageResponse = dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getAnnualReportAllQuery());
			List<DataCompanyAnnualReportAllExWarehouseVO> annualReportAlls = annualReportAllPageResponse.getData();
			// 企业年报信息总数
			Integer annualReportAllCount = annualReportAllPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setAnnualReportAlls(annualReportAlls);
			dataCompanyAllExWarehouseVO.setAnnualReportAllCount(annualReportAllCount);
			hasValue = true;
		}

		// 企业立案信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeCaseFiling())) {
			PageResponse<DataCompanyCaseFilingExWarehouseVO> caseFilingPageResponse = dataCompanyCaseFilingWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getCaseFilingQuery());
			List<DataCompanyCaseFilingExWarehouseVO> caseFilings = caseFilingPageResponse.getData();
			// 企业立案信息总数
			Integer caseFilingCount = caseFilingPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setCaseFilings(caseFilings);
			dataCompanyAllExWarehouseVO.setCaseFilingCount(caseFilingCount);
			hasValue = true;
		}

		// 企业法院公告信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeCourtAnnouncement())) {
			PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> courtAnnouncementPageResponse = dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getCourtAnnouncementQuery());
			List<DataCompanyCourtAnnouncementExWarehouseVO> courtAnnouncements = courtAnnouncementPageResponse.getData();
			// 企业法院公告信息总数
			Integer courtAnnouncementCount = courtAnnouncementPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setCourtAnnouncements(courtAnnouncements);
			dataCompanyAllExWarehouseVO.setCourtAnnouncementCount(courtAnnouncementCount);
			hasValue = true;
		}

		// 企业荣誉资质信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeHonorQualification())) {
			PageResponse<DataCompanyHonorQualificationExWarehouseVO> honorQualificationPageResponse = dataCompanyHonorQualificationWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getHonorQualificationQuery());
			List<DataCompanyHonorQualificationExWarehouseVO> honorQualifications = honorQualificationPageResponse.getData();
			// 企业荣誉资质信息总数
			Integer honorQualificationCount = honorQualificationPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setHonorQualifications(honorQualifications);
			dataCompanyAllExWarehouseVO.setHonorQualificationCount(honorQualificationCount);
			hasValue = true;
		}

		// 企业知识产权专利信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeIprPatentAll())) {

			PageResponse<DataCompanyIprPatentAllExWarehouseVO> iprPatentAllPageResponse = dataCompanyIprPatentAllWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getIprPatentAllQuery());
			List<DataCompanyIprPatentAllExWarehouseVO> iprPatentAlls = iprPatentAllPageResponse.getData();
			// 企业知识产权专利信息总数
			Integer iprPatentAllCount = iprPatentAllPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setIprPatentAlls(iprPatentAlls);
			dataCompanyAllExWarehouseVO.setIprPatentAllCount(iprPatentAllCount);
			hasValue = true;
		}

		// 企业被执行人信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeJudgmentDebtor())) {
			PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> judgmentDebtorPageResponse = dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getJudgmentDebtorQuery());
			List<DataCompanyJudgmentDebtorExWarehouseVO> judgmentDebtors = judgmentDebtorPageResponse.getData();
			// 企业被执行人信息总数
			Integer judgmentDebtorCount = judgmentDebtorPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setJudgmentDebtors(judgmentDebtors);
			dataCompanyAllExWarehouseVO.setJudgmentDebtorCount(judgmentDebtorCount);
			hasValue = true;
		}

		// 企业失信被执行人信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeDiscreditedJudgmentDebtor())) {
			PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtorPageResponse = dataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getDiscreditedJudgmentDebtorQuery());
			List<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtors = discreditedJudgmentDebtorPageResponse.getData();
			// 企业失信被执行人信息总数
			Integer discreditedJudgmentDebtorCount = discreditedJudgmentDebtorPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setDiscreditedJudgmentDebtors(discreditedJudgmentDebtors);
			dataCompanyAllExWarehouseVO.setDiscreditedJudgmentDebtorCount(discreditedJudgmentDebtorCount);
			hasValue = true;
		}

		// 企业裁判文书信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeJudgmentDocument())) {
			PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> judgmentDocumentPageResponse = dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getJudgmentDocumentQuery());
			List<DataCompanyJudgmentDocumentExWarehouseVO> judgmentDocuments = judgmentDocumentPageResponse.getData();
			// 企业裁判文书信息总数
			Integer judgmentDocumentCount = judgmentDocumentPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setJudgmentDocuments(judgmentDocuments);
			dataCompanyAllExWarehouseVO.setJudgmentDocumentCount(judgmentDocumentCount);
			hasValue = true;
		}

		// 企业开庭公告信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeOpenCourtAnnouncement())) {
			PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> openCourtAnnouncementPageResponse = dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getOpenCourtAnnouncementQuery());
			List<DataCompanyOpenCourtAnnouncementExWarehouseVO> openCourtAnnouncements = openCourtAnnouncementPageResponse.getData();
			// 企业开庭公告信息总数
			Integer openCourtAnnouncementCount = openCourtAnnouncementPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setOpenCourtAnnouncements(openCourtAnnouncements);
			dataCompanyAllExWarehouseVO.setOpenCourtAnnouncementCount(openCourtAnnouncementCount);
			hasValue = true;
		}

		// 企业行政处罚信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludePunishment())) {
			PageResponse<DataCompanyPunishmentExWarehouseVO> punishmentPageResponse = dataCompanyPunishmentWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getPunishmentQuery());
			List<DataCompanyPunishmentExWarehouseVO> punishments = punishmentPageResponse.getData();
			dataCompanyAllExWarehouseVO.setPunishments(punishments);
			Integer punishmentCount = punishmentPageResponse.getTotalCount();
			hasValue = true;
		}

		// 企业限制高消费信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeRestrictHighConsume())) {
			PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> restrictHighConsumePageResponse = dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getRestrictHighConsumeQuery());
			List<DataCompanyRestrictHighConsumeExWarehouseVO> restrictHighConsumes = restrictHighConsumePageResponse.getData();
			Integer restrictHighConsumeCount = restrictHighConsumePageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setRestrictHighConsumes(restrictHighConsumes);
			dataCompanyAllExWarehouseVO.setRestrictHighConsumeCount(restrictHighConsumeCount);
			hasValue = true;
		}

		// 企业严重违法信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeSeriousIllegal())) {
			PageResponse<DataCompanySeriousIllegalExWarehouseVO> seriousIllegalPageResponse = dataCompanySeriousIllegalWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getSeriousIllegalQuery());
			List<DataCompanySeriousIllegalExWarehouseVO> seriousIllegals = seriousIllegalPageResponse.getData();
			Integer seriousIllegalCount = seriousIllegalPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setSeriousIllegals(seriousIllegals);
			dataCompanyAllExWarehouseVO.setSeriousIllegalCount(seriousIllegalCount);
			hasValue = true;
		}

		// 企业股东信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeShareholder())) {
			PageResponse<DataCompanyShareholderExWarehouseVO> shareholderPageResponse = dataCompanyShareholderWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getShareholderQuery());
			List<DataCompanyShareholderExWarehouseVO> shareholders = shareholderPageResponse.getData();
			dataCompanyAllExWarehouseVO.setShareholders(shareholders);
			Integer shareholderCount = shareholderPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setShareholderCount(shareholderCount);
			hasValue = true;
		}

		// 企业统计信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeStatistic())) {
			SingleResponse<DataCompanyStatisticExWarehouseVO> statisticSingleResponse = dataCompanyStatisticWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, DataCompanyStatisticExWarehouseQueryCommand.create(null));
			DataCompanyStatisticExWarehouseVO statistic = statisticSingleResponse.getData();
			dataCompanyAllExWarehouseVO.setStatistic(statistic);

		}

		// 企业融资信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeVcFinancing())) {
			PageResponse<DataCompanyVcFinancingExWarehouseVO> vcFinancingPageResponse = dataCompanyVcFinancingWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getVcFinancingQuery());
			List<DataCompanyVcFinancingExWarehouseVO> vcFinancings = vcFinancingPageResponse.getData();
			Integer vcFinancingCount = vcFinancingPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setVcFinancings(vcFinancings);
			dataCompanyAllExWarehouseVO.setVcFinancingCount(vcFinancingCount);
			hasValue = true;
		}

		// 企业融资产品信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeVcProduct())) {
			PageResponse<DataCompanyVcProductExWarehouseVO> vcProductPageResponse = dataCompanyVcProductWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getVcProductQuery());
			List<DataCompanyVcProductExWarehouseVO> vcProducts = vcProductPageResponse.getData();
			Integer vcProductCount = vcProductPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setVcProducts(vcProducts);
			dataCompanyAllExWarehouseVO.setVcProductCount(vcProductCount);
			hasValue = true;
		}

		// 企业投资机构信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeVcInvestInstitution())) {
			SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> vcInvestInstitutionSingleResponse = dataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, DataCompanyVcInvestInstitutionExWarehouseQueryCommand.create(null));
			DataCompanyVcInvestInstitutionExWarehouseVO vcInvestInstitution = vcInvestInstitutionSingleResponse.getData();
			dataCompanyAllExWarehouseVO.setVcInvestInstitution(vcInvestInstitution);
			hasValue = true;
		}
		// 企业经营异常信息
		if (BooleanUtil.isTrue(dataCompanyAllExWarehouseQueryCommand.getIsIncludeAbnormal())) {
			PageResponse<DataCompanyAbnormalExWarehouseVO> vcProductPageResponse = dataCompanyAbnormalWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAllExWarehouseQueryCommand.getAbnormalQuery());
			List<DataCompanyAbnormalExWarehouseVO> vcProducts = vcProductPageResponse.getData();
			Integer vcProductCount = vcProductPageResponse.getTotalCount();
			dataCompanyAllExWarehouseVO.setAbnormals(vcProducts);
			dataCompanyAllExWarehouseVO.setAbnormalCount(vcProductCount);
			hasValue = true;
		}
		if (hasValue) {
			return SingleResponse.of(dataCompanyAllExWarehouseVO);
		}

		return SingleResponse.buildSuccess();
	}

	@Autowired
	public void setDataCompanyBasicWrapExWarehouseCommandExecutor(DataCompanyBasicWrapExWarehouseCommandExecutor dataCompanyBasicWrapExWarehouseCommandExecutor) {
		this.dataCompanyBasicWrapExWarehouseCommandExecutor = dataCompanyBasicWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyAnnualReportAllWrapExWarehouseCommandExecutor(DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor = dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyCaseFilingWrapExWarehouseCommandExecutor(DataCompanyCaseFilingWrapExWarehouseCommandExecutor dataCompanyCaseFilingWrapExWarehouseCommandExecutor) {
		this.dataCompanyCaseFilingWrapExWarehouseCommandExecutor = dataCompanyCaseFilingWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor(DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor) {
		this.dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor = dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyHonorQualificationWrapExWarehouseCommandExecutor(DataCompanyHonorQualificationWrapExWarehouseCommandExecutor dataCompanyHonorQualificationWrapExWarehouseCommandExecutor) {
		this.dataCompanyHonorQualificationWrapExWarehouseCommandExecutor = dataCompanyHonorQualificationWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentAllWrapExWarehouseCommandExecutor(DataCompanyIprPatentAllWrapExWarehouseCommandExecutor dataCompanyIprPatentAllWrapExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentAllWrapExWarehouseCommandExecutor = dataCompanyIprPatentAllWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor(DataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor = dataCompanyJudgmentDebtorWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor) {
		this.dataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor(DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor) {
		this.dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor = dataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor) {
		this.dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyPunishmentWrapExWarehouseCommandExecutor(DataCompanyPunishmentWrapExWarehouseCommandExecutor dataCompanyPunishmentWrapExWarehouseCommandExecutor) {
		this.dataCompanyPunishmentWrapExWarehouseCommandExecutor = dataCompanyPunishmentWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor) {
		this.dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor = dataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanySeriousIllegalWrapExWarehouseCommandExecutor(DataCompanySeriousIllegalWrapExWarehouseCommandExecutor dataCompanySeriousIllegalWrapExWarehouseCommandExecutor) {
		this.dataCompanySeriousIllegalWrapExWarehouseCommandExecutor = dataCompanySeriousIllegalWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyShareholderWrapExWarehouseCommandExecutor(DataCompanyShareholderWrapExWarehouseCommandExecutor dataCompanyShareholderWrapExWarehouseCommandExecutor) {
		this.dataCompanyShareholderWrapExWarehouseCommandExecutor = dataCompanyShareholderWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyStatisticWrapExWarehouseCommandExecutor(DataCompanyStatisticWrapExWarehouseCommandExecutor dataCompanyStatisticWrapExWarehouseCommandExecutor) {
		this.dataCompanyStatisticWrapExWarehouseCommandExecutor = dataCompanyStatisticWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyVcFinancingWrapExWarehouseCommandExecutor(DataCompanyVcFinancingWrapExWarehouseCommandExecutor dataCompanyVcFinancingWrapExWarehouseCommandExecutor) {
		this.dataCompanyVcFinancingWrapExWarehouseCommandExecutor = dataCompanyVcFinancingWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyVcProductWrapExWarehouseCommandExecutor(DataCompanyVcProductWrapExWarehouseCommandExecutor dataCompanyVcProductWrapExWarehouseCommandExecutor) {
		this.dataCompanyVcProductWrapExWarehouseCommandExecutor = dataCompanyVcProductWrapExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor(DataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor dataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor) {
		this.dataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor = dataCompanyVcInvestInstitutionWrapExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAbnormalWrapExWarehouseCommandExecutor(DataCompanyAbnormalWrapExWarehouseCommandExecutor dataCompanyAbnormalWrapExWarehouseCommandExecutor) {
		this.dataCompanyAbnormalWrapExWarehouseCommandExecutor = dataCompanyAbnormalWrapExWarehouseCommandExecutor;
	}
}
