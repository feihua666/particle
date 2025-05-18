package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentTransferWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 企业知识产权专利转让信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Data
@Schema
public class DataCompanyIprPatentTransferCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "转移类型")
    private String transferType;


    @Schema(description = "部门")
    private String dept;


    @Schema(description = "变更前权利人")
    private String changeBeforeRightHolder;


    @Schema(description = "变更前地址")
    private String changeBeforeAddress;


    @Schema(description = "变更后权利人")
    private String changeAfterRightHolder;


    @Schema(description = "变更后地址")
    private String changeAfterAddress;


    @Schema(description = "当前权利人")
    private String currentRightHolder;


    @Schema(description = "当前地址")
    private String currentAddress;


    @Schema(description = "变更生效日期")
    private LocalDate changeEffectiveDate;


    public static DataCompanyIprPatentTransferCreateCommand createByWarehouseCommand(DataCompanyIprPatentTransferWarehouseCommand dataCompanyIprPatentTransferWarehouseCommand){
        DataCompanyIprPatentTransferCreateCommand command = new DataCompanyIprPatentTransferCreateCommand();
        command.companyIprPatentId = dataCompanyIprPatentTransferWarehouseCommand.getCompanyIprPatentId();
        command.transferType = dataCompanyIprPatentTransferWarehouseCommand.getTransferType();
        command.dept = dataCompanyIprPatentTransferWarehouseCommand.getDept();
        command.changeBeforeRightHolder = dataCompanyIprPatentTransferWarehouseCommand.getChangeBeforeRightHolder();
        command.changeBeforeAddress = dataCompanyIprPatentTransferWarehouseCommand.getChangeBeforeAddress();
        command.changeAfterRightHolder = dataCompanyIprPatentTransferWarehouseCommand.getChangeAfterRightHolder();
        command.changeAfterAddress = dataCompanyIprPatentTransferWarehouseCommand.getChangeAfterAddress();
        command.currentRightHolder = dataCompanyIprPatentTransferWarehouseCommand.getCurrentRightHolder();
        command.currentAddress = dataCompanyIprPatentTransferWarehouseCommand.getCurrentAddress();
        command.changeEffectiveDate = dataCompanyIprPatentTransferWarehouseCommand.getChangeEffectiveDate();

        return command;
    }

}
