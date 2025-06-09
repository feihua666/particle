package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业统计 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Data
@Entity
public class DataCompanyStatistic extends AggreateRoot {

    private DataCompanyStatisticId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 成立年限数量
    */
    private Integer establishYearNum;

    /**
    * 历史名称数量
    */
    private Integer historyNameNum;

    /**
    * 上市板块数量
    */
    private Integer listedNum;

    /**
    * 上市板块详情json，如：[{"name":"A股"},{"name":"港股"}]
    */
    private String listedDetailJson;

    /**
    * 年报数量
    */
    private Integer annualReportNum;

    /**
    * 经营异常数量
    */
    private Integer abnormalNum;

    /**
    * 经营异常详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String abnormalDetailJson;

    /**
    * 立案信息数量
    */
    private Integer caseFilingNum;

    /**
    * 立案信息详情json，如：[{"year":2025,"count":2,"roleDictValue":"plaintiff"},{"year":2024,"count":3,"roleDictValue":"defendant"}]
    */
    private String caseFilingDetailJson;

    /**
    * 法院公告数量
    */
    private Integer courtAnnouncementNum;

    /**
    * 法院公告详情json，如：[{"year":2025,"count":2,"roleDictValue":"plaintiff"},{"year":2024,"count":3,"roleDictValue":"defendant"}]
    */
    private String courtAnnouncementDetailJson;

    /**
    * 荣誉资质数量
    */
    private Integer honorQualificationNum;

    /**
    * 荣誉资质详情json，如：[{"year":2025,"count":2,"grade":"国家级"},{"year":2024,"count":3,"grade":"国家级"}]
    */
    private String honorQualificationDetailJson;

    /**
    * 申请专利数量
    */
    private Integer iprPatentApplicantNum;

    /**
    * 权利专利数量
    */
    private Integer iprPatentRightNum;

	/**
	 * 发明专利数量
	 */
	private Integer iprPatentInventNum;

	/**
	 * 实用新型专利数量
	 */
	private Integer iprPatentUtilityNum;

	/**
	 * 外观专利数量
	 */
	private Integer iprPatentDesignNum;

	/**
	 * 有效专利数量
	 */
	private Integer iprPatentValidNum;

	/**
	 * 有效发明专利数量
	 */
	private Integer iprPatentValidInventNum;

	/**
	 * 有效实用新型专利数量
	 */
	private Integer iprPatentValidUtilityNum;

	/**
	 * 有效外观专利数量
	 */
	private Integer iprPatentValidDesignNum;

	/**
	 * 无效专利数量
	 */
	private Integer iprPatentInvalidNum;

	/**
	 * 在审专利数量，审中专利数量
	 */
	private Integer iprPatentUnderReviewNum;

    /**
    * 失信被执行人数量
    */
    private Integer discreditedJudgmentDebtorNum;

    /**
    * 失信被执行人详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String discreditedJudgmentDebtorDetailJson;

    /**
    * 被执行人数量
    */
    private Integer judgmentDebtorNum;

    /**
    * 被执行人详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String judgmentDebtorDetailJson;

    /**
    * 判断文书数量
    */
    private Integer judgmentDocumentNum;

    /**
    * 判断文书详情json，如：[{"year":2025,"count":2,"roleDictValue":"plaintiff"},{"year":2024,"count":3,"roleDictValue":"defendant"}]
    */
    private String judgmentDocumentDetailJson;

    /**
    * 开庭公告数量
    */
    private Integer openCourtAnnouncementNum;

    /**
    * 开庭公告详情json，如：[{"year":2025,"count":2,"roleDictValue":"plaintiff"},{"year":2024,"count":3,"roleDictValue":"defendant"}]
    */
    private String openCourtAnnouncementDetailJson;

    /**
    * 行政处罚数量
    */
    private Integer punishmentNum;

    /**
    * 行政处罚详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String punishmentDetailJson;

    /**
    * 限制高消费数量
    */
    private Integer restrictHighConsumeNum;

    /**
    * 限制高消费详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String restrictHighConsumeDetailJson;

    /**
    * 严重违法数量
    */
    private Integer seriousIllegalNum;

    /**
    * 严重违法详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String seriousIllegalDetailJson;

    /**
    * 股东数量
    */
    private Integer shareholderNum;

    /**
    * 股东详情json，如：[{"isRegPublic":true,"count":2},{"isListedLatestPublic":true,"count":3},{"isYearReportLatestPublic":true,"count":3}]
    */
    private String shareholderDetailJson;

    /**
    * 融资数量
    */
    private Integer vcFinancingNum;

    /**
    * 融资详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String vcFinancingDetailJson;

    /**
    * 融资产品数量
    */
    private Integer vcProductNum;

    /**
    * 商标数量
    */
    private Integer trademarkNum;

    /**
    * 商标详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String trademarkDetailJson;

    /**
    * 软件著作权数量
    */
    private Integer softwareCopyrightNum;

    /**
    * 软件著作权详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String softwareCopyrightDetailJson;

    /**
    * 作品著作权数量
    */
    private Integer workCopyrightNum;

    /**
    * 作品著作权详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String workCopyrightDetailJson;

    /**
    * 地理标识数量
    */
    private Integer geographicalIndicationNum;

    /**
    * 地理标识详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String geographicalIndicationDetailJson;

    /**
    * 集成电路数量
    */
    private Integer integratedCircuitNum;

    /**
    * 集成电路详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String integratedCircuitDetailJson;

    /**
    * 植物新品种数量
    */
    private Integer plantVarietyNum;

    /**
    * 植物新品种详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String plantVarietyDetailJson;

    /**
    * 行政许可数量
    */
    private Integer administrativeLicenseNum;

    /**
    * 行政许可详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String administrativeLicenseDetailJson;

    /**
    * 送达公告数量
    */
    private Integer deliveryAnnouncementNum;

    /**
    * 送达公告详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String deliveryAnnouncementDetailJson;

    /**
    * 终本案件数量
    */
    private Integer endCaseNum;

    /**
    * 终本案件详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String endCaseDetailJson;

    /**
    * 股权出质数量
    */
    private Integer equityPledgeNum;

    /**
    * 股权出质详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String equityPledgeDetailJson;

    /**
    * 知识产权出质数量
    */
    private Integer iprPledgeNum;

    /**
    * 知识产权出质详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String iprPledgeDetailJson;

    /**
    * 动产抵押数量
    */
    private Integer mortgageChattelNum;

    /**
    * 动产抵押详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String mortgageChattelDetailJson;

    /**
    * 主要人员数量
    */
    private Integer primeStaffNum;

    /**
    * 主要人员详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String primeStaffDetailJson;

    /**
    * 抽查检查数量
    */
    private Integer spotCheckNum;

    /**
    * 抽查检查详情json，如：[{"year":2025,"count":2},{"year":2024,"count":3}]
    */
    private String spotCheckDetailJson;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    


    /**
     * 创建企业统计领域模型对象
     * @return 企业统计领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyStatistic create(){
        return DomainFactory.create(DataCompanyStatistic.class);
    }
}