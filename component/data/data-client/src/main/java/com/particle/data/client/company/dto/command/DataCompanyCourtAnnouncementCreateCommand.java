package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementWarehouseCommand;

/**
 * <p>
 * 企业法院公告 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementCreateCommand extends AbstractBaseCommand {

    @Schema(description = "公告号")
    private String announcementNo;


    @Schema(description = "公告类型")
    private String announcementType;


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "法院公司id")
    private Long courtCompanyId;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "刊登板面页码")
    private String publishPage;


    @Schema(description = "刊登板面日期")
    private LocalDate publishPageDate;


    @Schema(description = "发布日期")
    private LocalDate publishDate;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyCourtAnnouncementCreateCommand createByWarehouseCommand(DataCompanyCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyCourtAnnouncementCreateCommand command = new DataCompanyCourtAnnouncementCreateCommand();
        command.announcementNo = dataCompanyBasicWarehouseCommand.getAnnouncementNo();
        command.announcementType = dataCompanyBasicWarehouseCommand.getAnnouncementType();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.courtCompanyId = dataCompanyBasicWarehouseCommand.getCourtCompanyId();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.publishPage = dataCompanyBasicWarehouseCommand.getPublishPage();
        command.publishPageDate = dataCompanyBasicWarehouseCommand.getPublishPageDate();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
