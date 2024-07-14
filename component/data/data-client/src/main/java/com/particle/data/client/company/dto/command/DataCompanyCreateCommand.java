package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

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



    @NotEmpty(message = "企业名称 不能为空")
        @Schema(description = "企业名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "统一社会信用代码 不能为空")
        @Schema(description = "统一社会信用代码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uscc;


    @Schema(description = "注册号")
    private String regNo;


    @Schema(description = "组织机构代码")
    private String orgCode;


    @Schema(description = "英文名称")
    private String enName;


    @Schema(description = "父级id")
    private Long parentId;


    @Schema(description = "最后更新时间",hidden = true)
    private LocalDateTime latestUpdateAt;


    public static DataCompanyCreateCommand createByWarehouseCommand(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyCreateCommand dataCompanyCreateCommand = new DataCompanyCreateCommand();
        dataCompanyCreateCommand.name = dataCompanyWarehouseCommand.getName();
        dataCompanyCreateCommand.uscc = dataCompanyWarehouseCommand.getUscc();
        dataCompanyCreateCommand.regNo = dataCompanyWarehouseCommand.getRegNo();
        dataCompanyCreateCommand.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        dataCompanyCreateCommand.enName = dataCompanyWarehouseCommand.getEnName();
        dataCompanyCreateCommand.parentId = dataCompanyWarehouseCommand.getParentId();
        return dataCompanyCreateCommand;
    }
}
