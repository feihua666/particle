package com.particle.agi.infrastructure.chat.service;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageMediaDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体对话消息媒体 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:37
 */
public interface IAgiAgentChatMessageMediaService extends IBaseService<AgiAgentChatMessageMediaDO> {


    /**
     * 根据唯一id查询
     * @param uniqueId
     * @return
     */
    default AgiAgentChatMessageMediaDO getByUniqueId(String uniqueId) {
        Assert.notNull(uniqueId,"uniqueId 不能为空");
        return getOne(Wrappers.<AgiAgentChatMessageMediaDO>lambdaQuery().eq(AgiAgentChatMessageMediaDO::getUniqueId, uniqueId));
    }



    /**
     * 根据唯一id查询多个
     * @param uniqueIds
     * @return
     */
    default List<AgiAgentChatMessageMediaDO> getByUniqueIds(List<String> uniqueIds) {
        Assert.notEmpty(uniqueIds,"uniqueIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageMediaDO>lambdaQuery().in(AgiAgentChatMessageMediaDO::getUniqueId, uniqueIds));
    }



    /**
     * 根据消息id查询多个
     * @param agiAgentChatMessageId
     * @return
     */
    default List<AgiAgentChatMessageMediaDO> getByAgiAgentChatMessageId(Long agiAgentChatMessageId) {
        Assert.notNull(agiAgentChatMessageId,"agiAgentChatMessageId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageMediaDO>lambdaQuery().eq(AgiAgentChatMessageMediaDO::getAgiAgentChatMessageId, agiAgentChatMessageId));
    }










}
