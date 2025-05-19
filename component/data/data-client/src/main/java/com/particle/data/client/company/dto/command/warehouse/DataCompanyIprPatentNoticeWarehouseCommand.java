package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业知识产权专利通知书信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeWarehouseCommand extends AbstractBaseCommand {



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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentNoticeDataMd5(publicDate,mailNo,receiverName,noticeType,noticeTypeDescription);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(mailNo)
                && StrUtil.isEmpty(receiverName)
                && StrUtil.isEmpty(noticeType)
                && StrUtil.isEmpty(noticeTypeDescription);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentNoticeExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(mailNo, exWarehouseVO.getMailNo())) {
            this.mailNo = null;
        }
        if (Objects.equals(receiverName, exWarehouseVO.getReceiverName())) {
            this.receiverName = null;
        }
        if (Objects.equals(noticeType, exWarehouseVO.getNoticeType())) {
            this.noticeType = null;
        }
        if (Objects.equals(noticeTypeDescription, exWarehouseVO.getNoticeTypeDescription())) {
            this.noticeTypeDescription = null;
        }
    }
    public static DataCompanyIprPatentNoticeWarehouseCommand createByDataCompanyIprPatentNoticeExWarehouseVO(DataCompanyIprPatentNoticeExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentNoticeWarehouseCommand command = new DataCompanyIprPatentNoticeWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.publicDate = exWarehouseVO.getPublicDate();
        command.mailNo = exWarehouseVO.getMailNo();
        command.receiverName = exWarehouseVO.getReceiverName();
        command.noticeType = exWarehouseVO.getNoticeType();
        command.noticeTypeDescription = exWarehouseVO.getNoticeTypeDescription();

        return command;
    }
}
