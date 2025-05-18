package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportSocialSecurityExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 企业年报社保入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportSocialSecurityWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报表ID 不能为空")
    @Schema(description = "企业年报表ID",requiredMode = Schema.RequiredMode.REQUIRED)
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


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyAnnualReportId)
                && Objects.isNull(year)
                && Objects.isNull(endowmentInsurancePeopleNum)
                && Objects.isNull(unemploymentInsurancePeopleNum)
                && Objects.isNull(medicalInsurancePeopleNum)
                && Objects.isNull(employmentInjuryInsurancePeopleNum)
                && Objects.isNull(maternityInsurancePeopleNum)
                && Objects.isNull(endowmentInsuranceBaseAmount)
                && Objects.isNull(unemploymentInsuranceBaseAmount)
                && Objects.isNull(medicalInsuranceBaseAmount)
                && Objects.isNull(maternityInsuranceBaseAmount)
                && Objects.isNull(endowmentInsurancePayAmount)
                && Objects.isNull(unemploymentInsurancePayAmount)
                && Objects.isNull(medicalInsurancePayAmount)
                && Objects.isNull(employmentInjuryInsurancePayAmount)
                && Objects.isNull(maternityInsurancePayAmount)
                && Objects.isNull(endowmentInsuranceOweAmount)
                && Objects.isNull(unemploymentInsuranceOweAmount)
                && Objects.isNull(medicalInsuranceOweAmount)
                && Objects.isNull(employmentInjuryInsuranceOweAmount)
                && Objects.isNull(maternityInsuranceOweAmount)
                && Objects.isNull(isEndowmentInsuranceBaseAmountPublic)
                && Objects.isNull(isUnemploymentInsuranceBaseAmountPublic)
                && Objects.isNull(isMedicalInsuranceBaseAmountPublic)
                && Objects.isNull(isMaternityInsuranceBaseAmountPublic)
                && Objects.isNull(isEndowmentInsurancePayAmountPublic)
                && Objects.isNull(isUnemploymentInsurancePayAmountPublic)
                && Objects.isNull(isMedicalInsurancePayAmountPublic)
                && Objects.isNull(isEmploymentInjuryInsurancePayAmountPublic)
                && Objects.isNull(isMaternityInsurancePayAmountPublic)
                && Objects.isNull(isEndowmentInsuranceOweAmountPublic)
                && Objects.isNull(isUnemploymentInsuranceOweAmountPublic)
                && Objects.isNull(isMedicalInsuranceOweAmountPublic)
                && Objects.isNull(isEmploymentInjuryInsuranceOweAmountPublic)
                && Objects.isNull(isMaternityInsuranceOweAmountPublic);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportSocialSecurityExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyAnnualReportId, exWarehouseVO.getCompanyAnnualReportId())) {
            this.companyAnnualReportId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (Objects.equals(endowmentInsurancePeopleNum, exWarehouseVO.getEndowmentInsurancePeopleNum())) {
            this.endowmentInsurancePeopleNum = null;
        }
        if (Objects.equals(unemploymentInsurancePeopleNum, exWarehouseVO.getUnemploymentInsurancePeopleNum())) {
            this.unemploymentInsurancePeopleNum = null;
        }
        if (Objects.equals(medicalInsurancePeopleNum, exWarehouseVO.getMedicalInsurancePeopleNum())) {
            this.medicalInsurancePeopleNum = null;
        }
        if (Objects.equals(employmentInjuryInsurancePeopleNum, exWarehouseVO.getEmploymentInjuryInsurancePeopleNum())) {
            this.employmentInjuryInsurancePeopleNum = null;
        }
        if (Objects.equals(maternityInsurancePeopleNum, exWarehouseVO.getMaternityInsurancePeopleNum())) {
            this.maternityInsurancePeopleNum = null;
        }
        if (NumberUtil.equals(endowmentInsuranceBaseAmount, exWarehouseVO.getEndowmentInsuranceBaseAmount())) {
            this.endowmentInsuranceBaseAmount = null;
        }
        if (NumberUtil.equals(unemploymentInsuranceBaseAmount, exWarehouseVO.getUnemploymentInsuranceBaseAmount())) {
            this.unemploymentInsuranceBaseAmount = null;
        }
        if (NumberUtil.equals(medicalInsuranceBaseAmount, exWarehouseVO.getMedicalInsuranceBaseAmount())) {
            this.medicalInsuranceBaseAmount = null;
        }
        if (NumberUtil.equals(maternityInsuranceBaseAmount, exWarehouseVO.getMaternityInsuranceBaseAmount())) {
            this.maternityInsuranceBaseAmount = null;
        }
        if (NumberUtil.equals(endowmentInsurancePayAmount, exWarehouseVO.getEndowmentInsurancePayAmount())) {
            this.endowmentInsurancePayAmount = null;
        }
        if (NumberUtil.equals(unemploymentInsurancePayAmount, exWarehouseVO.getUnemploymentInsurancePayAmount())) {
            this.unemploymentInsurancePayAmount = null;
        }
        if (NumberUtil.equals(medicalInsurancePayAmount, exWarehouseVO.getMedicalInsurancePayAmount())) {
            this.medicalInsurancePayAmount = null;
        }
        if (NumberUtil.equals(employmentInjuryInsurancePayAmount, exWarehouseVO.getEmploymentInjuryInsurancePayAmount())) {
            this.employmentInjuryInsurancePayAmount = null;
        }
        if (NumberUtil.equals(maternityInsurancePayAmount, exWarehouseVO.getMaternityInsurancePayAmount())) {
            this.maternityInsurancePayAmount = null;
        }
        if (NumberUtil.equals(endowmentInsuranceOweAmount, exWarehouseVO.getEndowmentInsuranceOweAmount())) {
            this.endowmentInsuranceOweAmount = null;
        }
        if (NumberUtil.equals(unemploymentInsuranceOweAmount, exWarehouseVO.getUnemploymentInsuranceOweAmount())) {
            this.unemploymentInsuranceOweAmount = null;
        }
        if (NumberUtil.equals(medicalInsuranceOweAmount, exWarehouseVO.getMedicalInsuranceOweAmount())) {
            this.medicalInsuranceOweAmount = null;
        }
        if (NumberUtil.equals(employmentInjuryInsuranceOweAmount, exWarehouseVO.getEmploymentInjuryInsuranceOweAmount())) {
            this.employmentInjuryInsuranceOweAmount = null;
        }
        if (NumberUtil.equals(maternityInsuranceOweAmount, exWarehouseVO.getMaternityInsuranceOweAmount())) {
            this.maternityInsuranceOweAmount = null;
        }
        if (Objects.equals(isEndowmentInsuranceBaseAmountPublic, exWarehouseVO.getIsEndowmentInsuranceBaseAmountPublic())) {
            this.isEndowmentInsuranceBaseAmountPublic = null;
        }
        if (Objects.equals(isUnemploymentInsuranceBaseAmountPublic, exWarehouseVO.getIsUnemploymentInsuranceBaseAmountPublic())) {
            this.isUnemploymentInsuranceBaseAmountPublic = null;
        }
        if (Objects.equals(isMedicalInsuranceBaseAmountPublic, exWarehouseVO.getIsMedicalInsuranceBaseAmountPublic())) {
            this.isMedicalInsuranceBaseAmountPublic = null;
        }
        if (Objects.equals(isMaternityInsuranceBaseAmountPublic, exWarehouseVO.getIsMaternityInsuranceBaseAmountPublic())) {
            this.isMaternityInsuranceBaseAmountPublic = null;
        }
        if (Objects.equals(isEndowmentInsurancePayAmountPublic, exWarehouseVO.getIsEndowmentInsurancePayAmountPublic())) {
            this.isEndowmentInsurancePayAmountPublic = null;
        }
        if (Objects.equals(isUnemploymentInsurancePayAmountPublic, exWarehouseVO.getIsUnemploymentInsurancePayAmountPublic())) {
            this.isUnemploymentInsurancePayAmountPublic = null;
        }
        if (Objects.equals(isMedicalInsurancePayAmountPublic, exWarehouseVO.getIsMedicalInsurancePayAmountPublic())) {
            this.isMedicalInsurancePayAmountPublic = null;
        }
        if (Objects.equals(isEmploymentInjuryInsurancePayAmountPublic, exWarehouseVO.getIsEmploymentInjuryInsurancePayAmountPublic())) {
            this.isEmploymentInjuryInsurancePayAmountPublic = null;
        }
        if (Objects.equals(isMaternityInsurancePayAmountPublic, exWarehouseVO.getIsMaternityInsurancePayAmountPublic())) {
            this.isMaternityInsurancePayAmountPublic = null;
        }
        if (Objects.equals(isEndowmentInsuranceOweAmountPublic, exWarehouseVO.getIsEndowmentInsuranceOweAmountPublic())) {
            this.isEndowmentInsuranceOweAmountPublic = null;
        }
        if (Objects.equals(isUnemploymentInsuranceOweAmountPublic, exWarehouseVO.getIsUnemploymentInsuranceOweAmountPublic())) {
            this.isUnemploymentInsuranceOweAmountPublic = null;
        }
        if (Objects.equals(isMedicalInsuranceOweAmountPublic, exWarehouseVO.getIsMedicalInsuranceOweAmountPublic())) {
            this.isMedicalInsuranceOweAmountPublic = null;
        }
        if (Objects.equals(isEmploymentInjuryInsuranceOweAmountPublic, exWarehouseVO.getIsEmploymentInjuryInsuranceOweAmountPublic())) {
            this.isEmploymentInjuryInsuranceOweAmountPublic = null;
        }
        if (Objects.equals(isMaternityInsuranceOweAmountPublic, exWarehouseVO.getIsMaternityInsuranceOweAmountPublic())) {
            this.isMaternityInsuranceOweAmountPublic = null;
        }


    }

    public static DataCompanyAnnualReportSocialSecurityWarehouseCommand createByDataCompanyAnnualReportSocialSecurityExWarehouseVO(DataCompanyAnnualReportSocialSecurityExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportSocialSecurityWarehouseCommand command = new DataCompanyAnnualReportSocialSecurityWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.endowmentInsurancePeopleNum = exWarehouseVO.getEndowmentInsurancePeopleNum();
        command.unemploymentInsurancePeopleNum = exWarehouseVO.getUnemploymentInsurancePeopleNum();
        command.medicalInsurancePeopleNum = exWarehouseVO.getMedicalInsurancePeopleNum();
        command.employmentInjuryInsurancePeopleNum = exWarehouseVO.getEmploymentInjuryInsurancePeopleNum();
        command.maternityInsurancePeopleNum = exWarehouseVO.getMaternityInsurancePeopleNum();
        command.endowmentInsuranceBaseAmount = exWarehouseVO.getEndowmentInsuranceBaseAmount();
        command.unemploymentInsuranceBaseAmount = exWarehouseVO.getUnemploymentInsuranceBaseAmount();
        command.medicalInsuranceBaseAmount = exWarehouseVO.getMedicalInsuranceBaseAmount();
        command.maternityInsuranceBaseAmount = exWarehouseVO.getMaternityInsuranceBaseAmount();
        command.endowmentInsurancePayAmount = exWarehouseVO.getEndowmentInsurancePayAmount();
        command.unemploymentInsurancePayAmount = exWarehouseVO.getUnemploymentInsurancePayAmount();
        command.medicalInsurancePayAmount = exWarehouseVO.getMedicalInsurancePayAmount();
        command.employmentInjuryInsurancePayAmount = exWarehouseVO.getEmploymentInjuryInsurancePayAmount();
        command.maternityInsurancePayAmount = exWarehouseVO.getMaternityInsurancePayAmount();
        command.endowmentInsuranceOweAmount = exWarehouseVO.getEndowmentInsuranceOweAmount();
        command.unemploymentInsuranceOweAmount = exWarehouseVO.getUnemploymentInsuranceOweAmount();
        command.medicalInsuranceOweAmount = exWarehouseVO.getMedicalInsuranceOweAmount();
        command.employmentInjuryInsuranceOweAmount = exWarehouseVO.getEmploymentInjuryInsuranceOweAmount();
        command.maternityInsuranceOweAmount = exWarehouseVO.getMaternityInsuranceOweAmount();
        command.isEndowmentInsuranceBaseAmountPublic = exWarehouseVO.getIsEndowmentInsuranceBaseAmountPublic();
        command.isUnemploymentInsuranceBaseAmountPublic = exWarehouseVO.getIsUnemploymentInsuranceBaseAmountPublic();
        command.isMedicalInsuranceBaseAmountPublic = exWarehouseVO.getIsMedicalInsuranceBaseAmountPublic();
        command.isMaternityInsuranceBaseAmountPublic = exWarehouseVO.getIsMaternityInsuranceBaseAmountPublic();
        command.isEndowmentInsurancePayAmountPublic = exWarehouseVO.getIsEndowmentInsurancePayAmountPublic();
        command.isUnemploymentInsurancePayAmountPublic = exWarehouseVO.getIsUnemploymentInsurancePayAmountPublic();
        command.isMedicalInsurancePayAmountPublic = exWarehouseVO.getIsMedicalInsurancePayAmountPublic();
        command.isEmploymentInjuryInsurancePayAmountPublic = exWarehouseVO.getIsEmploymentInjuryInsurancePayAmountPublic();
        command.isMaternityInsurancePayAmountPublic = exWarehouseVO.getIsMaternityInsurancePayAmountPublic();
        command.isEndowmentInsuranceOweAmountPublic = exWarehouseVO.getIsEndowmentInsuranceOweAmountPublic();
        command.isUnemploymentInsuranceOweAmountPublic = exWarehouseVO.getIsUnemploymentInsuranceOweAmountPublic();
        command.isMedicalInsuranceOweAmountPublic = exWarehouseVO.getIsMedicalInsuranceOweAmountPublic();
        command.isEmploymentInjuryInsuranceOweAmountPublic = exWarehouseVO.getIsEmploymentInjuryInsuranceOweAmountPublic();
        command.isMaternityInsuranceOweAmountPublic = exWarehouseVO.getIsMaternityInsuranceOweAmountPublic();


        return command;
    }
}
