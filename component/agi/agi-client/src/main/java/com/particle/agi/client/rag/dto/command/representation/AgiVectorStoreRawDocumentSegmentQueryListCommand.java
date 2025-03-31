package com.particle.agi.client.rag.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 知识存储原始文档片段 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentSegmentQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "知识存储原始文档表id")
    private Long agiVectorStoreRawDocumentId;


    @Schema(description = "片段内容")
    private String content;

	@Schema(description = "元数据信息json")
	private String metadataJson;


    @Schema(description = "是否已嵌入")
    private Boolean isEmbedded;










}
