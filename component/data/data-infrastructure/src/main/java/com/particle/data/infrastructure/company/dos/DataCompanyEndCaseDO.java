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
 * 企业终本案件表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:57
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_end_case")
public class DataCompanyEndCaseDO extends BaseDO {

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 被执行人名称
    */
    private String executedPersonName;

    /**
    * 是否被执行人为自然人，1=自然人，0=非自然人
    */
    private Boolean isExecutedPersonNaturalPerson;

    /**
    * 被执行人公司id，is_executed_person_natural_person = 0 时有值
    */
    private Long executedPersonCompanyId;

    /**
    * 被执行人个人id，is_executed_person_natural_person = 1 时有值
    */
    private Long executedPersonCompanyPersonId;

    /**
    * 被执行人证照/证件号码
    */
    private String executedPersonLicenseNo;

    /**
    * 法院名称
    */
    private String courtName;

    /**
    * 法院名称公司id
    */
    private Long courtCompanyId;

    /**
    * 立案日期
    */
    private LocalDate fileCaseDate;

    /**
    * 结束日期
    */
    private LocalDate finishedCaseDate;

    /**
    * 执行标的金额（万元）
    */
    private BigDecimal executeAmount;

    /**
    * 执行标的金额币种，字典id，如：人民币
    */
    private Long executeAmountCurrencyDictId;

    /**
    * 未履行金额（万元）
    */
    private BigDecimal unPerformAmount;

    /**
    * 未履行金额币种，字典id，如：人民币
    */
    private Long unPerformAmountCurrencyDictId;

    /**
    * 数据md5,case_no + executed_person_name + file_case_date
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
