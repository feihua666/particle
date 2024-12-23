package com.particle.oplog.infrastructure.error.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;

import java.util.List;

/**
 * <p>
 * 操作异常日志内容 服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
public interface IOpLogErrorContentService extends IBaseService<OpLogErrorContentDO> {

    /**
     * 根据异常日志id查询
     * @param opLogErrorId
     * @return
     */
    default OpLogErrorContentDO getByOpLogErrorId(Long opLogErrorId) {
        Assert.notNull(opLogErrorId,"opLogErrorId 不能为空");
        return getOne(Wrappers.<OpLogErrorContentDO>lambdaQuery().eq(OpLogErrorContentDO::getOpLogErrorId, opLogErrorId));
    }



    /**
     * 根据异常日志id查询多个
     * @param opLogErrorIds
     * @return
     */
    default List<OpLogErrorContentDO> getByOpLogErrorIds(List<Long> opLogErrorIds) {
        Assert.notEmpty(opLogErrorIds,"opLogErrorIds 不能为空");
        return list(Wrappers.<OpLogErrorContentDO>lambdaQuery().in(OpLogErrorContentDO::getOpLogErrorId, opLogErrorIds));
    }










}
