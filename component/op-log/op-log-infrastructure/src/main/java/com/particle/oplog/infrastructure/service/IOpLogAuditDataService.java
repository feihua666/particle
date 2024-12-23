package com.particle.oplog.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;

import java.util.List;

/**
 * <p>
 * 操作日志审计数据 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
public interface IOpLogAuditDataService extends IBaseService<OpLogAuditDataDO> {









    /**
     * 根据操作用户id查询
     * @param userId
     * @return
     */
    default List<OpLogAuditDataDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().eq(OpLogAuditDataDO::getUserId, userId));
    }



    /**
     * 根据操作用户id查询多个
     * @param userIds
     * @return
     */
    default List<OpLogAuditDataDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().in(OpLogAuditDataDO::getUserId, userIds));
    }


    /**
     * 根据数据id查询
     * @param mainDataId
     * @return
     */
    default List<OpLogAuditDataDO> getByMainDataId(Long dataId) {
        Assert.notNull(dataId,"dataId 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().eq(OpLogAuditDataDO::getDataId, dataId));
    }



    /**
     * 根据数据id查询多个
     * @param dataIds
     * @return
     */
    default List<OpLogAuditDataDO> getByMainDataIds(List<Long> dataIds) {
        Assert.notEmpty(dataIds,"dataIds 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().in(OpLogAuditDataDO::getDataId, dataIds));
    }




    /**
     * 根据操作日志id查询
     * @param opLogId
     * @return
     */
    default List<OpLogAuditDataDO> getByOpLogId(Long opLogId) {
        Assert.notNull(opLogId,"opLogId 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().eq(OpLogAuditDataDO::getOpLogId, opLogId));
    }



    /**
     * 根据操作日志id查询多个
     * @param opLogIds
     * @return
     */
    default List<OpLogAuditDataDO> getByOpLogIds(List<Long> opLogIds) {
        Assert.notEmpty(opLogIds,"opLogIds 不能为空");
        return list(Wrappers.<OpLogAuditDataDO>lambdaQuery().in(OpLogAuditDataDO::getOpLogId, opLogIds));
    }










}
