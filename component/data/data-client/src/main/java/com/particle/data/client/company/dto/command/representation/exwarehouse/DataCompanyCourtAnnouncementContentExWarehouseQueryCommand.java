package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业法院公告内容 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementContentExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @NotNull(message = "法院公告id 不能为空")
    @Schema(description = "法院公告id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyCourtAnnouncementId;

    public static DataCompanyCourtAnnouncementContentExWarehouseQueryCommand create(Long companyCourtAnnouncementId) {
        DataCompanyCourtAnnouncementContentExWarehouseQueryCommand command = new DataCompanyCourtAnnouncementContentExWarehouseQueryCommand();
        command.companyCourtAnnouncementId = companyCourtAnnouncementId;
        return command;
    }
}
