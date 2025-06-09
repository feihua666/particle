package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.global.dto.basic.VO;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 企业全貌 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-05 09:24:38
 */
@Data
@Schema
public class DataCompanyAllExWarehouseVO extends VO {

    @Schema(description = "企业标识信息")
    private DataCompanyExWarehouseVO companyUnique;

    @Schema(description = "企业基本信息")
    private DataCompanyBasicExWarehouseVO basic;

    @Schema(description = "企业年报信息")
    private List<DataCompanyAnnualReportAllExWarehouseVO> annualReportAlls;

    @Schema(description = "企业年报信息总数")
    private Integer annualReportAllCount;

    @Schema(description = "企业立案信息")
    private List<DataCompanyCaseFilingExWarehouseVO> caseFilings;

    @Schema(description = "企业立案信息总数")
    private Integer caseFilingCount;

    @Schema(description = "企业法院公告信息")
    private List<DataCompanyCourtAnnouncementExWarehouseVO> courtAnnouncements;

    @Schema(description = "企业法院公告信息总数")
    private Integer courtAnnouncementCount;

    @Schema(description = "企业荣誉资质信息")
    private List<DataCompanyHonorQualificationExWarehouseVO> honorQualifications;

    @Schema(description = "企业荣誉资质信息总数")
    private Integer honorQualificationCount;

    @Schema(description = "企业知识产权专利信息")
    private List<DataCompanyIprPatentAllExWarehouseVO> iprPatentAlls;

    @Schema(description = "企业知识产权专利信息总数")
    private Integer iprPatentAllCount;

    @Schema(description = "企业被执行人信息")
    private List<DataCompanyJudgmentDebtorExWarehouseVO> judgmentDebtors;

    @Schema(description = "企业被执行人信息总数")
    private Integer judgmentDebtorCount;


    @Schema(description = "企业失信被执行人信息")
    private List<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> discreditedJudgmentDebtors;

    @Schema(description = "企业失信被执行人信息总数")
    private Integer discreditedJudgmentDebtorCount;

    @Schema(description = "企业裁判文书信息")
    private List<DataCompanyJudgmentDocumentExWarehouseVO> judgmentDocuments;

    @Schema(description = "企业裁判文书信息总数")
    private Integer judgmentDocumentCount;

    @Schema(description = "企业开庭公告信息")
    private List<DataCompanyOpenCourtAnnouncementExWarehouseVO> openCourtAnnouncements;

    @Schema(description = "企业开庭公告信息总数")
    private Integer openCourtAnnouncementCount;

    @Schema(description = "企业行政处罚信息")
    private List<DataCompanyPunishmentExWarehouseVO> punishments;

    @Schema(description = "企业行政处罚信息总数")
    private Integer punishmentCount;

    @Schema(description = "企业限制高消费信息")
    private List<DataCompanyRestrictHighConsumeExWarehouseVO> restrictHighConsumes;

    @Schema(description = "企业限制高消费信息总数")
    private Integer restrictHighConsumeCount;

    @Schema(description = "企业严重违法信息")
    private List<DataCompanySeriousIllegalExWarehouseVO> seriousIllegals;

    @Schema(description = "企业严重违法信息总数")
    private Integer seriousIllegalCount;

    @Schema(description = "企业股东信息")
    private List<DataCompanyShareholderExWarehouseVO> shareholders;

    @Schema(description = "企业股东信息总数")
    private Integer shareholderCount;

    @Schema(description = "企业统计信息")
    private DataCompanyStatisticExWarehouseVO statistic;

    @Schema(description = "企业融资信息")
    private List<DataCompanyVcFinancingExWarehouseVO> vcFinancings;

    @Schema(description = "企业融资信息总数")
    private Integer vcFinancingCount;

    @Schema(description = "企业融资产品信息")
    private List<DataCompanyVcProductExWarehouseVO> vcProducts;

    @Schema(description = "企业融资产品信息总数")
    private Integer vcProductCount;

    @Schema(description = "企业投资机构信息")
    private DataCompanyVcInvestInstitutionExWarehouseVO vcInvestInstitution;

    @Schema(description = "企业经营异常信息")
    private List<DataCompanyAbnormalExWarehouseVO> abnormals;

    @Schema(description = "企业经营异常信息总数")
    private Integer abnormalCount;
}
