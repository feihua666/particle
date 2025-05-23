package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报对外投资表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_foreign_invest")
public class DataCompanyAnnualReportForeignInvestDO extends BaseDO {

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
	 * 对外投资企业注册号
	 */
	private String investCompanyRegNo;

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
	 * 数据md5,invest_company_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
