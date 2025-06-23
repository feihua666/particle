package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAssetsWarehouseCommand;

/**
 * <p>
 * 企业资产状况信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Data
@Schema
public class DataCompanyAnnualReportAssetsCreateCommand extends AbstractBaseCommand {



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


    public static DataCompanyAnnualReportAssetsCreateCommand createByWarehouseCommand(DataCompanyAnnualReportAssetsWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportAssetsCreateCommand command = new DataCompanyAnnualReportAssetsCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.totalAssets = dataCompanyBasicWarehouseCommand.getTotalAssets();
        command.totalAssetsCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalAssetsCurrencyDictId();
        command.totalOwnerEquity = dataCompanyBasicWarehouseCommand.getTotalOwnerEquity();
        command.totalOwnerEquityCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalOwnerEquityCurrencyDictId();
        command.totalSales = dataCompanyBasicWarehouseCommand.getTotalSales();
        command.totalSalesCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalSalesCurrencyDictId();
        command.totalProfit = dataCompanyBasicWarehouseCommand.getTotalProfit();
        command.totalProfitCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalProfitCurrencyDictId();
        command.primeBusProfit = dataCompanyBasicWarehouseCommand.getPrimeBusProfit();
        command.primeBusProfitCurrencyDictId = dataCompanyBasicWarehouseCommand.getPrimeBusProfitCurrencyDictId();
        command.retainedProfit = dataCompanyBasicWarehouseCommand.getRetainedProfit();
        command.retainedProfitCurrencyDictId = dataCompanyBasicWarehouseCommand.getRetainedProfitCurrencyDictId();
        command.totalTax = dataCompanyBasicWarehouseCommand.getTotalTax();
        command.totalTaxCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalTaxCurrencyDictId();
        command.totalLiability = dataCompanyBasicWarehouseCommand.getTotalLiability();
        command.totalLiabilityCurrencyDictId = dataCompanyBasicWarehouseCommand.getTotalLiabilityCurrencyDictId();
        command.isTotalAssetsPublic = dataCompanyBasicWarehouseCommand.getIsTotalAssetsPublic();
        command.isTotalOwnerEquityPublic = dataCompanyBasicWarehouseCommand.getIsTotalOwnerEquityPublic();
        command.isTotalSalesPublic = dataCompanyBasicWarehouseCommand.getIsTotalSalesPublic();
        command.isTotalProfitPublic = dataCompanyBasicWarehouseCommand.getIsTotalProfitPublic();
        command.isPrimeBusProfitPublic = dataCompanyBasicWarehouseCommand.getIsPrimeBusProfitPublic();
        command.isRetainedProfitPublic = dataCompanyBasicWarehouseCommand.getIsRetainedProfitPublic();
        command.isTotalTaxPublic = dataCompanyBasicWarehouseCommand.getIsTotalTaxPublic();
        command.isTotalLiabilityPublic = dataCompanyBasicWarehouseCommand.getIsTotalLiabilityPublic();


        return command;
    }
}
