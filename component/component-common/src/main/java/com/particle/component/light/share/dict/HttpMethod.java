package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * http请求方法 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-18 10:47:34
 */
public enum HttpMethod implements IDictItem {

    /**
     * post
     */
    post
    ,
    /**
     * get
     */
    get
    ,
    /**
     * delete
     */
    delete
    ,
    /**
     * put
     */
    put
    ,
    /**
     * patch
     */
    patch
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.http_method.groupCode();
    }

    /**
     * http请求方法 字典组
     */
    public enum Group implements IDictGroup {
        http_method;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

