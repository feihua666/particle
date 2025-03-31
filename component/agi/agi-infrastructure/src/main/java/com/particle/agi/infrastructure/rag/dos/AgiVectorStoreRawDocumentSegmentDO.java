package com.particle.agi.infrastructure.rag.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 知识存储原始文档片段表
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_vector_store_raw_document_segment")
public class AgiVectorStoreRawDocumentSegmentDO extends BaseDO {

    /**
    * 知识存储原始文档表id
    */
    private Long agiVectorStoreRawDocumentId;

    /**
    * 名称，可以是文件名，标题等
    */
    private String content;

	/**
	 * 元数据信息json
	 */
	private String metadataJson;

    /**
    * 是否已嵌入
    */
    private Boolean isEmbedded;

    /**
    * 描述
    */
    private String remark;


}
