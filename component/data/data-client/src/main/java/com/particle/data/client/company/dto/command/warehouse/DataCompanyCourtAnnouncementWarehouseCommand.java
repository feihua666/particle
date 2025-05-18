package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业法院公告入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementWarehouseCommand extends AbstractBaseCommand {

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

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyCourtAnnouncementDataMd5(announcementNo, announcementType, caseNo, caseReason, publishDate);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(announcementNo)
                && StrUtil.isEmpty(announcementType)
                && StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(caseReason)
                && Objects.isNull(courtCompanyId)
                && StrUtil.isEmpty(courtName)
                && StrUtil.isEmpty(publishPage)
                && Objects.isNull(publishPageDate)
                && Objects.isNull(publishDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyCourtAnnouncementExWarehouseVO exWarehouseVO) {
        if (Objects.equals(announcementNo, exWarehouseVO.getAnnouncementNo())) {
            this.announcementNo = null;
        }
        if (Objects.equals(announcementType, exWarehouseVO.getAnnouncementType())) {
            this.announcementType = null;
        }
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(caseReason, exWarehouseVO.getCaseReason())) {
            this.caseReason = null;
        }
        if (Objects.equals(courtCompanyId, exWarehouseVO.getCourtCompanyId())) {
            this.courtCompanyId = null;
        }
        if (Objects.equals(courtName, exWarehouseVO.getCourtName())) {
            this.courtName = null;
        }
        if (Objects.equals(publishPage, exWarehouseVO.getPublishPage())) {
            this.publishPage = null;
        }
        if (Objects.equals(publishPageDate, exWarehouseVO.getPublishPageDate())) {
            this.publishPageDate = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
    }

    public static DataCompanyCourtAnnouncementWarehouseCommand createByDataCompanyCourtAnnouncementExWarehouseVO(DataCompanyCourtAnnouncementExWarehouseVO exWarehouseVO) {
        DataCompanyCourtAnnouncementWarehouseCommand command = new DataCompanyCourtAnnouncementWarehouseCommand();
        command.announcementNo = exWarehouseVO.getAnnouncementNo();
        command.announcementType = exWarehouseVO.getAnnouncementType();
        command.caseNo = exWarehouseVO.getCaseNo();
        command.caseReason = exWarehouseVO.getCaseReason();
        command.courtCompanyId = exWarehouseVO.getCourtCompanyId();
        command.courtName = exWarehouseVO.getCourtName();
        command.publishPage = exWarehouseVO.getPublishPage();
        command.publishPageDate = exWarehouseVO.getPublishPageDate();
        command.publishDate = exWarehouseVO.getPublishDate();

        return command;
    }
}
