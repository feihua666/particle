package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利同族信息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Data
@Schema
public class DataCompanyIprPatentFamilyQueryListCommand extends AbstractBaseQueryCommand {



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


    @Schema(description = "国别")
    private String countryCode;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;









}