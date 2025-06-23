package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySpotCheckWarehouseCommand;

/**
 * <p>
 * 企业抽查检查 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Data
@Schema
public class DataCompanySpotCheckUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "检查实施机关")
    private String checkInstitute;


    @Schema(description = "检查实施机关公司id")
    private Long checkInstituteCompanyId;


    @Schema(description = "检查类型")
    private String checkTypeName;


    @Schema(description = "检查日期")
    private LocalDate checkDate;


    @Schema(description = "检查结果")
    private String checkResult;


    @NotEmpty(message = "数据md5 不能为空")
        @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;










    public static DataCompanySpotCheckUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanySpotCheckWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanySpotCheckUpdateCommand command = new DataCompanySpotCheckUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.checkInstitute = dataCompanyBasicWarehouseCommand.getCheckInstitute();
        command.checkInstituteCompanyId = dataCompanyBasicWarehouseCommand.getCheckInstituteCompanyId();
        command.checkTypeName = dataCompanyBasicWarehouseCommand.getCheckTypeName();
        command.checkDate = dataCompanyBasicWarehouseCommand.getCheckDate();
        command.checkResult = dataCompanyBasicWarehouseCommand.getCheckResult();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
