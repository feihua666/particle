package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权商标 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprTrademarkExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

    @Schema(description = "是否申请人")
    private Boolean isApplicant;

    @Schema(description = "是否代理人")
    private Boolean isAgent;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "申请号")
    private String applyNo;

    public static DataCompanyIprTrademarkExWarehouseQueryCommand create(Long companyId,
                                                                        Boolean isApplicant,
                                                                        Boolean isAgent,
                                                                        String regNo,
                                                                        String applyNo) {
        DataCompanyIprTrademarkExWarehouseQueryCommand command = new DataCompanyIprTrademarkExWarehouseQueryCommand();
        command.companyId = companyId;
        command.isApplicant = isApplicant;
        command.isAgent = isAgent;
        command.regNo = regNo;
        command.applyNo = applyNo;
        return command;
    }
}
