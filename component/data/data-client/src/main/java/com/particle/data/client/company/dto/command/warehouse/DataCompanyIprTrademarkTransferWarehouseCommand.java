package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标转让信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标id 不能为空")
        @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


    @Schema(description = "转让期号")
    private String transferIssueNo;


    @Schema(description = "转让页码")
    private String transferPageNo;


    @Schema(description = "转让公告日期")
    private LocalDate transferPublicDate;


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkTransferDataMd5(transferIssueNo);
        }
        return dataMd5;
    }


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkId)
                && StrUtil.isEmpty(transferIssueNo)
                && StrUtil.isEmpty(transferPageNo)
                && Objects.isNull(transferPublicDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkTransferExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprTrademarkId, exWarehouseVO.getCompanyIprTrademarkId())) {
            this.companyIprTrademarkId = null;
        }
        if (Objects.equals(transferIssueNo, exWarehouseVO.getTransferIssueNo())) {
            this.transferIssueNo = null;
        }
        if (Objects.equals(transferPageNo, exWarehouseVO.getTransferPageNo())) {
            this.transferPageNo = null;
        }
        if (Objects.equals(transferPublicDate, exWarehouseVO.getTransferPublicDate())) {
            this.transferPublicDate = null;
        }

    }

    public static DataCompanyIprTrademarkTransferWarehouseCommand createByDataCompanyIprTrademarkTransferExWarehouseVO(DataCompanyIprTrademarkTransferExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkTransferWarehouseCommand command = new DataCompanyIprTrademarkTransferWarehouseCommand();
        command.companyIprTrademarkId = exWarehouseVO.getCompanyIprTrademarkId();
        command.transferIssueNo = exWarehouseVO.getTransferIssueNo();
        command.transferPageNo = exWarehouseVO.getTransferPageNo();
        command.transferPublicDate = exWarehouseVO.getTransferPublicDate();
        return command;
    }
}
