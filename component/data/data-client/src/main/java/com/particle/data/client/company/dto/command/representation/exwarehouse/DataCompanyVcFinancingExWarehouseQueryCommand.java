package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业融资 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyVcFinancingExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "企业表ID")
    private Long companyId;

    public static DataCompanyVcFinancingExWarehouseQueryCommand create(Long companyId) {
        DataCompanyVcFinancingExWarehouseQueryCommand command = new DataCompanyVcFinancingExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
