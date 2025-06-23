package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业主要人员职位 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyPrimeStaffPositionExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业主要人员表ID 不能为空")
    @Schema(description = "企业主要人员表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyPrimeStaffId;

    public static DataCompanyPrimeStaffPositionExWarehouseQueryCommand create(Long companyPrimeStaffId) {
        DataCompanyPrimeStaffPositionExWarehouseQueryCommand command = new DataCompanyPrimeStaffPositionExWarehouseQueryCommand();
        command.companyPrimeStaffId = companyPrimeStaffId;
        return command;
    }
}
