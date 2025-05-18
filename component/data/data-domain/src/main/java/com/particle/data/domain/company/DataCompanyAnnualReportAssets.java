package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业资产状况信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:31
 */
@Data
@Entity
public class DataCompanyAnnualReportAssets extends AggreateRoot {

    private DataCompanyAnnualReportAssetsId id;

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
    * 资产总额（万元）
    */
    private BigDecimal totalAssets;

    /**
    * 资产总额币种，字典id，如：人民币
    */
    private Long totalAssetsCurrencyDictId;

    /**
    * 所有者权益合计（万元）
    */
    private BigDecimal totalOwnerEquity;

    /**
    * 所有者权益合计币种，字典id，如：人民币
    */
    private Long totalOwnerEquityCurrencyDictId;

    /**
    * 销售总额(营业总收入)（万元）
    */
    private BigDecimal totalSales;

    /**
    * 销售总额币种，字典id，如：人民币
    */
    private Long totalSalesCurrencyDictId;

    /**
    * 利润总额（万元）
    */
    private BigDecimal totalProfit;

    /**
    * 利润总额币种，字典id，如：人民币
    */
    private Long totalProfitCurrencyDictId;

    /**
    * 主营业务收入（万元）
    */
    private BigDecimal primeBusProfit;

    /**
    * 主营业务收入币种，字典id，如：人民币
    */
    private Long primeBusProfitCurrencyDictId;

    /**
    * 净利润（万元）
    */
    private BigDecimal retainedProfit;

    /**
    * 净利润币种，字典id，如：人民币
    */
    private Long retainedProfitCurrencyDictId;

    /**
    * 纳税总额（万元）
    */
    private BigDecimal totalTax;

    /**
    * 纳税总额币种，字典id，如：人民币
    */
    private Long totalTaxCurrencyDictId;

    /**
    * 负债总额（万元）
    */
    private BigDecimal totalLiability;

    /**
    * 负债总额币种，字典id，如：人民币
    */
    private Long totalLiabilityCurrencyDictId;

    /**
    * 是否资产总额公示
    */
    private Boolean isTotalAssetsPublic;

    /**
    * 是否所有者权益合计公示
    */
    private Boolean isTotalOwnerEquityPublic;

    /**
    * 是否销售总额公示
    */
    private Boolean isTotalSalesPublic;

    /**
    * 是否利润总额公示
    */
    private Boolean isTotalProfitPublic;

    /**
    * 是否主营业务收入公示
    */
    private Boolean isPrimeBusProfitPublic;

    /**
    * 是否净利润公示
    */
    private Boolean isRetainedProfitPublic;

    /**
    * 是否纳税总额公示
    */
    private Boolean isTotalTaxPublic;

    /**
    * 是否负债总额公示
    */
    private Boolean isTotalLiabilityPublic;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }

    /**
     * 创建企业资产状况信息领域模型对象
     * @return 企业资产状况信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReportAssets create(){
        return DomainFactory.create(DataCompanyAnnualReportAssets.class);
    }
}
