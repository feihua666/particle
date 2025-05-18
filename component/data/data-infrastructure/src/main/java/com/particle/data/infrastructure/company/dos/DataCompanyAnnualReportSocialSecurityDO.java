package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报社保表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_social_security")
public class DataCompanyAnnualReportSocialSecurityDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 企业年报表ID
    */
    private Long companyAnnualReportId;

    /**
    * 年报年度
    */
    private Integer year;

    /**
    * 城镇职工基本养老保险人数
    */
    private Integer endowmentInsurancePeopleNum;

    /**
    * 失业保险人数
    */
    private Integer unemploymentInsurancePeopleNum;

    /**
    * 职工基本医疗保险人数
    */
    private Integer medicalInsurancePeopleNum;

    /**
    * 工伤保险人数
    */
    private Integer employmentInjuryInsurancePeopleNum;

    /**
    * 生育保险人数
    */
    private Integer maternityInsurancePeopleNum;

    /**
    * 单位参加城镇职工基本养老保险缴费基数,属于 单位缴费基数（万元）
    */
    private BigDecimal endowmentInsuranceBaseAmount;
    
    /**
    * 单位参加失业保险缴费基数,属于 单位缴费基数（万元）
    */
    private BigDecimal unemploymentInsuranceBaseAmount;
    
    /**
    * 单位参加职工基本医疗保险缴费基数,属于 单位缴费基数（万元）
    */
    private BigDecimal medicalInsuranceBaseAmount;
    
    /**
    * 单位参加生育保险缴费基数,属于 单位缴费基数（万元）
    */
    private BigDecimal maternityInsuranceBaseAmount;
    
    /**
    * 参加城镇职工基本养老保险本期实际缴费金额，属于 本期实际缴费金额（万元）
    */
    private BigDecimal endowmentInsurancePayAmount;
    
    /**
    * 参加失业保险本期实际缴费金额，属于 本期实际缴费金额（万元）
    */
    private BigDecimal unemploymentInsurancePayAmount;
    
    /**
    * 参加职工基本医疗保险本期实际缴费金额，属于 本期实际缴费金额（万元）
    */
    private BigDecimal medicalInsurancePayAmount;
    
    /**
    * 参加工伤保险本期实际缴费金额，属于 本期实际缴费金额（万元）
    */
    private BigDecimal employmentInjuryInsurancePayAmount;
    
    /**
    * 参加生育保险本期实际缴费金额，属于 本期实际缴费金额（万元）
    */
    private BigDecimal maternityInsurancePayAmount;
    
    /**
    * 单位参加城镇职工基本养老保险累计欠缴金额，属于 单位累计欠缴金额（万元）
    */
    private BigDecimal endowmentInsuranceOweAmount;
    
    /**
    * 单位参加失业保险累计欠缴金额，属于 单位累计欠缴金额（万元）
    */
    private BigDecimal unemploymentInsuranceOweAmount;
    
    /**
    * 单位参加职工基本医疗保险累计欠缴金额，属于 单位累计欠缴金额（万元）
    */
    private BigDecimal medicalInsuranceOweAmount;
    
    /**
    * 单位参加工伤保险累计欠缴金额，属于 单位累计欠缴金额（万元）
    */
    private BigDecimal employmentInjuryInsuranceOweAmount;
    
    /**
    * 单位参加生育保险累计欠缴金额，属于 单位累计欠缴金额（万元）
    */
    private BigDecimal maternityInsuranceOweAmount;
    
    /**
    * 是否单位参加城镇职工基本养老保险缴费基数公示
    */
    private Boolean isEndowmentInsuranceBaseAmountPublic;

    /**
    * 是否单位参加失业保险缴费基数公示
    */
    private Boolean isUnemploymentInsuranceBaseAmountPublic;

    /**
    * 是否单位参加职工基本医疗保险缴费基数公示
    */
    private Boolean isMedicalInsuranceBaseAmountPublic;

    /**
    * 是否单位参加生育保险缴费基数公示
    */
    private Boolean isMaternityInsuranceBaseAmountPublic;

    /**
    * 是否参加城镇职工基本养老保险本期实际缴费金额公示
    */
    private Boolean isEndowmentInsurancePayAmountPublic;

    /**
    * 是否参加失业保险本期实际缴费金额公示
    */
    private Boolean isUnemploymentInsurancePayAmountPublic;

    /**
    * 是否参加职工基本医疗保险本期实际缴费金额公示
    */
    private Boolean isMedicalInsurancePayAmountPublic;

    /**
    * 是否参加工伤保险本期实际缴费金额公示
    */
    private Boolean isEmploymentInjuryInsurancePayAmountPublic;

    /**
    * 是否参加生育保险本期实际缴费金额公示
    */
    private Boolean isMaternityInsurancePayAmountPublic;

    /**
    * 是否单位参加城镇职工基本养老保险累计欠缴金额公示
    */
    private Boolean isEndowmentInsuranceOweAmountPublic;

    /**
    * 是否单位参加失业保险累计欠缴金额公示
    */
    private Boolean isUnemploymentInsuranceOweAmountPublic;

    /**
    * 是否单位参加职工基本医疗保险累计欠缴金额公示
    */
    private Boolean isMedicalInsuranceOweAmountPublic;

    /**
    * 是否单位参加工伤保险累计欠缴金额公示
    */
    private Boolean isEmploymentInjuryInsuranceOweAmountPublic;

    /**
    * 是否单位参加生育保险累计欠缴金额公示
    */
    private Boolean isMaternityInsuranceOweAmountPublic;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
