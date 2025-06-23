package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业送达公告内容入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementContentWarehouseCommand extends AbstractBaseCommand {

    @Schema(description = "企业送达公告id")
    private Long companyDeliveryAnnouncementId;

    @Schema(description = "公告内容")
    private String content;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyDeliveryAnnouncementId)
                && StrUtil.isEmpty(content)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyDeliveryAnnouncementContentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyDeliveryAnnouncementId, exWarehouseVO.getCompanyDeliveryAnnouncementId())) {
            this.companyDeliveryAnnouncementId = null;
        }
        if (Objects.equals(content, exWarehouseVO.getContent())) {
            this.content = null;
        }
    }
    public static DataCompanyDeliveryAnnouncementContentWarehouseCommand createByDataCompanyDeliveryAnnouncementContentExWarehouseVO(DataCompanyDeliveryAnnouncementContentExWarehouseVO exWarehouseVO) {
        DataCompanyDeliveryAnnouncementContentWarehouseCommand command = new DataCompanyDeliveryAnnouncementContentWarehouseCommand();
        command.companyDeliveryAnnouncementId = exWarehouseVO.getCompanyDeliveryAnnouncementId();
        command.content = exWarehouseVO.getContent();

        return command;

    }
}
