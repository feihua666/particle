package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommand;

/**
 * <p>
 * 企业送达公告内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementContentUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "企业送达公告id")
    private Long companyDeliveryAnnouncementId;


    @Schema(description = "公告内容")
    private String content;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    









    public static DataCompanyDeliveryAnnouncementContentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyDeliveryAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyDeliveryAnnouncementContentUpdateCommand command = new DataCompanyDeliveryAnnouncementContentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyDeliveryAnnouncementId = dataCompanyBasicWarehouseCommand.getCompanyDeliveryAnnouncementId();
        command.content = dataCompanyBasicWarehouseCommand.getContent();


        return command;
    }
}