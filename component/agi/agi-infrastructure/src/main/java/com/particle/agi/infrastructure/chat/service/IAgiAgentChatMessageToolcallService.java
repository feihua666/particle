package com.particle.agi.infrastructure.chat.service;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageToolcallDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体对话消息工具调用 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 17:37:24
 */
public interface IAgiAgentChatMessageToolcallService extends IBaseService<AgiAgentChatMessageToolcallDO> {

    /**
     * 根据智能体对话消息id查询
     * @param agiAgentChatMessageId
     * @return
     */
    default List<AgiAgentChatMessageToolcallDO> getByAgiAgentChatMessageId(Long agiAgentChatMessageId) {
        Assert.notNull(agiAgentChatMessageId,"agiAgentChatMessageId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolcallDO>lambdaQuery().eq(AgiAgentChatMessageToolcallDO::getAgiAgentChatMessageId, agiAgentChatMessageId));
    }



    /**
     * 根据智能体对话消息id查询多个
     * @param agiAgentChatMessageIds
     * @return
     */
    default List<AgiAgentChatMessageToolcallDO> getByAgiAgentChatMessageIds(List<Long> agiAgentChatMessageIds) {
        Assert.notEmpty(agiAgentChatMessageIds,"agiAgentChatMessageIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolcallDO>lambdaQuery().in(AgiAgentChatMessageToolcallDO::getAgiAgentChatMessageId, agiAgentChatMessageIds));
    }
            

    /**
     * 根据唯一id查询
     * @param uniqueId
     * @return
     */
    default AgiAgentChatMessageToolcallDO getByUniqueId(String uniqueId) {
        Assert.notNull(uniqueId,"uniqueId 不能为空");
        return getOne(Wrappers.<AgiAgentChatMessageToolcallDO>lambdaQuery().eq(AgiAgentChatMessageToolcallDO::getUniqueId, uniqueId));
    }



    /**
     * 根据唯一id查询多个
     * @param uniqueIds
     * @return
     */
    default List<AgiAgentChatMessageToolcallDO> getByUniqueIds(List<String> uniqueIds) {
        Assert.notEmpty(uniqueIds,"uniqueIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageToolcallDO>lambdaQuery().in(AgiAgentChatMessageToolcallDO::getUniqueId, uniqueIds));
    }
            












}
