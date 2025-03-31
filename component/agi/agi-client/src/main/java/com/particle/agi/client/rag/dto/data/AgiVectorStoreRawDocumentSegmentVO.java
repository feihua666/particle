package com.particle.agi.client.rag.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 知识存储原始文档片段 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentSegmentVO extends AbstractBaseIdVO {

    @Schema(description = "知识存储原始文档表id")
    private Long agiVectorStoreRawDocumentId;

    @TransBy(tableName = TransTableNameConstants.component_agi_vector_store_raw_document, byFieldName = "agiVectorStoreRawDocumentId", mapValueField = "name")
    @Schema(description = "知识存储原始文档名称")
    private String agiVectorStoreRawDocumentName;

    @Schema(description = "片段内容")
    private String content;

	@Schema(description = "元数据信息json")
	private String metadataJson;

    @Schema(description = "是否已嵌入")
    private Boolean isEmbedded;

    @Schema(description = "描述")
    private String remark;



}
