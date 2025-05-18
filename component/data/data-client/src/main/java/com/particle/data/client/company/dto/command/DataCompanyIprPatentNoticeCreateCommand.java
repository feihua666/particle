package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentNoticeWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利通知书信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
        @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static DataCompanyIprPatentNoticeCreateCommand createByWarehouseCommand(DataCompanyIprPatentNoticeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentNoticeCreateCommand command = new DataCompanyIprPatentNoticeCreateCommand();
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.mailNo = dataCompanyBasicWarehouseCommand.getMailNo();
        command.receiverName = dataCompanyBasicWarehouseCommand.getReceiverName();
        command.noticeType = dataCompanyBasicWarehouseCommand.getNoticeType();
        command.noticeTypeDescription = dataCompanyBasicWarehouseCommand.getNoticeTypeDescription();

        return command;
    }
}
