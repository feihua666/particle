package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;

/**
 * <p>
 * 企业法院公告内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementContentUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "法院公告表id")
    private Long companyCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyCourtAnnouncementContentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyCourtAnnouncementContentWarehouseCommand dataCompanyCourtAnnouncementContentWarehouseCommand){
        DataCompanyCourtAnnouncementContentUpdateCommand command = new DataCompanyCourtAnnouncementContentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyCourtAnnouncementId = dataCompanyCourtAnnouncementContentWarehouseCommand.getCompanyCourtAnnouncementId();
        command.content = dataCompanyCourtAnnouncementContentWarehouseCommand.getContent();
        command.dataMd5 = dataCompanyCourtAnnouncementContentWarehouseCommand.obtainDataMd5();

        return command;
    }
}
