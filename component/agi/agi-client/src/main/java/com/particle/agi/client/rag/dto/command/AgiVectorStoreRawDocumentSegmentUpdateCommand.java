package com.particle.agi.client.rag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 知识存储原始文档片段 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentSegmentUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "知识存储原始文档id 不能为空")
        @Schema(description = "知识存储原始文档id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long agiVectorStoreRawDocumentId;


    @NotEmpty(message = "片段内容 不能为空")
        @Schema(description = "片段内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

	@Schema(description = "元数据信息json")
	private String metadataJson;

    @Schema(description = "描述")
    private String remark;









}
