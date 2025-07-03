package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 内容多媒体表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_content_multimedia")
public class CmsContentMultimediaDO extends BaseDO {

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


}
