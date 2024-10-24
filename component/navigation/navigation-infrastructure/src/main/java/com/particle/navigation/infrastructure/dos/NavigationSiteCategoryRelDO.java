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
 * 导航网站分类关系表
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_site_category_rel")
public class NavigationSiteCategoryRelDO extends BaseDO {

    /**
    * 导航网站id
    */
    private Long navigationSiteId;

    /**
    * 导航分类id
    */
    private Long navigationCategoryId;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
