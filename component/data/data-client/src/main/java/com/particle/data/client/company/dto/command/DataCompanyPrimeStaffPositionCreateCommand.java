package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffPositionWarehouseCommand;

/**
 * <p>
 * 企业主要人员职位 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Data
@Schema
public class DataCompanyPrimeStaffPositionCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业主要人员表ID 不能为空")
        @Schema(description = "企业主要人员表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyPrimeStaffId;


    @Schema(description = "职位名称")
    private String positionName;


    @Schema(description = "职位")
    private Long positionDictId;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    









    public static DataCompanyPrimeStaffPositionCreateCommand createByWarehouseCommand(DataCompanyPrimeStaffPositionWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyPrimeStaffPositionCreateCommand command = new DataCompanyPrimeStaffPositionCreateCommand();
                command.companyPrimeStaffId = dataCompanyBasicWarehouseCommand.getCompanyPrimeStaffId();
        command.positionName = dataCompanyBasicWarehouseCommand.getPositionName();
        command.positionDictId = dataCompanyBasicWarehouseCommand.getPositionDictId();


        return command;
    }
}