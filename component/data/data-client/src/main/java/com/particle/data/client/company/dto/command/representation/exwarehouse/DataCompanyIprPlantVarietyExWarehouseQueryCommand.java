package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 企业知识产权植物新品种 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

    @Schema(description = "公告号")
    private String publicNo;

    @Schema(description = "申请号")
    private String applyNo;


    public static DataCompanyIprPlantVarietyExWarehouseQueryCommand create(Long companyId,String publicNo,String applyNo) {
        DataCompanyIprPlantVarietyExWarehouseQueryCommand command = new DataCompanyIprPlantVarietyExWarehouseQueryCommand();
        command.companyId = companyId;
        command.publicNo = publicNo;
        command.applyNo = applyNo;
        return command;
    }
}
