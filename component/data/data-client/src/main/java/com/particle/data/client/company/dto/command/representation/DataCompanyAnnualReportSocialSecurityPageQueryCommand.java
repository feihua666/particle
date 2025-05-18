package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报社保 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Data
@Schema
public class DataCompanyAnnualReportSocialSecurityPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
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


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
