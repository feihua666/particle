package com.particle.agi.infrastructure.model.service;

import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * AI模型提供商 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
public interface IAgiModelProviderService extends IBaseService<AgiModelProviderDO> {

    /**
     * 根据提供商编码查询
     * @param code
     * @return
     */
    default AgiModelProviderDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<AgiModelProviderDO>lambdaQuery().eq(AgiModelProviderDO::getCode, code));
    }



    /**
     * 根据提供商编码查询多个
     * @param codes
     * @return
     */
    default List<AgiModelProviderDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<AgiModelProviderDO>lambdaQuery().in(AgiModelProviderDO::getCode, codes));
    }
            
















}
