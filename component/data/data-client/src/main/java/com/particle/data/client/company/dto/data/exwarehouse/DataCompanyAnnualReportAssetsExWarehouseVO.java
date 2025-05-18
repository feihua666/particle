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
 * 企业资产状况信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAnnualReportAssetsExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;

    @Schema(description = "年报年度")
    private Integer year;

    @Schema(description = "资产总额（万元）")
    private BigDecimal totalAssets;

    @Schema(description = "资产总额币种")
    private Long totalAssetsCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalAssetsCurrencyDictId",mapValueField = "value")
    @Schema(description = "资产总额币种对应字典值")
    private String totalAssetsCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalAssetsCurrencyDictId",mapValueField = "name")
    @Schema(description = "资产总额币种对应字典名称")
    private String totalAssetsCurrencyDictName;

    @Schema(description = "所有者权益合计（万元）")
    private BigDecimal totalOwnerEquity;

    @Schema(description = "所有者权益合计币种")
    private Long totalOwnerEquityCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalOwnerEquityCurrencyDictId",mapValueField = "value")
    @Schema(description = "所有者权益合计币种对应字典值")
    private String totalOwnerEquityCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalOwnerEquityCurrencyDictId",mapValueField = "name")
    @Schema(description = "所有者权益合计币种对应字典名称")
    private String totalOwnerEquityCurrencyDictName;

    @Schema(description = "销售总额(营业总收入)（万元）")
    private BigDecimal totalSales;

    @Schema(description = "销售总额币种")
    private Long totalSalesCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalSalesCurrencyDictId",mapValueField = "value")
    @Schema(description = "销售总额币种对应字典值")
    private String totalSalesCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalSalesCurrencyDictId",mapValueField = "name")
    @Schema(description = "销售总额币种对应字典名称")
    private String totalSalesCurrencyDictName;

    @Schema(description = "利润总额（万元）")
    private BigDecimal totalProfit;

    @Schema(description = "利润总额币种")
    private Long totalProfitCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalProfitCurrencyDictId",mapValueField = "value")
    @Schema(description = "利润总额币种对应字典值")
    private String totalProfitCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalProfitCurrencyDictId",mapValueField = "name")
    @Schema(description = "利润总额币种对应字典名称")
    private String totalProfitCurrencyDictName;

    @Schema(description = "主营业务收入（万元）")
    private BigDecimal primeBusProfit;

    @Schema(description = "主营业务收入币种")
    private Long primeBusProfitCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "primeBusProfitCurrencyDictId",mapValueField = "value")
    @Schema(description = "主营业务收入币种对应字典值")
    private String primeBusProfitCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "primeBusProfitCurrencyDictId",mapValueField = "name")
    @Schema(description = "主营业务收入币种对应字典名称")
    private String primeBusProfitCurrencyDictName;

    @Schema(description = "净利润（万元）")
    private BigDecimal retainedProfit;

    @Schema(description = "净利润币种")
    private Long retainedProfitCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "retainedProfitCurrencyDictId",mapValueField = "value")
    @Schema(description = "净利润币种对应字典值")
    private String retainedProfitCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "retainedProfitCurrencyDictId",mapValueField = "name")
    @Schema(description = "净利润币种对应字典名称")
    private String retainedProfitCurrencyDictName;

    @Schema(description = "纳税总额（万元）")
    private BigDecimal totalTax;

    @Schema(description = "纳税总额币种")
    private Long totalTaxCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalTaxCurrencyDictId",mapValueField = "value")
    @Schema(description = "纳税总额币种对应字典值")
    private String totalTaxCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalTaxCurrencyDictId",mapValueField = "name")
    @Schema(description = "纳税总额币种对应字典名称")
    private String totalTaxCurrencyDictName;

    @Schema(description = "负债总额（万元）")
    private BigDecimal totalLiability;

    @Schema(description = "负债总额币种")
    private Long totalLiabilityCurrencyDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalLiabilityCurrencyDictId",mapValueField = "value")
    @Schema(description = "负债总额币种对应字典值")
    private String totalLiabilityCurrencyDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "totalLiabilityCurrencyDictId",mapValueField = "name")
    @Schema(description = "负债总额币种对应字典名称")
    private String totalLiabilityCurrencyDictName;

    @Schema(description = "是否资产总额公示")
    private Boolean isTotalAssetsPublic;

    @Schema(description = "是否所有者权益合计公示")
    private Boolean isTotalOwnerEquityPublic;

    @Schema(description = "是否销售总额公示")
    private Boolean isTotalSalesPublic;

    @Schema(description = "是否利润总额公示")
    private Boolean isTotalProfitPublic;

    @Schema(description = "是否主营业务收入公示")
    private Boolean isPrimeBusProfitPublic;

    @Schema(description = "是否净利润公示")
    private Boolean isRetainedProfitPublic;

    @Schema(description = "是否纳税总额公示")
    private Boolean isTotalTaxPublic;

    @Schema(description = "是否负债总额公示")
    private Boolean isTotalLiabilityPublic;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
