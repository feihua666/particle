package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业失信被执行人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyDiscreditedJudgmentDebtorWarehouseCommand extends AbstractBaseCommand {


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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyDiscreditedJudgmentDebtorDataMd5(caseNo,dishonestExecutedPersonName,obligation,performance);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(dishonestExecutedPersonName)
                && Objects.isNull(isDishonestExecutedPersonNaturalPerson)
                && Objects.isNull(dishonestExecutedPersonCompanyId)
                && Objects.isNull(dishonestExecutedPersonCompanyPersonId)
                && StrUtil.isEmpty(legalPersonName)
                && Objects.isNull(isLegalPersonNaturalPerson)
                && Objects.isNull(legalPersonCompanyId)
                && Objects.isNull(legalPersonCompanyPersonId)
                && StrUtil.isEmpty(dishonestExecutedPersonLicenseNo)
                && Objects.isNull(executeCourtCompanyId)
                && StrUtil.isEmpty(executeCourtName)
                && Objects.isNull(fileCaseDate)
                && Objects.isNull(finishedCaseDate)
                && Objects.isNull(isFinished)
                && Objects.isNull(publishDate)
                && StrUtil.isEmpty(documentNo)
                && Objects.isNull(decisionBasisDeptCompanyId)
                && StrUtil.isEmpty(decisionBasisDeptName)
                && StrUtil.isEmpty(obligation)
                && StrUtil.isEmpty(performance)
                && StrUtil.isEmpty(performPart)
                && StrUtil.isEmpty(unPerformPart)
                && StrUtil.isEmpty(dishonestExecutedPersonBehavior)
                && Objects.isNull(executeAmount)
                && Objects.isNull(executeAmountCurrencyDictId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyDiscreditedJudgmentDebtorExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(dishonestExecutedPersonName, exWarehouseVO.getDishonestExecutedPersonName())) {
            this.dishonestExecutedPersonName = null;
        }
        if (Objects.equals(isDishonestExecutedPersonNaturalPerson, exWarehouseVO.getIsDishonestExecutedPersonNaturalPerson())) {
            this.isDishonestExecutedPersonNaturalPerson = null;
        }
        if (Objects.equals(dishonestExecutedPersonCompanyId, exWarehouseVO.getDishonestExecutedPersonCompanyId())) {
            this.dishonestExecutedPersonCompanyId = null;
        }
        if (Objects.equals(dishonestExecutedPersonCompanyPersonId, exWarehouseVO.getDishonestExecutedPersonCompanyPersonId())) {
            this.dishonestExecutedPersonCompanyPersonId = null;
        }
        if (Objects.equals(legalPersonName, exWarehouseVO.getLegalPersonName())) {
            this.legalPersonName = null;
        }
        if (Objects.equals(isLegalPersonNaturalPerson, exWarehouseVO.getIsLegalPersonNaturalPerson())) {
            this.isLegalPersonNaturalPerson = null;
        }
        if (Objects.equals(legalPersonCompanyId, exWarehouseVO.getLegalPersonCompanyId())) {
            this.legalPersonCompanyId = null;
        }
        if (Objects.equals(legalPersonCompanyPersonId, exWarehouseVO.getLegalPersonCompanyPersonId())) {
            this.legalPersonCompanyPersonId = null;
        }
        if (Objects.equals(dishonestExecutedPersonLicenseNo, exWarehouseVO.getDishonestExecutedPersonLicenseNo())) {
            this.dishonestExecutedPersonLicenseNo = null;
        }
        if (Objects.equals(executeCourtCompanyId, exWarehouseVO.getExecuteCourtCompanyId())) {
            this.executeCourtCompanyId = null;
        }
        if (Objects.equals(executeCourtName, exWarehouseVO.getExecuteCourtName())) {
            this.executeCourtName = null;
        }
        if (Objects.equals(fileCaseDate, exWarehouseVO.getFileCaseDate())) {
            this.fileCaseDate = null;
        }
        if (Objects.equals(finishedCaseDate, exWarehouseVO.getFinishedCaseDate())) {
            this.finishedCaseDate = null;
        }
        if (Objects.equals(isFinished, exWarehouseVO.getIsFinished())) {
            this.isFinished = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
        if (Objects.equals(documentNo, exWarehouseVO.getDocumentNo())) {
            this.documentNo = null;
        }
        if (Objects.equals(decisionBasisDeptCompanyId, exWarehouseVO.getDecisionBasisDeptCompanyId())) {
            this.decisionBasisDeptCompanyId = null;
        }
        if (Objects.equals(decisionBasisDeptName, exWarehouseVO.getDecisionBasisDeptName())) {
            this.decisionBasisDeptName = null;
        }
        if (Objects.equals(obligation, exWarehouseVO.getObligation())) {
            this.obligation = null;
        }
        if (Objects.equals(performance, exWarehouseVO.getPerformance())) {
            this.performance = null;
        }
        if (Objects.equals(performPart, exWarehouseVO.getPerformPart())) {
            this.performPart = null;
        }
        if (Objects.equals(unPerformPart, exWarehouseVO.getUnPerformPart())) {
            this.unPerformPart = null;
        }
        if (Objects.equals(dishonestExecutedPersonBehavior, exWarehouseVO.getDishonestExecutedPersonBehavior())) {
            this.dishonestExecutedPersonBehavior = null;
        }
        if (NumberUtil.equals(executeAmount, exWarehouseVO.getExecuteAmount())) {
            this.executeAmount = null;
        }
        if (Objects.equals(executeAmountCurrencyDictId, exWarehouseVO.getExecuteAmountCurrencyDictId())) {
            this.executeAmountCurrencyDictId = null;
        }

    }

    public static DataCompanyDiscreditedJudgmentDebtorWarehouseCommand createByDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(DataCompanyDiscreditedJudgmentDebtorExWarehouseVO exWarehouseVO) {
        DataCompanyDiscreditedJudgmentDebtorWarehouseCommand command = new DataCompanyDiscreditedJudgmentDebtorWarehouseCommand();
        command.caseNo = exWarehouseVO.getCaseNo();
        command.dishonestExecutedPersonName = exWarehouseVO.getDishonestExecutedPersonName();
        command.isDishonestExecutedPersonNaturalPerson = exWarehouseVO.getIsDishonestExecutedPersonNaturalPerson();
        command.dishonestExecutedPersonCompanyId = exWarehouseVO.getDishonestExecutedPersonCompanyId();
        command.dishonestExecutedPersonCompanyPersonId = exWarehouseVO.getDishonestExecutedPersonCompanyPersonId();
        command.legalPersonName = exWarehouseVO.getLegalPersonName();
        command.isLegalPersonNaturalPerson = exWarehouseVO.getIsLegalPersonNaturalPerson();
        command.legalPersonCompanyId = exWarehouseVO.getLegalPersonCompanyId();
        command.legalPersonCompanyPersonId = exWarehouseVO.getLegalPersonCompanyPersonId();
        command.dishonestExecutedPersonLicenseNo = exWarehouseVO.getDishonestExecutedPersonLicenseNo();
        command.executeCourtCompanyId = exWarehouseVO.getExecuteCourtCompanyId();
        command.executeCourtName = exWarehouseVO.getExecuteCourtName();
        command.fileCaseDate = exWarehouseVO.getFileCaseDate();
        command.finishedCaseDate = exWarehouseVO.getFinishedCaseDate();
        command.isFinished = exWarehouseVO.getIsFinished();
        command.publishDate = exWarehouseVO.getPublishDate();
        command.documentNo = exWarehouseVO.getDocumentNo();
        command.decisionBasisDeptCompanyId = exWarehouseVO.getDecisionBasisDeptCompanyId();
        command.decisionBasisDeptName = exWarehouseVO.getDecisionBasisDeptName();
        command.obligation = exWarehouseVO.getObligation();
        command.performance = exWarehouseVO.getPerformance();
        command.performPart = exWarehouseVO.getPerformPart();
        command.unPerformPart = exWarehouseVO.getUnPerformPart();
        command.dishonestExecutedPersonBehavior = exWarehouseVO.getDishonestExecutedPersonBehavior();
        command.executeAmount = exWarehouseVO.getExecuteAmount();
        command.executeAmountCurrencyDictId = exWarehouseVO.getExecuteAmountCurrencyDictId();


        return command;
    }
}
