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
 * 企业年报股权变更表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_equity_change")
public class DataCompanyAnnualReportEquityChangeDO extends BaseDO {

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
    * 股东名称，可能是企业名称也可能是个人名称
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
    * 变更前比例
    */
    private BigDecimal percentBefore;
    
    /**
    * 变更后比例
    */
    private BigDecimal percentAfter;
    
    /**
    * 变更日期
    */
    private LocalDate changeDate;

	/**
	 * 数据md5,shareholder_name + percent_before + percent_after + change_date
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
