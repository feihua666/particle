package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业统计 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyStatisticExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @NotNull(message = "企业ID 不能为空")
    @Schema(description = "企业ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    public static DataCompanyStatisticExWarehouseQueryCommand create(Long companyId) {
        DataCompanyStatisticExWarehouseQueryCommand command = new DataCompanyStatisticExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
