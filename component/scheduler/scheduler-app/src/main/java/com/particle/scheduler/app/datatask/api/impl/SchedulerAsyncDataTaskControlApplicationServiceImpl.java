package com.particle.scheduler.app.datatask.api.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.scheduler.app.datatask.executor.SchedulerAsyncDataTaskCommandExecutor;
import com.particle.scheduler.app.datatask.structmapping.SchedulerAsyncDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskControlApplicationService;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskControlVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerAsyncDataTaskVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerDataTaskVO;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.value.SchedulerDictItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划异步数据任务 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerAsyncDataTaskControlApplicationServiceImpl implements ISchedulerAsyncDataTaskControlApplicationService {

    private SchedulerAsyncDataTaskCommandExecutor schedulerAsyncDataTaskCommandExecutor;
    private SchedulerDictGateway schedulerDictGateway;

    @Override
    public SchedulerAsyncDataTaskControlVO submit(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt) {
        SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO = schedulerAsyncDataTaskCommandExecutor.submit(groupIdentifier, uniqueIdentifier, params, dataExpireAt);
        SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO = mapping(schedulerAsyncDataTaskVO);
        return schedulerAsyncDataTaskControlVO;
    }

    @Override
    public SchedulerDataTaskVO submitAndGetDataTaskVO(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt) {
        SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO = submit(groupIdentifier, uniqueIdentifier, params, dataExpireAt);
        SchedulerDataTaskVO schedulerDataTaskVO = mapping(schedulerAsyncDataTaskControlVO, true);
        return schedulerDataTaskVO;
    }

    @Override
    public void processStart(Long id, String uniqueIdentifier) {
        schedulerAsyncDataTaskCommandExecutor.processStart(id, uniqueIdentifier);
    }

    @Override
    public void finish(Long id, String uniqueIdentifier, Boolean isHasError, String errorMessage, String result) {
        schedulerAsyncDataTaskCommandExecutor.finish(id, uniqueIdentifier, isHasError, errorMessage, result);
    }

    @Override
    public SchedulerAsyncDataTaskControlVO getControlVO(Long id, String uniqueIdentifier) {
        SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO = schedulerAsyncDataTaskCommandExecutor.getVO(id, uniqueIdentifier);
        SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO = mapping(schedulerAsyncDataTaskVO);
        return schedulerAsyncDataTaskControlVO;
    }

    @Override
    public SchedulerDataTaskVO getDataTaskVO(Long id, String uniqueIdentifier, boolean convertResultToMap) {
        SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO = getControlVO(id, uniqueIdentifier);
        return mapping(schedulerAsyncDataTaskControlVO, convertResultToMap);
    }

    /**
     * 映射结果
     * @param schedulerAsyncDataTaskControlVO
     * @param convertResultToMap
     * @return
     */
    private SchedulerDataTaskVO mapping(SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO, boolean convertResultToMap) {
        if (schedulerAsyncDataTaskControlVO == null) {
            return null;
        }
        Object dataResult = null;
        String result = schedulerAsyncDataTaskControlVO.getResult();
        if (result != null && convertResultToMap) {
            JSON parsed = JSONUtil.parse(result);
            dataResult = parsed;
        }else{
            dataResult = result;
        }
        return SchedulerDataTaskVO.create(schedulerAsyncDataTaskControlVO.getUniqueIdentifier(),
                schedulerAsyncDataTaskControlVO.getExecuteStatusDictValue(),
                schedulerAsyncDataTaskControlVO.getExecuteStatusDictName(),
                dataResult);
    }
    /**
     * 映射结果
     * @param schedulerAsyncDataTaskVO
     * @return
     */
    private SchedulerAsyncDataTaskControlVO mapping(SchedulerAsyncDataTaskVO schedulerAsyncDataTaskVO) {
        SchedulerAsyncDataTaskControlVO schedulerAsyncDataTaskControlVO = SchedulerAsyncDataTaskAppStructMapping.instance.schedulerAsyncDataTaskVOToSchedulerAsyncDataTaskControlVO(schedulerAsyncDataTaskVO);

        if (schedulerAsyncDataTaskControlVO != null) {
            SchedulerDictItemInfo schedulerDictItemInfo = schedulerDictGateway.getSchedulerDictItemInfoById(schedulerAsyncDataTaskVO.getExecuteStatusDictId());
            schedulerAsyncDataTaskControlVO.setExecuteStatusDictValue(schedulerDictItemInfo.getValue());
            schedulerAsyncDataTaskControlVO.setExecuteStatusDictName(schedulerDictItemInfo.getName());
        }
        return schedulerAsyncDataTaskControlVO;
    }
    @Autowired
    public void setSchedulerAsyncDataTaskCommandExecutor(SchedulerAsyncDataTaskCommandExecutor schedulerAsyncDataTaskCommandExecutor) {
        this.schedulerAsyncDataTaskCommandExecutor = schedulerAsyncDataTaskCommandExecutor;
    }
    @Autowired
    public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
        this.schedulerDictGateway = schedulerDictGateway;
    }
}
