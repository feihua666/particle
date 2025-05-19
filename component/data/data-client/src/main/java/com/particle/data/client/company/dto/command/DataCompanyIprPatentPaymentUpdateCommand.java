package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPaymentWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利缴费信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Data
@Schema
public class DataCompanyIprPatentPaymentUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
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

    public static DataCompanyIprPatentPaymentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentPaymentWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentPaymentUpdateCommand command = new DataCompanyIprPatentPaymentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.feeAmount = dataCompanyBasicWarehouseCommand.getFeeAmount();
        command.feeType = dataCompanyBasicWarehouseCommand.getFeeType();
        command.receiptNo = dataCompanyBasicWarehouseCommand.getReceiptNo();
        command.payer = dataCompanyBasicWarehouseCommand.getPayer();
        command.handleStatus = dataCompanyBasicWarehouseCommand.getHandleStatus();
        command.payDate = dataCompanyBasicWarehouseCommand.getPayDate();

        return command;
    }
}
