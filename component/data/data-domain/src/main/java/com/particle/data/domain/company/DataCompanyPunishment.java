package com.particle.data.domain.company;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业行政处罚 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Data
@Entity
public class DataCompanyPunishment extends AggreateRoot {

    private DataCompanyPunishmentId id;

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
    * 是否当事人为自然人，1=自然人，0=非自然人
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


    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }


    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyPunishmentDataMd5(companyName, punishNo, punishContent);
    }


    /**
     * 创建企业行政处罚领域模型对象
     * @return 企业行政处罚领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyPunishment create(){
        return DomainFactory.create(DataCompanyPunishment.class);
    }
}
