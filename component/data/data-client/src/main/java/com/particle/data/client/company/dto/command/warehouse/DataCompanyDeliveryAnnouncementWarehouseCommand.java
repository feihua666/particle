package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业送达公告入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementWarehouseCommand extends AbstractBaseCommand {



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


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyDeliveryAnnouncementDataMd5(caseNo,caseReason,title,courtName,publishDate);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(caseNo)
                && StrUtil.isEmpty(caseReason)
                && StrUtil.isEmpty(title)
                && StrUtil.isEmpty(courtName)
                && Objects.isNull(publishDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyDeliveryAnnouncementExWarehouseVO exWarehouseVO) {
        if (Objects.equals(caseNo, exWarehouseVO.getCaseNo())) {
            this.caseNo = null;
        }
        if (Objects.equals(caseReason, exWarehouseVO.getCaseReason())) {
            this.caseReason = null;
        }
        if (Objects.equals(title, exWarehouseVO.getTitle())) {
            this.title = null;
        }
        if (Objects.equals(courtName, exWarehouseVO.getCourtName())) {
            this.courtName = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }

    }

    public static DataCompanyDeliveryAnnouncementWarehouseCommand createByDataCompanyDeliveryAnnouncementExWarehouseVO(DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyDeliveryAnnouncementWarehouseCommand command = new DataCompanyDeliveryAnnouncementWarehouseCommand();
        command.caseNo = dataCompanyBasicWarehouseCommand.getCaseNo();
        command.caseReason = dataCompanyBasicWarehouseCommand.getCaseReason();
        command.title = dataCompanyBasicWarehouseCommand.getTitle();
        command.courtName = dataCompanyBasicWarehouseCommand.getCourtName();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();


        return command;
    }
}
