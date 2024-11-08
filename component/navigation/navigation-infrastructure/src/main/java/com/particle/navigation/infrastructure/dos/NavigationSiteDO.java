package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 导航网站表
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_site")
public class NavigationSiteDO extends BaseDO {

    /**
    * 网站名称
    */
    private String name;

    /**
    * 网站标题
    */
    private String title;

    /**
    * 网站logo图标地址
    */
    private String logoUrl;

    /**
    * 网站地址
    */
    private String url;

    /**
    * 网站截屏地址
    */
    private String screenshotUrl;

    /**
    * 网站截屏缩略图地址
    */
    private String screenshotThumbnailUrl;

    /**
    * 简短描述
    */
    private String content;

    /**
    * 详细描述
    */
    private String contentDetail;

	/**
	 * 收录时间
	 */
	private LocalDateTime collectionAt;

	/**
	 * 是否已发布，已发布不能修改和删除
	 */
	private Boolean isPublished;

	/**
	 * 下架原因，未发布原因
	 */
	private String unpublishedReason;

    /**
    * 描述
    */
    private String remark;


}
