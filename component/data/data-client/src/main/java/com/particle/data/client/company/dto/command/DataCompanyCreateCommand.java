package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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

	@Schema(description = "分类，1=企业，2=个体，3=组代")
	private Integer category;


    @Schema(description = "最后更新时间",hidden = true)
    private LocalDateTime latestUpdateAt;

	@Schema(description = "最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动")
	private LocalDateTime latestHandleAt;


    public static DataCompanyCreateCommand createByWarehouseCommand(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyCreateCommand dataCompanyCreateCommand = new DataCompanyCreateCommand();
        dataCompanyCreateCommand.name = dataCompanyWarehouseCommand.getName();
        dataCompanyCreateCommand.uscc = dataCompanyWarehouseCommand.getUscc();
        dataCompanyCreateCommand.regNo = dataCompanyWarehouseCommand.getRegNo();
        dataCompanyCreateCommand.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        dataCompanyCreateCommand.enName = dataCompanyWarehouseCommand.getEnName();
        dataCompanyCreateCommand.parentId = dataCompanyWarehouseCommand.getParentId();
        dataCompanyCreateCommand.category = dataCompanyWarehouseCommand.getCategory();
        return dataCompanyCreateCommand;
    }
}