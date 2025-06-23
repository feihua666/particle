package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业送达公告当事人 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业ID 不能为空")
    @Schema(description = "企业ID")
    private Long companyId;

    public static DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand create(Long companyId) {
        DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand command = new DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand();
        command.companyId = companyId;
        return command;
    }
}
