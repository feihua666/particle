package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommand;

/**
 * <p>
 * 企业开庭公告内容 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementContentCreateCommand extends AbstractBaseCommand {



    @Schema(description = "企业开庭公告id")
    private Long companyOpenCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;


    public static DataCompanyOpenCourtAnnouncementContentCreateCommand createByWarehouseCommand(DataCompanyOpenCourtAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyOpenCourtAnnouncementContentCreateCommand command = new DataCompanyOpenCourtAnnouncementContentCreateCommand();
        command.companyOpenCourtAnnouncementId = dataCompanyBasicWarehouseCommand.getCompanyOpenCourtAnnouncementId();
        command.content = dataCompanyBasicWarehouseCommand.getContent();


        return command;
    }
}
