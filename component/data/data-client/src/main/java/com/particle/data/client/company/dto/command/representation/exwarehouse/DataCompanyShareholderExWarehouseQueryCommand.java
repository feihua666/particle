package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业股东 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyShareholderExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    public static DataCompanyShareholderExWarehouseQueryCommand create(Long companyId) {
        DataCompanyShareholderExWarehouseQueryCommand command = new DataCompanyShareholderExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
