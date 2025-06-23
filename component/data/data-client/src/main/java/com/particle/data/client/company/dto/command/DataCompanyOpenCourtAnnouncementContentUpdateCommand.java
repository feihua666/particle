package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommand;

/**
 * <p>
 * 企业开庭公告内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementContentUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "企业开庭公告id")
    private Long companyOpenCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyOpenCourtAnnouncementContentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyOpenCourtAnnouncementContentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyOpenCourtAnnouncementContentUpdateCommand command = new DataCompanyOpenCourtAnnouncementContentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyOpenCourtAnnouncementId = dataCompanyBasicWarehouseCommand.getCompanyOpenCourtAnnouncementId();
        command.content = dataCompanyBasicWarehouseCommand.getContent();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
