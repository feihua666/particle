package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommand;

/**
 * <p>
 * 企业开庭公告 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementUpdateCommand extends AbstractBaseUpdateCommand {

    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "法庭")
    private String courtRoom;


    @Schema(description = "承办部门")
    private String undertakeDept;


    @Schema(description = "开庭日期")
    private LocalDate openDate;


    @Schema(description = "排期日期")
    private LocalDate planDate;


    @Schema(description = "审判长/主审人")
    private String presidingJudge;


    @Schema(description = "观看链接/视频链接")
    private String videoUrl;


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyOpenCourtAnnouncementUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyOpenCourtAnnouncementWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyOpenCourtAnnouncementUpdateCommand command = new DataCompanyOpenCourtAnnouncementUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.courtRoom = dataCompanyBasicWarehouseCommand.getCourtRoom();
        command.undertakeDept = dataCompanyBasicWarehouseCommand.getUndertakeDept();
        command.openDate = dataCompanyBasicWarehouseCommand.getOpenDate();
        command.planDate = dataCompanyBasicWarehouseCommand.getPlanDate();
        command.presidingJudge = dataCompanyBasicWarehouseCommand.getPresidingJudge();
        command.videoUrl = dataCompanyBasicWarehouseCommand.getVideoUrl();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
