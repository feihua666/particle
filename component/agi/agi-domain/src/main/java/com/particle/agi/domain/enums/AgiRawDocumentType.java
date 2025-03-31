package com.particle.agi.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 文档类型 字典项
 * 保持和{@link com.particle.global.ai.enums.AIDocumentType} 的名称相同以做兼容
 * </p>
 *
 * @author yw
 * @since 2025-01-09 14:30:51
 */
public enum AgiRawDocumentType implements IDictItem {

    /**
     * word
     */
    word
    ,
    /**
     * excel
     */
    excel
    ,
    /**
     * pdf
     */
    pdf
    ,
    /**
     * html
     */
    html
    ,
    /**
     * xml
     */
    xml
    ,
    /**
     * markdown
     */
    markdown
    ,
    /**
     * txt
     */
    txt
    ,
    /**
     * json
     */
    json
    ,
    /**
     * ppt
     */
    ppt;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.agi_raw_document_type.groupCode();
    }

    /**
     * 文档类型 字典组
     */
    public enum Group implements IDictGroup {
        agi_raw_document_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

