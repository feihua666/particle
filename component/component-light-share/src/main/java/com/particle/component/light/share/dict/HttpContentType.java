package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http内容类型 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-14 18:19:35
 */
public enum HttpContentType implements IDictItem {

    /**
     * application/json
     */
    application_json
    ,
    /**
     * multipart/form-data
     */
    multipart_form_data
    ,
    /**
     * application/x-www-form-urlencoded
     */
    application_x_www_form_urlencoded
    ,
    /**
     * text/plain
     */
    text_plain
    ,
    /**
     * text/xml
     */
    text_xml
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.http_content_type.groupCode();
    }

    /**
     * http内容类型 字典组
     */
    public enum Group implements IDictGroup {
        http_content_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

