package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业融资产品竞品关系 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业融资产品ID 不能为空")
    @Schema(description = "企业融资产品ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcProductId;


    @NotNull(message = "企业竞品id 不能为空")
    @Schema(description = "企业竞品id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcCompetitiveProductId;



    public static DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand create(Long companyVcProductId,Long companyVcCompetitiveProductId) {
        DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand command = new DataCompanyVcProductCompetitiveProductRelExWarehouseQueryCommand();
        command.companyVcProductId = companyVcProductId;
        command.companyVcCompetitiveProductId = companyVcCompetitiveProductId;
        return command;
    }
}
