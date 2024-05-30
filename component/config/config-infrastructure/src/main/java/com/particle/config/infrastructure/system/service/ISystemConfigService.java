package com.particle.config.infrastructure.system.service;

import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 系统参数配置 服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
public interface ISystemConfigService extends IBaseService<SystemConfigDO> {

    /**
     * 根据参数配置编码查询
     * @param code
     * @return
     */
    default SystemConfigDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<SystemConfigDO>lambdaQuery().eq(SystemConfigDO::getCode, code));
    }



    /**
     * 根据参数配置编码查询多个
     * @param codes
     * @return
     */
    default List<SystemConfigDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<SystemConfigDO>lambdaQuery().in(SystemConfigDO::getCode, codes));
    }


    /**
     * 根据参数配置标签查询多个
     * @param tag
     * @return
     */
    default List<SystemConfigDO> getByTag(String tag) {
        Assert.notEmpty(tag,"tag 不能为空");
        return list(Wrappers.<SystemConfigDO>lambdaQuery().eq(SystemConfigDO::getTag, tag));
    }













}
