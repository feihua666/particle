package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业统计入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyStatisticWarehouseCommand extends AbstractBaseCommand {

    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "成立年限数量")
    private Integer establishYearNum;


    @Schema(description = "历史名称数量")
    private Integer historyNameNum;


    @Schema(description = "上市板块数量")
    private Integer listedNum;


    @Schema(description = "上市板块详情json")
    private String listedDetailJson;


    @Schema(description = "年报数量")
    private Integer annualReportNum;


    @Schema(description = "经营异常数量")
    private Integer abnormalNum;


    @Schema(description = "经营异常详情json")
    private String abnormalDetailJson;


    @Schema(description = "立案信息数量")
    private Integer caseFilingNum;


    @Schema(description = "立案信息详情json")
    private String caseFilingDetailJson;


    @Schema(description = "法院公告数量")
    private Integer courtAnnouncementNum;


    @Schema(description = "法院公告详情json")
    private String courtAnnouncementDetailJson;


    @Schema(description = "荣誉资质数量")
    private Integer honorQualificationNum;


    @Schema(description = "荣誉资质详情json")
    private String honorQualificationDetailJson;


    @Schema(description = "申请专利数量")
    private Integer iprPatentApplicantNum;


    @Schema(description = "权利专利数量")
    private Integer iprPatentRightNum;


    @Schema(description = "发明专利数量")
    private Integer iprPatentInventNum;

    @Schema(description = "实用新型专利数量")
    private Integer iprPatentUtilityNum;

    @Schema(description = "外观专利数量")
    private Integer iprPatentDesignNum;

    @Schema(description = "有效专利数量")
    private Integer iprPatentValidNum;

    @Schema(description = "有效发明专利数量")
    private Integer iprPatentValidInventNum;

    @Schema(description = "有效实用新型专利数量")
    private Integer iprPatentValidUtilityNum;

    @Schema(description = "有效外观专利数量")
    private Integer iprPatentValidDesignNum;

    @Schema(description = "无效专利数量")
    private Integer iprPatentInvalidNum;

    @Schema(description = "在审专利数量，审中专利数量")
    private Integer iprPatentUnderReviewNum;


    @Schema(description = "失信被执行人数量")
    private Integer discreditedJudgmentDebtorNum;


    @Schema(description = "失信被执行人详情json")
    private String discreditedJudgmentDebtorDetailJson;


    @Schema(description = "被执行人数量")
    private Integer judgmentDebtorNum;


    @Schema(description = "被执行人详情json")
    private String judgmentDebtorDetailJson;


    @Schema(description = "判断文书数量")
    private Integer judgmentDocumentNum;


    @Schema(description = "判断文书详情json")
    private String judgmentDocumentDetailJson;


    @Schema(description = "开庭公告数量")
    private Integer openCourtAnnouncementNum;


    @Schema(description = "开庭公告详情json")
    private String openCourtAnnouncementDetailJson;


    @Schema(description = "行政处罚数量")
    private Integer punishmentNum;


    @Schema(description = "行政处罚详情json")
    private String punishmentDetailJson;


    @Schema(description = "限制高消费数量")
    private Integer restrictHighConsumeNum;


    @Schema(description = "限制高消费详情json")
    private String restrictHighConsumeDetailJson;


    @Schema(description = "严重违法数量")
    private Integer seriousIllegalNum;


    @Schema(description = "严重违法详情json")
    private String seriousIllegalDetailJson;


    @Schema(description = "股东数量")
    private Integer shareholderNum;


    @Schema(description = "股东详情json")
    private String shareholderDetailJson;


    @Schema(description = "融资数量")
    private Integer vcFinancingNum;


    @Schema(description = "融资详情json")
    private String vcFinancingDetailJson;


    @Schema(description = "融资产品数量")
    private Integer vcProductNum;


    @Schema(description = "商标数量")
    private Integer trademarkNum;


    @Schema(description = "商标详情json")
    private String trademarkDetailJson;


    @Schema(description = "软件著作权数量")
    private Integer softwareCopyrightNum;


    @Schema(description = "软件著作权详情json")
    private String softwareCopyrightDetailJson;


    @Schema(description = "作品著作权数量")
    private Integer workCopyrightNum;


    @Schema(description = "作品著作权详情json")
    private String workCopyrightDetailJson;


    @Schema(description = "地理标识数量")
    private Integer geographicalIndicationNum;


    @Schema(description = "地理标识详情json")
    private String geographicalIndicationDetailJson;


    @Schema(description = "集成电路数量")
    private Integer integratedCircuitNum;


    @Schema(description = "集成电路详情json")
    private String integratedCircuitDetailJson;


    @Schema(description = "植物新品种数量")
    private Integer plantVarietyNum;


    @Schema(description = "植物新品种详情json")
    private String plantVarietyDetailJson;


    @Schema(description = "行政许可数量")
    private Integer administrativeLicenseNum;


    @Schema(description = "行政许可详情json")
    private String administrativeLicenseDetailJson;


    @Schema(description = "送达公告数量")
    private Integer deliveryAnnouncementNum;


    @Schema(description = "送达公告详情json")
    private String deliveryAnnouncementDetailJson;


    @Schema(description = "终本案件数量")
    private Integer endCaseNum;


    @Schema(description = "终本案件详情json")
    private String endCaseDetailJson;


    @Schema(description = "股权出质数量")
    private Integer equityPledgeNum;


    @Schema(description = "股权出质详情json")
    private String equityPledgeDetailJson;


    @Schema(description = "知识产权出质数量")
    private Integer iprPledgeNum;


    @Schema(description = "知识产权出质详情json")
    private String iprPledgeDetailJson;


    @Schema(description = "动产抵押数量")
    private Integer mortgageChattelNum;


    @Schema(description = "动产抵押详情json")
    private String mortgageChattelDetailJson;


    @Schema(description = "主要人员数量")
    private Integer primeStaffNum;


    @Schema(description = "主要人员详情json")
    private String primeStaffDetailJson;


    @Schema(description = "抽查检查数量")
    private Integer spotCheckNum;


    @Schema(description = "抽查检查详情json")
    private String spotCheckDetailJson;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(establishYearNum)
                && Objects.isNull(historyNameNum)
                && Objects.isNull(listedNum)
                && StrUtil.isEmpty(listedDetailJson)
                && Objects.isNull(annualReportNum)
                && Objects.isNull(abnormalNum)
                && StrUtil.isEmpty(abnormalDetailJson)
                && Objects.isNull(caseFilingNum)
                && StrUtil.isEmpty(caseFilingDetailJson)
                && Objects.isNull(courtAnnouncementNum)
                && StrUtil.isEmpty(courtAnnouncementDetailJson)
                && Objects.isNull(honorQualificationNum)
                && StrUtil.isEmpty(honorQualificationDetailJson)
                && Objects.isNull(iprPatentApplicantNum)
                && Objects.isNull(iprPatentRightNum)
                && Objects.isNull(iprPatentInventNum)
                && Objects.isNull(iprPatentUtilityNum)
                && Objects.isNull(iprPatentDesignNum)
                && Objects.isNull(iprPatentValidNum)
                && Objects.isNull(iprPatentValidInventNum)
                && Objects.isNull(iprPatentValidUtilityNum)
                && Objects.isNull(iprPatentValidDesignNum)
                && Objects.isNull(iprPatentInvalidNum)
                && Objects.isNull(iprPatentUnderReviewNum)
                && Objects.isNull(discreditedJudgmentDebtorNum)
                && StrUtil.isEmpty(discreditedJudgmentDebtorDetailJson)
                && Objects.isNull(judgmentDebtorNum)
                && StrUtil.isEmpty(judgmentDebtorDetailJson)
                && Objects.isNull(judgmentDocumentNum)
                && StrUtil.isEmpty(judgmentDocumentDetailJson)
                && Objects.isNull(openCourtAnnouncementNum)
                && StrUtil.isEmpty(openCourtAnnouncementDetailJson)
                && Objects.isNull(punishmentNum)
                && StrUtil.isEmpty(punishmentDetailJson)
                && Objects.isNull(restrictHighConsumeNum)
                && StrUtil.isEmpty(restrictHighConsumeDetailJson)
                && Objects.isNull(seriousIllegalNum)
                && StrUtil.isEmpty(seriousIllegalDetailJson)
                && Objects.isNull(shareholderNum)
                && StrUtil.isEmpty(shareholderDetailJson)
                && Objects.isNull(vcFinancingNum)
                && StrUtil.isEmpty(vcFinancingDetailJson)
                && Objects.isNull(vcProductNum)
                && Objects.isNull(trademarkNum)
                && StrUtil.isEmpty(trademarkDetailJson)
                && Objects.isNull(softwareCopyrightNum)
                && StrUtil.isEmpty(softwareCopyrightDetailJson)
                && Objects.isNull(workCopyrightNum)
                && StrUtil.isEmpty(workCopyrightDetailJson)
                && Objects.isNull(geographicalIndicationNum)
                && StrUtil.isEmpty(geographicalIndicationDetailJson)
                && Objects.isNull(integratedCircuitNum)
                && StrUtil.isEmpty(integratedCircuitDetailJson)
                && Objects.isNull(plantVarietyNum)
                && StrUtil.isEmpty(plantVarietyDetailJson)
                && Objects.isNull(administrativeLicenseNum)
                && StrUtil.isEmpty(administrativeLicenseDetailJson)
                && Objects.isNull(deliveryAnnouncementNum)
                && StrUtil.isEmpty(deliveryAnnouncementDetailJson)
                && Objects.isNull(endCaseNum)
                && StrUtil.isEmpty(endCaseDetailJson)
                && Objects.isNull(equityPledgeNum)
                && StrUtil.isEmpty(equityPledgeDetailJson)
                && Objects.isNull(iprPledgeNum)
                && StrUtil.isEmpty(iprPledgeDetailJson)
                && Objects.isNull(mortgageChattelNum)
                && StrUtil.isEmpty(mortgageChattelDetailJson)
                && Objects.isNull(primeStaffNum)
                && StrUtil.isEmpty(primeStaffDetailJson)
                && Objects.isNull(spotCheckNum)
                && StrUtil.isEmpty(spotCheckDetailJson)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyStatisticExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(establishYearNum, exWarehouseVO.getEstablishYearNum())) {
            this.establishYearNum = null;
        }
        if (Objects.equals(historyNameNum, exWarehouseVO.getHistoryNameNum())) {
            this.historyNameNum = null;
        }
        if (Objects.equals(listedNum, exWarehouseVO.getListedNum())) {
            this.listedNum = null;
        }
        if (Objects.equals(listedDetailJson, exWarehouseVO.getListedDetailJson())) {
            this.listedDetailJson = null;
        }
        if (Objects.equals(annualReportNum, exWarehouseVO.getAnnualReportNum())) {
            this.annualReportNum = null;
        }
        if (Objects.equals(abnormalNum, exWarehouseVO.getAbnormalNum())) {
            this.abnormalNum = null;
        }
        if (Objects.equals(abnormalDetailJson, exWarehouseVO.getAbnormalDetailJson())) {
            this.abnormalDetailJson = null;
        }
        if (Objects.equals(caseFilingNum, exWarehouseVO.getCaseFilingNum())) {
            this.caseFilingNum = null;
        }
        if (Objects.equals(caseFilingDetailJson, exWarehouseVO.getCaseFilingDetailJson())) {
            this.caseFilingDetailJson = null;
        }
        if (Objects.equals(courtAnnouncementNum, exWarehouseVO.getCourtAnnouncementNum())) {
            this.courtAnnouncementNum = null;
        }
        if (Objects.equals(courtAnnouncementDetailJson, exWarehouseVO.getCourtAnnouncementDetailJson())) {
            this.courtAnnouncementDetailJson = null;
        }
        if (Objects.equals(honorQualificationNum, exWarehouseVO.getHonorQualificationNum())) {
            this.honorQualificationNum = null;
        }
        if (Objects.equals(honorQualificationDetailJson, exWarehouseVO.getHonorQualificationDetailJson())) {
            this.honorQualificationDetailJson = null;
        }
        if (Objects.equals(iprPatentApplicantNum, exWarehouseVO.getIprPatentApplicantNum())) {
            this.iprPatentApplicantNum = null;
        }
        if (Objects.equals(iprPatentRightNum, exWarehouseVO.getIprPatentRightNum())) {
            this.iprPatentRightNum = null;
        }

        if (Objects.equals(iprPatentInventNum, exWarehouseVO.getIprPatentInventNum())) {
            this.iprPatentInventNum = null;
        }
        if (Objects.equals(iprPatentUtilityNum, exWarehouseVO.getIprPatentUtilityNum())) {
            this.iprPatentUtilityNum = null;
        }
        if (Objects.equals(iprPatentDesignNum, exWarehouseVO.getIprPatentDesignNum())) {
            this.iprPatentDesignNum = null;
        }
        if (Objects.equals(iprPatentValidNum, exWarehouseVO.getIprPatentValidNum())) {
            this.iprPatentValidNum = null;
        }
        if (Objects.equals(iprPatentValidInventNum, exWarehouseVO.getIprPatentValidInventNum())) {
            this.iprPatentValidInventNum = null;
        }
        if (Objects.equals(iprPatentValidUtilityNum, exWarehouseVO.getIprPatentValidUtilityNum())) {
            this.iprPatentValidUtilityNum = null;
        }
        if (Objects.equals(iprPatentValidDesignNum, exWarehouseVO.getIprPatentValidDesignNum())) {
            this.iprPatentValidDesignNum = null;
        }
        if (Objects.equals(iprPatentInvalidNum, exWarehouseVO.getIprPatentInvalidNum())) {
            this.iprPatentInvalidNum = null;
        }
        if (Objects.equals(iprPatentUnderReviewNum, exWarehouseVO.getIprPatentUnderReviewNum())) {
            this.iprPatentUnderReviewNum = null;
        }
        if (Objects.equals(discreditedJudgmentDebtorNum, exWarehouseVO.getDiscreditedJudgmentDebtorNum())) {
            this.discreditedJudgmentDebtorNum = null;
        }
        if (Objects.equals(discreditedJudgmentDebtorDetailJson, exWarehouseVO.getDiscreditedJudgmentDebtorDetailJson())) {
            this.discreditedJudgmentDebtorDetailJson = null;
        }
        if (Objects.equals(judgmentDebtorNum, exWarehouseVO.getJudgmentDebtorNum())) {
            this.judgmentDebtorNum = null;
        }
        if (Objects.equals(judgmentDebtorDetailJson, exWarehouseVO.getJudgmentDebtorDetailJson())) {
            this.judgmentDebtorDetailJson = null;
        }
        if (Objects.equals(judgmentDocumentNum, exWarehouseVO.getJudgmentDocumentNum())) {
            this.judgmentDocumentNum = null;
        }
        if (Objects.equals(judgmentDocumentDetailJson, exWarehouseVO.getJudgmentDocumentDetailJson())) {
            this.judgmentDocumentDetailJson = null;
        }
        if (Objects.equals(openCourtAnnouncementNum, exWarehouseVO.getOpenCourtAnnouncementNum())) {
            this.openCourtAnnouncementNum = null;
        }
        if (Objects.equals(openCourtAnnouncementDetailJson, exWarehouseVO.getOpenCourtAnnouncementDetailJson())) {
            this.openCourtAnnouncementDetailJson = null;
        }
        if (Objects.equals(punishmentNum, exWarehouseVO.getPunishmentNum())) {
            this.punishmentNum = null;
        }
        if (Objects.equals(punishmentDetailJson, exWarehouseVO.getPunishmentDetailJson())) {
            this.punishmentDetailJson = null;
        }
        if (Objects.equals(restrictHighConsumeNum, exWarehouseVO.getRestrictHighConsumeNum())) {
            this.restrictHighConsumeNum = null;
        }
        if (Objects.equals(restrictHighConsumeDetailJson, exWarehouseVO.getRestrictHighConsumeDetailJson())) {
            this.restrictHighConsumeDetailJson = null;
        }
        if (Objects.equals(seriousIllegalNum, exWarehouseVO.getSeriousIllegalNum())) {
            this.seriousIllegalNum = null;
        }
        if (Objects.equals(seriousIllegalDetailJson, exWarehouseVO.getSeriousIllegalDetailJson())) {
            this.seriousIllegalDetailJson = null;
        }
        if (Objects.equals(shareholderNum, exWarehouseVO.getShareholderNum())) {
            this.shareholderNum = null;
        }
        if (Objects.equals(shareholderDetailJson, exWarehouseVO.getShareholderDetailJson())) {
            this.shareholderDetailJson = null;
        }
        if (Objects.equals(vcFinancingNum, exWarehouseVO.getVcFinancingNum())) {
            this.vcFinancingNum = null;
        }
        if (Objects.equals(vcFinancingDetailJson, exWarehouseVO.getVcFinancingDetailJson())) {
            this.vcFinancingDetailJson = null;
        }
        if (Objects.equals(vcProductNum, exWarehouseVO.getVcProductNum())) {
            this.vcProductNum = null;
        }
        if (Objects.equals(trademarkNum, exWarehouseVO.getTrademarkNum())) {
            this.trademarkNum = null;
        }
        if (Objects.equals(trademarkDetailJson, exWarehouseVO.getTrademarkDetailJson())) {
            this.trademarkDetailJson = null;
        }
        if (Objects.equals(softwareCopyrightNum, exWarehouseVO.getSoftwareCopyrightNum())) {
            this.softwareCopyrightNum = null;
        }
        if (Objects.equals(softwareCopyrightDetailJson, exWarehouseVO.getSoftwareCopyrightDetailJson())) {
            this.softwareCopyrightDetailJson = null;
        }
        if (Objects.equals(workCopyrightNum, exWarehouseVO.getWorkCopyrightNum())) {
            this.workCopyrightNum = null;
        }
        if (Objects.equals(workCopyrightDetailJson, exWarehouseVO.getWorkCopyrightDetailJson())) {
            this.workCopyrightDetailJson = null;
        }
        if (Objects.equals(geographicalIndicationNum, exWarehouseVO.getGeographicalIndicationNum())) {
            this.geographicalIndicationNum = null;
        }
        if (Objects.equals(geographicalIndicationDetailJson, exWarehouseVO.getGeographicalIndicationDetailJson())) {
            this.geographicalIndicationDetailJson = null;
        }
        if (Objects.equals(integratedCircuitNum, exWarehouseVO.getIntegratedCircuitNum())) {
            this.integratedCircuitNum = null;
        }
        if (Objects.equals(integratedCircuitDetailJson, exWarehouseVO.getIntegratedCircuitDetailJson())) {
            this.integratedCircuitDetailJson = null;
        }
        if (Objects.equals(plantVarietyNum, exWarehouseVO.getPlantVarietyNum())) {
            this.plantVarietyNum = null;
        }
        if (Objects.equals(plantVarietyDetailJson, exWarehouseVO.getPlantVarietyDetailJson())) {
            this.plantVarietyDetailJson = null;
        }
        if (Objects.equals(administrativeLicenseNum, exWarehouseVO.getAdministrativeLicenseNum())) {
            this.administrativeLicenseNum = null;
        }
        if (Objects.equals(administrativeLicenseDetailJson, exWarehouseVO.getAdministrativeLicenseDetailJson())) {
            this.administrativeLicenseDetailJson = null;
        }
        if (Objects.equals(deliveryAnnouncementNum, exWarehouseVO.getDeliveryAnnouncementNum())) {
            this.deliveryAnnouncementNum = null;
        }
        if (Objects.equals(deliveryAnnouncementDetailJson, exWarehouseVO.getDeliveryAnnouncementDetailJson())) {
            this.deliveryAnnouncementDetailJson = null;
        }
        if (Objects.equals(endCaseNum, exWarehouseVO.getEndCaseNum())) {
            this.endCaseNum = null;
        }
        if (Objects.equals(endCaseDetailJson, exWarehouseVO.getEndCaseDetailJson())) {
            this.endCaseDetailJson = null;
        }
        if (Objects.equals(equityPledgeNum, exWarehouseVO.getEquityPledgeNum())) {
            this.equityPledgeNum = null;
        }
        if (Objects.equals(equityPledgeDetailJson, exWarehouseVO.getEquityPledgeDetailJson())) {
            this.equityPledgeDetailJson = null;
        }
        if (Objects.equals(iprPledgeNum, exWarehouseVO.getIprPledgeNum())) {
            this.iprPledgeNum = null;
        }
        if (Objects.equals(iprPledgeDetailJson, exWarehouseVO.getIprPledgeDetailJson())) {
            this.iprPledgeDetailJson = null;
        }
        if (Objects.equals(mortgageChattelNum, exWarehouseVO.getMortgageChattelNum())) {
            this.mortgageChattelNum = null;
        }
        if (Objects.equals(mortgageChattelDetailJson, exWarehouseVO.getMortgageChattelDetailJson())) {
            this.mortgageChattelDetailJson = null;
        }
        if (Objects.equals(primeStaffNum, exWarehouseVO.getPrimeStaffNum())) {
            this.primeStaffNum = null;
        }
        if (Objects.equals(primeStaffDetailJson, exWarehouseVO.getPrimeStaffDetailJson())) {
            this.primeStaffDetailJson = null;
        }
        if (Objects.equals(spotCheckNum, exWarehouseVO.getSpotCheckNum())) {
            this.spotCheckNum = null;
        }
        if (Objects.equals(spotCheckDetailJson, exWarehouseVO.getSpotCheckDetailJson())) {
            this.spotCheckDetailJson = null;
        }

    }

    public static DataCompanyStatisticWarehouseCommand createByDataCompanyStatisticExWarehouseVO(DataCompanyStatisticExWarehouseVO exWarehouseVO){
        DataCompanyStatisticWarehouseCommand command = new DataCompanyStatisticWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.establishYearNum = exWarehouseVO.getEstablishYearNum();
        command.historyNameNum = exWarehouseVO.getHistoryNameNum();
        command.listedNum = exWarehouseVO.getListedNum();
        command.listedDetailJson = exWarehouseVO.getListedDetailJson();
        command.annualReportNum = exWarehouseVO.getAnnualReportNum();
        command.abnormalNum = exWarehouseVO.getAbnormalNum();
        command.abnormalDetailJson = exWarehouseVO.getAbnormalDetailJson();
        command.caseFilingNum = exWarehouseVO.getCaseFilingNum();
        command.caseFilingDetailJson = exWarehouseVO.getCaseFilingDetailJson();
        command.courtAnnouncementNum = exWarehouseVO.getCourtAnnouncementNum();
        command.courtAnnouncementDetailJson = exWarehouseVO.getCourtAnnouncementDetailJson();
        command.honorQualificationNum = exWarehouseVO.getHonorQualificationNum();
        command.honorQualificationDetailJson = exWarehouseVO.getHonorQualificationDetailJson();
        command.iprPatentApplicantNum = exWarehouseVO.getIprPatentApplicantNum();
        command.iprPatentRightNum = exWarehouseVO.getIprPatentRightNum();
        command.iprPatentInventNum = exWarehouseVO.getIprPatentInventNum();
        command.iprPatentUtilityNum = exWarehouseVO.getIprPatentUtilityNum();
        command.iprPatentDesignNum = exWarehouseVO.getIprPatentDesignNum();
        command.iprPatentValidNum = exWarehouseVO.getIprPatentValidNum();
        command.iprPatentValidInventNum = exWarehouseVO.getIprPatentValidInventNum();
        command.iprPatentValidUtilityNum = exWarehouseVO.getIprPatentValidUtilityNum();
        command.iprPatentValidDesignNum = exWarehouseVO.getIprPatentValidDesignNum();
        command.iprPatentInvalidNum = exWarehouseVO.getIprPatentInvalidNum();
        command.iprPatentUnderReviewNum = exWarehouseVO.getIprPatentUnderReviewNum();
        command.discreditedJudgmentDebtorNum = exWarehouseVO.getDiscreditedJudgmentDebtorNum();
        command.discreditedJudgmentDebtorDetailJson = exWarehouseVO.getDiscreditedJudgmentDebtorDetailJson();
        command.judgmentDebtorNum = exWarehouseVO.getJudgmentDebtorNum();
        command.judgmentDebtorDetailJson = exWarehouseVO.getJudgmentDebtorDetailJson();
        command.judgmentDocumentNum = exWarehouseVO.getJudgmentDocumentNum();
        command.judgmentDocumentDetailJson = exWarehouseVO.getJudgmentDocumentDetailJson();
        command.openCourtAnnouncementNum = exWarehouseVO.getOpenCourtAnnouncementNum();
        command.openCourtAnnouncementDetailJson = exWarehouseVO.getOpenCourtAnnouncementDetailJson();
        command.punishmentNum = exWarehouseVO.getPunishmentNum();
        command.punishmentDetailJson = exWarehouseVO.getPunishmentDetailJson();
        command.restrictHighConsumeNum = exWarehouseVO.getRestrictHighConsumeNum();
        command.restrictHighConsumeDetailJson = exWarehouseVO.getRestrictHighConsumeDetailJson();
        command.seriousIllegalNum = exWarehouseVO.getSeriousIllegalNum();
        command.seriousIllegalDetailJson = exWarehouseVO.getSeriousIllegalDetailJson();
        command.shareholderNum = exWarehouseVO.getShareholderNum();
        command.shareholderDetailJson = exWarehouseVO.getShareholderDetailJson();
        command.vcFinancingNum = exWarehouseVO.getVcFinancingNum();
        command.vcFinancingDetailJson = exWarehouseVO.getVcFinancingDetailJson();
        command.vcProductNum = exWarehouseVO.getVcProductNum();
        command.trademarkNum = exWarehouseVO.getTrademarkNum();
        command.trademarkDetailJson = exWarehouseVO.getTrademarkDetailJson();
        command.softwareCopyrightNum = exWarehouseVO.getSoftwareCopyrightNum();
        command.softwareCopyrightDetailJson = exWarehouseVO.getSoftwareCopyrightDetailJson();
        command.workCopyrightNum = exWarehouseVO.getWorkCopyrightNum();
        command.workCopyrightDetailJson = exWarehouseVO.getWorkCopyrightDetailJson();
        command.geographicalIndicationNum = exWarehouseVO.getGeographicalIndicationNum();
        command.geographicalIndicationDetailJson = exWarehouseVO.getGeographicalIndicationDetailJson();
        command.integratedCircuitNum = exWarehouseVO.getIntegratedCircuitNum();
        command.integratedCircuitDetailJson = exWarehouseVO.getIntegratedCircuitDetailJson();
        command.plantVarietyNum = exWarehouseVO.getPlantVarietyNum();
        command.plantVarietyDetailJson = exWarehouseVO.getPlantVarietyDetailJson();
        command.administrativeLicenseNum = exWarehouseVO.getAdministrativeLicenseNum();
        command.administrativeLicenseDetailJson = exWarehouseVO.getAdministrativeLicenseDetailJson();
        command.deliveryAnnouncementNum = exWarehouseVO.getDeliveryAnnouncementNum();
        command.deliveryAnnouncementDetailJson = exWarehouseVO.getDeliveryAnnouncementDetailJson();
        command.endCaseNum = exWarehouseVO.getEndCaseNum();
        command.endCaseDetailJson = exWarehouseVO.getEndCaseDetailJson();
        command.equityPledgeNum = exWarehouseVO.getEquityPledgeNum();
        command.equityPledgeDetailJson = exWarehouseVO.getEquityPledgeDetailJson();
        command.iprPledgeNum = exWarehouseVO.getIprPledgeNum();
        command.iprPledgeDetailJson = exWarehouseVO.getIprPledgeDetailJson();
        command.mortgageChattelNum = exWarehouseVO.getMortgageChattelNum();
        command.mortgageChattelDetailJson = exWarehouseVO.getMortgageChattelDetailJson();
        command.primeStaffNum = exWarehouseVO.getPrimeStaffNum();
        command.primeStaffDetailJson = exWarehouseVO.getPrimeStaffDetailJson();
        command.spotCheckNum = exWarehouseVO.getSpotCheckNum();
        command.spotCheckDetailJson = exWarehouseVO.getSpotCheckDetailJson();


        return command;
    }
}
