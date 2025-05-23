package com.particle.scheduler.app.datatask.api.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.scheduler.app.datatask.executor.SchedulerJobDataTaskCommandExecutor;
import com.particle.scheduler.app.datatask.structmapping.SchedulerJobDataTaskAppStructMapping;
import com.particle.scheduler.client.datatask.api.ISchedulerJobDataTaskControlApplicationService;
import com.particle.scheduler.client.datatask.dto.data.SchedulerDataTaskVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskControlVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskVO;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.value.SchedulerDictItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划数据任务 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Transactional
@Service
@CatchAndLog
public class SchedulerJobDataTaskControlApplicationServiceImpl implements ISchedulerJobDataTaskControlApplicationService {

    private SchedulerJobDataTaskCommandExecutor schedulerJobDataTaskCommandExecutor;
    private SchedulerDictGateway schedulerDictGateway;

    @Override
    public SchedulerJobDataTaskControlVO submit(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt) {
        SchedulerJobDataTaskVO schedulerJobDataTaskVO = schedulerJobDataTaskCommandExecutor.submit(groupIdentifier, uniqueIdentifier, params, dataExpireAt);
        SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO = mapping(schedulerJobDataTaskVO);
        return schedulerJobDataTaskControlVO;
    }
    @Override
    public SchedulerDataTaskVO submitAndGetDataTaskVO(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt) {
        SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO = submit(groupIdentifier, uniqueIdentifier, params, dataExpireAt);
        SchedulerDataTaskVO schedulerDataTaskVO = mapping(schedulerJobDataTaskControlVO, true);
        return schedulerDataTaskVO;
    }
    @Override
    public void processStart(Long id, String uniqueIdentifier) {
        schedulerJobDataTaskCommandExecutor.processStart(id, uniqueIdentifier);
    }

    @Override
    public void finish(Long id, String uniqueIdentifier, Boolean isHasError, String errorMessage, String result) {
        schedulerJobDataTaskCommandExecutor.finish(id, uniqueIdentifier, isHasError, errorMessage, result);
    }

    @Override
    public SchedulerJobDataTaskControlVO getControlVO(Long id, String uniqueIdentifier) {
        SchedulerJobDataTaskVO schedulerJobDataTaskVO = schedulerJobDataTaskCommandExecutor.getVO(id, uniqueIdentifier);
        SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO = mapping(schedulerJobDataTaskVO);
        return schedulerJobDataTaskControlVO;
    }
    @Override
    public SchedulerDataTaskVO getDataTaskVO(Long id, String uniqueIdentifier, boolean convertResultToMap) {
        SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO = getControlVO(id, uniqueIdentifier);
        return mapping(schedulerJobDataTaskControlVO,true);
    }
    /**
     * 映射结果
     * @param schedulerJobDataTaskControlVO
     * @param convertResultToMap
     * @return
     */
    private SchedulerDataTaskVO mapping(SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO, boolean convertResultToMap) {
        if (schedulerJobDataTaskControlVO == null) {
            return null;
        }
        Object dataResult = null;
        String result = schedulerJobDataTaskControlVO.getResult();
        if (result != null && convertResultToMap) {
            JSON parsed = JSONUtil.parse(result);
            dataResult = parsed;
        }else{
            dataResult = result;
        }
        return SchedulerDataTaskVO.create(schedulerJobDataTaskControlVO.getUniqueIdentifier(),
                schedulerJobDataTaskControlVO.getExecuteStatusDictValue(),
                schedulerJobDataTaskControlVO.getExecuteStatusDictName(),
                dataResult);
    }
    /**
     * 映射结果
     * @param schedulerJobDataTaskVO
     * @return
     */
    private SchedulerJobDataTaskControlVO mapping(SchedulerJobDataTaskVO schedulerJobDataTaskVO) {
        SchedulerJobDataTaskControlVO schedulerJobDataTaskControlVO = SchedulerJobDataTaskAppStructMapping.instance.schedulerJobDataTaskVOToSchedulerJobDataTaskControlVO(schedulerJobDataTaskVO);

        SchedulerDictItemInfo schedulerDictItemInfo = schedulerDictGateway.getSchedulerDictItemInfoById(schedulerJobDataTaskVO.getExecuteStatusDictId());
        schedulerJobDataTaskControlVO.setExecuteStatusDictValue(schedulerDictItemInfo.getValue());
        schedulerJobDataTaskControlVO.setExecuteStatusDictName(schedulerDictItemInfo.getName());
        return schedulerJobDataTaskControlVO;
    }
    @Autowired
    public void setSchedulerJobDataTaskCommandExecutor(SchedulerJobDataTaskCommandExecutor schedulerJobDataTaskCommandExecutor) {
        this.schedulerJobDataTaskCommandExecutor = schedulerJobDataTaskCommandExecutor;
    }
    @Autowired
    public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
        this.schedulerDictGateway = schedulerDictGateway;
    }
}
