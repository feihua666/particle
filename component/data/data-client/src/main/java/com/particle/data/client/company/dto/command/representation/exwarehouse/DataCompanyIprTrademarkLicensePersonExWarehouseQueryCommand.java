package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权商标许可人 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业知识产权商标许可id 不能为空")
    @Schema(description = "企业知识产权商标许可id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkLicenseId;


    public static DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand create(Long companyIprTrademarkLicenseId) {
        DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand command = new DataCompanyIprTrademarkLicensePersonExWarehouseQueryCommand();
        command.companyIprTrademarkLicenseId = companyIprTrademarkLicenseId;
        return command;
    }
}
