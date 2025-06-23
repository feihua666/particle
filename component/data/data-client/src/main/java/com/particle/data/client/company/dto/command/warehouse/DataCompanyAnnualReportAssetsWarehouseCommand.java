package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAssetsExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * 企业资产状况信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyAnnualReportAssetsWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报id 不能为空")
    @Schema(description = "企业年报id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
    @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;


    @Schema(description = "资产总额（万元）")
    private BigDecimal totalAssets;


    @Schema(description = "资产总额币种")
    private Long totalAssetsCurrencyDictId;


    @Schema(description = "所有者权益合计（万元）")
    private BigDecimal totalOwnerEquity;


    @Schema(description = "所有者权益合计币种")
    private Long totalOwnerEquityCurrencyDictId;


    @Schema(description = "销售总额(营业总收入)（万元）")
    private BigDecimal totalSales;


    @Schema(description = "销售总额币种")
    private Long totalSalesCurrencyDictId;


    @Schema(description = "利润总额（万元）")
    private BigDecimal totalProfit;


    @Schema(description = "利润总额币种")
    private Long totalProfitCurrencyDictId;


    @Schema(description = "主营业务收入（万元）")
    private BigDecimal primeBusProfit;


    @Schema(description = "主营业务收入币种")
    private Long primeBusProfitCurrencyDictId;


    @Schema(description = "净利润（万元）")
    private BigDecimal retainedProfit;


    @Schema(description = "净利润币种")
    private Long retainedProfitCurrencyDictId;


    @Schema(description = "纳税总额（万元）")
    private BigDecimal totalTax;


    @Schema(description = "纳税总额币种")
    private Long totalTaxCurrencyDictId;


    @Schema(description = "负债总额（万元）")
    private BigDecimal totalLiability;


    @Schema(description = "负债总额币种")
    private Long totalLiabilityCurrencyDictId;


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



    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyAnnualReportId)
                && Objects.isNull(year)
                && Objects.isNull(totalAssets)
                && Objects.isNull(totalAssetsCurrencyDictId)
                && Objects.isNull(totalOwnerEquity)
                && Objects.isNull(totalOwnerEquityCurrencyDictId)
                && Objects.isNull(totalSales)
                && Objects.isNull(totalSalesCurrencyDictId)
                && Objects.isNull(totalProfit)
                && Objects.isNull(totalProfitCurrencyDictId)
                && Objects.isNull(primeBusProfit)
                && Objects.isNull(primeBusProfitCurrencyDictId)
                && Objects.isNull(retainedProfit)
                && Objects.isNull(retainedProfitCurrencyDictId)
                && Objects.isNull(totalTax)
                && Objects.isNull(totalTaxCurrencyDictId)
                && Objects.isNull(totalLiability)
                && Objects.isNull(totalLiabilityCurrencyDictId)
                && Objects.isNull(isTotalAssetsPublic)
                && Objects.isNull(isTotalOwnerEquityPublic)
                && Objects.isNull(isTotalSalesPublic)
                && Objects.isNull(isTotalProfitPublic)
                && Objects.isNull(isPrimeBusProfitPublic)
                && Objects.isNull(isRetainedProfitPublic)
                && Objects.isNull(isTotalTaxPublic)
                && Objects.isNull(isTotalLiabilityPublic);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyAnnualReportAssetsExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyAnnualReportId, exWarehouseVO.getCompanyAnnualReportId())) {
            this.companyAnnualReportId = null;
        }
        if (Objects.equals(year, exWarehouseVO.getYear())) {
            this.year = null;
        }
        if (NumberUtil.equals(totalAssets, exWarehouseVO.getTotalAssets())) {
            this.totalAssets = null;
        }
        if (Objects.equals(totalAssetsCurrencyDictId, exWarehouseVO.getTotalAssetsCurrencyDictId())) {
            this.totalAssetsCurrencyDictId = null;
        }
        if (NumberUtil.equals(totalOwnerEquity, exWarehouseVO.getTotalOwnerEquity())) {
            this.totalOwnerEquity = null;
        }
        if (Objects.equals(totalOwnerEquityCurrencyDictId, exWarehouseVO.getTotalOwnerEquityCurrencyDictId())) {
            this.totalOwnerEquityCurrencyDictId = null;
        }
        if (NumberUtil.equals(totalSales, exWarehouseVO.getTotalSales())) {
            this.totalSales = null;
        }
        if (Objects.equals(totalSalesCurrencyDictId, exWarehouseVO.getTotalSalesCurrencyDictId())) {
            this.totalSalesCurrencyDictId = null;
        }
        if (NumberUtil.equals(totalProfit, exWarehouseVO.getTotalProfit())) {
            this.totalProfit = null;
        }
        if (Objects.equals(totalProfitCurrencyDictId, exWarehouseVO.getTotalProfitCurrencyDictId())) {
            this.totalProfitCurrencyDictId = null;
        }
        if (NumberUtil.equals(primeBusProfit, exWarehouseVO.getPrimeBusProfit())) {
            this.primeBusProfit = null;
        }
        if (Objects.equals(primeBusProfitCurrencyDictId, exWarehouseVO.getPrimeBusProfitCurrencyDictId())) {
            this.primeBusProfitCurrencyDictId = null;
        }
        if (NumberUtil.equals(retainedProfit, exWarehouseVO.getRetainedProfit())) {
            this.retainedProfit = null;
        }
        if (Objects.equals(retainedProfitCurrencyDictId, exWarehouseVO.getRetainedProfitCurrencyDictId())) {
            this.retainedProfitCurrencyDictId = null;
        }
        if (NumberUtil.equals(totalTax, exWarehouseVO.getTotalTax())) {
            this.totalTax = null;
        }
        if (Objects.equals(totalTaxCurrencyDictId, exWarehouseVO.getTotalTaxCurrencyDictId())) {
            this.totalTaxCurrencyDictId = null;
        }
        if (NumberUtil.equals(totalLiability, exWarehouseVO.getTotalLiability())) {
            this.totalLiability = null;
        }
        if (Objects.equals(totalLiabilityCurrencyDictId, exWarehouseVO.getTotalLiabilityCurrencyDictId())) {
            this.totalLiabilityCurrencyDictId = null;
        }
        if (Objects.equals(isTotalAssetsPublic, exWarehouseVO.getIsTotalAssetsPublic())) {
            this.isTotalAssetsPublic = null;
        }
        if (Objects.equals(isTotalOwnerEquityPublic, exWarehouseVO.getIsTotalOwnerEquityPublic())) {
            this.isTotalOwnerEquityPublic = null;
        }
        if (Objects.equals(isTotalSalesPublic, exWarehouseVO.getIsTotalSalesPublic())) {
            this.isTotalSalesPublic = null;
        }
        if (Objects.equals(isTotalProfitPublic, exWarehouseVO.getIsTotalProfitPublic())) {
            this.isTotalProfitPublic = null;
        }
        if (Objects.equals(isPrimeBusProfitPublic, exWarehouseVO.getIsPrimeBusProfitPublic())) {
            this.isPrimeBusProfitPublic = null;
        }
        if (Objects.equals(isRetainedProfitPublic, exWarehouseVO.getIsRetainedProfitPublic())) {
            this.isRetainedProfitPublic = null;
        }
        if (Objects.equals(isTotalTaxPublic, exWarehouseVO.getIsTotalTaxPublic())) {
            this.isTotalTaxPublic = null;
        }
        if (Objects.equals(isTotalLiabilityPublic, exWarehouseVO.getIsTotalLiabilityPublic())) {
            this.isTotalLiabilityPublic = null;
        }

    }

    public static DataCompanyAnnualReportAssetsWarehouseCommand createByDataCompanyAnnualReportAssetsExWarehouseVO(DataCompanyAnnualReportAssetsExWarehouseVO exWarehouseVO) {
        DataCompanyAnnualReportAssetsWarehouseCommand command = new DataCompanyAnnualReportAssetsWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.companyAnnualReportId = exWarehouseVO.getCompanyAnnualReportId();
        command.year = exWarehouseVO.getYear();
        command.totalAssets = exWarehouseVO.obtainTotalAssets();
        command.totalAssetsCurrencyDictId = exWarehouseVO.getTotalAssetsCurrencyDictId();
        command.totalOwnerEquity = exWarehouseVO.obtainTotalOwnerEquity();
        command.totalOwnerEquityCurrencyDictId = exWarehouseVO.getTotalOwnerEquityCurrencyDictId();
        command.totalSales = exWarehouseVO.obtainTotalSales();
        command.totalSalesCurrencyDictId = exWarehouseVO.getTotalSalesCurrencyDictId();
        command.totalProfit = exWarehouseVO.obtainTotalProfit();
        command.totalProfitCurrencyDictId = exWarehouseVO.getTotalProfitCurrencyDictId();
        command.primeBusProfit = exWarehouseVO.obtainPrimeBusProfit();
        command.primeBusProfitCurrencyDictId = exWarehouseVO.getPrimeBusProfitCurrencyDictId();
        command.retainedProfit = exWarehouseVO.obtainRetainedProfit();
        command.retainedProfitCurrencyDictId = exWarehouseVO.getRetainedProfitCurrencyDictId();
        command.totalTax = exWarehouseVO.obtainTotalTax();
        command.totalTaxCurrencyDictId = exWarehouseVO.getTotalTaxCurrencyDictId();
        command.totalLiability = exWarehouseVO.obtainTotalLiability();
        command.totalLiabilityCurrencyDictId = exWarehouseVO.getTotalLiabilityCurrencyDictId();
        command.isTotalAssetsPublic = exWarehouseVO.getIsTotalAssetsPublic();
        command.isTotalOwnerEquityPublic = exWarehouseVO.getIsTotalOwnerEquityPublic();
        command.isTotalSalesPublic = exWarehouseVO.getIsTotalSalesPublic();
        command.isTotalProfitPublic = exWarehouseVO.getIsTotalProfitPublic();
        command.isPrimeBusProfitPublic = exWarehouseVO.getIsPrimeBusProfitPublic();
        command.isRetainedProfitPublic = exWarehouseVO.getIsRetainedProfitPublic();
        command.isTotalTaxPublic = exWarehouseVO.getIsTotalTaxPublic();
        command.isTotalLiabilityPublic = exWarehouseVO.getIsTotalLiabilityPublic();


        return command;
    }
}
