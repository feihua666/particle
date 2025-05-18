package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业开庭公告入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementWarehouseCommand extends AbstractBaseCommand {


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

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyOpenCourtAnnouncementDataMd5(caseNo, caseReason, openDate);
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(caseReason)
                && StrUtil.isEmpty(courtName)
                && StrUtil.isEmpty(courtRoom)
                && StrUtil.isEmpty(undertakeDept)
                && Objects.isNull(openDate)
                && Objects.isNull(planDate)
                && StrUtil.isEmpty(presidingJudge)
                && StrUtil.isEmpty(videoUrl);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyOpenCourtAnnouncementExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(caseReason, exWarehouseVO.getCaseReason())) {
            this.caseReason = null;
        }
        if (Objects.equals(courtName, exWarehouseVO.getCourtName())) {
            this.courtName = null;
        }
        if (Objects.equals(courtRoom, exWarehouseVO.getCourtRoom())) {
            this.courtRoom = null;
        }
        if (Objects.equals(undertakeDept, exWarehouseVO.getUndertakeDept())) {
            this.undertakeDept = null;
        }
        if (Objects.equals(openDate, exWarehouseVO.getOpenDate())) {
            this.openDate = null;
        }
        if (Objects.equals(planDate, exWarehouseVO.getPlanDate())) {
            this.planDate = null;
        }
        if (Objects.equals(presidingJudge, exWarehouseVO.getPresidingJudge())) {
            this.presidingJudge = null;
        }
        if (Objects.equals(videoUrl, exWarehouseVO.getVideoUrl())) {
            this.videoUrl = null;
        }

    }

    public static DataCompanyOpenCourtAnnouncementWarehouseCommand createByDataCompanyOpenCourtAnnouncementExWarehouseVO(DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyOpenCourtAnnouncementWarehouseCommand command = new DataCompanyOpenCourtAnnouncementWarehouseCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.courtRoom = dataCompanyBasicWarehouseCommand.getCourtRoom();
        command.undertakeDept = dataCompanyBasicWarehouseCommand.getUndertakeDept();
        command.openDate = dataCompanyBasicWarehouseCommand.getOpenDate();
        command.planDate = dataCompanyBasicWarehouseCommand.getPlanDate();
        command.presidingJudge = dataCompanyBasicWarehouseCommand.getPresidingJudge();
        command.videoUrl = dataCompanyBasicWarehouseCommand.getVideoUrl();


        return command;
    }
}
