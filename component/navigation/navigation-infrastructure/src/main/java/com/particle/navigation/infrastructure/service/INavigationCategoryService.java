package com.particle.navigation.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.navigation.infrastructure.dos.NavigationCategoryDO;

import java.util.List;

/**
 * <p>
 * 导航分类 服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
public interface INavigationCategoryService extends IBaseService<NavigationCategoryDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default NavigationCategoryDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<NavigationCategoryDO>lambdaQuery().eq(NavigationCategoryDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<NavigationCategoryDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<NavigationCategoryDO>lambdaQuery().in(NavigationCategoryDO::getCode, codes));
    }

























}
