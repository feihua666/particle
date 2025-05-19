package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand;

/**
 * <p>
 * 企业融资历史投资机构关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业融资表ID")
    private Long companyVcFinancingId;



    @Schema(description = "企业投资机构表id")
    private Long companyVcInvestInstitutionId;


    public static DataCompanyVcFinancingInvestInstitutionRelUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcFinancingInvestInstitutionRelUpdateCommand command = new DataCompanyVcFinancingInvestInstitutionRelUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyVcFinancingId = dataCompanyBasicWarehouseCommand.getCompanyVcFinancingId();
        command.companyVcInvestInstitutionId = dataCompanyBasicWarehouseCommand.getCompanyVcInvestInstitutionId();


        return command;
    }
}
