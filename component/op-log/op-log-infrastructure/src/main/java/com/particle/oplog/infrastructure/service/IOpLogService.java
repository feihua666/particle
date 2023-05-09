package com.particle.oplog.infrastructure.service;

import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
public interface IOpLogService extends IBaseService<OpLogDO> {

    /**
     * 根据操作用户id查询
     * @param userId
     * @return
     */
    default List<OpLogDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<OpLogDO>lambdaQuery().eq(OpLogDO::getUserId, userId));
    }

    /**
     * 根据操作用户id查询多个
     * @param userIds
     * @return
     */
    default List<OpLogDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<OpLogDO>lambdaQuery().in(OpLogDO::getUserId, userIds));
    }

    /**
     * 根据主数据id查询
     * @param mainDataId
     * @return
     */
    default List<OpLogDO> getByMainDataId(Long mainDataId) {
        Assert.notNull(mainDataId,"mainDataId 不能为空");
        return list(Wrappers.<OpLogDO>lambdaQuery().eq(OpLogDO::getMainDataId, mainDataId));
    }


    /**
     * 根据主数据id查询多个
     * @param mainDataIds
     * @return
     */
    default List<OpLogDO> getByMainDataIds(List<Long> mainDataIds) {
        Assert.notEmpty(mainDataIds,"mainDataIds 不能为空");
        return list(Wrappers.<OpLogDO>lambdaQuery().in(OpLogDO::getMainDataId, mainDataIds));
    }

}
