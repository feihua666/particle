package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业立案信息 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyCaseFilingExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业ID 不能为空")
    @Schema(description = "企业ID")
    private Long companyId;

    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyCaseFilingExWarehouseQueryCommand create(Long companyId,String caseNo) {
        DataCompanyCaseFilingExWarehouseQueryCommand command = new DataCompanyCaseFilingExWarehouseQueryCommand();
        command.companyId = companyId;
        command.caseNo = caseNo;
        return command;
    }
}
