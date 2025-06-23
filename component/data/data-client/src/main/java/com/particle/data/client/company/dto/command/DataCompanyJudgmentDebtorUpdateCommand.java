package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;

/**
 * <p>
 * 企业被执行人 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Data
@Schema
public class DataCompanyJudgmentDebtorUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "被执行人名称")
    private String executedPersonName;


    @Schema(description = "是否被执行人为自然人")
    private Boolean isExecutedPersonNaturalPerson;


    @Schema(description = "被执行人公司id")
    private Long executedPersonCompanyId;


    @Schema(description = "被执行人个人id")
    private Long executedPersonCompanyPersonId;


    @Schema(description = "被执行人证照/证件号码")
    private String executedPersonLicenseNo;


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


    @Schema(description = "执行标的金额（万元）")
    private BigDecimal executeAmount;


    @Schema(description = "执行标的金额币种")
    private Long executeAmountCurrencyDictId;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyJudgmentDebtorUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyJudgmentDebtorWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyJudgmentDebtorUpdateCommand command = new DataCompanyJudgmentDebtorUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.executedPersonName = dataCompanyBasicWarehouseCommand.getExecutedPersonName();
        command.isExecutedPersonNaturalPerson = dataCompanyBasicWarehouseCommand.getIsExecutedPersonNaturalPerson();
        command.executedPersonCompanyId = dataCompanyBasicWarehouseCommand.getExecutedPersonCompanyId();
        command.executedPersonCompanyPersonId = dataCompanyBasicWarehouseCommand.getExecutedPersonCompanyPersonId();
        command.executedPersonLicenseNo = dataCompanyBasicWarehouseCommand.getExecutedPersonLicenseNo();
        command.executeCourtCompanyId = dataCompanyBasicWarehouseCommand.getExecuteCourtCompanyId();
        command.executeCourtName = dataCompanyBasicWarehouseCommand.getExecuteCourtName();
        command.fileCaseDate = dataCompanyBasicWarehouseCommand.getFileCaseDate();
        command.finishedCaseDate = dataCompanyBasicWarehouseCommand.getFinishedCaseDate();
        command.isFinished = dataCompanyBasicWarehouseCommand.getIsFinished();
        command.executeAmount = dataCompanyBasicWarehouseCommand.getExecuteAmount();
        command.executeAmountCurrencyDictId = dataCompanyBasicWarehouseCommand.getExecuteAmountCurrencyDictId();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
