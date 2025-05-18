package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业股东表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:01
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_shareholder")
public class DataCompanyShareholderDO extends BaseDO {

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
    

}
