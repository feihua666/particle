package com.particle.agi.domain.rag;

import cn.hutool.json.JSONUtil;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 知识存储原始文档片段 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Data
@Entity
public class AgiVectorStoreRawDocumentSegment extends AggreateRoot {

    private AgiVectorStoreRawDocumentSegmentId id;

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

    public void initForAdd() {
        this.isEmbedded = false;
    }

    /**
     * 修改为已嵌入
     */
    public void changeToEmbedded() {
        this.isEmbedded = true;
    }
    /**
     * 修改为未嵌入
     */
    public void changeToNotEmbedded() {
        this.isEmbedded = false;
    }

    /**
     * 转为AgiDocument，主要用于向量化处理
     * @return
     */
    public AgiDocument toAgiDocument() {
        Map<String,Object> metadata = null;
        if (metadataJson != null) {
            metadata = JSONUtil.toBean(metadataJson, Map.class);
        }else {
            metadata = new HashMap<>();
        }
        // 没有备注不添加，在向量化时会检验，metadata 中key和value都不能为null
        // 参见：{@link org.springframework.ai.document.Document} 构造函数
        // if (remark != null) {
        //     metadata.put("agiVectorStoreRawDocumentSegmentRemark", remark);
        // }
        return AgiDocument.create(id.getId().toString(), content, null, metadata, null);
    }
    /**
     * 创建知识存储原始文档片段领域模型对象
     * @return 知识存储原始文档片段领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiVectorStoreRawDocumentSegment create(){
        return DomainFactory.create(AgiVectorStoreRawDocumentSegment.class);
    }
}
