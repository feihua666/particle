package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 导航网站标签关系表
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_site_tag_rel")
public class NavigationSiteTagRelDO extends BaseDO {

    /**
    * 网站id
    */
    private Long navigationSiteId;

    /**
    * 网站标签id
    */
    private Long navigationSiteTagId;


}
