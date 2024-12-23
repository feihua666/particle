package com.particle.scheduler.infrastructure.temptask.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;

import java.util.List;

/**
 * <p>
 * 任务计划临时任务 服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
public interface ISchedulerTempTaskService extends IBaseService<SchedulerTempTaskDO> {

    /**
     * 根据临时任务编码查询
     * @param code
     * @return
     */
    default SchedulerTempTaskDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<SchedulerTempTaskDO>lambdaQuery().eq(SchedulerTempTaskDO::getCode, code));
    }



    /**
     * 根据临时任务编码查询多个
     * @param codes
     * @return
     */
    default List<SchedulerTempTaskDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<SchedulerTempTaskDO>lambdaQuery().in(SchedulerTempTaskDO::getCode, codes));
    }










}
