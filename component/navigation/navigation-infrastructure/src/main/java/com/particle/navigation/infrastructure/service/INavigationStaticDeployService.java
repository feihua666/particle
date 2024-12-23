package com.particle.navigation.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.navigation.infrastructure.dos.NavigationStaticDeployDO;

import java.util.List;

/**
 * <p>
 * 导航网站静态部署 服务类
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
public interface INavigationStaticDeployService extends IBaseService<NavigationStaticDeployDO> {

    /**
     * 根据部署编码查询
     * @param code
     * @return
     */
    default NavigationStaticDeployDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<NavigationStaticDeployDO>lambdaQuery().eq(NavigationStaticDeployDO::getCode, code));
    }



    /**
     * 根据部署编码查询多个
     * @param codes
     * @return
     */
    default List<NavigationStaticDeployDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<NavigationStaticDeployDO>lambdaQuery().in(NavigationStaticDeployDO::getCode, codes));
    }
















}
