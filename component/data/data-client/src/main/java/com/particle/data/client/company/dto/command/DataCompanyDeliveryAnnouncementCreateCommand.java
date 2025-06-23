package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;

/**
 * <p>
 * 企业送达公告 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementCreateCommand extends AbstractBaseCommand {



    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "公告标题")
    private String title;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "发布日期")
    private LocalDate publishDate;

	@Schema(description = "数据md5,case_no + case_reason + title + court_name + publish_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    









    public static DataCompanyDeliveryAnnouncementCreateCommand createByWarehouseCommand(DataCompanyDeliveryAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyDeliveryAnnouncementCreateCommand command = new DataCompanyDeliveryAnnouncementCreateCommand();
                command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.title = dataCompanyBasicWarehouseCommand.getTitle();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();


        return command;
    }
}