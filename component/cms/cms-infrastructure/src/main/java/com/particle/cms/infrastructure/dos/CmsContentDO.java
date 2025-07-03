package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_content")
public class CmsContentDO extends BaseDO {

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目id
    */
    private Long cmsChannelId;

    /**
    * 内容分类id
    */
    private Long cmsContentCategoryId;

    /**
    * 标题
    */
    private String title;

    /**
    * 作者
    */
    private String author;

    /**
    * 来源，原文，如果是原创，写原创即可
    */
    private String original;

    /**
    * 简介
    */
    private String profile;

    /**
    * 审核状态，字典id
    */
    private Long auditStatusDictId;

    /**
    * 是否发布
    */
    private Boolean isPublic;

    /**
    * 发布时间
    */
    private LocalDateTime publicAt;
    
    /**
    * 内容类型，字典id，article=文章，library=文库，gallery=图库
    */
    private Long contentTypeDictId;

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
    * 内容模板路径
    */
    private String templatePath;

    /**
    * 内容模板,默认index.html
    */
    private String templateIndex;

    /**
    * 内容静态页存放路径
    */
    private String staticPath;

    /**
    * 页面访问量,页面展示次数
    */
    private Integer pv;

    /**
    * 页面访问ip数,一天之内相同IP地址只被计算一次
    */
    private Integer iv;

    /**
    * 页面访问用户数,页面访问电脑客户端数,一天之内相同cookie的访问只被计算1次
    */
    private Integer uv;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
