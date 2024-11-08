package com.particle.navigation.infrastructure.service;

import com.particle.navigation.infrastructure.dos.NavigationSiteTagDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 导航网站标签 服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
public interface INavigationSiteTagService extends IBaseService<NavigationSiteTagDO> {

    /**
     * 根据标签编码查询
     * @param code
     * @return
     */
    default NavigationSiteTagDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<NavigationSiteTagDO>lambdaQuery().eq(NavigationSiteTagDO::getCode, code));
    }



    /**
     * 根据标签编码查询多个
     * @param codes
     * @return
     */
    default List<NavigationSiteTagDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<NavigationSiteTagDO>lambdaQuery().in(NavigationSiteTagDO::getCode, codes));
    }
            

    /**
     * 根据标签名称查询
     * @param name
     * @return
     */
    default NavigationSiteTagDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<NavigationSiteTagDO>lambdaQuery().eq(NavigationSiteTagDO::getName, name));
    }



    /**
     * 根据标签名称查询多个
     * @param names
     * @return
     */
    default List<NavigationSiteTagDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<NavigationSiteTagDO>lambdaQuery().in(NavigationSiteTagDO::getName, names));
    }
            











}
