package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentNoticeWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利通知书信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "通知发文日期")
    private LocalDate publicDate;


    @Schema(description = "通知挂号")
    private String mailNo;


    @Schema(description = "通知收件人姓名")
    private String receiverName;


    @Schema(description = "通知书类型")
    private String noticeType;


    @Schema(description = "通知书类型说明")
    private String noticeTypeDescription;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyIprPatentNoticeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentNoticeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentNoticeUpdateCommand command = new DataCompanyIprPatentNoticeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.mailNo = dataCompanyBasicWarehouseCommand.getMailNo();
        command.receiverName = dataCompanyBasicWarehouseCommand.getReceiverName();
        command.noticeType = dataCompanyBasicWarehouseCommand.getNoticeType();
        command.noticeTypeDescription = dataCompanyBasicWarehouseCommand.getNoticeTypeDescription();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
