package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业开庭公告内容入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementContentWarehouseCommand extends AbstractBaseCommand {



    @Schema(description = "企业开庭公告id")
    private Long companyOpenCourtAnnouncementId;


    @Schema(description = "公告内容")
    private String content;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyOpenCourtAnnouncementContentDataMd5(content);
        }
        return dataMd5;
    }


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyOpenCourtAnnouncementId)
                && StrUtil.isEmpty(content);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyOpenCourtAnnouncementContentExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyOpenCourtAnnouncementId, exWarehouseVO.getCompanyOpenCourtAnnouncementId())) {
            this.companyOpenCourtAnnouncementId = null;
        }
        if (Objects.equals(content, exWarehouseVO.getContent())) {
            this.content = null;
        }
    }

    public static DataCompanyOpenCourtAnnouncementContentWarehouseCommand createByDataCompanyOpenCourtAnnouncementContentExWarehouseVO(DataCompanyOpenCourtAnnouncementContentExWarehouseVO exWarehouseVO) {
        DataCompanyOpenCourtAnnouncementContentWarehouseCommand command = new DataCompanyOpenCourtAnnouncementContentWarehouseCommand();
        command.companyOpenCourtAnnouncementId = exWarehouseVO.getCompanyOpenCourtAnnouncementId();
        command.content = exWarehouseVO.getContent();

        return command;
    }
}
