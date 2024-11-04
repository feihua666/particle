package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 导航网站 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
@Data
@Entity
public class NavigationSite extends AggreateRoot {

    private NavigationSiteId id;

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
	 * 收费情况，字典id
	 */
	private Long feeSituationDictId;

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


    /**
     * 添加时使用
     */
    public void initForAdd() {
        if (this.collectionAt == null) {
            this.collectionAt = LocalDateTime.now();
        }
    }

    /**
     * 创建导航网站领域模型对象
     * @return 导航网站领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationSite create(){
        return DomainFactory.create(NavigationSite.class);
    }
}