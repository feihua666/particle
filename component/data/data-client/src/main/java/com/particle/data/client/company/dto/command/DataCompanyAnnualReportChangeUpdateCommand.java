package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportChangeWarehouseCommand;

/**
 * <p>
 * 企业年报变更 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Data
@Schema
public class DataCompanyAnnualReportChangeUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotNull(message = "企业年报表ID 不能为空")
        @Schema(description = "企业年报表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyAnnualReportId;


    @NotNull(message = "年报年度 不能为空")
        @Schema(description = "年报年度",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer year;



        @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "变更事项")
    private Long changeItemDictId;


    @NotEmpty(message = "变更事项 不能为空")
        @Schema(description = "变更事项",requiredMode = Schema.RequiredMode.REQUIRED)
    private String changeItemName;


    @Schema(description = "变更前内容")
    private String contentBefore;


    @Schema(description = "变更后内容")
    private String contentAfter;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    public static DataCompanyAnnualReportChangeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAnnualReportChangeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportChangeUpdateCommand command = new DataCompanyAnnualReportChangeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.serialNumber = dataCompanyBasicWarehouseCommand.getSerialNumber();
        command.changeItemDictId = dataCompanyBasicWarehouseCommand.getChangeItemDictId();
        command.changeItemName = dataCompanyBasicWarehouseCommand.getChangeItemName();
        command.contentBefore = dataCompanyBasicWarehouseCommand.getContentBefore();
        command.contentAfter = dataCompanyBasicWarehouseCommand.getContentAfter();
        command.changeDate = dataCompanyBasicWarehouseCommand.getChangeDate();

        return command;
    }
}
