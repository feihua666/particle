package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 企业年报行政许可 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Data
@Schema
public class DataCompanyAnnualReportAdministrativeLicenseCreateCommand extends AbstractBaseCommand {



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

    @NotEmpty(message = "许可文件名称 不能为空")
    @Schema(description = "许可文件名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String fileName;


    @Schema(description = "许可文件到期日期")
    private LocalDate validToDate;

    public static DataCompanyAnnualReportAdministrativeLicenseCreateCommand createByWarehouseCommand(DataCompanyAnnualReportAdministrativeLicenseWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAnnualReportAdministrativeLicenseCreateCommand command = new DataCompanyAnnualReportAdministrativeLicenseCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyAnnualReportId = dataCompanyBasicWarehouseCommand.getCompanyAnnualReportId();
        command.year = dataCompanyBasicWarehouseCommand.getYear();
        command.serialNumber = dataCompanyBasicWarehouseCommand.getSerialNumber();
        command.fileName = dataCompanyBasicWarehouseCommand.getFileName();
        command.validToDate = dataCompanyBasicWarehouseCommand.getValidToDate();


        return command;
    }
}
