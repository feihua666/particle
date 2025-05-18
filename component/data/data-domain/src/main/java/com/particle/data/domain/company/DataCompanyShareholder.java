package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业股东 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Data
@Entity
public class DataCompanyShareholder extends AggreateRoot {

    private DataCompanyShareholderId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 股东名称
    */
    private String shareholderName;

    /**
    * 是否股东为自然人，1=自然人，0=非自然人
    */
    private Boolean isShareholderNaturalPerson;

    /**
    * 股东公司id，is_shareholder_natural_person = 0 时有值
    */
    private Long shareholderCompanyId;

    /**
    * 股东个人id，is_shareholder_natural_person = 1 时有值
    */
    private Long shareholderCompanyPersonId;

    /**
    * 持股比例
    */
    private BigDecimal shareholdingPercent;

    /**
    * 持股数量
    */
    private Integer shareholdingNum;

    /**
    * 持股金额（万元）
    */
    private BigDecimal shareholdingAmount;

    /**
    * 持股金额币种，字典id，如：人民币
    */
    private Long shareholdingAmountCurrencyDictId;

    /**
    * 认缴出资金额（万元）
    */
    private BigDecimal subCapital;

    /**
    * 认缴出资金额币种，字典id，如：人民币
    */
    private Long subCapitalCurrencyDictId;

    /**
    * 认缴出资方式
    */
    private Long subCapitalTypeDictId;

    /**
    * 认缴出资日期
    */
    private LocalDate subCapitalDate;

    /**
    * 实缴出资金额（万元）
    */
    private BigDecimal actualCapital;

    /**
    * 实缴出资金额币种，字典id，如：人民币
    */
    private Long actualCapitalCurrencyDictId;

    /**
    * 实缴出资日期
    */
    private LocalDate actualCapitalDate;

    /**
    * 是否工商登记股东，如：国家企业信用信息公示系统中登记
    */
    private Boolean isRegPublic;

    /**
    * 是否上市最新公示股东，上市公司股东，如：招股书、科创板
    */
    private Boolean isListedLatestPublic;

	/**
	 * 上市最新公示股东日期
	 */
	private LocalDate listedLatestPublicDate;

    /**
    * 是否最新年报股东
    */
    private Boolean isYearReportLatestPublic;

    /**
    * 最新年报股东年份
    */
    private Integer yearReportLatestPublicYear;

	/**
	 * 数据md5,shareholder_name
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
        this.dataMd5 = SomeMd5Tool.dataCompanyShareholderDataMd5(shareholderName);
    }
    /**
     * 创建企业股东领域模型对象
     * @return 企业股东领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyShareholder create(){
        return DomainFactory.create(DataCompanyShareholder.class);
    }
}
