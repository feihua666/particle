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
 * 企业年报股东 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Data
@Entity
public class DataCompanyAnnualReportShareholder extends AggreateRoot {

    private DataCompanyAnnualReportShareholderId id;

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
    * 股东名称
    */
    private String shareholderName;

    /**
    * 是否股东名称为自然人，1=自然人，0=非自然人
    */
    private Boolean isShareholderNaturalPerson;

    /**
    * 股东名称公司id，is_shareholder_natural_person = 0 时有值
    */
    private Long shareholderCompanyId;

    /**
    * 股东名称个人id，is_shareholder_natural_person = 1 时有值
    */
    private Long shareholderCompanyPersonId;

    /**
    * 持股比例
    */
    private BigDecimal shareholdingPercent;

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
        this.dataMd5 = SomeMd5Tool.dataCompanyAnnualReportShareholderDataMd5(shareholderName);
    }

    /**
     * 创建企业年报股东领域模型对象
     * @return 企业年报股东领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReportShareholder create(){
        return DomainFactory.create(DataCompanyAnnualReportShareholder.class);
    }
}
