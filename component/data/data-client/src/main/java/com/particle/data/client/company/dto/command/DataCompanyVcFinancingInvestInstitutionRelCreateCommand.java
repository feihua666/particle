package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand;

/**
 * <p>
 * 企业融资历史投资机构关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业融资表ID 不能为空")
        @Schema(description = "企业融资表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcFinancingId;


    @NotNull(message = "企业投资机构表id 不能为空")
        @Schema(description = "企业投资机构表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcInvestInstitutionId;



    public static DataCompanyVcFinancingInvestInstitutionRelCreateCommand createByWarehouseCommand(DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcFinancingInvestInstitutionRelCreateCommand command = new DataCompanyVcFinancingInvestInstitutionRelCreateCommand();
                command.companyVcFinancingId = dataCompanyBasicWarehouseCommand.getCompanyVcFinancingId();
        command.companyVcInvestInstitutionId = dataCompanyBasicWarehouseCommand.getCompanyVcInvestInstitutionId();


        return command;
    }
}