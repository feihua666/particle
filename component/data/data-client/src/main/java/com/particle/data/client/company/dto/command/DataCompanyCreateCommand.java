package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 企业 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyCreateCommand extends AbstractBaseCommand {

    @Schema(description = "企业名称")
    private String name;

    @Schema(description = "统一社会信用代码")
    private String uscc;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "组织机构代码")
    private String orgCode;

    @Schema(description = "英文名称")
    private String enName;

    @Schema(description = "父级id")
    private Long parentId;

    public static DataCompanyCreateCommand createByWarehouseCommand(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyCreateCommand command = new DataCompanyCreateCommand();
        command.name = dataCompanyWarehouseCommand.getName();
        command.uscc = dataCompanyWarehouseCommand.getUscc();
        command.regNo = dataCompanyWarehouseCommand.getRegNo();
        command.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        command.enName = dataCompanyWarehouseCommand.getEnName();
        command.parentId = dataCompanyWarehouseCommand.getParentId();

        return command;
    }
}
