package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 企业失信被执行人 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Data
@Schema
public class DataCompanyDiscreditedJudgmentDebtorUpdateCommand extends AbstractBaseUpdateCommand {


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


    public static DataCompanyDiscreditedJudgmentDebtorUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand){
        DataCompanyDiscreditedJudgmentDebtorUpdateCommand command = new DataCompanyDiscreditedJudgmentDebtorUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.caseNo = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getCaseNo();
        command.dishonestExecutedPersonName = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonName();
        command.isDishonestExecutedPersonNaturalPerson = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getIsDishonestExecutedPersonNaturalPerson();
        command.dishonestExecutedPersonCompanyId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonCompanyId();
        command.dishonestExecutedPersonCompanyPersonId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonCompanyPersonId();
        command.legalPersonName = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonName();
        command.isLegalPersonNaturalPerson = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getIsLegalPersonNaturalPerson();
        command.legalPersonCompanyId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonCompanyPersonId();
        command.dishonestExecutedPersonLicenseNo = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonLicenseNo();
        command.executeCourtCompanyId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteCourtCompanyId();
        command.executeCourtName = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteCourtName();
        command.fileCaseDate = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getFileCaseDate();
        command.finishedCaseDate = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getFinishedCaseDate();
        command.isFinished = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getIsFinished();
        command.publishDate = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getPublishDate();
        command.documentNo = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDocumentNo();
        command.decisionBasisDeptCompanyId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDecisionBasisDeptCompanyId();
        command.decisionBasisDeptName = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDecisionBasisDeptName();
        command.obligation = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getObligation();
        command.performance = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getPerformance();
        command.performPart = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getPerformPart();
        command.unPerformPart = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getUnPerformPart();
        command.dishonestExecutedPersonBehavior = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonBehavior();
        command.executeAmount = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteAmount();
        command.executeAmountCurrencyDictId = dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteAmountCurrencyDictId();

        return command;
    }
}
