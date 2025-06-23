package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommand;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Data
@Schema
public class DataCompanyIprPlantVarietyChangeUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "企业知识产权植物新品种id")
    private Long companyIprPlantVarietyId;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    @Schema(description = "变更前")
    private String changeBefore;


    @Schema(description = "变更后")
    private String changeAfter;


    @Schema(description = "原因")
    private String changeReason;


    @Schema(description = "是否名称变更")
    private Boolean isName;


    @Schema(description = "是否转让变更")
    private Boolean isTransfer;


    @Schema(description = "是否培育人变更")
    private Boolean isCultivate;


    @Schema(description = "是否申请人变更")
    private Boolean isApplicant;


    @Schema(description = "是否代理人变更")
    private Boolean isAgent;


    @Schema(description = "是否代理机构变更")
    private Boolean isAgency;


    @Schema(description = "数据md5")
    private String dataMd5;


    public static DataCompanyIprPlantVarietyChangeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPlantVarietyChangeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPlantVarietyChangeUpdateCommand command = new DataCompanyIprPlantVarietyChangeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprPlantVarietyId = dataCompanyBasicWarehouseCommand.getCompanyIprPlantVarietyId();
        command.changeDate = dataCompanyBasicWarehouseCommand.getChangeDate();
        command.changeBefore = dataCompanyBasicWarehouseCommand.getChangeBefore();
        command.changeAfter = dataCompanyBasicWarehouseCommand.getChangeAfter();
        command.changeReason = dataCompanyBasicWarehouseCommand.getChangeReason();
        command.isName = dataCompanyBasicWarehouseCommand.getIsName();
        command.isTransfer = dataCompanyBasicWarehouseCommand.getIsTransfer();
        command.isCultivate = dataCompanyBasicWarehouseCommand.getIsCultivate();
        command.isApplicant = dataCompanyBasicWarehouseCommand.getIsApplicant();
        command.isAgent = dataCompanyBasicWarehouseCommand.getIsAgent();
        command.isAgency = dataCompanyBasicWarehouseCommand.getIsAgency();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
