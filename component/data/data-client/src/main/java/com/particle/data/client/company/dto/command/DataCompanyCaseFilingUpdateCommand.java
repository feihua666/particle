package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingWarehouseCommand;

/**
 * <p>
 * 企业立案信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Data
@Schema
public class DataCompanyCaseFilingUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;


    @Schema(description = "执行法院名称")
    private String executeCourtName;


    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;


    @Schema(description = "开庭日期")
    private LocalDate openCourtDate;


    @Schema(description = "结束日期")
    private LocalDate finishedCourtDate;


    @Schema(description = "是否已结案")
    private Boolean isFinished;


    @Schema(description = "承办部门")
    private String undertakeDept;


    @Schema(description = "承办法官")
    private String undertakeJudger;


    @Schema(description = "法官助理")
    private String assistant;


    @Schema(description = "案件审理程序")
    private String caseTrialProcedure;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyCaseFilingUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyCaseFilingWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyCaseFilingUpdateCommand command = new DataCompanyCaseFilingUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.executeCourtCompanyId = dataCompanyBasicWarehouseCommand.getExecuteCourtCompanyId();
        command.executeCourtName = dataCompanyBasicWarehouseCommand.getExecuteCourtName();
        command.fileCaseDate = dataCompanyBasicWarehouseCommand.getFileCaseDate();
        command.openCourtDate = dataCompanyBasicWarehouseCommand.getOpenCourtDate();
        command.finishedCourtDate = dataCompanyBasicWarehouseCommand.getFinishedCourtDate();
        command.isFinished = dataCompanyBasicWarehouseCommand.getIsFinished();
        command.undertakeDept = dataCompanyBasicWarehouseCommand.getUndertakeDept();
        command.undertakeJudger = dataCompanyBasicWarehouseCommand.getUndertakeJudger();
        command.assistant = dataCompanyBasicWarehouseCommand.getAssistant();
        command.caseTrialProcedure = dataCompanyBasicWarehouseCommand.getCaseTrialProcedure();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
