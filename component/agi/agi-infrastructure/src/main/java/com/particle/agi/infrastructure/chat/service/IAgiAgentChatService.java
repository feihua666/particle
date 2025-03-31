package com.particle.agi.infrastructure.chat.service;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体对话 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:30:54
 */
public interface IAgiAgentChatService extends IBaseService<AgiAgentChatDO> {

    /**
     * 根据智能体id查询
     * @param agiAgentId
     * @return
     */
    default List<AgiAgentChatDO> getByAgiAgentId(Long agiAgentId) {
        Assert.notNull(agiAgentId,"agiAgentId 不能为空");
        return list(Wrappers.<AgiAgentChatDO>lambdaQuery().eq(AgiAgentChatDO::getAgiAgentId, agiAgentId));
    }



    /**
     * 根据智能体id查询多个
     * @param agiAgentIds
     * @return
     */
    default List<AgiAgentChatDO> getByAgiAgentIds(List<Long> agiAgentIds) {
        Assert.notEmpty(agiAgentIds,"agiAgentIds 不能为空");
        return list(Wrappers.<AgiAgentChatDO>lambdaQuery().in(AgiAgentChatDO::getAgiAgentId, agiAgentIds));
    }
            

    /**
     * 根据对话id查询
     * @param chatId
     * @return
     */
    default AgiAgentChatDO getByChatId(String chatId) {
        Assert.notNull(chatId,"chatId 不能为空");
        return getOne(Wrappers.<AgiAgentChatDO>lambdaQuery().eq(AgiAgentChatDO::getChatId, chatId));
    }



    /**
     * 根据对话id查询多个
     * @param chatIds
     * @return
     */
    default List<AgiAgentChatDO> getByChatIds(List<String> chatIds) {
        Assert.notEmpty(chatIds,"chatIds 不能为空");
        return list(Wrappers.<AgiAgentChatDO>lambdaQuery().in(AgiAgentChatDO::getChatId, chatIds));
    }
            

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<AgiAgentChatDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<AgiAgentChatDO>lambdaQuery().eq(AgiAgentChatDO::getUserId, userId));
    }



    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<AgiAgentChatDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<AgiAgentChatDO>lambdaQuery().in(AgiAgentChatDO::getUserId, userIds));
    }
            











}
