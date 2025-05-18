package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业融资历史投资机构关系 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业融资ID 不能为空")
    @Schema(description = "企业融资ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcFinancingId;

    @NotNull(message = "企业投资机构ID 不能为空")
    @Schema(description = "企业投资机构ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcInvestInstitutionId;

    public static DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand create(Long companyVcFinancingId, Long companyVcInvestInstitutionId) {
        DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand command = new DataCompanyVcFinancingInvestInstitutionRelExWarehouseQueryCommand();
        command.companyVcFinancingId = companyVcFinancingId;
        command.companyVcInvestInstitutionId = companyVcInvestInstitutionId;
        return command;
    }
}
