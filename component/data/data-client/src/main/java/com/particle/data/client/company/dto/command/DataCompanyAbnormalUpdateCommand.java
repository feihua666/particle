package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyAbnormalWarehouseCommand;

/**
 * <p>
 * 企业经营异常 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Data
@Schema
public class DataCompanyAbnormalUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

	@Schema(description = "企业名称")
	private String companyName;


    @Schema(description = "列入决定书文号")
    private String putNo;


    @NotEmpty(message = "列入异常名录原因 不能为空")
        @Schema(description = "列入异常名录原因",requiredMode = Schema.RequiredMode.REQUIRED)
    private String putReason;


    @NotNull(message = "列入异常名录日期 不能为空")
        @Schema(description = "列入异常名录日期",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate putDate;


    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;


    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;


    @Schema(description = "移出决定书文号")
    private String removeNo;


    @Schema(description = "移出异常名录原因")
    private String removeReason;


    @Schema(description = "移出异常名录日期")
    private LocalDate removeDate;


    @Schema(description = "作出移出决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移出决定机关名称")
    private String removeInstituteName;

    public static DataCompanyAbnormalUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyAbnormalWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyAbnormalUpdateCommand command = new DataCompanyAbnormalUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyName = dataCompanyBasicWarehouseCommand.getCompanyName();
        command.putNo = dataCompanyBasicWarehouseCommand.getPutNo();
        command.putReason = dataCompanyBasicWarehouseCommand.getPutReason();
        command.putDate = dataCompanyBasicWarehouseCommand.getPutDate();
        command.putInstituteCompanyId = dataCompanyBasicWarehouseCommand.getPutInstituteCompanyId();
        command.putInstituteName = dataCompanyBasicWarehouseCommand.getPutInstituteName();
        command.removeNo = dataCompanyBasicWarehouseCommand.getRemoveNo();
        command.removeReason = dataCompanyBasicWarehouseCommand.getRemoveReason();
        command.removeDate = dataCompanyBasicWarehouseCommand.getRemoveDate();
        command.removeInstituteCompanyId = dataCompanyBasicWarehouseCommand.getRemoveInstituteCompanyId();
        command.removeInstituteName = dataCompanyBasicWarehouseCommand.getRemoveInstituteName();


        return command;
    }
}
