package com.particle.navigation.infrastructure.service;

import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.navigation.infrastructure.dos.NavigationSiteCategoryRelDO;

/**
 * <p>
 * 导航网站分类关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
public interface INavigationSiteCategoryRelService extends IBaseService<NavigationSiteCategoryRelDO> {

    default boolean removeBySiteId(Long siteId){
        return deleteByColumn(siteId,NavigationSiteCategoryRelDO::getNavigationSiteId);
    }

    default boolean removeByCategoryId(Long categoryId){
        return deleteByColumn(categoryId,NavigationSiteCategoryRelDO::getNavigationCategoryId);
    }

}
