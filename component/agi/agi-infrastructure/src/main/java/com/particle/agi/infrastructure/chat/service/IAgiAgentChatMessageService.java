package com.particle.agi.infrastructure.chat.service;

import com.particle.agi.infrastructure.chat.dos.AgiAgentChatMessageDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 智能体对话消息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-02-21 15:31:19
 */
public interface IAgiAgentChatMessageService extends IBaseService<AgiAgentChatMessageDO> {

    /**
     * 根据智能体对话id查询
     * @param agiAgentChatId
     * @return
     */
    default List<AgiAgentChatMessageDO> getByAgiAgentChatId(Long agiAgentChatId) {
        Assert.notNull(agiAgentChatId,"agiAgentChatId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().eq(AgiAgentChatMessageDO::getAgiAgentChatId, agiAgentChatId));
    }

    /**
     * 根据智能体对话id查询多个
     * @param agiAgentChatIds
     * @return
     */
    default List<AgiAgentChatMessageDO> getByAgiAgentChatIds(List<Long> agiAgentChatIds) {
        Assert.notEmpty(agiAgentChatIds,"agiAgentChatIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().in(AgiAgentChatMessageDO::getAgiAgentChatId, agiAgentChatIds));
    }


    /**
     * 根据智能体id查询
     * @param agiAgentId
     * @return
     */
    default List<AgiAgentChatMessageDO> getByAgiAgentId(Long agiAgentId) {
        Assert.notNull(agiAgentId,"agiAgentId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().eq(AgiAgentChatMessageDO::getAgiAgentId, agiAgentId));
    }



    /**
     * 根据智能体id查询多个
     * @param agiAgentIds
     * @return
     */
    default List<AgiAgentChatMessageDO> getByAgiAgentIds(List<Long> agiAgentIds) {
        Assert.notEmpty(agiAgentIds,"agiAgentIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().in(AgiAgentChatMessageDO::getAgiAgentId, agiAgentIds));
    }


    /**
     * 根据对话id查询
     * @param chatId
     * @return
     */
    default List<AgiAgentChatMessageDO> getByChatId(String chatId) {
        Assert.notNull(chatId,"chatId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().eq(AgiAgentChatMessageDO::getChatId, chatId));
    }
    /**
     * 根据对话id查询是否存在
     * @param chatId
     * @return
     */
    default Boolean existByChatId(String chatId) {
        Assert.notNull(chatId,"chatId 不能为空");
        return exists(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().eq(AgiAgentChatMessageDO::getChatId, chatId));
    }


    /**
     * 根据对话id查询多个
     * @param chatIds
     * @return
     */
    default List<AgiAgentChatMessageDO> getByChatIds(List<String> chatIds) {
        Assert.notEmpty(chatIds,"chatIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().in(AgiAgentChatMessageDO::getChatId, chatIds));
    }


    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<AgiAgentChatMessageDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().eq(AgiAgentChatMessageDO::getUserId, userId));
    }



    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<AgiAgentChatMessageDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<AgiAgentChatMessageDO>lambdaQuery().in(AgiAgentChatMessageDO::getUserId, userIds));
    }












}
