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
 * 知识存储原始文档表
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Accessors(chain = true)
@Data
@TableName("component_agi_vector_store_raw_document")
public class AgiVectorStoreRawDocumentDO extends BaseDO {

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


}
