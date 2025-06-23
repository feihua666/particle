package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportSocialSecurityWarehouseCommand;

/**
 * <p>
 * 企业年报社保 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Data
@Schema
public class DataCompanyAnnualReportSocialSecurityCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报id 不能为空")
    @Schema(description = "企业年报id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;


    @Schema(description = "城镇职工基本养老保险人数")
    private Integer endowmentInsurancePeopleNum;


    @Schema(description = "失业保险人数")
    private Integer unemploymentInsurancePeopleNum;


    @Schema(description = "职工基本医疗保险人数")
    private Integer medicalInsurancePeopleNum;


    @Schema(description = "工伤保险人数")
    private Integer employmentInjuryInsurancePeopleNum;


    @Schema(description = "生育保险人数")
    private Integer maternityInsurancePeopleNum;


    @Schema(description = "单位参加城镇职工基本养老保险缴费基数")
    private BigDecimal endowmentInsuranceBaseAmount;


    @Schema(description = "单位参加失业保险缴费基数")
    private BigDecimal unemploymentInsuranceBaseAmount;


    @Schema(description = "单位参加职工基本医疗保险缴费基数")
    private BigDecimal medicalInsuranceBaseAmount;


    @Schema(description = "单位参加生育保险缴费基数")
    private BigDecimal maternityInsuranceBaseAmount;


    @Schema(description = "参加城镇职工基本养老保险本期实际缴费金额")
    private BigDecimal endowmentInsurancePayAmount;


    @Schema(description = "参加失业保险本期实际缴费金额")
    private BigDecimal unemploymentInsurancePayAmount;


    @Schema(description = "参加职工基本医疗保险本期实际缴费金额")
    private BigDecimal medicalInsurancePayAmount;


    @Schema(description = "参加工伤保险本期实际缴费金额")
    private BigDecimal employmentInjuryInsurancePayAmount;


    @Schema(description = "参加生育保险本期实际缴费金额")
    private BigDecimal maternityInsurancePayAmount;


    @Schema(description = "单位参加城镇职工基本养老保险累计欠缴金额")
    private BigDecimal endowmentInsuranceOweAmount;


    @Schema(description = "单位参加失业保险累计欠缴金额")
    private BigDecimal unemploymentInsuranceOweAmount;


    @Schema(description = "单位参加职工基本医疗保险累计欠缴金额")
    private BigDecimal medicalInsuranceOweAmount;


    @Schema(description = "单位参加工伤保险累计欠缴金额")
    private BigDecimal employmentInjuryInsuranceOweAmount;


    @Schema(description = "单位参加生育保险累计欠缴金额")
    private BigDecimal maternityInsuranceOweAmount;


    @Schema(description = "是否单位参加城镇职工基本养老保险缴费基数公示")
    private Boolean isEndowmentInsuranceBaseAmountPublic;


    @Schema(description = "是否单位参加失业保险缴费基数公示")
    private Boolean isUnemploymentInsuranceBaseAmountPublic;


    @Schema(description = "是否单位参加职工基本医疗保险缴费基数公示")
    private Boolean isMedicalInsuranceBaseAmountPublic;


    @Schema(description = "是否单位参加生育保险缴费基数公示")
    private Boolean isMaternityInsuranceBaseAmountPublic;


    @Schema(description = "是否参加城镇职工基本养老保险本期实际缴费金额公示")
    private Boolean isEndowmentInsurancePayAmountPublic;


    @Schema(description = "是否参加失业保险本期实际缴费金额公示")
    private Boolean isUnemploymentInsurancePayAmountPublic;


    @Schema(description = "是否参加职工基本医疗保险本期实际缴费金额公示")
    private Boolean isMedicalInsurancePayAmountPublic;


    @Schema(description = "是否参加工伤保险本期实际缴费金额公示")
    private Boolean isEmploymentInjuryInsurancePayAmountPublic;


    @Schema(description = "是否参加生育保险本期实际缴费金额公示")
    private Boolean isMaternityInsurancePayAmountPublic;


    @Schema(description = "是否单位参加城镇职工基本养老保险累计欠缴金额公示")
    private Boolean isEndowmentInsuranceOweAmountPublic;


    @Schema(description = "是否单位参加失业保险累计欠缴金额公示")
    private Boolean isUnemploymentInsuranceOweAmountPublic;


    @Schema(description = "是否单位参加职工基本医疗保险累计欠缴金额公示")
    private Boolean isMedicalInsuranceOweAmountPublic;


    @Schema(description = "是否单位参加工伤保险累计欠缴金额公示")
    private Boolean isEmploymentInjuryInsuranceOweAmountPublic;


    @Schema(description = "是否单位参加生育保险累计欠缴金额公示")
    private Boolean isMaternityInsuranceOweAmountPublic;


    public static DataCompanyAnnualReportSocialSecurityCreateCommand createByWarehouseCommand(DataCompanyAnnualReportSocialSecurityWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportSocialSecurityCreateCommand command = new DataCompanyAnnualReportSocialSecurityCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.endowmentInsurancePeopleNum = dataCompanyBasicWarehouseCommand.getEndowmentInsurancePeopleNum();
        command.unemploymentInsurancePeopleNum = dataCompanyBasicWarehouseCommand.getUnemploymentInsurancePeopleNum();
        command.medicalInsurancePeopleNum = dataCompanyBasicWarehouseCommand.getMedicalInsurancePeopleNum();
        command.employmentInjuryInsurancePeopleNum = dataCompanyBasicWarehouseCommand.getEmploymentInjuryInsurancePeopleNum();
        command.maternityInsurancePeopleNum = dataCompanyBasicWarehouseCommand.getMaternityInsurancePeopleNum();
        command.endowmentInsuranceBaseAmount = dataCompanyBasicWarehouseCommand.getEndowmentInsuranceBaseAmount();
        command.unemploymentInsuranceBaseAmount = dataCompanyBasicWarehouseCommand.getUnemploymentInsuranceBaseAmount();
        command.medicalInsuranceBaseAmount = dataCompanyBasicWarehouseCommand.getMedicalInsuranceBaseAmount();
        command.maternityInsuranceBaseAmount = dataCompanyBasicWarehouseCommand.getMaternityInsuranceBaseAmount();
        command.endowmentInsurancePayAmount = dataCompanyBasicWarehouseCommand.getEndowmentInsurancePayAmount();
        command.unemploymentInsurancePayAmount = dataCompanyBasicWarehouseCommand.getUnemploymentInsurancePayAmount();
        command.medicalInsurancePayAmount = dataCompanyBasicWarehouseCommand.getMedicalInsurancePayAmount();
        command.employmentInjuryInsurancePayAmount = dataCompanyBasicWarehouseCommand.getEmploymentInjuryInsurancePayAmount();
        command.maternityInsurancePayAmount = dataCompanyBasicWarehouseCommand.getMaternityInsurancePayAmount();
        command.endowmentInsuranceOweAmount = dataCompanyBasicWarehouseCommand.getEndowmentInsuranceOweAmount();
        command.unemploymentInsuranceOweAmount = dataCompanyBasicWarehouseCommand.getUnemploymentInsuranceOweAmount();
        command.medicalInsuranceOweAmount = dataCompanyBasicWarehouseCommand.getMedicalInsuranceOweAmount();
        command.employmentInjuryInsuranceOweAmount = dataCompanyBasicWarehouseCommand.getEmploymentInjuryInsuranceOweAmount();
        command.maternityInsuranceOweAmount = dataCompanyBasicWarehouseCommand.getMaternityInsuranceOweAmount();
        command.isEndowmentInsuranceBaseAmountPublic = dataCompanyBasicWarehouseCommand.getIsEndowmentInsuranceBaseAmountPublic();
        command.isUnemploymentInsuranceBaseAmountPublic = dataCompanyBasicWarehouseCommand.getIsUnemploymentInsuranceBaseAmountPublic();
        command.isMedicalInsuranceBaseAmountPublic = dataCompanyBasicWarehouseCommand.getIsMedicalInsuranceBaseAmountPublic();
        command.isMaternityInsuranceBaseAmountPublic = dataCompanyBasicWarehouseCommand.getIsMaternityInsuranceBaseAmountPublic();
        command.isEndowmentInsurancePayAmountPublic = dataCompanyBasicWarehouseCommand.getIsEndowmentInsurancePayAmountPublic();
        command.isUnemploymentInsurancePayAmountPublic = dataCompanyBasicWarehouseCommand.getIsUnemploymentInsurancePayAmountPublic();
        command.isMedicalInsurancePayAmountPublic = dataCompanyBasicWarehouseCommand.getIsMedicalInsurancePayAmountPublic();
        command.isEmploymentInjuryInsurancePayAmountPublic = dataCompanyBasicWarehouseCommand.getIsEmploymentInjuryInsurancePayAmountPublic();
        command.isMaternityInsurancePayAmountPublic = dataCompanyBasicWarehouseCommand.getIsMaternityInsurancePayAmountPublic();
        command.isEndowmentInsuranceOweAmountPublic = dataCompanyBasicWarehouseCommand.getIsEndowmentInsuranceOweAmountPublic();
        command.isUnemploymentInsuranceOweAmountPublic = dataCompanyBasicWarehouseCommand.getIsUnemploymentInsuranceOweAmountPublic();
        command.isMedicalInsuranceOweAmountPublic = dataCompanyBasicWarehouseCommand.getIsMedicalInsuranceOweAmountPublic();
        command.isEmploymentInjuryInsuranceOweAmountPublic = dataCompanyBasicWarehouseCommand.getIsEmploymentInjuryInsuranceOweAmountPublic();
        command.isMaternityInsuranceOweAmountPublic = dataCompanyBasicWarehouseCommand.getIsMaternityInsuranceOweAmountPublic();


        return command;
    }
}
