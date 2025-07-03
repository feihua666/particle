package com.particle.cms.infrastructure.service;

import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 站点 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
public interface ICmsSiteService extends IBaseService<CmsSiteDO> {

    /**
     * 根据站点编码查询
     * @param code
     * @return
     */
    default CmsSiteDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CmsSiteDO>lambdaQuery().eq(CmsSiteDO::getCode, code));
    }



    /**
     * 根据站点编码查询多个
     * @param codes
     * @return
     */
    default List<CmsSiteDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CmsSiteDO>lambdaQuery().in(CmsSiteDO::getCode, codes));
    }
            

    /**
     * 根据站点名称查询
     * @param name
     * @return
     */
    default CmsSiteDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<CmsSiteDO>lambdaQuery().eq(CmsSiteDO::getName, name));
    }



    /**
     * 根据站点名称查询多个
     * @param names
     * @return
     */
    default List<CmsSiteDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<CmsSiteDO>lambdaQuery().in(CmsSiteDO::getName, names));
    }
            

    /**
     * 根据站点域名查询
     * @param domain
     * @return
     */
    default CmsSiteDO getByDomain(String domain) {
        Assert.notNull(domain,"domain 不能为空");
        return getOne(Wrappers.<CmsSiteDO>lambdaQuery().eq(CmsSiteDO::getDomain, domain));
    }



    /**
     * 根据站点域名查询多个
     * @param domains
     * @return
     */
    default List<CmsSiteDO> getByDomains(List<String> domains) {
        Assert.notEmpty(domains,"domains 不能为空");
        return list(Wrappers.<CmsSiteDO>lambdaQuery().in(CmsSiteDO::getDomain, domains));
    }
            
















}
