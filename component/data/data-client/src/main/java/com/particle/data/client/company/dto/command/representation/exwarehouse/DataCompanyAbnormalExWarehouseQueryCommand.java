package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 企业经营异常 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyAbnormalExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "企业表ID")
    private Long companyId;


    public static DataCompanyAbnormalExWarehouseQueryCommand create(Long companyId) {
        DataCompanyAbnormalExWarehouseQueryCommand command = new DataCompanyAbnormalExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
