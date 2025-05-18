package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;

/**
 * <p>
 * 企业法院公告内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementContentCreateCommand extends AbstractBaseCommand {



    @Schema(description = "法院公告表id")
    private Long companyCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;



    public static DataCompanyCourtAnnouncementContentCreateCommand createByWarehouseCommand(DataCompanyCourtAnnouncementContentWarehouseCommand dataCompanyCourtAnnouncementContentWarehouseCommand){
        DataCompanyCourtAnnouncementContentCreateCommand command = new DataCompanyCourtAnnouncementContentCreateCommand();
        command.companyCourtAnnouncementId = dataCompanyCourtAnnouncementContentWarehouseCommand.getCompanyCourtAnnouncementId();
        command.content = dataCompanyCourtAnnouncementContentWarehouseCommand.getContent();


        return command;
    }
}
