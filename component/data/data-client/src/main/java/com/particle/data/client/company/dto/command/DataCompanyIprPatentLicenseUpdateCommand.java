package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLicenseWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利许可信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Data
@Schema
public class DataCompanyIprPatentLicenseUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "专利权许可类型")
    private String licenseType;


    @Schema(description = "专利备案合同号编码")
    private String filingContractNo;


    @Schema(description = "合同备案日期")
    private LocalDate filingContractDate;


    @Schema(description = "让与人")
    private String assignor;


    @Schema(description = "合同变更日期")
    private LocalDate contractChangeDate;


    @Schema(description = "受让人")
    private String assignee;


    @Schema(description = "合同解除日期")
    private LocalDate contractRescissionDate;

    public static DataCompanyIprPatentLicenseUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentLicenseWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentLicenseUpdateCommand command = new DataCompanyIprPatentLicenseUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.licenseType = dataCompanyBasicWarehouseCommand.getLicenseType();
        command.filingContractNo = dataCompanyBasicWarehouseCommand.getFilingContractNo();
        command.filingContractDate = dataCompanyBasicWarehouseCommand.getFilingContractDate();
        command.assignor = dataCompanyBasicWarehouseCommand.getAssignor();
        command.contractChangeDate = dataCompanyBasicWarehouseCommand.getContractChangeDate();
        command.assignee = dataCompanyBasicWarehouseCommand.getAssignee();
        command.contractRescissionDate = dataCompanyBasicWarehouseCommand.getContractRescissionDate();

        return command;
    }
}
