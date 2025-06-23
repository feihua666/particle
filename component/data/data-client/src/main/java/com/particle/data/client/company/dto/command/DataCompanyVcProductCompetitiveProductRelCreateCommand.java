package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommand;

/**
 * <p>
 * 企业融资产品竞品关系 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业融资产品id 不能为空")
    @Schema(description = "企业融资产品id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcProductId;


    @NotNull(message = "企业竞品id 不能为空")
    @Schema(description = "企业竞品id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcCompetitiveProductId;


    public static DataCompanyVcProductCompetitiveProductRelCreateCommand createByWarehouseCommand(DataCompanyVcProductCompetitiveProductRelWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcProductCompetitiveProductRelCreateCommand command = new DataCompanyVcProductCompetitiveProductRelCreateCommand();
                command.companyVcProductId = dataCompanyBasicWarehouseCommand.getCompanyVcProductId();
        command.companyVcCompetitiveProductId = dataCompanyBasicWarehouseCommand.getCompanyVcCompetitiveProductId();


        return command;
    }
}
