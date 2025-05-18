package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业股东 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyShareholderExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "股东名称")
    private String shareholderName;
    
    @Schema(description = "是否法人为自然人")
    private Boolean isShareholderNaturalPerson;
    
    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;
    
    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;
    
    @Schema(description = "持股比例")
    private BigDecimal shareholdingPercent;
        
    @Schema(description = "持股数量")
    private Integer shareholdingNum;
    
    @Schema(description = "持股金额（万元）")
    private BigDecimal shareholdingAmount;
        
    @Schema(description = "持股金额币种")
    private Long shareholdingAmountCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "shareholdingAmountCurrencyDictId",mapValueField = "name")
    @Schema(description = "持股金额币种对应字典名称")
    private String shareholdingAmountCurrencyDictName;
        
    @Schema(description = "认缴出资金额（万元）")
    private BigDecimal subCapital;
        
    @Schema(description = "认缴出资金额币种")
    private Long subCapitalCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "认缴出资金额币种对应字典名称")
    private String subCapitalCurrencyDictName;
        
    @Schema(description = "认缴出资方式")
    private Long subCapitalTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "subCapitalTypeDictId",mapValueField = "name")
    @Schema(description = "认缴出资方式对应字典名称")
    private String subCapitalTypeDictName;
        
    @Schema(description = "认缴出资日期")
    private LocalDate subCapitalDate;
        
    @Schema(description = "实缴出资金额（万元）")
    private BigDecimal actualCapital;
        
    @Schema(description = "实缴出资金额币种")
    private Long actualCapitalCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "actualCapitalCurrencyDictId",mapValueField = "name")
    @Schema(description = "实缴出资金额币种对应字典名称")
    private String actualCapitalCurrencyDictName;
        
    @Schema(description = "实缴出资日期")
    private LocalDate actualCapitalDate;
        
    @Schema(description = "是否工商登记股东")
    private Boolean isRegPublic;
    
    @Schema(description = "是否上市最新公示股东")
    private Boolean isListedLatestPublic;

	@Schema(description = "上市最新公示股东日期")
	private LocalDate listedLatestPublicDate;
    
    @Schema(description = "是否最新年报股东")
    private Boolean isYearReportLatestPublic;
    
    @Schema(description = "最新年报股东年份")
    private Integer yearReportLatestPublicYear;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
