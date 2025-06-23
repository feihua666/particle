package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业知识产权植物新品种id 不能为空")
    @Schema(description = "企业知识产权植物新品种id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPlantVarietyId;


    public static DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand create(Long companyIprPlantVarietyId) {
        DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand command = new DataCompanyIprPlantVarietyChangeExWarehouseQueryCommand();
        command.companyIprPlantVarietyId = companyIprPlantVarietyId;
        return command;
    }
}
