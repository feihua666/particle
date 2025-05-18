package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业裁判文书内容 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyJudgmentDocumentContentExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @NotNull(message = "裁判文书id 不能为空")
    @Schema(description = "裁判文书id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyJudgmentDocumentId;

    public static DataCompanyJudgmentDocumentContentExWarehouseQueryCommand create(Long companyJudgmentDocumentId) {
        DataCompanyJudgmentDocumentContentExWarehouseQueryCommand command = new DataCompanyJudgmentDocumentContentExWarehouseQueryCommand();
        command.companyJudgmentDocumentId = companyJudgmentDocumentId;
        return command;
    }
}
