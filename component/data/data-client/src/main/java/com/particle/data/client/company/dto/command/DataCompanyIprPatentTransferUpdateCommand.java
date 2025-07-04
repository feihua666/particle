package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentTransferWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 * 企业知识产权专利转让信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Data
@Schema
public class DataCompanyIprPatentTransferUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
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

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyIprPatentTransferUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentTransferWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentTransferUpdateCommand command = new DataCompanyIprPatentTransferUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.transferType = dataCompanyBasicWarehouseCommand.getTransferType();
        command.dept = dataCompanyBasicWarehouseCommand.getDept();
        command.changeBeforeRightHolder = dataCompanyBasicWarehouseCommand.getChangeBeforeRightHolder();
        command.changeBeforeAddress = dataCompanyBasicWarehouseCommand.getChangeBeforeAddress();
        command.changeAfterRightHolder = dataCompanyBasicWarehouseCommand.getChangeAfterRightHolder();
        command.changeAfterAddress = dataCompanyBasicWarehouseCommand.getChangeAfterAddress();
        command.currentRightHolder = dataCompanyBasicWarehouseCommand.getCurrentRightHolder();
        command.currentAddress = dataCompanyBasicWarehouseCommand.getCurrentAddress();
        command.changeEffectiveDate = dataCompanyBasicWarehouseCommand.getChangeEffectiveDate();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
