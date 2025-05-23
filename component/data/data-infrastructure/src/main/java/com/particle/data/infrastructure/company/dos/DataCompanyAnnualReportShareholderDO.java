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
 * 企业年报股东表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_shareholder")
public class DataCompanyAnnualReportShareholderDO extends BaseDO {

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
	 * 实缴出资方式
	 */
	private Long actualCapitalTypeDictId;

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
    

}