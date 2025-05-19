package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPledgeWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利质押信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Data
@Schema
public class DataCompanyIprPatentPledgeUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "质押登记号")
    private String pledgeNo;


    @Schema(description = "质押保全类型")
    private String pledgePreserveType;


    @Schema(description = "质押保全权力类型")
    private String pledgePreserveRightType;


    @Schema(description = "生效日期")
    private LocalDate effectiveDate;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "解除日期")
    private LocalDate rescissionDate;



    public static DataCompanyIprPatentPledgeUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentPledgeWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentPledgeUpdateCommand command = new DataCompanyIprPatentPledgeUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.pledgeNo = dataCompanyBasicWarehouseCommand.getPledgeNo();
        command.pledgePreserveType = dataCompanyBasicWarehouseCommand.getPledgePreserveType();
        command.pledgePreserveRightType = dataCompanyBasicWarehouseCommand.getPledgePreserveRightType();
        command.effectiveDate = dataCompanyBasicWarehouseCommand.getEffectiveDate();
        command.changeDate = dataCompanyBasicWarehouseCommand.getChangeDate();
        command.pledgor = dataCompanyBasicWarehouseCommand.getPledgor();
        command.pledgee = dataCompanyBasicWarehouseCommand.getPledgee();
        command.rescissionDate = dataCompanyBasicWarehouseCommand.getRescissionDate();


        return command;
    }
}
