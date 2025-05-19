package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业知识产权专利缴费信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentPaymentWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "费用金额(元)")
    private BigDecimal feeAmount;


    @Schema(description = "费用种类")
    private String feeType;


    @Schema(description = "收据号")
    private String receiptNo;


    @Schema(description = "缴费人信息")
    private String payer;


    @Schema(description = "处理状态")
    private String handleStatus;


    @Schema(description = "缴费日期")
    private LocalDate payDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentPaymentDataMd5(feeType,receiptNo,payer,handleStatus,payDate);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && Objects.isNull(feeAmount)
                && StrUtil.isEmpty(feeType)
                && StrUtil.isEmpty(receiptNo)
                && StrUtil.isEmpty(payer)
                && StrUtil.isEmpty(handleStatus)
                && Objects.isNull(payDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentPaymentExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (NumberUtil.equals(feeAmount, exWarehouseVO.getFeeAmount())) {
            this.feeAmount = null;
        }
        if (Objects.equals(feeType, exWarehouseVO.getFeeType())) {
            this.feeType = null;
        }
        if (Objects.equals(receiptNo, exWarehouseVO.getReceiptNo())) {
            this.receiptNo = null;
        }
        if (Objects.equals(payer, exWarehouseVO.getPayer())) {
            this.payer = null;
        }
        if (Objects.equals(handleStatus, exWarehouseVO.getHandleStatus())) {
            this.handleStatus = null;
        }
        if (Objects.equals(payDate, exWarehouseVO.getPayDate())) {
            this.payDate = null;
        }

    }
    public static DataCompanyIprPatentPaymentWarehouseCommand createByDataCompanyIprPatentPaymentExWarehouseVO(DataCompanyIprPatentPaymentExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentPaymentWarehouseCommand command = new DataCompanyIprPatentPaymentWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.feeAmount = exWarehouseVO.getFeeAmount();
        command.feeType = exWarehouseVO.getFeeType();
        command.receiptNo = exWarehouseVO.getReceiptNo();
        command.payer = exWarehouseVO.getPayer();
        command.handleStatus = exWarehouseVO.getHandleStatus();
        command.payDate = exWarehouseVO.getPayDate();

        return command;
    }
}
