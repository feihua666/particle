package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommand;

/**
 * <p>
 * 企业融资产品竞品关系 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业融资产品表ID")
    private Long companyVcProductId;



    @Schema(description = "企业竞品id")
    private Long companyVcCompetitiveProductId;



    public static DataCompanyVcProductCompetitiveProductRelUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyVcProductCompetitiveProductRelWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcProductCompetitiveProductRelUpdateCommand command = new DataCompanyVcProductCompetitiveProductRelUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyVcProductId = dataCompanyBasicWarehouseCommand.getCompanyVcProductId();
        command.companyVcCompetitiveProductId = dataCompanyBasicWarehouseCommand.getCompanyVcCompetitiveProductId();


        return command;
    }
}
