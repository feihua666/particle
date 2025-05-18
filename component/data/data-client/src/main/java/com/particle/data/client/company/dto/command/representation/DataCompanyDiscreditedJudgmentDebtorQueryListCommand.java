package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业失信被执行人 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Data
@Schema
public class DataCompanyDiscreditedJudgmentDebtorQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "被执行人名称")
    private String dishonestExecutedPersonName;


    @Schema(description = "是否被执行人为自然人")
    private Boolean isDishonestExecutedPersonNaturalPerson;


    @Schema(description = "被执行人公司id")
    private Long dishonestExecutedPersonCompanyId;


    @Schema(description = "被执行人个人id")
    private Long dishonestExecutedPersonCompanyPersonId;


    @Schema(description = "法人名称")
    private String legalPersonName;


    @Schema(description = "是否法人为自然人")
    private Boolean isLegalPersonNaturalPerson;


    @Schema(description = "法人公司id")
    private Long legalPersonCompanyId;


    @Schema(description = "法人个人id")
    private Long legalPersonCompanyPersonId;


    @Schema(description = "被执行人证照/证件号码")
    private String dishonestExecutedPersonLicenseNo;


    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;


    @Schema(description = "执行法院名称")
    private String executeCourtName;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;


    @Schema(description = "结案日期")
    private LocalDate finishedCaseDate;


    @Schema(description = "是否已结案")
    private Boolean isFinished;


    @Schema(description = "发布日期")
    private LocalDate publishDate;


    @Schema(description = "执行依据文号")
    private String documentNo;


    @Schema(description = "做出执行的依据单位公司id")
    private Long decisionBasisDeptCompanyId;


    @Schema(description = "做出执行的依据单位")
    private String decisionBasisDeptName;


    @Schema(description = "生效法律文书确定的义务")
    private String obligation;


    @Schema(description = "履行情况")
    private String performance;


    @Schema(description = "已履行")
    private String performPart;


    @Schema(description = "未履行")
    private String unPerformPart;


    @Schema(description = "失信被执行人行为具体情形")
    private String dishonestExecutedPersonBehavior;


    @Schema(description = "执行标的金额(万元)")
    private BigDecimal executeAmount;


    @Schema(description = "执行标的金额币种")
    private Long executeAmountCurrencyDictId;

	@Schema(description = "数据md5,case_no + dishonest_executed_person_name + obligation + performance")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;









}