package com.particle.openplatform.domain.doc.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 接口文档参数字段分类 字典项
 * </p>
 *
 * @author yw
 * @since 2024-03-19 18:09:22
 */
public enum OpenPlatformDocParamFieldType implements IDictItem {

    /**
     * 请求头
     */
    request_header
    ,
    /**
     * 请求参数
     */
    request_param
    ,
    /**
     * 响应参数
     */
    response_param
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_doc_param_field_type.groupCode();
    }

    /**
     * 接口文档参数字段分类 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_doc_param_field_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

