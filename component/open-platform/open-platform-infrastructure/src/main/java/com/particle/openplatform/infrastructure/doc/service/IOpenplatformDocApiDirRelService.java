package com.particle.openplatform.infrastructure.doc.service;

import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 开放接口文档接口与目录关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
public interface IOpenplatformDocApiDirRelService extends IBaseService<OpenplatformDocApiDirRelDO> {

    /**
     * 根据开放接口文档接口id查询
     * @param openplatformDocApiId
     * @return
     */
    default List<OpenplatformDocApiDirRelDO> getByOpenplatformDocApiId(Long openplatformDocApiId) {
        Assert.notNull(openplatformDocApiId,"openplatformDocApiId 不能为空");
        return list(Wrappers.<OpenplatformDocApiDirRelDO>lambdaQuery().eq(OpenplatformDocApiDirRelDO::getOpenplatformDocApiId, openplatformDocApiId));
    }



    /**
     * 根据开放接口文档接口id查询多个
     * @param openplatformDocApiIds
     * @return
     */
    default List<OpenplatformDocApiDirRelDO> getByOpenplatformDocApiIds(List<Long> openplatformDocApiIds) {
        Assert.notEmpty(openplatformDocApiIds,"openplatformDocApiIds 不能为空");
        return list(Wrappers.<OpenplatformDocApiDirRelDO>lambdaQuery().in(OpenplatformDocApiDirRelDO::getOpenplatformDocApiId, openplatformDocApiIds));
    }


    /**
     * 根据开放接口文档目录id查询
     * @param openplatformDocDirId
     * @return
     */
    default List<OpenplatformDocApiDirRelDO> getByOpenplatformDocDirId(Long openplatformDocDirId) {
        Assert.notNull(openplatformDocDirId,"openplatformDocDirId 不能为空");
        return list(Wrappers.<OpenplatformDocApiDirRelDO>lambdaQuery().eq(OpenplatformDocApiDirRelDO::getOpenplatformDocDirId, openplatformDocDirId));
    }



    /**
     * 根据开放接口文档目录id查询多个
     * @param openplatformDocDirIds
     * @return
     */
    default List<OpenplatformDocApiDirRelDO> getByOpenplatformDocDirIds(List<Long> openplatformDocDirIds) {
        Assert.notEmpty(openplatformDocDirIds,"openplatformDocDirIds 不能为空");
        return list(Wrappers.<OpenplatformDocApiDirRelDO>lambdaQuery().in(OpenplatformDocApiDirRelDO::getOpenplatformDocDirId, openplatformDocDirIds));
    }
            








}
