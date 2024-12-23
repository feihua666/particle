package com.particle.scheduler.infrastructure.schedule.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.scheduler.domain.schedule.value.SchedulerExecuteRecordClearParam;
import com.particle.scheduler.domain.value.NameAndGroupParam;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import com.particle.scheduler.infrastructure.schedule.structmapping.SchedulerExecuteRecordInfrastructureStructMapping;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * <p>
 * 任务计划执行记录 服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
public interface ISchedulerExecuteRecordService extends IBaseService<SchedulerExecuteRecordDO> {



    /**
     * 根据任务名称查询
     * @param name
     * @return
     */
    default SchedulerExecuteRecordDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<SchedulerExecuteRecordDO>lambdaQuery().eq(SchedulerExecuteRecordDO::getName, name));
    }



    /**
     * 根据任务名称查询多个
     * @param names
     * @return
     */
    default List<SchedulerExecuteRecordDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<SchedulerExecuteRecordDO>lambdaQuery().in(SchedulerExecuteRecordDO::getName, names));
    }


    /**
     * 根据任务组查询
     * @param groupName
     * @return
     */
    default SchedulerExecuteRecordDO getByGroupName(String groupName) {
        Assert.notNull(groupName,"groupName 不能为空");
        return getOne(Wrappers.<SchedulerExecuteRecordDO>lambdaQuery().eq(SchedulerExecuteRecordDO::getGroupName, groupName));
    }



    /**
     * 根据任务组查询多个
     * @param groupNames
     * @return
     */
    default List<SchedulerExecuteRecordDO> getByGroupNames(List<String> groupNames) {
        Assert.notEmpty(groupNames,"groupNames 不能为空");
        return list(Wrappers.<SchedulerExecuteRecordDO>lambdaQuery().in(SchedulerExecuteRecordDO::getGroupName, groupNames));
    }






    /**
     * 是否存在执行中的任务
     * 注意这里没有考虑实例情况
     * @param nameAndGroupForm
     * @return
     */
    public boolean existRunning(NameAndGroupParam nameAndGroupForm);

    /**
     * 任务开始记录
     * @param context
     * @return
     */
    public Long addStartJobRecord(JobExecutionContext context) throws SchedulerException;

    /**
     * 任务执行完成记录更新
     * @param recordId
     * @param result
     * @param statusDictId
     * @return
     */
    public boolean endJobUpdateRecord(Long recordId,String result,Long statusDictId);

    /**
     * 任务冲突直接记录
     * @param context
     * @param result
     * @return
     */
    public boolean jobConflictOverRecord(JobExecutionContext context,String result) throws SchedulerException;

    /**
     * 清理
     * @param form
     * @return
     */
    default public boolean clear(SchedulerExecuteRecordClearParam form){
        SchedulerExecuteRecordDO schedulerRecord = SchedulerExecuteRecordInfrastructureStructMapping.instance.schedulerExecuteRecordClearParamToSchedulerExecuteRecordDO(form);

        return remove(Wrappers.query(schedulerRecord).lambda().le(SchedulerExecuteRecordDO::getCreateAt,form.getBeforeAt()));
    }


}
