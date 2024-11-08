package com.particle.navigation.infrastructure.service;

import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.navigation.infrastructure.dos.NavigationSiteTagRelDO;

/**
 * <p>
 * 导航网站标签关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
public interface INavigationSiteTagRelService extends IBaseService<NavigationSiteTagRelDO> {


    default boolean removeBySiteId(Long siteId){
        return deleteByColumn(siteId, NavigationSiteTagRelDO::getNavigationSiteId);
    }
    default boolean removeByTagId(Long tagId){
        return deleteByColumn(tagId, NavigationSiteTagRelDO::getNavigationSiteTagId);
    }

}
