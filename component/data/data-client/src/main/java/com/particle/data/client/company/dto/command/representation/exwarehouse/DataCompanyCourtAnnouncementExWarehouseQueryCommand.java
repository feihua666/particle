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
 * 企业法院公告 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业ID 不能为空")
    @Schema(description = "企业ID")
    private Long companyId;

    @Schema(description = "公告号")
    private String announcementNo;

    @Schema(description = "案号")
    private String caseNo;

    public static DataCompanyCourtAnnouncementExWarehouseQueryCommand create(Long companyId,String announcementNo,String caseNo) {
        DataCompanyCourtAnnouncementExWarehouseQueryCommand command = new DataCompanyCourtAnnouncementExWarehouseQueryCommand();
        command.companyId = companyId;
        command.announcementNo = announcementNo;
        command.caseNo = caseNo;
        return command;
    }
}
