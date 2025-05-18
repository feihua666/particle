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
 * 企业行政处罚表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_punishment")
public class DataCompanyPunishmentDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 企业名称，（信用中国、工商公示）
    */
    private String companyName;

    /**
    * 法人名称，is_legal_person_natural_person 等于0时为人名，等于1时为公司名，（信用中国、工商公示）
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
    * 行政处罚决定书文号，（信用中国、工商公示）
    */
    private String punishNo;

    /**
    * 违法行为类型 ，（信用中国、工商公示）
    */
    private String illegalType;

    /**
    * 处罚依据，（信用中国、工商公示）
    */
    private String punishBasis;

    /**
    * 违法事实，（信用中国、工商公示）
    */
    private String illegalFact;

    /**
    * 处罚类别，（信用中国、工商公示）
    */
    private String punishType;

    /**
    * 处罚内容，（信用中国、工商公示）
    */
    private String punishContent;

    /**
    * 罚款金额（万元），（信用中国、工商公示）
    */
    private BigDecimal fineAmount;

    /**
    * 罚款金额币种，字典id，如：人民币
    */
    private Long fineAmountCurrencyDictId;

    /**
    * 没收金额（万元），（信用中国、工商公示）
    */
    private BigDecimal confiscateAmount;

    /**
    * 没收金额币种，字典id，如：人民币
    */
    private Long confiscateAmountCurrencyDictId;

    /**
    * 作出行政处罚决定机关公司id
    */
    private Long instituteCompanyId;

    /**
    * 作出行政处罚决定机关名称，冗余公司名称，（信用中国、工商公示）
    */
    private String instituteName;

    /**
    * 暂扣或吊销证照名称及编号，（信用中国、工商公示）
    */
    private String suspendOrRevokeLicenseNameCode;

    /**
    * 作出行政处罚决定开始日期，作出行政处罚决定日期，（信用中国、工商公示）
    */
    private LocalDate punishDecisionStartDate;

    /**
    * 作出行政处罚决定结束日期，处罚有效期，（工商公示）
    */
    private LocalDate punishDecisionEndDate;

    /**
    * 发布开始日期，公示日期，（工商公示）
    */
    private LocalDate publishStartDate;

    /**
    * 发布结束日期，公示截止期，（工商公示）
    */
    private LocalDate publishEndDate;

    /**
    * 数据来源，如：盂县消防救援大队，（信用中国）
    */
    private String dataFrom;

    /**
    * 数据来源公司id
    */
    private Long dataFromCompanyId;

    /**
    * 数据来源名称，冗余公司名称，如：盂县消防救援大队（信用中国、工商公示）
    */
    private String dataFromName;

    /**
    * 备注，（工商公示）
    */
    private String remark;

    /**
    * 是否数据标识为工商公示，1=工商公示，0=非工商公示
    */
    private Boolean isDataFlagGs;

    /**
    * 是否数据标识为信用中国，1=信用中国，0=非信用中国
    */
    private Boolean isDataFlagXyzg;

	/**
	 * 数据md5,company_name + punish_no + punish_content
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
