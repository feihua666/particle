package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业年报 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyAnnualReportExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "企业ID")
    private Long companyId;

    @Schema(description = "年报年度")
    private Integer year;
    public static DataCompanyAnnualReportExWarehouseQueryCommand create(Long companyId) {
        DataCompanyAnnualReportExWarehouseQueryCommand command = new DataCompanyAnnualReportExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
