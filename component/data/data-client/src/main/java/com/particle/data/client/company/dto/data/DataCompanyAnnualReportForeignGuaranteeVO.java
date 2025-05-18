package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报对外担保 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Data
@Schema
public class DataCompanyAnnualReportForeignGuaranteeVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;
    
    @Schema(description = "年报年度")
    private Integer year;
    
    @Schema(description = "序号")
    private Integer serialNumber;
    
    @Schema(description = "债务人名称")
    private String debtorName;
    
    @Schema(description = "是否债务人为自然人")
    private Boolean isDebtorNaturalPerson;
    
    @Schema(description = "债务人公司id")
    private Long debtorCompanyId;
    
    @Schema(description = "债务人个人id")
    private Long debtorCompanyPersonId;
    
    @Schema(description = "债权人名称")
    private String creditorName;
    
    @Schema(description = "是否债权人为自然人")
    private Boolean isCreditorNaturalPerson;
    
    @Schema(description = "债权人公司id")
    private Long creditorCompanyId;
    
    @Schema(description = "债权人个人id")
    private Long creditorCompanyPersonId;
    
    @Schema(description = "主债权种类")
    private Long creditoTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "creditoTypeDictId",mapValueField = "name")
    @Schema(description = "主债权种类对应字典名称")
    private String creditoTypeDictName;
        
    @Schema(description = "主债权金额(万元)")
    private BigDecimal creditoAmount;
        
    @Schema(description = "主债权金额币种")
    private Long creditoAmountCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "creditoAmountCurrencyDictId",mapValueField = "name")
    @Schema(description = "主债权金额币种对应字典名称")
    private String creditoAmountCurrencyDictName;
        
    @Schema(description = "履行债务的期限自")
    private LocalDate debtFromDate;
        
    @Schema(description = "履行债务的期限至")
    private LocalDate debtToDate;
        
    @Schema(description = "保证担保的范围")
    private String guaranteeScope;
    
    @Schema(description = "保证的期间")
    private Long guaranteeTermDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "guaranteeTermDictId",mapValueField = "name")
    @Schema(description = "保证的期间对应字典名称")
    private String guaranteeTermDictName;
        
    @Schema(description = "保证的方式")
    private Long guaranteeTypeDictId;

	@Schema(description = "数据md5,debtor_name + creditor_name")
	private String dataMd5;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "guaranteeTypeDictId",mapValueField = "name")
    @Schema(description = "保证的方式对应字典名称")
    private String guaranteeTypeDictName;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}