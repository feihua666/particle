package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标转让人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferPersonWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标转让id 不能为空")
    @Schema(description = "企业知识产权商标转让id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkTransferId;


    @Schema(description = "原始转让人名称")
    private String transferName;


    @Schema(description = "中文转让人名称")
    private String transferNameCn;


    @Schema(description = "英文转让人名称")
    private String transferNameEn;


    @Schema(description = "是否被转让人")
    private Boolean isTransferred;


    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkTransferPersonDataMd5(transferName,isTransferred);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkTransferId)
                && StrUtil.isEmpty(transferName)
                && StrUtil.isEmpty(transferNameCn)
                && StrUtil.isEmpty(transferNameEn)
                && Objects.isNull(isTransferred)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkTransferPersonExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyIprTrademarkTransferId, exWarehouseVO.getCompanyIprTrademarkTransferId())) {
            this.companyIprTrademarkTransferId = null;
        }
        if (Objects.equals(transferName, exWarehouseVO.getTransferName())) {
            this.transferName = null;
        }
        if (Objects.equals(transferNameCn, exWarehouseVO.getTransferNameCn())) {
            this.transferNameCn = null;
        }
        if (Objects.equals(transferNameEn, exWarehouseVO.getTransferNameEn())) {
            this.transferNameEn = null;
        }
        if (Objects.equals(isTransferred, exWarehouseVO.getIsTransferred())) {
            this.isTransferred = null;
        }

    }

    public static DataCompanyIprTrademarkTransferPersonWarehouseCommand createByDataCompanyIprTrademarkTransferPersonExWarehouseVO(DataCompanyIprTrademarkTransferPersonExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkTransferPersonWarehouseCommand command = new DataCompanyIprTrademarkTransferPersonWarehouseCommand();
        command.companyIprTrademarkTransferId = exWarehouseVO.getCompanyIprTrademarkTransferId();
        command.transferName = exWarehouseVO.getTransferName();
        command.transferNameCn = exWarehouseVO.getTransferNameCn();
        command.transferNameEn = exWarehouseVO.getTransferNameEn();
        command.isTransferred = exWarehouseVO.getIsTransferred();
        return command;
    }

}
