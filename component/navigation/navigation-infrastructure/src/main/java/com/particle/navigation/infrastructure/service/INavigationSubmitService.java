package com.particle.navigation.infrastructure.service;

import com.particle.navigation.infrastructure.dos.NavigationSubmitDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 导航提交 服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
public interface INavigationSubmitService extends IBaseService<NavigationSubmitDO> {



    /**
     * 根据网站地址查询
     * @param url
     * @return
     */
    default NavigationSubmitDO getByUrl(String url) {
        Assert.notNull(url,"url 不能为空");
        return getOne(Wrappers.<NavigationSubmitDO>lambdaQuery().eq(NavigationSubmitDO::getUrl, url));
    }



    /**
     * 根据网站地址查询多个
     * @param urls
     * @return
     */
    default List<NavigationSubmitDO> getByUrls(List<String> urls) {
        Assert.notEmpty(urls,"urls 不能为空");
        return list(Wrappers.<NavigationSubmitDO>lambdaQuery().in(NavigationSubmitDO::getUrl, urls));
    }
            











}
