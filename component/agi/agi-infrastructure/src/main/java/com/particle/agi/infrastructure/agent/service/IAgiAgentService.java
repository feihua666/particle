package com.particle.agi.infrastructure.agent.service;

import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
public interface IAgiAgentService extends IBaseService<AgiAgentDO> {

    /**
     * 根据智能体名称查询
     * @param name
     * @return
     */
    default AgiAgentDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<AgiAgentDO>lambdaQuery().eq(AgiAgentDO::getName, name));
    }



    /**
     * 根据智能体名称查询多个
     * @param names
     * @return
     */
    default List<AgiAgentDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<AgiAgentDO>lambdaQuery().in(AgiAgentDO::getName, names));
    }
            














    /**
     * 根据知识库id查询
     * @param agiKnowledgeBaseId
     * @return
     */
    default List<AgiAgentDO> getByAgiKnowledgeBaseId(Long agiKnowledgeBaseId) {
        Assert.notNull(agiKnowledgeBaseId,"agiKnowledgeBaseId 不能为空");
        return list(Wrappers.<AgiAgentDO>lambdaQuery().eq(AgiAgentDO::getAgiKnowledgeBaseId, agiKnowledgeBaseId));
    }



    /**
     * 根据知识库id查询多个
     * @param agiKnowledgeBaseIds
     * @return
     */
    default List<AgiAgentDO> getByAgiKnowledgeBaseIds(List<Long> agiKnowledgeBaseIds) {
        Assert.notEmpty(agiKnowledgeBaseIds,"agiKnowledgeBaseIds 不能为空");
        return list(Wrappers.<AgiAgentDO>lambdaQuery().in(AgiAgentDO::getAgiKnowledgeBaseId, agiKnowledgeBaseIds));
    }
            

















}
