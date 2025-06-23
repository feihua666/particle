package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyStatisticWarehouseCommand;

/**
 * <p>
 * 企业统计 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Data
@Schema
public class DataCompanyStatisticCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
        @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;










    public static DataCompanyStatisticCreateCommand createByWarehouseCommand(DataCompanyStatisticWarehouseCommand dataCompanyStatisticWarehouseCommand){
        DataCompanyStatisticCreateCommand command = new DataCompanyStatisticCreateCommand();
        command.companyId = dataCompanyStatisticWarehouseCommand.getCompanyId();
        command.establishYearNum = dataCompanyStatisticWarehouseCommand.getEstablishYearNum();
        command.historyNameNum = dataCompanyStatisticWarehouseCommand.getHistoryNameNum();
        command.listedNum = dataCompanyStatisticWarehouseCommand.getListedNum();
        command.listedDetailJson = dataCompanyStatisticWarehouseCommand.getListedDetailJson();
        command.annualReportNum = dataCompanyStatisticWarehouseCommand.getAnnualReportNum();
        command.abnormalNum = dataCompanyStatisticWarehouseCommand.getAbnormalNum();
        command.abnormalDetailJson = dataCompanyStatisticWarehouseCommand.getAbnormalDetailJson();
        command.caseFilingNum = dataCompanyStatisticWarehouseCommand.getCaseFilingNum();
        command.caseFilingDetailJson = dataCompanyStatisticWarehouseCommand.getCaseFilingDetailJson();
        command.courtAnnouncementNum = dataCompanyStatisticWarehouseCommand.getCourtAnnouncementNum();
        command.courtAnnouncementDetailJson = dataCompanyStatisticWarehouseCommand.getCourtAnnouncementDetailJson();
        command.honorQualificationNum = dataCompanyStatisticWarehouseCommand.getHonorQualificationNum();
        command.honorQualificationDetailJson = dataCompanyStatisticWarehouseCommand.getHonorQualificationDetailJson();
        command.iprPatentApplicantNum = dataCompanyStatisticWarehouseCommand.getIprPatentApplicantNum();
        command.iprPatentRightNum = dataCompanyStatisticWarehouseCommand.getIprPatentRightNum();
        command.iprPatentInventNum = dataCompanyStatisticWarehouseCommand.getIprPatentInventNum();
        command.iprPatentUtilityNum = dataCompanyStatisticWarehouseCommand.getIprPatentUtilityNum();
        command.iprPatentDesignNum = dataCompanyStatisticWarehouseCommand.getIprPatentDesignNum();
        command.iprPatentValidNum = dataCompanyStatisticWarehouseCommand.getIprPatentValidNum();
        command.iprPatentValidInventNum = dataCompanyStatisticWarehouseCommand.getIprPatentValidInventNum();
        command.iprPatentValidUtilityNum = dataCompanyStatisticWarehouseCommand.getIprPatentValidUtilityNum();
        command.iprPatentValidDesignNum = dataCompanyStatisticWarehouseCommand.getIprPatentValidDesignNum();
        command.iprPatentInvalidNum = dataCompanyStatisticWarehouseCommand.getIprPatentInvalidNum();
        command.iprPatentUnderReviewNum = dataCompanyStatisticWarehouseCommand.getIprPatentUnderReviewNum();
        command.discreditedJudgmentDebtorNum = dataCompanyStatisticWarehouseCommand.getDiscreditedJudgmentDebtorNum();
        command.discreditedJudgmentDebtorDetailJson = dataCompanyStatisticWarehouseCommand.getDiscreditedJudgmentDebtorDetailJson();
        command.judgmentDebtorNum = dataCompanyStatisticWarehouseCommand.getJudgmentDebtorNum();
        command.judgmentDebtorDetailJson = dataCompanyStatisticWarehouseCommand.getJudgmentDebtorDetailJson();
        command.judgmentDocumentNum = dataCompanyStatisticWarehouseCommand.getJudgmentDocumentNum();
        command.judgmentDocumentDetailJson = dataCompanyStatisticWarehouseCommand.getJudgmentDocumentDetailJson();
        command.openCourtAnnouncementNum = dataCompanyStatisticWarehouseCommand.getOpenCourtAnnouncementNum();
        command.openCourtAnnouncementDetailJson = dataCompanyStatisticWarehouseCommand.getOpenCourtAnnouncementDetailJson();
        command.punishmentNum = dataCompanyStatisticWarehouseCommand.getPunishmentNum();
        command.punishmentDetailJson = dataCompanyStatisticWarehouseCommand.getPunishmentDetailJson();
        command.restrictHighConsumeNum = dataCompanyStatisticWarehouseCommand.getRestrictHighConsumeNum();
        command.restrictHighConsumeDetailJson = dataCompanyStatisticWarehouseCommand.getRestrictHighConsumeDetailJson();
        command.seriousIllegalNum = dataCompanyStatisticWarehouseCommand.getSeriousIllegalNum();
        command.seriousIllegalDetailJson = dataCompanyStatisticWarehouseCommand.getSeriousIllegalDetailJson();
        command.shareholderNum = dataCompanyStatisticWarehouseCommand.getShareholderNum();
        command.shareholderDetailJson = dataCompanyStatisticWarehouseCommand.getShareholderDetailJson();
        command.vcFinancingNum = dataCompanyStatisticWarehouseCommand.getVcFinancingNum();
        command.vcFinancingDetailJson = dataCompanyStatisticWarehouseCommand.getVcFinancingDetailJson();
        command.vcProductNum = dataCompanyStatisticWarehouseCommand.getVcProductNum();
        command.trademarkNum = dataCompanyStatisticWarehouseCommand.getTrademarkNum();
        command.trademarkDetailJson = dataCompanyStatisticWarehouseCommand.getTrademarkDetailJson();
        command.softwareCopyrightNum = dataCompanyStatisticWarehouseCommand.getSoftwareCopyrightNum();
        command.softwareCopyrightDetailJson = dataCompanyStatisticWarehouseCommand.getSoftwareCopyrightDetailJson();
        command.workCopyrightNum = dataCompanyStatisticWarehouseCommand.getWorkCopyrightNum();
        command.workCopyrightDetailJson = dataCompanyStatisticWarehouseCommand.getWorkCopyrightDetailJson();
        command.geographicalIndicationNum = dataCompanyStatisticWarehouseCommand.getGeographicalIndicationNum();
        command.geographicalIndicationDetailJson = dataCompanyStatisticWarehouseCommand.getGeographicalIndicationDetailJson();
        command.integratedCircuitNum = dataCompanyStatisticWarehouseCommand.getIntegratedCircuitNum();
        command.integratedCircuitDetailJson = dataCompanyStatisticWarehouseCommand.getIntegratedCircuitDetailJson();
        command.plantVarietyNum = dataCompanyStatisticWarehouseCommand.getPlantVarietyNum();
        command.plantVarietyDetailJson = dataCompanyStatisticWarehouseCommand.getPlantVarietyDetailJson();
        command.administrativeLicenseNum = dataCompanyStatisticWarehouseCommand.getAdministrativeLicenseNum();
        command.administrativeLicenseDetailJson = dataCompanyStatisticWarehouseCommand.getAdministrativeLicenseDetailJson();
        command.deliveryAnnouncementNum = dataCompanyStatisticWarehouseCommand.getDeliveryAnnouncementNum();
        command.deliveryAnnouncementDetailJson = dataCompanyStatisticWarehouseCommand.getDeliveryAnnouncementDetailJson();
        command.endCaseNum = dataCompanyStatisticWarehouseCommand.getEndCaseNum();
        command.endCaseDetailJson = dataCompanyStatisticWarehouseCommand.getEndCaseDetailJson();
        command.equityPledgeNum = dataCompanyStatisticWarehouseCommand.getEquityPledgeNum();
        command.equityPledgeDetailJson = dataCompanyStatisticWarehouseCommand.getEquityPledgeDetailJson();
        command.iprPledgeNum = dataCompanyStatisticWarehouseCommand.getIprPledgeNum();
        command.iprPledgeDetailJson = dataCompanyStatisticWarehouseCommand.getIprPledgeDetailJson();
        command.mortgageChattelNum = dataCompanyStatisticWarehouseCommand.getMortgageChattelNum();
        command.mortgageChattelDetailJson = dataCompanyStatisticWarehouseCommand.getMortgageChattelDetailJson();
        command.primeStaffNum = dataCompanyStatisticWarehouseCommand.getPrimeStaffNum();
        command.primeStaffDetailJson = dataCompanyStatisticWarehouseCommand.getPrimeStaffDetailJson();
        command.spotCheckNum = dataCompanyStatisticWarehouseCommand.getSpotCheckNum();
        command.spotCheckDetailJson = dataCompanyStatisticWarehouseCommand.getSpotCheckDetailJson();


        return command;
    }
}
