package com.particle.usagecount.infrastructure.service;

import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 使用次数定义 服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
public interface IUsageCountDefineService extends IBaseService<UsageCountDefineDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default UsageCountDefineDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<UsageCountDefineDO>lambdaQuery().eq(UsageCountDefineDO::getCode, code));
    }



    /**
     * 根据编码查询多个
     * @param codes
     * @return
     */
    default List<UsageCountDefineDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<UsageCountDefineDO>lambdaQuery().in(UsageCountDefineDO::getCode, codes));
    }
            


    /**
     * 根据匹配的url地址查询
     * @param urlPattern
     * @return
     */
    default UsageCountDefineDO getByUrlPattern(String urlPattern) {
        Assert.notNull(urlPattern,"urlPattern 不能为空");
        return getOne(Wrappers.<UsageCountDefineDO>lambdaQuery().eq(UsageCountDefineDO::getUrlPattern, urlPattern));
    }



    /**
     * 根据匹配的url地址查询多个
     * @param urlPatterns
     * @return
     */
    default List<UsageCountDefineDO> getByUrlPatterns(List<String> urlPatterns) {
        Assert.notEmpty(urlPatterns,"urlPatterns 不能为空");
        return list(Wrappers.<UsageCountDefineDO>lambdaQuery().in(UsageCountDefineDO::getUrlPattern, urlPatterns));
    }

}
