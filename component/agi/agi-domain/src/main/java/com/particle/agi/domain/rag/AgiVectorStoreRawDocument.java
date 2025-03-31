package com.particle.agi.domain.rag;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 知识存储原始文档 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Data
@Entity
public class AgiVectorStoreRawDocument extends AggreateRoot {

    private AgiVectorStoreRawDocumentId id;

    /**
    * 名称，可以是文件名，标题等
    */
    private String name;

    /**
    * 文件名称，类型为文件时有值
    */
    private String fileName;

    /**
    * 类型，字典id,如：word，excel，weblink
    */
    private Long typeDictId;

    /**
    * 地址，类型为文件、网址时有值
    */
    private String url;

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
     * 修改为已嵌入
     */
    public void changeToNotEmbedded() {
        this.isEmbedded = false;
    }

    /**
     * 是否已嵌入
     * @return
     */
    public Boolean hasEmbedded() {
        return this.isEmbedded;
    }

    /**
     * 创建知识存储原始文档领域模型对象
     * @return 知识存储原始文档领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AgiVectorStoreRawDocument create(){
        return DomainFactory.create(AgiVectorStoreRawDocument.class);
    }
}
