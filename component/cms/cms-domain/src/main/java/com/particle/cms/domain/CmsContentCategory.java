package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 内容分类 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Data
@Entity
public class CmsContentCategory extends AggreateRoot {

    private CmsContentCategoryId id;

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目id
    */
    private Long cmsChannelId;

    /**
    * 分类名称
    */
    private String name;

    /**
    * 图片地址，主要用于列表展示
    */
    private String imageUrl;

    /**
    * 图片描述，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription;

    /**
    * 图片地址1，主要用于列表展示
    */
    private String imageUrl1;

    /**
    * 图片描述1，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription1;

    /**
    * 图片地址2，主要用于列表展示
    */
    private String imageUrl2;

    /**
    * 图片描述2，可能主要用于详情展示，如：图片来源于网络
    */
    private String imageDescription2;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建内容分类领域模型对象
     * @return 内容分类领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsContentCategory create(){
        return DomainFactory.create(CmsContentCategory.class);
    }
}
