package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentStatisticWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利统计 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "同族专利数量")
    private Integer familyNum;


    @Schema(description = "扩展同族专利数量")
    private Integer extFamilyNum;


    @Schema(description = "被引证数量")
    private Integer citedNum;


    @Schema(description = "引证专利数量")
    private Integer quoteNum;


    @Schema(description = "权利要求数量")
    private Integer claimNum;


    @Schema(description = "独权数")
    private Integer independentClaimNum;


    @Schema(description = "从权数")
    private Integer dependentClaimNum;


    @Schema(description = "转让次数")
    private Integer transferNum;


    @Schema(description = "许可次数")
    private Integer licenseNum;


    @Schema(description = "质押次数")
    private Integer pledgeNum;


    @Schema(description = "无效次数")
    private Integer invalidNum;


    @Schema(description = "诉讼次数")
    private Integer litigationNum;


    @Schema(description = "IPC分类数量")
    private Integer ipcCategoryNum;



    public static DataCompanyIprPatentStatisticUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand){
        DataCompanyIprPatentStatisticUpdateCommand command = new DataCompanyIprPatentStatisticUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprPatentId = dataCompanyIprPatentStatisticWarehouseCommand.getCompanyIprPatentId();
        command.familyNum = dataCompanyIprPatentStatisticWarehouseCommand.getFamilyNum();
        command.extFamilyNum = dataCompanyIprPatentStatisticWarehouseCommand.getExtFamilyNum();
        command.citedNum = dataCompanyIprPatentStatisticWarehouseCommand.getCitedNum();
        command.quoteNum = dataCompanyIprPatentStatisticWarehouseCommand.getQuoteNum();
        command.claimNum = dataCompanyIprPatentStatisticWarehouseCommand.getClaimNum();
        command.independentClaimNum = dataCompanyIprPatentStatisticWarehouseCommand.getIndependentClaimNum();
        command.dependentClaimNum = dataCompanyIprPatentStatisticWarehouseCommand.getDependentClaimNum();
        command.transferNum = dataCompanyIprPatentStatisticWarehouseCommand.getTransferNum();
        command.licenseNum = dataCompanyIprPatentStatisticWarehouseCommand.getLicenseNum();
        command.pledgeNum = dataCompanyIprPatentStatisticWarehouseCommand.getPledgeNum();
        command.invalidNum = dataCompanyIprPatentStatisticWarehouseCommand.getInvalidNum();
        command.litigationNum = dataCompanyIprPatentStatisticWarehouseCommand.getLitigationNum();
        command.ipcCategoryNum = dataCompanyIprPatentStatisticWarehouseCommand.getIpcCategoryNum();


        return command;
    }
}
