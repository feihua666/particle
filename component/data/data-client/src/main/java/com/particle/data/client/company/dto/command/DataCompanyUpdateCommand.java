package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyUpdateCommand extends AbstractBaseUpdateCommand {


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


    public static DataCompanyUpdateCommand createByWarehouseCommand(Long id,Integer version,DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyUpdateCommand dataCompanyUpdateCommand = new DataCompanyUpdateCommand();
        dataCompanyUpdateCommand.setId(id);
        dataCompanyUpdateCommand.setVersion(version);
        dataCompanyUpdateCommand.name = dataCompanyWarehouseCommand.getName();
        dataCompanyUpdateCommand.uscc = dataCompanyWarehouseCommand.getUscc();
        dataCompanyUpdateCommand.regNo = dataCompanyWarehouseCommand.getRegNo();
        dataCompanyUpdateCommand.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        dataCompanyUpdateCommand.enName = dataCompanyWarehouseCommand.getEnName();
        dataCompanyUpdateCommand.parentId = dataCompanyWarehouseCommand.getParentId();
        dataCompanyUpdateCommand.category = dataCompanyWarehouseCommand.getCategory();
        return dataCompanyUpdateCommand;
    }


}