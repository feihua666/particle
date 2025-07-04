package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentQuoteWarehouseCommand;

/**
 * <p>
 * 企业知识产权专利引证信息 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Data
@Schema
public class DataCompanyIprPatentQuoteUpdateCommand extends AbstractBaseUpdateCommand {


    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "原始申请号")
    private String applyNo;

	@Schema(description = "标准申请号，如：CN101995000006852")
	private String standardApplyNo;


    @Schema(description = "申请日期")
    private LocalDate applyDate;


    @Schema(description = "原始公布号")
    private String publicNo;

	@Schema(description = "标准公布号")
	private String standardPublicNo;


    @Schema(description = "公布日")
    private LocalDate publicDate;


    @Schema(description = "原始标题")
    private String title;

	@Schema(description = "中文标题")
	private String titleCn;

	@Schema(description = "英文标题")
	private String titleEn;


    @Schema(description = "引证来源")
    private String quoteFrom;


    @Schema(description = "引证来源类型")
    private String quoteFromType;


    @Schema(description = "申请人引证标准号")
    private String applicantQuoteNo;


    @Schema(description = "审查员引证标准号")
    private String examinerQuoteNo;


    public static DataCompanyIprPatentQuoteUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyIprPatentQuoteWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentQuoteUpdateCommand command = new DataCompanyIprPatentQuoteUpdateCommand();
        command.setId(id);
        command.setVersion(version);
                command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.applyNo = dataCompanyBasicWarehouseCommand.getApplyNo();
        command.applyDate = dataCompanyBasicWarehouseCommand.getApplyDate();
        command.publicNo = dataCompanyBasicWarehouseCommand.getPublicNo();
        command.publicDate = dataCompanyBasicWarehouseCommand.getPublicDate();
        command.title = dataCompanyBasicWarehouseCommand.getTitle();
        command.titleCn = dataCompanyBasicWarehouseCommand.getTitleCn();
        command.titleEn = dataCompanyBasicWarehouseCommand.getTitleEn();
        command.quoteFrom = dataCompanyBasicWarehouseCommand.getQuoteFrom();
        command.quoteFromType = dataCompanyBasicWarehouseCommand.getQuoteFromType();
        command.applicantQuoteNo = dataCompanyBasicWarehouseCommand.getApplicantQuoteNo();
        command.examinerQuoteNo = dataCompanyBasicWarehouseCommand.getExaminerQuoteNo();


        return command;
    }
}
