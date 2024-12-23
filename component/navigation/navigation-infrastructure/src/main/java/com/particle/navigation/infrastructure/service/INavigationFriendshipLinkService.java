package com.particle.navigation.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.navigation.infrastructure.dos.NavigationFriendshipLinkDO;

import java.util.List;

/**
 * <p>
 * 导航友情链接 服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
public interface INavigationFriendshipLinkService extends IBaseService<NavigationFriendshipLinkDO> {




    /**
     * 根据网站地址查询
     * @param url
     * @return
     */
    default NavigationFriendshipLinkDO getByUrl(String url) {
        Assert.notNull(url,"url 不能为空");
        return getOne(Wrappers.<NavigationFriendshipLinkDO>lambdaQuery().eq(NavigationFriendshipLinkDO::getUrl, url));
    }



    /**
     * 根据网站地址查询多个
     * @param urls
     * @return
     */
    default List<NavigationFriendshipLinkDO> getByUrls(List<String> urls) {
        Assert.notEmpty(urls,"urls 不能为空");
        return list(Wrappers.<NavigationFriendshipLinkDO>lambdaQuery().in(NavigationFriendshipLinkDO::getUrl, urls));
    }













}
