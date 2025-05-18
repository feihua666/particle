package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权专利统计 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @NotNull(message = "企业知识产权专利id 不能为空")
    @Schema(description = "企业知识产权专利id")
    private Long companyIprPatentId;


    public static DataCompanyIprPatentStatisticExWarehouseQueryCommand create(Long companyIprPatentId) {
        DataCompanyIprPatentStatisticExWarehouseQueryCommand command = new DataCompanyIprPatentStatisticExWarehouseQueryCommand();
        command.companyIprPatentId = companyIprPatentId;
        return command;
    }
}
