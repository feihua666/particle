package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.particle.data.client.company.dto.command.warehouse.DataCompanySeriousIllegalWarehouseCommand;

/**
 * <p>
 * 企业严重违法 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Data
@Schema
public class DataCompanySeriousIllegalUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "企业表ID")
    private Long companyId;

	@Schema(description = "企业名称")
	private String companyName;

	@Schema(description = "列入决定书文号")
	private String putNo;


    @Schema(description = "列入原因")
    private String putReason;


    @Schema(description = "列入日期")
    private LocalDate putDate;


    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;


    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;

	@Schema(description = "移出决定书文号")
	private String removeNo;


    @Schema(description = "移除原因")
    private String removeReason;


    @Schema(description = "移除日期")
    private LocalDate removeDate;


    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanySeriousIllegalUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanySeriousIllegalWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanySeriousIllegalUpdateCommand command = new DataCompanySeriousIllegalUpdateCommand();
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
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
