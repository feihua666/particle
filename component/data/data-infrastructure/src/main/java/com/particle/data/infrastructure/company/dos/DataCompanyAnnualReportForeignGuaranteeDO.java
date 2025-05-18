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
 * 企业年报对外担保表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_foreign_guarantee")
public class DataCompanyAnnualReportForeignGuaranteeDO extends BaseDO {

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
    * 债务人名称
    */
    private String debtorName;

    /**
    * 是否债务人为自然人，1=自然人，0=非自然人
    */
    private Boolean isDebtorNaturalPerson;

    /**
    * 债务人公司id，is_debtor_natural_person = 0 时有值
    */
    private Long debtorCompanyId;

    /**
    * 债务人个人id，is_debtor_natural_person = 1 时有值
    */
    private Long debtorCompanyPersonId;

    /**
    * 债权人名称
    */
    private String creditorName;

    /**
    * 是否债权人为自然人，1=自然人，0=非自然人
    */
    private Boolean isCreditorNaturalPerson;

    /**
    * 债权人公司id，is_creditor_natural_person = 0 时有值
    */
    private Long creditorCompanyId;

    /**
    * 债权人个人id，is_creditor_natural_person = 1 时有值
    */
    private Long creditorCompanyPersonId;

    /**
    * 主债权种类，字典id，如：合同、其它
    */
    private Long creditoTypeDictId;

    /**
    * 主债权金额(万元)
    */
    private BigDecimal creditoAmount;
    
    /**
    * 主债权金额币种，字典id，如：人民币
    */
    private Long creditoAmountCurrencyDictId;

    /**
    * 履行债务的期限自
    */
    private LocalDate debtFromDate;
    
    /**
    * 履行债务的期限至
    */
    private LocalDate debtToDate;
    
    /**
    * 保证担保的范围
    */
    private String guaranteeScope;

    /**
    * 保证的期间，字典id，如：期限、未约定
    */
    private Long guaranteeTermDictId;

    /**
    * 保证的方式，字典id，如：一般保证、连带保证、未约定
    */
    private Long guaranteeTypeDictId;

	/**
	 * 数据md5,debtor_name + creditor_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}