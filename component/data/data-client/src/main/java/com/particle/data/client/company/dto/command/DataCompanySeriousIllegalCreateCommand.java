package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;

/**
 * <p>
 * 企业严重违法 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Data
@Schema
public class DataCompanySeriousIllegalCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "类别")
    private String type;


    @NotEmpty(message = "列入原因 不能为空")
        @Schema(description = "列入原因",requiredMode = Schema.RequiredMode.REQUIRED)
    private String putReason;


    @NotNull(message = "列入日期 不能为空")
        @Schema(description = "列入日期",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate putDate;


    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;


    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;


    @Schema(description = "移除原因")
    private String removeReason;


    @Schema(description = "移除日期")
    private LocalDate removeDate;


    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

    public static DataCompanySeriousIllegalCreateCommand createByWarehouseCommand(DataCompanySeriousIllegalWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanySeriousIllegalCreateCommand command = new DataCompanySeriousIllegalCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.type = dataCompanyBasicWarehouseCommand.getType();
        command.putReason = dataCompanyBasicWarehouseCommand.getPutReason();
        command.putDate = dataCompanyBasicWarehouseCommand.getPutDate();
        command.putInstituteCompanyId = dataCompanyBasicWarehouseCommand.getPutInstituteCompanyId();
        command.putInstituteName = dataCompanyBasicWarehouseCommand.getPutInstituteName();
        command.removeReason = dataCompanyBasicWarehouseCommand.getRemoveReason();
        command.removeDate = dataCompanyBasicWarehouseCommand.getRemoveDate();
        command.removeInstituteCompanyId = dataCompanyBasicWarehouseCommand.getRemoveInstituteCompanyId();
        command.removeInstituteName = dataCompanyBasicWarehouseCommand.getRemoveInstituteName();


        return command;
    }
}
