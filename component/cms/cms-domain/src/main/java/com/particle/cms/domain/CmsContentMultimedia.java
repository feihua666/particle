package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 内容多媒体 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Data
@Entity
public class CmsContentMultimedia extends AggreateRoot {

    private CmsContentMultimediaId id;

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 内容id
    */
    private Long cmsContentId;

    /**
    * 文本内容
    */
    private String content;

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
    * 文件名称
    */
    private String fileName;

    /**
    * 文件地址
    */
    private String fileUrl;

    /**
    * 文件大小，单位字节
    */
    private Integer fileSize;

    /**
    * 文件大小字符串，有高可读性
    */
    private String fileSizeStr;

    /**
    * 媒体类型
    */
    private String mediaType;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;



    /**
     * 创建内容多媒体领域模型对象
     * @return 内容多媒体领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsContentMultimedia create(){
        return DomainFactory.create(CmsContentMultimedia.class);
    }
}
