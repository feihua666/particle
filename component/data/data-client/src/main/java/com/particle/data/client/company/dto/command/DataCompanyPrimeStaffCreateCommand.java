package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffWarehouseCommand;

/**
 * <p>
 * 企业主要人员 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Data
@Schema
public class DataCompanyPrimeStaffCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "姓名")
    private String staffName;


    @Schema(description = "个人表ID")
    private Long staffCompanyPersonId;


    @Schema(description = "职位名称")
    private String positionNames;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;


    public static DataCompanyPrimeStaffCreateCommand createByWarehouseCommand(DataCompanyPrimeStaffWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyPrimeStaffCreateCommand command = new DataCompanyPrimeStaffCreateCommand();
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.staffName = dataCompanyBasicWarehouseCommand.getStaffName();
        command.staffCompanyPersonId = dataCompanyBasicWarehouseCommand.getStaffCompanyPersonId();
        command.positionNames = dataCompanyBasicWarehouseCommand.getPositionNames();

        return command;
    }
}
