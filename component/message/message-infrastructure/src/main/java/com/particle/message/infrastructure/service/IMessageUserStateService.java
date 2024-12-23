package com.particle.message.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.message.infrastructure.dos.MessageUserStateDO;

import java.util.List;

/**
 * <p>
 * 用户消息读取状态 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:36:07
 */
public interface IMessageUserStateService extends IBaseService<MessageUserStateDO> {

    /**
     * 根据消息表主键查询
     * @param messageId
     * @return
     */
    default List<MessageUserStateDO> getByMessageId(Long messageId) {
        Assert.notNull(messageId,"messageId 不能为空");
        return list(Wrappers.<MessageUserStateDO>lambdaQuery().eq(MessageUserStateDO::getMessageId, messageId));
    }



    /**
     * 根据消息表主键查询多个
     * @param messageIds
     * @return
     */
    default List<MessageUserStateDO> getByMessageIds(List<Long> messageIds) {
        Assert.notEmpty(messageIds,"messageIds 不能为空");
        return list(Wrappers.<MessageUserStateDO>lambdaQuery().in(MessageUserStateDO::getMessageId, messageIds));
    }


    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    default List<MessageUserStateDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<MessageUserStateDO>lambdaQuery().eq(MessageUserStateDO::getUserId, userId));
    }



    /**
     * 根据用户id查询多个
     * @param userIds
     * @return
     */
    default List<MessageUserStateDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<MessageUserStateDO>lambdaQuery().in(MessageUserStateDO::getUserId, userIds));
    }











}
