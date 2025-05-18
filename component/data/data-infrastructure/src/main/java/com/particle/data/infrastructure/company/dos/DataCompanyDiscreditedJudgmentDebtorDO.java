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
 * 企业失信被执行人表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_discredited_judgment_debtor")
public class DataCompanyDiscreditedJudgmentDebtorDO extends BaseDO {

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 被执行人名称，冗余公司名称
    */
    private String dishonestExecutedPersonName;

    /**
    * 是否被执行人为自然人，1=自然人，0=非自然人
    */
    private Boolean isDishonestExecutedPersonNaturalPerson;

    /**
    * 被执行人公司id，is_dishonest_executed_person_natural_person = 0 时有值
    */
    private Long dishonestExecutedPersonCompanyId;

    /**
    * 被执行人个人id，is_dishonest_executed_person_natural_person = 1 时有值
    */
    private Long dishonestExecutedPersonCompanyPersonId;

    /**
    * 法人名称，is_legal_person_natural_person 等于0时为人名，等于1时为公司名
    */
    private String legalPersonName;

    /**
    * 是否法人为自然人，1=自然人，0=非自然人
    */
    private Boolean isLegalPersonNaturalPerson;

    /**
    * 法人公司id，is_legal_person_natural_person = 0 时有值
    */
    private Long legalPersonCompanyId;

    /**
    * 法人个人id，is_legal_person_natural_person = 1 时有值
    */
    private Long legalPersonCompanyPersonId;

    /**
    * 被执行人证照/证件号码
    */
    private String dishonestExecutedPersonLicenseNo;

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
    * 发布日期
    */
    private LocalDate publishDate;

    /**
    * 执行依据文号
    */
    private String documentNo;

    /**
    * 做出执行的依据单位公司id
    */
    private Long decisionBasisDeptCompanyId;

    /**
    * 做出执行的依据单位，冗余公司名称
    */
    private String decisionBasisDeptName;

    /**
    * 生效法律文书确定的义务
    */
    private String obligation;

    /**
    * 履行情况，如：全部未履行
    */
    private String performance;

    /**
    * 已履行
    */
    private String performPart;

    /**
    * 未履行
    */
    private String unPerformPart;

    /**
    * 失信被执行人行为具体情形，如：违反财产报告制度
    */
    private String dishonestExecutedPersonBehavior;

    /**
    * 执行标的金额(万元)
    */
    private BigDecimal executeAmount;

    /**
    * 执行标的金额币种，字典id，如：人民币
    */
    private Long executeAmountCurrencyDictId;

	/**
	 * 数据md5,case_no + dishonest_executed_person_name + obligation + performance
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}