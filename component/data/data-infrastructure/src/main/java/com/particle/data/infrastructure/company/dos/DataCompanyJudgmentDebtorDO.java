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
 * 企业被执行人表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_judgment_debtor")
public class DataCompanyJudgmentDebtorDO extends BaseDO {

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
    * 执行法院公司id
    */
    private Long executeCourtCompanyId;

    /**
    * 执行法院名称，冗余公司名称
    */
    private String executeCourtName;

    /**
    * 立案日期
    */
    private LocalDate fileCaseDate;

    /**
    * 结案日期
    */
    private LocalDate finishedCaseDate;

    /**
    * 是否已结案，1=已结案，0=未结案，执行中
    */
    private Boolean isFinished;

    /**
    * 执行标的金额（万元）
    */
    private BigDecimal executeAmount;

    /**
    * 执行标的金额币种，字典id，如：人民币
    */
    private Long executeAmountCurrencyDictId;

	/**
	 * 数据md5,case_no + executed_person_name + file_case_date
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
