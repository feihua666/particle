package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报对外投资 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Data
@Entity
public class DataCompanyAnnualReportForeignInvest extends AggreateRoot {

    private DataCompanyAnnualReportForeignInvestId id;

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
    * 序号
    */
    private Integer serialNumber;

    /**
    * 对外投资企业，企业表ID
    */
    private Long investCompanyId;

	/**
	 * 对外投资企业名称
	 */
	private String investCompanyName;

	/**
	 * 对外投资企业统一社会信用代码
	 */
	private String investCompanyUscc;

    /**
    * 对外投资比例
    */
    private BigDecimal investPercent;

    /**
    * 对外投资金额（万元）
    */
    private BigDecimal investAmount;

    /**
    * 对外投资金额币种，字典id，如：人民币
    */
    private Long investAmountCurrencyDictId;

	/**
	 * 数据md5,invest_company_name + invest_company_uscc
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyAnnualReportForeignInvestDataMd5(investCompanyName, investCompanyUscc);
    }

    /**
     * 创建企业年报对外投资领域模型对象
     * @return 企业年报对外投资领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReportForeignInvest create(){
        return DomainFactory.create(DataCompanyAnnualReportForeignInvest.class);
    }
}
