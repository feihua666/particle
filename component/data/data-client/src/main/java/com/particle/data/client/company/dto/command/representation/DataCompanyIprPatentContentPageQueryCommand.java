package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利内容 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Data
@Schema
public class DataCompanyIprPatentContentPageQueryCommand extends AbstractBasePageQueryCommand {



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

	@Schema(description = "数据md5,abstract_content")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}