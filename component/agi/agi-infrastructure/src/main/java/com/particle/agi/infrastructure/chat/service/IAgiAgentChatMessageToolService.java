package com.particle.agi.infrastructure.chat.service;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体对话消息工具 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:11
 */
public interface IAgiAgentChatMessageToolService extends IBaseService<AgiAgentChatMessageToolDO> {

    /**
     * 根据智能体对话消息id查询
     * @param agiAgentChatMessageId
     * @return
     */
    default List<AgiAgentChatMessageToolDO> getByAgiAgentChatMessageId(Long agiAgentChatMessageId) {
        Assert.notNull(agiAgentChatMessageId,"agiAgentChatMessageId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolDO>lambdaQuery().eq(AgiAgentChatMessageToolDO::getAgiAgentChatMessageId, agiAgentChatMessageId));
    }



    /**
     * 根据智能体对话消息id查询多个
     * @param agiAgentChatMessageIds
     * @return
     */
    default List<AgiAgentChatMessageToolDO> getByAgiAgentChatMessageIds(List<Long> agiAgentChatMessageIds) {
        Assert.notEmpty(agiAgentChatMessageIds,"agiAgentChatMessageIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolDO>lambdaQuery().in(AgiAgentChatMessageToolDO::getAgiAgentChatMessageId, agiAgentChatMessageIds));
    }
            

    /**
     * 根据唯一id查询
     * @param uniqueId
     * @return
     */
    default AgiAgentChatMessageToolDO getByUniqueId(String uniqueId) {
        Assert.notNull(uniqueId,"uniqueId 不能为空");
        return getOne(Wrappers.<AgiAgentChatMessageToolDO>lambdaQuery().eq(AgiAgentChatMessageToolDO::getUniqueId, uniqueId));
    }



    /**
     * 根据唯一id查询多个
     * @param uniqueIds
     * @return
     */
    default List<AgiAgentChatMessageToolDO> getByUniqueIds(List<String> uniqueIds) {
        Assert.notEmpty(uniqueIds,"uniqueIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolDO>lambdaQuery().in(AgiAgentChatMessageToolDO::getUniqueId, uniqueIds));
    }
            











}
