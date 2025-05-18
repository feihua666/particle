package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentLicenseWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利许可信息 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Data
@Schema
public class DataCompanyIprPatentLicenseCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
        @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static DataCompanyIprPatentLicenseCreateCommand createByWarehouseCommand(DataCompanyIprPatentLicenseWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentLicenseCreateCommand command = new DataCompanyIprPatentLicenseCreateCommand();
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
