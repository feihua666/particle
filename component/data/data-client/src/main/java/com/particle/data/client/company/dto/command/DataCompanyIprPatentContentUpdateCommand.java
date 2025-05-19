package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentContentWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利内容 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Data
@Schema
public class DataCompanyIprPatentContentUpdateCommand extends AbstractBaseUpdateCommand {




    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "原始摘要内容")
    private String abstractContent;

	@Schema(description = "英文摘要内容")
	private String abstractContentEn;

	@Schema(description = "中文摘要内容")
	private String abstractContentCn;

	@Schema(description = "原始摘要内容地址")
	private String abstractContentUrl;

	@Schema(description = "英文摘要内容地址")
	private String abstractContentEnUrl;

	@Schema(description = "中文摘要内容地址")
	private String abstractContentCnUrl;

	@Schema(description = "原始简要说明")
	private String briefContent;

	@Schema(description = "英文简要说明")
	private String briefContentEn;

	@Schema(description = "中文简要说明")
	private String briefContentCn;

	@Schema(description = "原始说明书")
	private String instructionContent;

	@Schema(description = "英文说明书")
	private String instructionContentEn;

	@Schema(description = "中文说明书")
	private String instructionContentCn;

	@Schema(description = "原始说明书地址")
	private String instructionContentUrl;

	@Schema(description = "英文说明书地址")
	private String instructionContentEnUrl;

	@Schema(description = "中文说明书地址")
	private String instructionContentCnUrl;

	@Schema(description = "原始权利要求书")
	private String claimContent;

	@Schema(description = "英文权利要求书")
	private String claimContentEn;

	@Schema(description = "中文权利要求书")
	private String claimContentCn;

	@Schema(description = "原始权利要求书地址")
	private String claimContentUrl;

	@Schema(description = "英文权利要求书地址")
	private String claimContentEnUrl;

	@Schema(description = "中文权利要求书地址")
	private String claimContentCnUrl;

    public static DataCompanyIprPatentContentUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentContentWarehouseCommand dataCompanyIprPatentContentWarehouseCommand){
        DataCompanyIprPatentContentUpdateCommand command = new DataCompanyIprPatentContentUpdateCommand();
        command.setId(id);
        command.setVersion(version);
		command.companyIprPatentId = dataCompanyIprPatentContentWarehouseCommand.getCompanyIprPatentId();
		command.abstractContent = dataCompanyIprPatentContentWarehouseCommand.getAbstractContent();
		command.abstractContentEn = dataCompanyIprPatentContentWarehouseCommand.getAbstractContentEn();
		command.abstractContentCn = dataCompanyIprPatentContentWarehouseCommand.getAbstractContentCn();
		command.abstractContentUrl = dataCompanyIprPatentContentWarehouseCommand.getAbstractContentUrl();
		command.abstractContentEnUrl = dataCompanyIprPatentContentWarehouseCommand.getAbstractContentEnUrl();
		command.abstractContentCnUrl = dataCompanyIprPatentContentWarehouseCommand.getAbstractContentCnUrl();
		command.briefContent = dataCompanyIprPatentContentWarehouseCommand.getBriefContent();
		command.briefContentEn = dataCompanyIprPatentContentWarehouseCommand.getBriefContentEn();
		command.briefContentCn = dataCompanyIprPatentContentWarehouseCommand.getBriefContentCn();
		command.instructionContent = dataCompanyIprPatentContentWarehouseCommand.getInstructionContent();
		command.instructionContentEn = dataCompanyIprPatentContentWarehouseCommand.getInstructionContentEn();
		command.instructionContentCn = dataCompanyIprPatentContentWarehouseCommand.getInstructionContentCn();
		command.instructionContentUrl = dataCompanyIprPatentContentWarehouseCommand.getInstructionContentUrl();
		command.instructionContentEnUrl = dataCompanyIprPatentContentWarehouseCommand.getInstructionContentEnUrl();
		command.instructionContentCnUrl = dataCompanyIprPatentContentWarehouseCommand.getInstructionContentCnUrl();
		command.claimContent = dataCompanyIprPatentContentWarehouseCommand.getClaimContent();
		command.claimContentEn = dataCompanyIprPatentContentWarehouseCommand.getClaimContentEn();
		command.claimContentCn = dataCompanyIprPatentContentWarehouseCommand.getClaimContentCn();
		command.claimContentUrl = dataCompanyIprPatentContentWarehouseCommand.getClaimContentUrl();
		command.claimContentEnUrl = dataCompanyIprPatentContentWarehouseCommand.getClaimContentEnUrl();
		command.claimContentCnUrl = dataCompanyIprPatentContentWarehouseCommand.getClaimContentCnUrl();

        return command;
    }
}
