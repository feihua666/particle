package com.particle.data.domain.company;

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
 * 企业裁判文书 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:21
 */
@Data
@Entity
public class DataCompanyJudgmentDocument extends AggreateRoot {

    private DataCompanyJudgmentDocumentId id;

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 案由
    */
    private String caseReason;

    /**
    * 案件审理法院公司id
    */
    private Long caseCourtCompanyId;

    /**
    * 案件审理法院名称，冗余公司名称
    */
    private String caseCourtName;

    /**
    * 案件裁判日期
    */
    private LocalDate caseJudgeDate;

    /**
    * 案件审理程序，如： 民事再审
    */
    private String caseTrialProcedure;

    /**
    * 法律依据，如：《中华人民共和国民事诉讼法》第二百一十五条第一款最高人民法院关于适用《中华人民共和国民事诉讼法》的解释第三百九十三条第二款
    */
    private String caseLegalBasis;

    /**
    * 案件类型,字典id，如：刑事案件、民事案件
    */
    private Long caseTypeDictId;

	/**
	 * 案件类型名称
	 */
	private String caseTypeName;

    /**
    * 案件涉及金额（万元）
    */
    private BigDecimal caseAmount;

    /**
    * 案件涉及金额币种，字典id，如：人民币
    */
    private Long caseAmountCurrencyDictId;

    /**
    * 文书类型,字典id，如：判决书、裁定书、调解书、决定书、通知书、令、其他
    */
    private Long documentTypeDictId;

	/**
	 * 文书类型名称
	 */
	private String documentTypeName;

    /**
    * 文书发布日期
    */
    private LocalDate documentPublishDate;

    /**
    * 文书发布标题，案件名称
    */
    private String documentPublishTitle;

	/**
	 * 数据md5,case_no + case_reason + case_judge_date + case_trial_procedure + case_type_name + document_type_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyJudgmentDocumentDataMd5(caseNo,caseReason,caseJudgeDate,caseTrialProcedure,caseTypeName,documentTypeName);
    }

    /**
     * 创建企业裁判文书领域模型对象
     * @return 企业裁判文书领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyJudgmentDocument create(){
        return DomainFactory.create(DataCompanyJudgmentDocument.class);
    }
}
