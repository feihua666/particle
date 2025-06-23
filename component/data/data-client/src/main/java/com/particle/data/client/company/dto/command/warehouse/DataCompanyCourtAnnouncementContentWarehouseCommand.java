package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业法院公告内容入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementContentWarehouseCommand extends AbstractBaseCommand {


    @Schema(description = "法院公告表id")
    private Long companyCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyCourtAnnouncementContentDataMd5(content);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyCourtAnnouncementId)
                && StrUtil.isEmpty(content);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyCourtAnnouncementContentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyCourtAnnouncementId, exWarehouseVO.getCompanyCourtAnnouncementId())) {
            this.companyCourtAnnouncementId = null;
        }
        if (Objects.equals(content, exWarehouseVO.getContent())) {
            this.content = null;
        }
    }

    public static DataCompanyCourtAnnouncementContentWarehouseCommand createByDataCompanyCourtAnnouncementContentExWarehouseVO(DataCompanyCourtAnnouncementContentExWarehouseVO exWarehouseVO) {
        DataCompanyCourtAnnouncementContentWarehouseCommand command = new DataCompanyCourtAnnouncementContentWarehouseCommand();
        command.companyCourtAnnouncementId = exWarehouseVO.getCompanyCourtAnnouncementId();
        command.content = exWarehouseVO.getContent();

        return command;

    }
}
