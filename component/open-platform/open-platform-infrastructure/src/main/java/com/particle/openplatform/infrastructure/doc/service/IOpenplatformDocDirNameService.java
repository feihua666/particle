package com.particle.openplatform.infrastructure.doc.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 开放接口目录名称 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
public interface IOpenplatformDocDirNameService extends IBaseService<OpenplatformDocDirNameDO> {

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    default OpenplatformDocDirNameDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<OpenplatformDocDirNameDO>lambdaQuery().eq(OpenplatformDocDirNameDO::getCode, code));
    }








}
