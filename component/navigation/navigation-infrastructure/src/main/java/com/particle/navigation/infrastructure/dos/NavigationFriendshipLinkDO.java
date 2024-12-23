package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航友情链接表
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_friendship_link")
public class NavigationFriendshipLinkDO extends BaseDO {

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
