package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业失信被执行人 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand create(Long companyId,String caseNo) {
        DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand command = new DataCompanyDiscreditedJudgmentDebtorExWarehouseQueryCommand();
        command.companyId = companyId;
        command.caseNo = caseNo;
        return command;
    }
}
