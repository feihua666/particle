package com.particle.message.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.message.infrastructure.dos.MessageDO;

import java.util.List;

/**
 * <p>
 * 消息 服务类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
public interface IMessageService extends IBaseService<MessageDO> {








    /**
     * 根据发送人id查询
     * @param sendUserId
     * @return
     */
    default List<MessageDO> getBySendUserId(String sendUserId) {
        Assert.notNull(sendUserId,"sendUserId 不能为空");
        return list(Wrappers.<MessageDO>lambdaQuery().eq(MessageDO::getSendUserId, sendUserId));
    }



    /**
     * 根据发送人id查询多个
     * @param sendUserIds
     * @return
     */
    default List<MessageDO> getBySendUserIds(List<String> sendUserIds) {
        Assert.notEmpty(sendUserIds,"sendUserIds 不能为空");
        return list(Wrappers.<MessageDO>lambdaQuery().in(MessageDO::getSendUserId, sendUserIds));
    }










}
